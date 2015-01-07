'use strict';

/* Services */

var phonecatServices = angular.module('phonecatServices', ['ngResource']);

phonecatServices.factory('Phone', ['$resource',
  function($resource){
    return $resource('phones/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
    });
  }]);



phonecatServices.factory('LoginService', ['$resource',
  function($resource){
   //return $resource('http://localhost\\:8080/spring-angularjs/rest/login/:par', {},
	return $resource('/spring-angularjs/rest/login/:par', {},
	
			{
				authenticate: {
					method: 'POST',
					params: {par:'@par',username :'@username','password':'@password'},
	
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				},
			}
		);
  }]);


phonecatServices.factory('UserService', ['$resource',
  function($resource){
   return $resource('http://localhost\\:8080/spring-angularjs/users/:role', {},
	
			{
				query: {
					method: 'GET',
					params: {role:'@role'},
	
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				},
			}
		);
  }]);


phonecatServices.factory('PersonsService', ['$resource',
  function($resource){
   // return $resource('http://localhost\\:8080/spring-angularjs/persons', {},
   return $resource('http://localhost\\:8080/spring-angularjs/persons', {},
			{}
		);
  }]);
  
 phonecatServices.factory('TaskNotAssignmentService', ['$resource',
  function($resource){
   return $resource('http://localhost\\:8080/spring-angularjs/task/not', {},
			{}
		);
  }]); 


   

  
phonecatServices.factory('httpInterceptor', ['$q', '$rootScope', '$location',
    function ($q, $rootScope, $location) {
        return {
            request: function (config) {
         
		        		if (angular.isDefined($rootScope.authToken)) {
		        			var authToken = $rootScope.authToken;
		        			
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