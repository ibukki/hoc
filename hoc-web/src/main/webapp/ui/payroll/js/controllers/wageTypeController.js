hocAdminApp.controller('wageTypeController', function($scope,$http,WageTypeService,$modal){
	
	//set up i18n
	$scope.i18n = {};
	jQuery.i18n.properties({
        name : 'message',
        path : 'ui/payroll/i18n/',  
        mode : 'map', 
        callback : function() {  
            $scope.i18n = $.i18n.map;  
        }  
    });  
	
	$scope.wtcselection = {};
	
	var fromDBCellTemplate = "<div style='width:100%;height:100%;text-align: center;line-height:30px;'><img style='display:{{COL_FIELD}}' src='ui/common/images/icon_new_row.png'></div>";
	$scope.wageTypeConf = {
		gridOptions : {
			columnDefs:[
			            { field:"columnDisplayStyle",displayName:"N",enableColumnResize:false,enableCellEdit: false,cellTemplate:fromDBCellTemplate,width:30},
			            { field: 'wagetypeId', displayName:$scope.i18n.PAYROLL_WAGE_TYPE_ID, cellEditableCondition: function($scope){
			            		return !$scope.row.entity.fromDB;
			            	} 
			            },
			            { field: 'description', displayName: 'Description'},
			            { field: 'type', displayName: 'Type',editableCellTemplate: 'ui-grid/dropdownEditor',
					      cellFilter: 'typeFilter', 
					      editDropdownValueLabel: 'type', editDropdownOptionsArray: [
										      { id: 'P', type: 'Primary' }, 
										      { id: 'S', type: 'Secondary' } 
										    ] }
			            ],
			data:[],
			enableColumnMenu:false,
			enableColumnResize: true,
			enableCellEditOnFocus: true,
		    enableColumnReordering: true,
		    enableRowSelection: true,
		    enableCellSelection: true,
		    showGroupPanel: false,
		    multiSelect:true,
		}
		
	};

	
	WageTypeService.getWageTypes($scope,$http);
	
	//get grid api
	$scope.wageTypeConf.gridOptions.onRegisterApi = function(gridApi){
      //set gridApi on scope
      $scope.wageTypeConf.gridApi = gridApi;
    }

	$scope.createNewWageType = function(){
		$scope.wageTypeConf.gridOptions.data.push({
			columnDisplayStyle: ""	
		});
	};
	
	$scope.saveWageType = function(){
		var data = $scope.wageTypeConf.gridOptions.data;
		var dataArr = [];
		for(var i = 0 ; i < data.length; i++){
			var dbdata = {};
			dbdata.type = data[i].type;
			dbdata.wagetypeId = data[i].wagetypeId;
			dbdata.description = data[i].description;
			dataArr.push(dbdata);
		}
		
		WageTypeService.saveWageTypes($scope,$http,dataArr,function(data){
			WageTypeService.getWageTypes($scope,$http);
		});
	};
	
	$scope.deleteWageType = function(){
		var dRows = $scope.wageTypeConf.gridApi.selection.getSelectedRows();
		
		if(dRows && dRows.length > 0){
			$scope.confirmModal = $modal({scope: $scope, template: 'ui/common/pages/confirmation.html?sdate='+new Date().toTimeString(), backdrop:"static", show: false,animation : "am-fade-and-scale"});
			$scope.confirmationTitle = "delete wage type confirm";
			$scope.confirmationText = "Are you sure to proceed the deletion ?"
			$scope.confirmModal.$promise.then($scope.confirmModal.show);
		}
		
		
	};
	
	$scope.proceedOK = function(){
		
		if($scope.confirmModal){
			$scope.confirmModal.hide();
		}
		var dRows = $scope.wageTypeConf.gridApi.selection.getSelectedRows();
		var deleteArr = [];
		for ( var i = 0 ; i< dRows.length; i++) {
			if(dRows[i].fromDB){
				deleteArr.push({
					wagetypeId: dRows[i].wagetypeId
				});
			}
		}
		
		if(deleteArr.length > 0){
			$http({
			    url: g_contextpath + "/rest/wagetypeconf/delete", 
			    method: "POST",
			    data: JSON.stringify(deleteArr),
			    params: {}
			 }).success(function(data, status) {
				 if(data > 0){
					 var remainArr = [];
					 var data = $scope.wageTypeConf.gridOptions.data;
					 for(var i = 0 ; i < data.length; i++){
						 if(!$scope.contains(dRows, data[i].$$hashKey)){
							 remainArr.push(data[i]);
						 }
					 }
					 $scope.wageTypeConf.gridOptions.data = remainArr;
				 }
		     }).error(function(data, status) {
		         //do nothing 
		     });
		}else{
			//just remove them from the UI
			var remainArr = [];
			 var data = $scope.wageTypeConf.gridOptions.data;
			 for(var i = 0 ; i < data.length; i++){
				 if(!$scope.contains(dRows, data[i].$$hashKey)){
					 remainArr.push(data[i]);
				 }
			 }
			 $scope.wageTypeConf.gridOptions.data = remainArr;
		}
		
	}
	
	$scope.contains = function(a, obj) {
	    for (var i = 0; i < a.length; i++) {
	        if (a[i].$$hashKey == obj) {
	            return true;
	        }
	    }
	    return false;
	}
	
})
.filter('typeFilter', function() {
  var typeHash = {
    'P': 'Primary',
    'S': 'Secondary'
  };
  
  return function(input) {
    if (!input){
      return '';
    } else {
      return typeHash[input];
    }
  };
});


hocAdminApp.factory('WageTypeService',function(){
	var wtService = {
	
		getWageTypes : function($scope, $http){
			$scope.wageTypeConf.gridOptions.data = [];
			$http({
			    url: g_contextpath + "/rest/wagetypeconf/list", 
			    method: "GET",
			    params: {}
			 }).success(function(data, status) {
				 for(var i = 0 ; i < data.length; i++){
					 var vdata = data[i];
					 vdata.fromDB = true;
					 vdata.columnDisplayStyle = 'none';
					 $scope.wageTypeConf.gridOptions.data.push(vdata);
				 }
		     }).error(function(data, status) {
		          
		     });
		},
		saveWageTypes: function($scope, $http, data,callback){
			$http.post(g_contextpath + "/rest/wagetypeconf/save",JSON.stringify(data),{}).success(function(data, status){
				callback(data);
			}).error(function(data, status) {
		          
		    });
		}
	};
	
	return wtService;
});