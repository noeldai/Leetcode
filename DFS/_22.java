class Solution {

  public List<String> generateParenthesis(int n) {
    if (n < 0) {
      return new ArrayList<>();
    }

    List<String> result = new ArrayList<>();

    helper(result, new StringBuilder(), n, 0);

    return result;
  }

  private void helper(List<String> result, StringBuilder sb, int n, int delta) {
    //success case
    if (sb.length() == 2 * n && delta == 0) {
      result.add(sb.toString());
    }

    //fail case
    if (sb.length() >= 2 * n || delta < 0) {
      return;
    }

    sb.append('(');
    helper(result, sb, n, delta + 1);
    sb.setLength(sb.length() - 1);

    sb.append(')');
    helper(result, sb, n, delta - 1);
    sb.setLength(sb.length() - 1);
  }
}