
var app = angular.module("mainApp", []);


app.controller("pat", function ($scope, $http) {

    $scope.testAlert = function () {
        $http.get("http://localhost:8080/132/parts/article/432215").then(
            promice.success(function (name) {

                },
                promice.error(function (response, status) {
                    alert("kk");
                }))
        );
    }
});


app.factory('myService', function($http) {

    var getData = function() {

        // Angular $http() and then() both return promises themselves
        return $http({method:"GET", url:"http://localhost:8080/132/parts/article/432215"}).then(function(result){

            // What we return here is the data that will be accessible
            // to us after the promise resolves
            return result.data;
        });
    };


    return { getData: getData };
});


function myFunction($scope, myService) {
    var myDataPromise = myService.getData();
    myDataPromise.then(function(result) {

        // this is only run after getData() resolves
        $scope.data = result;
        console.log("data.name"+$scope.data.name);
    });
}