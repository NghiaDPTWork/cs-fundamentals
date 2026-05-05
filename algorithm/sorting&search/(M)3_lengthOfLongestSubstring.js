/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
  let result = "";
  let expect = "";

  for (let i = 0; i <= s.length - 1; i++) {
    let char = s[i];
    let index = expect.indexOf(char);

    index !== -1
      ? (expect = expect.substring(index + 1) + char)
      : (expect = expect + char);

    expect.length > result.length ? (result = expect) : (result = result);
  }
  return result.length;
};
