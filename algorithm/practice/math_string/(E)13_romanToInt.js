/**
 * @param {string} s
 * @return {number}
 */
var romanToInt = function (s) {
  const romanMap = {
    I: 1,
    V: 5,
    X: 10,
    L: 50,
    C: 100,
    D: 500,
    M: 1000,
  };

  let total = 0;
  let current = "";
  let next = "";

  for (let i = 0; i <= s.length - 1; i++) {
    let current = romanMap[s[i]];
    let next = romanMap[s[i + 1]];

    i < s.length - 1 && current < next
      ? (total -= current)
      : (total += current);
  }
  return total;
};
