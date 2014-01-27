var gdrlogsServices = angular.module('gdrlogsServices', ['ngResource']);

gdrlogsServices.factory('ListMagieLogs', ['$resource', function($resource) {
	return $resource('../gdrlogs_frontoffice/rest/logs', {}, 
			{
				getMagieLogsByDate: {method:'GET', url:'../gdrlogs_frontoffice/rest/logs/magielogs', params:{date:'@date'}, isArray:true},
				getSysOutLogsByDate: {method:'GET', url:'../gdrlogs_frontoffice/rest/logs/sysoutlogs', params:{date:'@date'}, isArray:true}
			});
}]);