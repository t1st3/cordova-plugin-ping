 
exports.defineAutoTests = function () {
  describe('Ping (window.ping)', function () {
    it('should exist', function (done) {
      expect(window.ping).toBeDefined();
      done();
    });

    it('should contain a results specification that is an array', function (done) {
      var p = new window.ping(['github.com']);
      expect(p.results).toBeDefined();
      //expect(p.results.length > 0).toBe(true);
      done();
    });
  });
};
