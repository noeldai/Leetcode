/**
 * https://leetcode.com/problems/maximum-number-of-visible-points/
 * <p>
 * 本质是Sliding Window，比较难得是用数学的方法找到这个Window
 * 在这个题目中Window是每个点到x轴的夹角关系, 通过数学方式算atan
 * 注意angle和rad的转换：1°=π/180 rad
 * 
 * Time: O(nlogn + 2n) = O(nlogn)
 * Space: O(n)
 */
class Solution {

  public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
    //Step1: 算出每个点距离x轴的弧度制夹角 radian measure
    //Edge case: 特殊处理和原点重合的点，记录个数直接加到结果中
    int numPointsInOrigin = 0;
    List<Double> angleList = new ArrayList<>();
    int xOrigin = location.get(0);
    int yOrigin = location.get(1);

    for (List<Integer> point : points) {
      int xPoint = point.get(0);
      int yPoint = point.get(1);

      if (xPoint == xOrigin && yPoint == yOrigin) {
        numPointsInOrigin++;
      } else {
        angleList.add(Math.atan2(yPoint - yOrigin, xPoint - xOrigin));
      }
    }

    //Step2: sort这个夹角list
    Collections.sort(angleList);

    //Step3: 特殊处理 - 类似循环数组
    int angleListSize = angleList.size();
    for (int i = 0; i < angleListSize; i++) {
      angleList.add(angleList.get(i) + 2 * Math.PI);
    }

    //Step4: 滑动窗口
    double angleDegree = angle * Math.PI / 180;
    int result = 0;
    int left = 0;
    int right = 0;
    while (left < angleListSize) {
      while (right < 2 * angleListSize
          && angleList.get(right) - angleList.get(left) <= angleDegree) {
        right++;
      }

      result = Math.max(result, right - left);
      left++;
    }

    //返回结果
    return result + numPointsInOrigin;
  }
}