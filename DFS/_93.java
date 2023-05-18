class Solution {

  public List<String> restoreIpAddresses(String s) {
    if (s == null || s.length() < 4) {
      return new ArrayList<>();
    }

    List<String> result = new ArrayList<>();
    helper(result, new StringBuilder(), s, 0, 0);

    return result;
  }

  private void helper(List<String> result, StringBuilder sb, String s, int idx, int part) {
    int sbLen = sb.length();

    // success case
    if (idx == s.length() && part == 4) {
      sb.setLength(sbLen - 1);
      result.add(sb.toString());
      return;
    }

    // fail case
    if (idx >= s.length() || part >= 4) {
      return;
    }

    // recursion body
    int curVal = 0;
    for (int i = idx; i < s.length(); i++) {
      curVal = curVal * 10 + (s.charAt(i) - '0');
      if (curVal >= 0 && curVal <= 255) {
        sb.append(curVal + ".");
        helper(result, sb, s, i + 1, part + 1);
        sb.setLength(sbLen);
      }

      if (curVal == 0) {
        break;
      }
    }
  }
}