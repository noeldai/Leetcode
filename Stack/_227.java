/**
 * https://leetcode.com/problems/basic-calculator-ii/
 * 基础计算器：包括运算+，-，*, /
 *
 * 因为没有括号，所以可以直接用stack来操作 - 遇到*或者/那么将之前的值pop出来操作
 * Time: O(n)
 * Space: O(n) -> 可以优化到O(1)，因为我们总是把当前的数字和前一位数字做操作
 */

//Method 1：用stack space O(n) 解法
class Solution {

  public int calculate(String s) {
    //corner case
    if (s == null || s.length() == 0) {
      return 0;
    }

    //initilize
    Stack<Integer> stack = new Stack<>();
    int num = 0;
    char sign = '+'; //last sign/operator before num

    //pre-processing
    s = s.replaceAll(" ", ""); //处理最后一个位置是空格的情况 eg:" 3/2 "
    int sLen = s.length();

    //main operation
    for (int i = 0; i < sLen; i++) {
      char curCh = s.charAt(i);

      if (Character.isDigit(curCh)) {
        num = num * 10 + (curCh - '0');
      }

      if (!Character.isDigit(curCh) || i == sLen - 1) {
        if (sign == '+') {
          stack.push(num);
        } else if (sign == '-') {
          stack.push(-num);
        } else if (sign == '*') {
          stack.push(stack.pop() * num);
        } else if (sign == '/') {
          stack.push(stack.pop() / num);
        }

        sign = curCh;
        num = 0;
      }
    }

    //add all the num in stack
    return stack.stream().mapToInt(x -> x).sum();
  }
}

//Method 2：Space O(1)
