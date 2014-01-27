var gdrlogsApp = angular.module('gdrlogsApp', ['ngRoute', 'gdrlogsServices', 'gdrlogsControllers']);

gdrlogsApp.config(['$routeProvider', function ($routeProvider) {
	$routeProvider.
	when('/accueil', {
		templateUrl: 'app/partials/accueil.html'
	}).
	when('/alerte', {
		templateUrl: 'app/partials/alerte.html'
	}).
	when('/recherche', {
		templateUrl: 'app/partials/recherche.html'
	}).
	when('/statistique', {
		templateUrl: 'app/partials/statistique.html'
	}).
	when('/administration', {
		templateUrl: 'app/partials/administration.html'
	}).
	otherwise({
		redirectTo: '/accueil'
	});
}]);