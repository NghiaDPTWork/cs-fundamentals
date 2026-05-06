/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
  let maxString = "";
  let indexDuplicate = 0;
  let expectString = "";

  for (let i = 0; i <= s.length - 1; i++) {
    indexDuplicate = expectString.indexOf(s.charAt(i));

    indexDuplicate !== -1
      ? (expectString =
          expectString.substring(indexDuplicate + 1, expectString.length) +
          s.charAt(i))
      : (expectString = expectString + s.charAt(i));

    expectString.length > maxString.length
      ? (maxString = expectString)
      : (maxString = maxString);
  }

  return maxString.length;
};
