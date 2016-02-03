 
exports.defineAutoTests = function () {
  describe('Ping (window.Ping)', function () {
    it('should exist', function (done) {
      expect(window.Ping).toBeDefined();
      done();
    });
  });

  describe('Success callback', function () {
    it('should take an argument that is an array of results (for existing domain)', function (done) {
      var p, success, err;
      p = new window.Ping();
      success = function (r) {
        expect(r).toBeDefined();
        expect(r.length > 0).toBe(true);
        expect(r[0].target).toBe('github.com');
        expect(r[0].status).toBe('success');
        expect(typeof r[0].avg).toBe('number');
        done();
      };
      err = function (e) {
        console.log(e);
      };
      p.ping(['github.com'], success, err);
    });

    it('should take an argument that is an array of results (for un-existing domain)', function (done) {
      var p, success, err;
      p = new window.Ping();
      success = function (r) {
        expect(r).toBeDefined();
        expect(r.length > 0).toBe(true);
        expect(r[0].target).toBe('undefineddomain.com');
        expect(r[0].status).toBe('timeout');
        expect(typeof r[0].avg).toBe('number');
        done();
      };
      err = function (e) {
        console.log(e);
      };
      p.ping(['undefineddomain.com'], success, err);
    });
  });
};
