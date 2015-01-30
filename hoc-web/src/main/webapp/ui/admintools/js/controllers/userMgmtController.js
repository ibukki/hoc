hocAdminApp.controller('userMgmtController', function($scope, $http,
		UserMgmtService) {

	$scope.userInfoInput = {};

	$scope.saveUserInfo = function() {
		var data = $scope.userInfoInput;
		UserMgmtService.saveUserInfo($scope, $http, data,
				function(data, status) {
					var msg = status;
					alert(msg);
				});
	};
});

hocAdminApp.factory('UserMgmtService', function() {
	var usrmgtService = {};
	usrmgtService.getUserInfo = function($scope, $http, userid) {
		$http({
			url : g_contextpath + "/rest/user/getbyid",
			method : "GET",
			params : {}
		}).success(function(data, status) {
			$scope.doing_async = false;
			$scope.bufferedUserMgmtData = data;
		}).error(function(data, status) {
		});
	};

	usrmgtService.saveUserInfo = function($scope, $http, data, callback) {
		$http.post(g_contextpath + "/rest/user/save", JSON.stringify(data), {})
				.success(function(data, status) {
					callback(data, status);
				}).error(function(data, status) {
					alert(status);
				});
	};
	return usrmgtService;
});