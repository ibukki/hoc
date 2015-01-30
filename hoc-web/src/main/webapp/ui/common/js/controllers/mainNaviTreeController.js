hocAdminApp.controller('mainNaviTreeController', function($scope, $timeout, MainNavService,$http, $location) {

	$scope.mainNaviTreeData = [{
		label:"loading"	 
	}];
	
	$scope.mainNaviTree = {};
	
	MainNavService.getNaviItem($http,function(data){
		
		$scope.mainNaviTreeData = data;
		
		//add the page to routeProvider
		//do not change this part of the code, unless you know what
		//you are doing
		for(var i = 0 ; i < data.length; i++){
			var navi = data[i];
			if(navi.url){
				$scope._addNaviToRouter(navi);
			}
			
			var level1Child = navi.children;
			if(level1Child && level1Child.length > 0){
				for(var j = 0 ; j < level1Child.length; j++){
					var subNavi = level1Child[j];
					if(subNavi.url){
						$scope._addNaviToRouter(subNavi);
					}
					
					
					var level2Child = subNavi.children;
					if(level2Child && level2Child.length){
						for(var k = 0 ; k < level2Child.length; k++){
							var thirdNavi = level2Child[k];
							if(thirdNavi.url){
								$scope._addNaviToRouter(thirdNavi);
							}
						}
					}
				}
			}
		}
		
		if(hocAdminApp.currentAppPath){
			$location.path(hocAdminApp.currentAppPath);
			//select tree
			if(hocAdminApp.selectNaviBranch){
				setTimeout(function(){
					if($scope.mainNaviTree.select_branch){
						$scope.mainNaviTree.select_branch(hocAdminApp.selectNaviBranch);
					}
				},1000);
				
			}
		}
		
	});
		
	$scope._addNaviToRouter = function(navi, selectNaviLabel){
		var naviPath = "/"+navi.href.substring(1, navi.href.length);
		hocAdminApp.routeProvider.when(naviPath, {templateUrl: navi.url});
		if(hocAdminApp.currentAppPath == naviPath){
			hocAdminApp.selectNaviBranch = navi;
		}
	};

});

//register a service
hocAdminApp.factory('MainNavService',function(){
	var naviItems = {};
	naviItems.getNaviItem = function($http,callback){
		$http({
		    url: g_contextpath + "/rest/moduleconfig/list", 
		    method: "GET",
		    params: {locale:$.i18n.browserLang()}
		 }).success(function(data, status) {
			 callback(data);
	     }).error(function(data, status) {
	          
	     });
    
	};
	return naviItems;
});