hocAdminApp.controller('loginController',
		function($scope, $http, LoginService) {
			$scope.login_user = {};
			
			$scope.loginType = "I"; //I Individual, E Enterprise

			$scope.login = function() {
				if (LoginService.isAuthorized) {
					// TODO
				} else {
					LoginService.login($scope, $http, $scope.loginType);
				}
			};
			
			$scope.switchLoginType = function(type){
				$scope.loginType = type;
			}
			
		});

hocAdminApp.factory('LoginService', function() {
	var loginService = {
		isAuthorized : false,
	};

	loginService.login = function($scope, $http, type) {
		var loginUrl = g_contextpath + "/rest/auth/ind_login";
		if(type == 'E'){
			loginUrl = g_contextpath + "/rest/auth/ent_login";
		}
		$http({
			url : loginUrl,
			method : "GET",
			params : {
				companyId : $scope.login_user.companyid,
				userId : $scope.login_user.userid,
				password : $scope.login_user.password
			}
		}).success(function(data, status, headers, config) {
			if (status == '200' || status == '304') {
				loginService.isAuthorized = true;
				// TODO - Save login authentication to cache or session
				//redirect to home page
				location.href = g_contextpath + '/home.do';
			}
		}).error(function(data, status) {
			// TODO - error handling and display it in UI
		});
	};

	return loginService;
});