'use strict';

/* Services */

var appServices = angular.module('appServices', ['ngResource']);
/*
phonecatServices.factory('Phone', ['$resource',
  function($resource){
    return $resource('phones/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
    });
  }]);*/



appServices.factory('LoginService', ['$resource','$rootScope',
  function($resource,$rootScope){
   return $resource($rootScope.address+'/login/:par', {},
	
			{
				authenticate: {
					method: 'POST',
					params: {par:'@par',username :'@username','password':'@password'},
	
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				},
			}
		);
  }]);


appServices.factory('UserRoleService', ['$resource','$rootScope',
  function($resource,$rootScope){
   return $resource($rootScope.address+'/users/:role', {},
	
			{
				query: {
					method: 'GET',
					params: {role:'@role'},
	
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				},
				get: {
					method: 'GET',
					params: {role:'@role'},
					isArray:true,
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				},
			}
		);
  }]);
appServices.factory('UserService', ['$resource','$rootScope',
  function($resource,$rootScope){
   return $resource($rootScope.address+'/users/username/:username', {},
	
			{
				
				get: {
					method: 'GET',
					params: {role:'@username'},
					
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				},
			}
		);
  }]);

appServices.factory('PersonsService', ['$resource','$rootScope',
  function($resource,$rootScope){
   // return $resource('http://localhost\\:8080/spring-angularjs/persons', {},
   return $resource($rootScope.address+'/persons', {},
			{}
		);
  }]);
  
appServices.factory('TaskService', ['$resource','$rootScope',
  function($resource,$rootScope){
   return $resource($rootScope.address+'/task/:username/:fase', {},
			{get: {
				method: 'GET',
				params: {username:'@username',fase:'@fase',page:'page'},
				
				headers : {'Content-Type': 'application/x-www-form-urlencoded'}
			},
			query: {
				method: 'GET',
				params: {username:'@username'},
				
				headers : {'Content-Type': 'application/x-www-form-urlencoded'}
			}}
		);
  }]); 

appServices.factory('TaskCountService', ['$resource','$rootScope',
  function($resource,$rootScope){
   return $resource($rootScope.address+'/task/count/:username', {},
			{get: {
				method: 'GET',
				params: {username:'@username'},
				
				headers : {'Content-Type': 'application/x-www-form-urlencoded'}
			},
			query: {
				method: 'GET',
				params: {username:'@username'},
				
				headers : {'Content-Type': 'application/x-www-form-urlencoded'}
			}}
		);
  }]); 
   

  
appServices.factory('httpInterceptor', ['$q', '$rootScope', '$location',
    function ($q, $rootScope, $location) {
        return {
            request: function (config) {
         
		        		if (angular.isDefined($rootScope.authToken)) {
		        			var authToken = $rootScope.authToken;
		        			if(config.url.indexOf("tooltip") == -1 && config.url.indexOf("pagination") == -1)
		        				config.url = config.url + "?token=" + authToken;
		        			
		        		}
		      return config || $q.when(config);
            },
            requestError: function(request){
                return $q.reject(request);
            },
            response: function (response) {
                return response || $q.when(response);
            },
            responseError: function (response) {
                var status = response.status;
                if(status==401)
                //	$location.path('/login');
                return $q.reject(response);
            }
        };
}]);