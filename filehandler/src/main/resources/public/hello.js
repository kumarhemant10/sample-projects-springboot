var app = angular.module('demo', []);

app.controller('Download', function($scope, $http) {
	$scope.download = function(){
		var url = "http://localhost:8080/modelo"
			window.open(url, "_self");
	}
});


