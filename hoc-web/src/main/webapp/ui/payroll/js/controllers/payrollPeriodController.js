hocAdminApp.controller('payrollPeriodController', function($scope,$alert,PayrollPeriodService,$http, $modal){
	
	//set up i18n
	$scope.i18n = {};
	jQuery.i18n.properties({
        name : 'message',
        path : 'ui/payroll/i18n/',  
        mode : 'map', 
        callback : function() {// åŠ è½½æˆ�åŠŸå�Žè®¾ç½®æ˜¾ç¤ºå†…å®¹  
            $scope.i18n = $.i18n.map;  
        }  
    });  
	
	$scope.doing_async = true;
	$scope.payrollPeriodNaviTreeData = [{
		label:"loading..."
	}];
	$scope.bufferedPayrollPeriodData;
	PayrollPeriodService.getNaviItem($scope,$http);
	
	$scope.payrollPeriodPage = {};
	$scope.pygrid = {
			data:[],
			enableColumnMenu:false
	}
	$scope.payrollPeriodNaviTreeHandler = function(branch){
		if(branch.data.level == 2){
			//load detailed data
			var payrollPYear = branch.data.payrollYear;
			var payrollPName = branch.label;
			var payrollPType = branch.data.payrollType;
			
			$scope.payrollPeriodPage.payrollPeriodYear = payrollPYear;
			$scope.payrollPeriodPage.payrollPeriodType = payrollPType;
			$scope.payrollPeriodPage.payrollPeriodName = payrollPName;
			
			$scope.pygrid.data = [];
			//filter out gird data
			for(var i = 0 ; i < $scope.bufferedPayrollPeriodData.length; i++){
				var gridDataRow = $scope.bufferedPayrollPeriodData[i];
				var filteredRow = {};
				if(gridDataRow.payYear == payrollPYear && gridDataRow.payType == payrollPType){
					filteredRow = {
						sequence : gridDataRow.paySeq,
						payrollperiod_type: gridDataRow.payType,
						begin_date:gridDataRow.begdaTxt,
						end_date:gridDataRow.enddaTxt,
						pay_date:gridDataRow.payDateTxt
					};
					$scope.pygrid.data.push(filteredRow);
				}
			}
			setTimeout(function(){
				$scope.pygridApi.grid.handleWindowResize();
			},500);
		}
	};
	
	$scope.pygrid.onRegisterApi = function(gridApi){
	      //set gridApi on scope
	      $scope.pygridApi = gridApi;
	};
	    
	var ppwModal = null;
	
	$scope.createNewPayPeriod = function(){
		$scope.payperiodWizard.show_step1 = true;
		$scope.payperiodWizard.show_step2 = false;
		
		$scope.payperiodWizard.payrollPeriodYears=[];
		$scope.payperiodWizard.payrollPeriodYear="";
		$scope.payperiodWizard.payrollPeriodName="";
		$scope.payperiodWizard.payrollPeriodType="";
		//build payrollPeriodYear
		var myDate= new Date(); 
		var startYear=myDate.getFullYear()-10; //start year
		var endYear=myDate.getFullYear()+10;   //end year
		for (var i=startYear;i<=endYear;i++) 
		{ 
			$scope.payperiodWizard.payrollPeriodYears.push({id:i,label:i}); 
		}
		
		//build payrollTypes
		$scope.payperiodWizard.payrollPeriodTypes=[];
		$scope.payperiodWizard.payrollPeriodTypes.push(
			{id:1,label:"Regular Payroll"}
		);
		
		ppwModal = $modal({scope: $scope, template: 'ui/payroll/pages/payrollPeriodWizard.html', show: false,animation : "am-fade-and-slide-top"});
		$scope.title = "Shit Fuck";
		//show modal
		ppwModal.$promise.then(ppwModal.show);
		
	};
	
	$scope.ppwNext = function(){
		$scope.payperiodWizard.show_step1 = false;
		$scope.payperiodWizard.show_step2 = true;
		
		if($scope.payperiodWizard.gridOptions.data.length == 0){
			//generate payroll periods
			$scope.generatePayrollPeriod();
		}
	};
	
	$scope.ppwSave = function(){
		var uiData = $scope.payperiodWizard.gridOptions.data;
		var pArr = [];
		for(var i = 0; i < uiData.length; i++){
			var pRow = {};
			pRow.payYear = $scope.payperiodWizard.payrollPeriodYear;
			pRow.payType = $scope.payperiodWizard.payrollPeriodType;
			pRow.payName = $scope.payperiodWizard.payrollPeriodName;
			pRow.begdaTxt = uiData[i].begin_date;
			pRow.paySeq = uiData[i].sequence;
			pRow.enddaTxt = uiData[i].end_date;
			pRow.payDateTxt = uiData[i].paydate;
			pArr.push(pRow);
		}
		
		//save payroll periods
		PayrollPeriodService.savePayrollPeriods($scope,$http,JSON.stringify(pArr),function(data){
			$scope.payperiodWizard.show_step1 = false;
			$scope.payperiodWizard.show_step2 = false;
			
			if(ppwModal){
				ppwModal.hide();
			}
			//Reload the naviItem
			$scope.doing_async = true;
			PayrollPeriodService.getNaviItem($scope,$http);
		});
	};
	
	$scope.ppwPrevious = function(){
		$scope.payperiodWizard.show_step1 = true;
		$scope.payperiodWizard.show_step2 = false;
	};
	
	$scope.deletePayPeriod = function(){
		var payYear = $scope.payrollPeriodPage.payrollPeriodYear;
		var payName = $scope.payrollPeriodPage.payrollPeriodType;
		
		if(!payYear || !payName){
			$alert({title: 'Selection', content: 'Please select an valid entry to delete', placement: 'top', type: 'info', show: true, duration:2});
		}
		console.debug(payYear + "  " + payName);
		
	}
	
	var editDateCellTemplate =  '<div class="form-group ppwDatePicker"><input class="form-control" data-container=".ppwModal" ng-model="COL_FIELD" data-date-format="yyyy-MM-dd" data-date-type="string" data-min-date="02/10/2010" data-max-date="02/10/2020" data-autoclose="1" bs-datepicker type="text"></div>';
	$scope.payperiodWizard = {
			show:false,
			show_step1:false,
			show_step2:false,
			payrollPeriodYear:"",
			payrollPeriodYears:[],
			payrollPeriodType:"",
			payrollPeriodTypes:[],
			gridOptions:{
				columnDefs:[
				            { name: 'sequence', displayName:'Sequence', enableCellEdit: false },
				            { name: 'begin_date', displayName: 'Begin Date' },
				            { name: 'end_date', displayName: 'End Date' },
				            { name: 'paydate', displayName: 'Pay Date', type: 'object', enableCellEdit:true, editableCellTemplate:editDateCellTemplate}
				            ],
				data:[]
			}
	};
	
	$scope.generatePayrollPeriod = function(){
		var period = {};
		if($scope.payperiodWizard.payrollPeriodYear == ""){
			return;
		}
		if($scope.payperiodWizard.payrollPeriodType == ""){
			return;
		}
		
		if($scope.payperiodWizard.payrollPeriodType == 1){
			//monthly payroll
			for(var i = 0; i < 12; i ++){
				period = {
						"sequence" : i+1,
						"begin_date":$scope.getFirstDay($scope.payperiodWizard.payrollPeriodYear, i),
						"end_date":$scope.getLastDay($scope.payperiodWizard.payrollPeriodYear, i), 
						"paydate" : $scope.getFirstDay($scope.payperiodWizard.payrollPeriodYear, i)		
				};
				$scope.payperiodWizard.gridOptions.data.push(period);
			}
		}
		
	};
	
	$scope.getFirstDay = function(year, month){
		var new_date = new Date(Date.UTC(year,month,1));
		return new_date.toISOString().substring(0,10);
	};
	
	$scope.getLastDay = function(year,month){   
		 var new_year = year;   
		 var new_month = month + 1; 
		 var new_date = new Date(Date.UTC(new_year,new_month,1));                
		 var lastDay = new Date(new_date.getTime()-1000*60*60*24); 
		 return lastDay.toISOString().substring(0,10);
	};   
});


//define a payrollPeriod service
//register a service
hocAdminApp.factory('PayrollPeriodService',function(){
	var ppService = {
			parseTreeData : function(data){
				var naviData = [];
				var naviItems = {};
				if(data && data.length > 0){
					for(var i = 0 ; i < data.length; i++){
						var naviItem = {
								label:"",
								children:[],
								data:{
									level:1
								}
						};
						var naviKey = data[i].payYear + "_" + data[i].payType;
						if(naviItems[naviKey] == null){
							naviItem.label = data[i].payYear;
							naviItem.children.push({
								label:data[i].payName,
								data:{
									level:2,
									payrollYear:data[i].payYear,
									payrollType:data[i].payType
								}
							});
							naviItems[naviKey] = naviItem;
						}
					}
					for(var pro in naviItems){
						naviData.push(naviItems[pro]);
					}
				}
				return naviData;
			}
			
	};
	ppService.getNaviItem = function($scope, $http){
		var that = this;
		$http({
		    url: g_contextpath + "/rest/payrollperiod/list", 
		    method: "GET",
		    params: {}
		 }).success(function(data, status) {
			 $scope.doing_async = false;
			 $scope.bufferedPayrollPeriodData = data;
			 $scope.payrollPeriodNaviTreeData = that.parseTreeData(data);
	        
	     }).error(function(data, status) {
	          
	     });
	};
	
	ppService.savePayrollPeriods = function($scope, $http, data, callback){
		var that = this;
		$http.post(g_contextpath + "/rest/payrollperiod/save",data,{}).success(function(data, status){
			callback(data);
		}).error(function(data, status) {
	          
	    });
		
	};
	return ppService;
});