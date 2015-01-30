hocAdminApp.controller('registerController',
		function($scope, $http, registerService) {
			
	$scope.register = {
		agreement:true
	};
	
	$scope.registerNewUser = function(){
	    registerService.register($scope,$http);
	}
	
});

hocAdminApp.factory('registerService', function() {
    var registerService = {
        isAuthorized : false,
    };

    registerService.register = function($scope, $http) {
        var regUser = $scope.register;
        $http({
            url : g_contextpath+"/rest/user/register",
            method : "POST",
            data : JSON.stringify(regUser),
        }).success(function(data, status, headers, config) {
            if (status == '200' || status == '304') {
                location.href = g_contextpath + '/home.htm';
            }
        }).error(function(data, status) {
            // TODO - error handling and display it in UI
        });
    };

    return registerService;
});