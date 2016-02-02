
var utils = require('cordova/utils'),
  exec = require('cordova/exec'),
  cordova = require('cordova');

function Ping (ipList) {
  this.results = null;
  var self = this;
  self.doPing(ipList, function (info) {
    self.results = info;
  }, function (e) {
    utils.alert('[ERROR] Error initializing Cordova: ' + e);
  });
}

Ping.prototype.doPing = function (ipList, successCallback, errorCallback) {
  exec(successCallback, errorCallback, "Ping", "getPingInfo", ipList);
};

module.exports = Ping;
