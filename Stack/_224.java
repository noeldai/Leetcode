/**
 * https://leetcode.com/problems/basic-calculator/ 基础计算器：包括运算+，-，和括号
 * <p>
 * 思路：处理加减用stack，处理括号用recursion - 左括号说明进入recursion下一层，右括号弹栈
 * Time: O(n); Space: O(n)
 */

//Method 1: Stack with recursive help
class Solution {

  private int idx = 0; //global indx position

  public int calculate(String s) {
    //corner case
    if (s == null || s.length() == 0) {
      return 0;
    }

    //initialize
    Stack<Integer> stack = new Stack<>();
    char operator = '+'; //last operator we have meet
    int num = 0;

    while (idx < s.length()) {
      char curCh = s.charAt(idx++);
      if (curCh >= '0' && curCh <= '9') {
        num = num * 10 + (curCh - '0');
      }

      if (curCh == '(') { //calling recursion
        num = calculate(s);
      }

      if (idx >= s.length() || curCh == '+' || curCh == '-' || curCh == ')') {
        if (operator == '+') {
          stack.push(num);
        } else {
          stack.push(-num);
        }

        operator = curCh;
        num = 0;
      }

      if (curCh == ')') { //close/pop current recursion level
        break;
      }
    }

    return stack.stream().mapToInt(x -> x).sum(); //iterate the current stack and sum up all int
  }
}

//Method 2: Iterative pure stack
class Solution {

  public int calculate(String s) {
    //corner case
    if (s == null || s.length() == 0) {
      return 0;
    }

    //initialize
    int strLen = s.length();
    Stack<Integer> stack = new Stack<>();
    int sign = 1; // 1 = '+', -1 = '-'
    int result = 0;

    for (int i = 0; i < strLen; i++) {
      char curCh = s.charAt(i);

      if (Character.isDigit(curCh)) {
        int curVal = curCh - '0';
        while (i + 1 < strLen && Character.isDigit(s.charAt(i + 1))) {
          curVal = curVal * 10 + (s.charAt(i + 1) - '0');
          i++;
        }
        result += curVal * sign;
      } else if (curCh == '+') {
        sign = 1;
      } else if (curCh == '-') {
        sign = -1;
      } else if (curCh == '(') {
        stack.push(result); //先放数字进stack
        stack.push(sign); //然后放符号
        result = 0;
        sign = 1;
      } else if (curCh == ')') {
        result = result * stack.pop() + stack.pop(); //先出来符号，然后和符号位置之前的sum相加
      }
    }

    return result;
  }
}