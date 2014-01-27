var gdrlogsControllers = angular.module('gdrlogsControllers', []);

gdrlogsControllers.controller('gdrLogsListController', ['$scope', 'ListMagieLogs', function($scope, listMagieLogs) {
	var date = new Date();
	var dateLg = date.getTime();
	var dateStr = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
	$scope.datedujour = dateStr;
	$scope.magieLogs = listMagieLogs.getMagieLogsByDate({date:dateLg});
	$scope.sysOutLogs = listMagieLogs.getSysOutLogsByDate({date:dateLg});
	
	$scope.checkMagieLog = [];
	$scope.checkSysOutLog = [];
	
	
	$scope.alertMagieLog = function(id) {
		console.log($scope);
		console.log(id);
	};
	
	$scope.alertSysOutLog = function(id) {
		console.log($scope);
		console.log(id);
	};
	
}]);