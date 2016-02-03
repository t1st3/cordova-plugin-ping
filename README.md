
# cordova-plugin-ping

[![NPM version](https://img.shields.io/npm/v/cordova-plugin-ping.svg)](https://www.npmjs.org/package/cordova-plugin-ping)

This plugin implements the [`ping` software utility](https://en.wikipedia.org/wiki/Ping_%28networking_utility%29).

## Installation

> cordova plugin add cordova-plugin-ping

or

> cordova plugin add https://github.com/t1st3/cordova-plugin-ping.git

## Usage

This plugin defines a global `ping` object.
Although the object is in the global scope, it is not available until after the `deviceready` event.

### Ping a domain
        
```js
document.addEventListener('deviceready', onDeviceReady, false);
function onDeviceReady() {
  var p, success, err, ipList;
  ipList = ['tiste.org'];
  success = function (results) {
    console.log(results);
  };
  err = function (e) {
    console.log('Error: ' + e);
  };
  p = new Ping();
  p.ping(ipList, success, err);
}
```

### Ping an IPv4 address

```js
document.addEventListener('deviceready', onDeviceReady, false);
function onDeviceReady() {
  var p, success, err, ipList;
  ipList = ['192.168.1.254'];
  success = function (results) {
    console.log(results);
  };
  err = function (e) {
    console.log('Error: ' + e);
  };
  p = new Ping();
  p.ping(ipList, success, err);
}
```

### Ping multiple domains or IP addresses

```js
document.addEventListener('deviceready', onDeviceReady, false);
function onDeviceReady() {
  var p, success, err, ipList;
  ipList = ['tiste.org', 'undefineddomain.com', '192.168.1.254'];
  success = function (results) {
    console.log(results);
  };
  err = function (e) {
    console.log('Error: ' + e);
  };
  p = new Ping();
  p.ping(ipList, success, err);
}
```

## Properties

- ping.results

## ping.results

Get the the results of the`ping`.

Results look like this:

```json
[
  {
    "target": "github.com",
    "status": "success",
    "avg": 40.131
  },
  {
    "target": "undefineddomain.com",
    "status": "timeout",
    "avg": 0
  },
  {
    "target": "192.168.1.1",
    "status": "success",
    "avg": 35.654
  }
]
```

### Supported Platforms

- Android


*****

## License

This project is licensed under the [MIT license](https://opensource.org/licenses/MIT). Check the [LICENSE.md file](https://github.com/t1st3/cordova-plugin-ping/blob/master/LICENSE.md).
