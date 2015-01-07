'use strict';

/* Controllers */

var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('PhoneListCtrl', ['$scope', 'Phone',
  function($scope, Phone) {
    $scope.phones = Phone.query();
    $scope.orderProp = 'age';
  }]);

phonecatControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone',
  function($scope, $routeParams, Phone) {
    $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
      $scope.mainImageUrl = phone.images[0];
    });

    $scope.setImage = function(imageUrl) {
      $scope.mainImageUrl = imageUrl;
    }
  }]);

phonecatControllers.controller('LoginCtrl', ['$scope','$rootScope','$routeParams', '$cookieStore','$window','$location','LoginService',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,LoginService) {
  	$scope.rememberMe = false;
	
	$scope.login = function() {
		LoginService.authenticate({par:'',username: $scope.username, password: $scope.password}, function(authenticationResult) {
			var authToken = authenticationResult.token;
			$rootScope.authToken = authToken;
			if ($scope.rememberMe) {
				$cookieStore.put('authToken', authToken);
			}
			
			LoginService.get({par:'user',username: '', password: ''}, function(user) {
				
				$rootScope.username=user.username;
				$rootScope.authority=user.authority;
				$location.path("/dashboard");
			}
			, function(response)
			{
				$scope.error="Error User";
			}
			);
			
			
		}
		, function(response)
		{
			$scope.error="Bad Credentials";
		}
		);
	};
  }]);
  
  
phonecatControllers.controller('SituazioneTaskCtrl', ['$scope','$rootScope','$routeParams','$cookieStore', '$window','$location','TaskNotAssignmentService','UserService',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,TaskNotAssignmentService,UserService) {
  	$scope.pagina="situazione_task";	
  	UserService.get({role:'ROLE_USER'}, function(users) {
  		$scope.users=users;
  		$scope.leftUser = [];
  		$scope.rightUser = [];
  		var leftUser,rightUser;
  		for(var i=0;i<users.length;i++)
  		{	
  			if(i%2==0)
  				$scope.leftUser.push({'username':users[i].username,'image':users[i].image,'taskassegnati':20,'taskcarico':10,'taskconclusi':5});
  			else
  				$scope.rightUser.push({'username':users[i].username,'image':users[i].image,'taskassegnati':20,'taskcarico':10,'taskconclusi':5});
  		}
  		
  		  
        $scope.taskassegnati=20;
        $scope.x=10;
        $scope.taskconclusi=5;
                            
  		
  	},function(data)
  	{
  		console.log="Errore load user ruolo ROLE_USER"
  	});
  	  	$scope.logout = function() {
		delete $rootScope.user;
		delete $rootScope.authToken;
		$cookieStore.remove('authToken');
		$location.path("/login");
	};
  	
  	$scope.clickUser=function(username)
  	{
  		$window.alert(username);
  	};
}]);




phonecatControllers.controller('CreaTaskCtrl', ['$scope','$rootScope','$routeParams','$cookieStore', '$window','$location','TaskNotAssignmentService',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,TaskNotAssignmentService) {
  	$scope.pagina="crea_task";	
}]);
  
  
  
  phonecatControllers.controller('DashboardCtrl', ['$scope','$rootScope','$routeParams','$cookieStore', '$window','$location','TaskNotAssignmentService','LoginService',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,TaskNotAssignmentService,LoginService) {
  	
  			
  	var authority=$rootScope.authority;
	if(authority==='ROLE_ADMIN')
	{
			$rootScope.menu_laterale=[{'name':'Situazione Task','page':'#!/situazione_task'},{'name':'Crea Task','page':'#!/crea_task'},{'name':'Assegna Task','page':'#!/assegna_task'},{'name':'Task Conclusi','page':'#!/task_conclusi'}]
			//$scope.menu_laterale=[{'name':'Situazione Task','page':'situazione_task'},{'name':'Crea Task','page':'crea_task'},{'name':'Assegna Task','page':'assegna_task'},{'name':'Task Conclusi','page':'task_conclusi'}]
	}else
	{
			$rootScope.menu_laterale=[{'name':'Nuovi Task','page':'#!/nuovi_task'},{'name':'Task Presi','page':'#!/task_presi'},{'name':'Task Conclusi','page':'#!/task_conclusi'},{'name':'test','page':'#!/test'}]
			//$scope.menu_laterale=[{'name':'Nuovi Task','page':'nuovi_task'},{'name':'Task Presi','page':'task_presi'},{'name':'Task Conclusi','page':'task_conclusi'},{'name':'test','page':'test'}]
	}
	$location.path("/situazione_task");
	
	
  	$scope.logout = function() {
		delete $rootScope.user;
		delete $rootScope.authToken;
		$cookieStore.remove('authToken');
		$location.path("/login");
	};
  		
  }]);
  
  
 
  