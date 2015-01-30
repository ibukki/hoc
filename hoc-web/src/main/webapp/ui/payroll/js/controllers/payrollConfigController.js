hocAdminApp.controller('payrollConfigurationController', function($scope,$http, VariableConfigService){
	$scope.variableConfig = {
		gridOptions : {
			columnDefs:[
			            { field:"configType",displayName:"Configuration Type",enableColumnResize:false,enableCellEdit: false,enableFiltering:false},
			            { field: 'configTypeDescr', displayName:'Configuration Description', cellEditableCondition: function($scope){
			            		return true;
			            	} 
			            },
			            { field: 'configGrpId', displayName: 'Configure Gourp ID'},
			            { field: 'configGrpDescr', displayName: 'Configure Group Description'},
			            { field: 'sequence',displayName: 'Sequence',enableFiltering:false},
			            { field: 'amount1', displayName: 'Amount1',enableFiltering:false},
			            { field: 'amount1Descr', displayName: 'I',enableFiltering:false, cellTemplate:'<span class="glyphicon glyphicon-question-sign grid_info"></span>',width:20},
			            { field: 'amount2', displayName: 'Amount2',enableFiltering:false},
			            { field: 'amount3', displayName: 'Amount3',enableFiltering:false},
			            { field: 'amount4', displayName: 'Amount4',enableFiltering:false},
			            ],
			data:[],
			enableColumnResize: true,
			enableCellEditOnFocus: true,
		    enableColumnReordering: true,
		    enableRowSelection: true,
		    enableCellSelection: true,
		    showGroupPanel: false,
		    multiSelect:true,
		    enableFiltering: true
		}
	};
	
	VariableConfigService.getConfig($scope, $http);
	
	$scope.createConfig = function(){
		var rowData = {};
		$scope.variableConfig.gridOptions.data.push(rowData);
	};
});

hocAdminApp.factory('VariableConfigService',function(){
	var configService = {
		getConfig : function($scope, $http){
			$scope.variableConfig.gridOptions.data = [];
			$http({
			    url: g_contextpath + "/rest/payrollconfig/list", 
			    method: "GET",
			    params: {}
			 }).success(function(data, status) {
				 $scope.variableConfig.gridOptions.data = data;
		     }).error(function(data, status) {
		          
		     });
		}
	};
	
	return configService;
	
});
