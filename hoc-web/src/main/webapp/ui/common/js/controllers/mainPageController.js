hocAdminApp.controller('mainPageController', function($scope, msgBus) {
	
	$scope.mainNaviExpanded = true;
	
	$scope.mainNaviWidth = {
			normalWith:225,
			smallWidth:40
	};
	
	$scope.sendMsg = function(msg){
		msgBus.emitMsg(msg);
	};
	
	($scope.calculatePageLayout = function(){
		//adjust page layout
		$scope.pageHeightStyle = {
				height:  $("body")[0].offsetHeight - $(".pageHeader").height() + "px"
		};
		
		$scope.pageContentSizeStyle = {
				height:  $("body")[0].offsetHeight - $(".pageHeader").height() + "px",
				width: document.body.clientWidth - $(".side_nav").width() + "px"
		};
	})();
	
	$scope.toggleSideNav = function(){
		if($scope.mainNaviExpanded){
			$scope.mainNaviExpanded = false;
			
			$(".side_nav").animate({
				width:$scope.mainNaviWidth.smallWidth + "px"
			},function(){
				$(".side_nav").css("width",$scope.mainNaviWidth.smallWidth);
				
				tellAngular();
				$scope.sendMsg("page_resized");
			});
			
		}else{
			$scope.mainNaviExpanded = true;
			
			$scope.pageContentSizeStyle = {
					height:  $("body")[0].offsetHeight - $(".pageHeader").height() + "px",
					width: document.body.clientWidth - $scope.mainNaviWidth.normalWith + "px"
			};
			
			$(".side_nav").animate({
				width:$scope.mainNaviWidth.mainNaviWidth + "px"
			},function(){
				$(".side_nav").css("width",$scope.mainNaviWidth.normalWith);
				$scope.sendMsg("page_resized");
			});
		}
		
		
	};
	
	$scope.isViewLoading = false;
	$scope.$on('$routeChangeStart', function() {
	  $scope.isViewLoading = true;
	});
	$scope.$on('$routeChangeSuccess', function() {
	  $scope.isViewLoading = false;
	});
	$scope.$on('$routeChangeError', function() {
		  $scope.isViewLoading = false;
	});
});

hocAdminApp.factory('UserLoginService',function(){
	var loginService = {
		
	};
	return loginService;
});