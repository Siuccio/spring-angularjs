'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'ngCookies',
  'phonecatControllers',
  'phonecatFilters',
  'phonecatServices'
]);

phonecatApp.run(function($rootScope) {
  $rootScope.address="http://localhost\\:8080/spring-angularjs/rest";
	//$rootScope.address="http://localhost\\:8080/rest";
});

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

       
       	
  }]);
