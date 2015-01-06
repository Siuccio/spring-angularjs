'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'ngCookies',
  'phonecatControllers',
  'phonecatFilters',
  'phonecatServices'
]);



phonecatApp.config(['$routeProvider', '$locationProvider', '$httpProvider',
  function($routeProvider, $locationProvider, $httpProvider) {
    
    $routeProvider.
    
    when('/login', {
        templateUrl: 'partials/login.html',
        controller: 'LoginCtrl'
      }).
          when('/dashboard', {
        templateUrl: 'partials/dashboard.html',
        controller: 'DashboardCtrl'
      }).
          when('/situazione_task', {
        templateUrl: 'partials/dashboard.html',
        controller: 'SituazioneTaskCtrl'
      }).
       when('/crea_task', {
        templateUrl: 'partials/dashboard.html',
        controller: 'CreaTaskCtrl'
      }).
        
      otherwise({
        redirectTo: '/login'
      });
      
       			$locationProvider.hashPrefix('!');
			
		$httpProvider.interceptors.push('httpInterceptor'); 	
		    
		    /* Registers auth token interceptor, auth token is either passed by header or by query parameter
		     * as soon as there is an authenticated user */
		/*    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
		        return {
		        	'request': function(config) {
		        		
		  
		        			config.headers={'Authorization' : '111'};
		        	
		        		return config;
		        	}
		        };
		    }
	    );*/
       
       	
  }]);
