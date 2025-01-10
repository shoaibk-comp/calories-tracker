angular.module('common', ['ngMessages'])
    .controller('BaseFormCtrl', ['$scope', '$http', function ($scope, $http) {

        var fieldWithFocus;

        $scope.vm = {
            submitted: false,
            errorMessages: []
        };

        $scope.focus = function (fieldName) {
            fieldWithFocus = fieldName;
        };

        $scope.blur = function (fieldName) {
            fieldWithFocus = undefined;
        };

        $scope.isMessagesVisible = function (fieldName) {
            return fieldWithFocus === fieldName || $scope.vm.submitted;
        };

        $scope.preparePostData = function () {
            var username = $scope.vm.username !== undefined ? $scope.vm.username : '';
            var password = $scope.vm.password !== undefined ? $scope.vm.password : '';
            var email = $scope.vm.email !== undefined ? $scope.vm.email : '';

            return {
                username: username,
                password: password,
                email: email
            };
        }


        $scope.login = function (username, password) {
            var postData = $scope.preparePostData();

            // Assuming you have a way to get the CSRF token from a meta tag or some other method
            //var csrfToken = document.querySelector('meta[name="_csrf"]');
            //var csrfHeader = document.querySelector('meta[name="_csrf_header"]');

            $http({
                method: 'POST',
                url: '/calories/authenticate',
                data: postData,
                headers: {
                    "Content-Type": "application/json",
                }
            })
            .then(function(response) {
                if (response.data.message === 'Login successful') {
                    window.location.replace('/calories/resources/calories-tracker.html');
                }
                else {
                    $scope.vm.errorMessages = [];
                    $scope.vm.errorMessages.push({description: 'Access denied'});
                }
            }).catch((error) => {
                $scope.vm.errorMessages = [];
                $scope.vm.errorMessages.push({description: 'Authentication Failed!'});
            });
        }


    }])
    .directive('checkPasswordsMatch', function () {
        return {
            require: 'ngModel',
            link: function (scope, elm, attrs, ngModel) {
                ngModel.$validators.checkPasswordsMatch = function (modelValue, viewValue) {
                    if (scope.vm && scope.vm.password && viewValue) {
                        return scope.vm.password === viewValue;
                    }
                    return true;
                };
            }
        };
    });