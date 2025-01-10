///////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Defines the javascript files that need to be loaded and their dependencies.
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////

require.config({
    paths: {
        angular: '../../bower_components/angular/angular',
        angularMessages: '../../bower_components/angular-messages/angular-messages',
        lodash: "../../bower_components/lodash/lodash",
        editableTableWidgets: 'editable-table-widgets',
        common: 'common',
        createUserApp: 'new-user'
    },
    shim: {
        angular: {
            exports: "angular"
        },
        angularMessages: {
            deps: ['angular']
        },
        editableTableWidgets: {
            deps: ['angular', 'lodash']
        },
        common: {
          deps: ['angular', 'angularMessages','editableTableWidgets']
        },
        createUserApp: {
            deps: [ 'common']
        }
    }
});

require(['createUserApp'], function () {

    angular.bootstrap(document.getElementById('createUserApp'), ['newUserApp']);

});