var s3BrowserApp = angular.module("s3BrowserApp", ["ui.router"]);

s3BrowserApp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("s3buckets");

    $stateProvider
        .state('s3buckets', {
            url: '/s3buckets',
            templateUrl: URLS.partialsBuckets,
            controller: 'BucketListingController'
        })
        .state('listbucket', {
            url: '/listbucket?bucket&prefix',
            templateUrl: URLS.partialsListBucket,
            controller: 'BucketContentController'
        }).
        state('about', {
            url: '/about',
            templateUrl: URLS.partialsAbout
        });
});


s3BrowserApp.factory("s3BucketsFactory", function ($http) {
    var factory = {};
    factory.getBuckets = function () {
        return $http.get(URLS.getRootBuckets);
    };

    factory.listKeysInBucket = function (bucket) {
        return $http.get(URLS.listKeysInBucket, {
            params: {
                bucket: bucket
            }
        } );
    };

    factory.listKeysInBucketWithPrefix = function (bucket, prefix) {
        return $http.get(URLS.listKeysInBucketWithPrefix, {
            params: {
                bucket: bucket,
                prefix: prefix
            }
        } );
    };

    return factory;
});


s3BrowserApp.controller("BucketListingController", function ($scope, s3BucketsFactory) {
    function init() {
        $scope.statusmessage = "";
        $scope.errormessage = '';
        $scope.listLoaded = false;

        s3BucketsFactory.getBuckets().success(function (data) {
            $scope.buckets = data;
            $scope.listLoaded = true;
        }).error(function (data, status, errors, config) {
            $scope.setErrorMessage("Could not load list of buckets!");
            $scope.listLoaded = true;
        });
    }

    $scope.setErrorMessage = function (message) {
        $scope.errormessage = message;
        $scope.statusmessage = '';
    };

    $scope.setStatusMessage = function (message) {
        $scope.statusmessage = message;
        $scope.errormessage = '';
    };

    init();
});

s3BrowserApp.controller("BucketContentController", function ($scope, s3BucketsFactory, $stateParams) {
    function init() {
        $scope.statusmessage = "";
        $scope.errormessage = '';
        $scope.bucket = $stateParams.bucket;
        $scope.prefix = $stateParams.prefix;
        $scope.listLoaded=false;

        var prefix = '';
        if ($stateParams.prefix) {
            prefix = $stateParams.prefix;
        }
        s3BucketsFactory.listKeysInBucketWithPrefix($stateParams.bucket, prefix).success(function(data) {
            $scope.objectListing = data;
            $scope.listLoaded = true;
        }).error(function (data, status, errors, config) {
            $scope.listLoaded = true;
            $scope.setErrorMessage("Could not load list of keys/folders in the bucket!");
        });
    }


    $scope.setErrorMessage = function (message) {
        $scope.errormessage = message;
        $scope.statusmessage = '';
    };

    $scope.setStatusMessage = function (message) {
        $scope.statusmessage = message;
        $scope.errormessage = '';
    };

    init();
});