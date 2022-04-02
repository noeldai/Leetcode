/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * 在正常的palindrome的基础上可以删掉一个char
 * 左右双指针，当找到第一个不同的char时，判断remove左或者右，然后判断剩下的是不是严格的palindrome
 *
 * Time:O(n) loop the string only once
 * Space： O（1）
 *
 * Follow up:
 * 1. 可以删掉n个char - LC 1216
 */
class Solution {

  public boolean validPalindrome(String s) {
    if (s == null || s.length() == 0) {
      return true;
    }

    int left = 0;
    int right = s.length() - 1;
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return checkPalindrome(s, left, right - 1) || checkPalindrome(s, left + 1, right);
      }

      left++;
      right--;
    }

    return true;
  }

  private boolean checkPalindrome(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }

    return true;
  }
}