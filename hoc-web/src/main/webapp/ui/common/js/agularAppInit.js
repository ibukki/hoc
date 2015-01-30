var hocAdminApp, deps;

//add dependency to angularBootstrapNavTree
deps = ['angularBootstrapNavTree','ngRoute','ngAnimate','mgcrea.ngStrap','ngDragDrop'];

//add dependency to ui grid
uiGridDeps = ["ui.grid","ui.grid.resizeColumns",'ui.grid.edit','ui.grid.selection','ui.grid.cellNav'];
for(var i = 0; i < uiGridDeps.length; i++){
	deps.push(uiGridDeps[i]);
}

hocAdminApp = angular.module('hocAdmin', deps, function($provide){
	 $provide.factory('msgBus', ['$rootScope', function($rootScope) {
	        var msgBus = {};
	        msgBus.emitMsg = function(msg) {
	        $rootScope.$emit(msg);
	        };
	        msgBus.onMsg = function(msg, scope, func) {
	            var unbind = $rootScope.$on(msg, func);
	            scope.$on('$destroy', unbind);
	        };
	        return msgBus;
	  }]);
});

//set up route
hocAdminApp.config(['$routeProvider', function($routeProvider) {
	
	  hocAdminApp.routeProvider  = $routeProvider;
	
	  $routeProvider.
	      when('/home', {templateUrl: 'ui/landing/pages/home.html'}).
	      otherwise({redirectTo: '/home'});
}]);

//get navigation path
if(location.href.lastIndexOf("#") > 0){
	hocAdminApp.currentAppPath = location.href.substr(location.href.lastIndexOf("#")+1);
}


//handle window resize method
//It is not a good way to do this, but ...
function tellAngular() {
	// handle page navi auto resize
    var domElt = document.getElementsByTagName("body")[0];
    scope = angular.element(domElt).scope();
    scope.$apply(function() {
    	scope.pageHeightStyle = {
    			height:  $("body")[0].offsetHeight - $(".pageHeader").height() + "px"
    	};
    	
    	scope.pageContentSizeStyle = {
    			height:  $("body")[0].offsetHeight - $(".pageHeader").height() + "px",
    			width: document.body.clientWidth - $(".side_nav").width() + "px"
    	};
    	
    });
    
}

//calling tellAngular on resize 
$(window).resize(function(){
	tellAngular();
	
});