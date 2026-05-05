/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function (s) {
  if (!s || s.length < 1) return "";

  let result = "";
  let current = "";

  for (let i = 0; i <= s.length - 1; i++) {
    current = expand(s, i, i);
    if (current.length > result.length) result = current;

    current = expand(s, i, i + 1);
    if (current.length > result.length) result = current;
  }

  return result;
};

function expand(s, left, right) {
  while (left >= 0 && right <= s.length - 1 && s[left] === s[right]) {
    left--;
    right++;
  }
  return s.slice(left + 1, right);
}
