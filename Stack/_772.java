/**
 * https://leetcode.com/problems/basic-calculator-iii/
 */
class Solution {

  private int idx = 0;

  public int calculate(String s) {
    //corner case
    if (s == null || s.length() == 0) {
      return 0;
    }

    //initlize
    Stack<Integer> stack = new Stack<>();
    char operator = '+';
    int num = 0;

    while (idx < s.length()) {
      char curCh = s.charAt(idx++);
      if (Character.isDigit(curCh)) {
        num = num * 10 + (curCh - '0');
      }

      if (curCh == '(') {
        num = calculate(s);
      }

      if (idx >= s.length() || curCh == '+' || curCh == '-' || curCh == '*' || curCh == '/'
          || curCh == ')') {
        if (operator == '+') {
          stack.push(num);
        } else if (operator == '-') {
          stack.push(-num);
        } else if (operator == '*') {
          stack.push(stack.pop() * num);
        } else if (operator == '/') {
          stack.push(stack.pop() / num);
        }

        operator = curCh;
        num = 0;
      }

      if (curCh == ')') {
        break;
      }
    }

    return stack.stream().mapToInt(x -> x).sum();
  }
}