hocAdminApp.controller('variableConfigurationController', function($scope,$http,$aside,$popover,VariableConfigService){
	
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
	
	$scope.configData = [];
	$scope.configInput = {};
	$scope.configGroups = [];
	$scope.isNewConfigGroup = false;
	VariableConfigService.getConfig($scope, $http);
	VariableConfigService.getGroups($scope,$http);
	
	$scope.pageAside;
	$scope.createConfig = function(){
		
		$scope.configInput = {
				title:"Create a new config"
		};
		$scope.disableConfigTypeInput = "";
		$scope.configInput.sequence = 0;
		$scope.pageAside = $aside({
			scope:$scope,
			template:"ui/payroll/pages/variableConfigurationEdit.html",
			placement:"right",
			animation:"am-slide-right",
			container:"body"
		});
		
	};
	
	$scope.createNewConfigGroup = function(){
		if($scope.isNewConfigGroup){
			$scope.isNewConfigGroup = false;
		}else{
			$scope.isNewConfigGroup = true;
		}
	};
	
	$scope.selectOneConfig = function(index, type){
		$scope.configInput = {};
		$scope.disableConfigTypeInput = "disabled";
		var data = $scope.configData[index];
		$scope.configInput.title = data.descr;
		for(var pro in data){
			$scope.configInput[pro] = data[pro];
		}
		
		$scope.pageAside = $aside({
			scope:$scope,
			template:"ui/payroll/pages/variableConfigurationEdit.html",
			placement:"right",
			animation:"am-slide-right",
			container:"body"
		});
	};
	
	
	
	$scope.showAmountDescription = function(index){
		if($scope.configInput.showDescrNumber == index){
			if($scope.spopover){
				jQuery(".popover").remove();
				$scope.spopover = null;
			}else{
				$scope.spopover = $popover(angular.element('#amt'+index+"Descr"), {
					scope: $scope,
					template:"ui/payroll/pages/variableConfigurationEditDescr.html",
					placement:"left",
					animation:"am-flip-x",
					show:true
				});
			}
		}else{
			$scope.configInput.showDescrNumber = index;
			if($scope.spopover){
				jQuery(".popover").remove();
				$scope.spopover = null;
			}
			$scope.spopover = $popover(angular.element('#amt'+index+"Descr"), {
				scope: $scope,
				template:"ui/payroll/pages/variableConfigurationEditDescr.html",
				placement:"left",
				animation:"am-flip-x",
				show:true
			});
		}
	};
	
	$scope.saveVariableConfig = function(){
		var dSave = {
			configType:"",
			descr:"",
			configGrpId:"",
			configGrpDescr:"",
			sequence:0,
			amount1:0,
			amount2:0,
			amount3:0,
			amount4:0,
			locale:$.i18n.browserLang(),
			amount1Descr:"",
			amount2Descr:"",
			amount3Descr:"",
			amount4Descr:""
		};
		for(var pro in dSave){
			if($scope.configInput[pro]){
				dSave[pro] = $scope.configInput[pro];
			}
		}
		
		VariableConfigService.saveConfig(dSave, $http, function(){
			if($scope.pageAside){
				$scope.pageAside.hide();
				VariableConfigService.getConfig($scope, $http);
			}
		});
	};
});

hocAdminApp.factory('VariableConfigService',function(){
	var configService = {
		getConfig : function($scope, $http){
			$scope.configData = [];
			$http({
			    url: g_contextpath + "/rest/variable/list?locale="+$.i18n.browserLang(), 
			    method: "GET",
			    params: {}
			 }).success(function(data, status) {
				 $scope.configData = data;
		     }).error(function(data, status) {
		          
		     });
		},
		
		getGroups : function($scope, $http){
			$http({
			    url: g_contextpath + "/rest/variable/groups", 
			    method: "GET",
			    params: {}
			 }).success(function(data, status) {
				 $scope.configGroups = data;
		     }).error(function(data, status) {
		          
		     });
			
		},
		saveConfig : function(sdata, $http, callback){
			var sdataArr = [];
			sdataArr.push(sdata);
			$http({
			    url: g_contextpath + "/rest/variable/save", 
			    method: "POST",
			    data:JSON.stringify(sdataArr),
			    params: {}
			 }).success(function(data, status) {
				 callback();
		     }).error(function(data, status) {
		          
		     });
		}
	};
	
	return configService;
	
});
