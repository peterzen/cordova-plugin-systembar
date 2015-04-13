/*global cordova, module*/

module.exports = {
    hide: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SystembarPlugin", "hide", [name]);
    },
    show: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SystembarPlugin", "show", [name]);
    }
};
