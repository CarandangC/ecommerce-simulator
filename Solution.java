class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int j = 0; i < nums.length; j ++){
            for (int i = 0; i < nums.length; i ++){
                if (j != i){
                    if ((nums[j] + nums [i]) == target){
                        int[] arr = {j,i};
                        return (arr);
                    }
                }
            }
        }
    }
}