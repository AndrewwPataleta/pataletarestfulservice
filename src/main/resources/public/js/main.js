/**
 * Created by andrew on 4/7/17.
 */
var app = angular.module("mainApp", []);

app.controller("appCtr", function ($scope, $http) {
    $scope.spareparts = [];

    $http.get("http://localhost:8080/132/parts/article/T2280").success(function (data) {
        $scope.spareparts = data;
    });

});