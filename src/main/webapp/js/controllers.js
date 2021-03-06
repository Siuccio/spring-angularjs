'use strict';

/* Controllers */

var appControllers = angular.module('appControllers', []);

appControllers.controller('PhoneListCtrl', ['$scope', 'Phone',
  function($scope, Phone) {
    $scope.phones = Phone.query();
    $scope.orderProp = 'age';
  }]);

appControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone',
  function($scope, $routeParams, Phone) {
    $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
      $scope.mainImageUrl = phone.images[0];
    });

    $scope.setImage = function(imageUrl) {
      $scope.mainImageUrl = imageUrl;
    }
  }]);

appControllers.controller('LoginCtrl', ['$scope','$rootScope','$routeParams', '$cookieStore','$window','$location','LoginService',
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
        $rootScope.authority=user.role;
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
  
  
appControllers.controller('SituazioneTaskCtrl', ['$scope','$rootScope','$routeParams','$cookieStore', '$window','$location','$q','TaskCountService','UserRoleService',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,$q,TaskCountService,UserRoleService) {
    $scope.pagina="situazione_task";  
    UserRoleService.get({role:'ROLE_USER'}, function(users) {
      $scope.users=users;
      $scope.leftUser = [];
      $scope.rightUser = [];
      var leftUser,rightUser;
      var promises=[]
      for(var i=0;i<users.length;i++)
      { 
          var promise =  TaskCountService.get({username:users[i].username}).$promise;
          promises.push(promise);
        
      }
      $q.all(promises).then(function(task){
           
           for(var i=0;i<task.length;i++)
           {
                    var user_task={'username':users[i].username,'image':users[i].image,'taskassignmentcount':task[i].taskAssignmentCount,'tasktakecount':task[i].taskTakeCount,'taskconclusicount':task[i].taskConcludeCount};
                    if(i%2==0)
                      $scope.leftUser.push(user_task);
                    else
                      $scope.rightUser.push(user_task);
           }
                   
          
      });
                            
      
    },function(data)
    {
      console.log="Errore load user ruolo ROLE_USER"
    });

    
    $scope.clickUser=function(username)
    {
      //
      //$rootScope.username=username;
      $location.path("lista_task/"+username);
     
    };
}]);


appControllers.controller('DetailsTaskCtrl', ['$scope','$rootScope','$routeParams','$cookieStore', '$window','$location','TaskServiceID',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,TaskServiceID) {
    $scope.task_id=$routeParams.task_id;
    var id=$routeParams.task_id;
    $scope.pagina="details_task";  
    $scope.modify=false;
    $scope.chooseArea = [
    {  name: 'SVILUPPO'},
    { name: 'BUG_FIX'}];
    $scope.chooseCritical = [
    { name: 'LOW'},
    { name: 'HIGH'},{ name: 'EMERGERCY'}];

    $scope.chooseFase= [
    { name: 'ASSIGNMENT'},
    { name: 'NOT_ASSIGNMENT'},{ name: 'TAKE'},{ name: 'CONCLUDE'}];

      //$scope.area='BUG_FIX';
      //$scope.critical='LOW';
      //$scope.fase='TAKE';
     

  $scope.today = function() {
    $scope.dt = new Date();
  };
  $scope.today();

  $scope.clear = function () {
    $scope.dt = null;
  };

  // Disable weekend selection
  $scope.disabled = function(date, mode) {
    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  };

  $scope.toggleMin = function() {
    $scope.minDate = $scope.minDate ? null : new Date();
  };
  $scope.toggleMin();
  $scope.mydp ={openedAssigment:false,openConcluse:false}
  $scope.openAssigment = function($event) {
     $event.preventDefault();
     $event.stopPropagation();
     if (typeof($scope.mydp.openedAssigment) === 'undefined'){
       $scope.mydp = {};
     }
     $scope.mydp.openedAssigment = true;
  };
$scope.openConcluse = function($event) {
     $event.preventDefault();
     $event.stopPropagation();
     if (typeof($scope.mydp.openConcluse) === 'undefined'){
       $scope.mydp = {};
     }
     $scope.mydp.openConcluse = true;
  };



  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

    TaskServiceID.get({id:id}, function(task) {
  
            $scope.newTask={id:task.id,title:task.title,description:task.description,
              area:task.area,
              criticality:task.criticality,fase:task.fase,assignment:task.assignment,conclusion:task.conclusion,dayWork:task.dayWork};
       });
    $scope.setModify=function(value)
    {
        $scope.modify=value;

    }
    $scope.isModify=function()
    {
        return $scope.modify;
    }

    $scope.save=function()
    {
     
      TaskServiceID.update({id:$scope.newTask.id},{id:$scope.newTask.id,title:$scope.newTask.title, description:$scope.newTask.description,area:$scope.newTask.area,assignment:$scope.newTask.assignment,conclusion:$scope.conclusion,criticality:$scope.newTask.criticality,dayWork:$scope.newTask.dayWork,fase:$scope.newTask.fase},function(task)
        {
            $scope.setModify(false);
        });
    }
}]);

appControllers.controller('CreaTaskCtrl', ['$scope','$rootScope','$routeParams','$cookieStore', '$window','$location','TaskService',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,TaskService) {
    $scope.pagina="crea_task";  
}]);
  

appControllers.controller('ListaTaskCtrl', ['$scope','$rootScope','$routeParams','$cookieStore', '$window','$location','TaskService','UserService','ngTableParams',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,TaskService,UserService,ngTableParams) {
    $scope.pagina="lista_task";  
    var username=$routeParams.username;


    UserService.get({username:username}, function(user) {
      $scope.user=user;
   
      $scope.image=user.image;
      
       TaskService.get({username:user.username,fase:'ASSIGNMENT',page:0}, function(task) {
          var title=[];
          var size=task.size;
          
          $scope.totalElementAssigment=task.totalElements;
          $scope.itemsperpageAssigment=task.numberOfElements;
          $scope.totalPagesAssigment=task.totalPages;
          $scope.tasksAssigment=task;  
       });
  
      TaskService.get({username:user.username,fase:'TAKE',page:0}, function(task) {
          var title=[];
          var size=task.size;
          
          $scope.totalElementTake=task.totalElements;
          $scope.itemsperpageTake=task.numberOfElements;
          $scope.tasksTake=task;  
       });   


      TaskService.get({username:user.username,fase:'CONCLUDE',page:0}, function(task) {
          var title=[];
          var size=task.size;
          
          $scope.totalElementConclude=task.totalElements;
          $scope.itemsperpageConclude=task.numberOfElements;
          $scope.tasksConclude=task;  
       });                     
      
    },function(data)
    {
      console.log="Errore load user ruolo ROLE_USER"
    });


    
$scope.maxSize = 3;
   


$scope.data = { currentPageAssigment: 1,currentPageTake:1,currentPageConclude:1}
$scope.pageChangedAssigment = function() {
   var curr=$scope.data.currentPageAssigment-1;
   var user=$scope.user;
    TaskService.get({username:user.username,fase:'ASSIGNMENT',page:curr}, function(task) {
          $scope.totalElementAssigment=task.totalElements;
          $scope.itemsperpageAssigment=task.numberOfElements;
          $scope.tasksAssigment=task;  
       });
    
  };

  $scope.pageChangedTake = function() {
   var curr=$scope.data.currentPageTake-1;
   var user=$scope.user;
    TaskService.get({username:user.username,fase:'TAKE',page:curr}, function(task) {
          $scope.totalElementTake=task.totalElements;
          $scope.itemsperpageTake=task.numberOfElements;
          $scope.tasksTake=task;  
       });
    
  };

  $scope.pageChangedConclude = function() {
   var curr=$scope.data.currentPageConclude-1;
   var user=$scope.user;
    TaskService.get({username:user.username,fase:'CONCLUDE',page:curr}, function(task) {
          $scope.totalElementConclude=task.totalElements;
          $scope.itemsperpageConclude=task.numberOfElements;
          $scope.tasksConclude=task;  
       });
    
  };
  $scope.$watch('currentPageAssigment', function(newPage){
    

    //or any other code here
  });

  $scope.openTask=function(id)
  {
    $location.path("/details_task/"+id);
  }


}]);
  
  
appControllers.controller('DashboardCtrl', ['$scope','$rootScope','$routeParams','$cookieStore', '$window','$location','TaskService','LoginService',
  function($scope,$rootScope, $routeParams,$cookieStore,$window,$location,TaskService,LoginService) {
    
        
    var authority=$rootScope.authority;
  if(authority==='ROLE_ADMIN')
  {
      $rootScope.menu_laterale=[{'name':'Situazione Task','page':'#!/situazione_task'},{'name':'Crea Task','page':'#!/crea_task'},{'name':'Assegna Task','page':'#!/assegna_task'},{'name':'Task Conclusi','page':'#!/task_conclusi'}]
     $location.path("/situazione_task");
      //$scope.menu_laterale=[{'name':'Situazione Task','page':'situazione_task'},{'name':'Crea Task','page':'crea_task'},{'name':'Assegna Task','page':'assegna_task'},{'name':'Task Conclusi','page':'task_conclusi'}]
  }else
  {
      $rootScope.menu_laterale=[{'name':'Nuovi Task','page':'#!/nuovi_task'},{'name':'Task Presi','page':'#!/task_presi'},{'name':'Task Conclusi','page':'#!/task_conclusi'}]
      //$scope.menu_laterale=[{'name':'Nuovi Task','page':'nuovi_task'},{'name':'Task Presi','page':'task_presi'},{'name':'Task Conclusi','page':'task_conclusi'},{'name':'test','page':'test'}]
      $location.path("/nuovi_task");
  }
  
  
  
 



  
      
  }]);
  
  
 
  