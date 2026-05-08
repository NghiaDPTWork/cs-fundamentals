/**
 * @param {string} a
 * @param {string} b
 * @return {string}
 */
var addBinary = function (a, b) {
  let result = "";
  let carry = 0;
  let i = a.length - 1;
  let j = b.length - 1;

  while (carry !== 0 || i >= 0 || j >= 0) {
    let sum = carry;

    if (i >= 0) {
      sum += a.charCodeAt(i) - 48; // 48 is ASCII code for '0'
      i--;
    }

    if (j >= 0) {
      sum += b.charCodeAt(j) - 48;
      j--;
    }

    result = (sum % 2) + result;
    carry = Math.floor(sum / 2);
  }

  return result;
};
