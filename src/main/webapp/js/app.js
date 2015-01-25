'use strict';

/* App Module */

var appSpringAngular = angular.module('appSpringAngular', [
  'ngRoute',
  'ngAnimate',
  'ngCookies',
  'appControllers',
  'appFilters',
  'appServices',
  'ngTable',
  'ui.bootstrap'
]);

appSpringAngular.run(function($rootScope,$cookieStore,$location) {
  $rootScope.address="http://localhost\\:8080/spring-angularjs/rest";
  


     $rootScope.logout = function() {
      delete $rootScope.user;
      delete $rootScope.authToken;
      $cookieStore.remove('authToken');
      $location.path("/login");
    };
});

appSpringAngular.config(['$routeProvider', '$locationProvider', '$httpProvider',
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
        when('/assegna_task', {
        templateUrl: 'partials/dashboard.html',
        controller: 'CreaTaskCtrl'
      }).
        when('/task_conclusi', {
        templateUrl: 'partials/dashboard.html',
        controller: 'CreaTaskCtrl'
      }).
       when('/lista_task/:username', {
        templateUrl: 'partials/dashboard.html',
        controller: 'ListaTaskCtrl'
      }).
        when('/nuovi_task', {
        templateUrl: 'partials/dashboard.html',
        controller: 'CreaTaskCtrl'
      }).
    when('/task_presi', {
        templateUrl: 'partials/dashboard.html',
        controller: 'CreaTaskCtrl'
      }).
      when('/details_task/:task_id', {
        templateUrl: 'partials/dashboard.html',
        controller: 'DetailsTaskCtrl'
      }).
      otherwise({
        redirectTo: '/login'
      });
      
        $locationProvider.hashPrefix('!');
      
    $httpProvider.interceptors.push('httpInterceptor');   

       
        
  }]);
