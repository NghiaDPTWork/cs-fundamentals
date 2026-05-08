/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function (s) {
  let maxString = "";
  let currentString = "";

  for (let i = 0; i <= s.length - 1; i++) {
    // odd
    currentString = expand(s, i, i);
    currentString.length > maxString.length && (maxString = currentString);

    // event
    currentString = expand(s, i, i + 1);
    currentString.length > maxString.length && (maxString = currentString);
  }
  return maxString;
};

function expand(s, left, right) {
  while (left >= 0 && right <= s.length - 1 && s[left] === s[right]) {
    left--;
    right++;
  }
  return s.slice(left + 1, right);
}
