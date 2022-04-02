/**
 * https://leetcode.com/problems/valid-palindrome/
 *
 * 在常规palindrome基础上注意check大小写和非字母char
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {

  public boolean isPalindrome(String s) {
    //corner case
    if (s == null || s.length() == 0) {
      return true;
    }

    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }

      if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
        return false;
      }
    }

    return true;
  }
}