/**
 * @param {number} x
 * @return {boolean}
 */
var isPalindrome = function (x) {
  let expect = 0;
  let digit = 0;

  if (x > 0 && x % 10 === 0) return false;

  while (x > expect) {
    digit = x % 10;
    expect = expect * 10 + digit;
    x = Math.floor(x / 10);
  }

  return expect === x || Math.floor(expect / 10) === x;
};
