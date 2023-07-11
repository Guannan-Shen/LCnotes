import java.util.Arrays;

public class MySorting {

    public MySorting(){
    }

    public static void insertionSortRe(int[] nums, int n){
        // recursive algorithm
        n = Math.min(n, nums.length);
        if(n == 1) return;
        // sort first n - 1 elements
        insertionSortRe(nums, n - 1);
        int key = nums[n - 1];
        int j = n - 2;
        // insert the nth element into sorted subarray, increasing order
        while(j >= 0 && nums[j] >= key){
            nums[j + 1] = nums[j];
            j--;
        }
        nums[j + 1] = key;
    }

    public void insertionSort(int[] nums, int n){
        // in place sort, sort the first n elements, n should less than the length
        // from smallest to largest
        n = Math.min(n, nums.length);
        for(int i = 1; i < n; i++){
            int j = i - 1;
            // always keep the left side of the array sorted, compare from right to left
            // always try to move one element at once
            int key = nums[i];
            while(j >= 0 && nums[j] > key){
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }

    public int[] addBinInt(int[] nums1, int[] nums2){
        if(nums1.length != nums2.length) return new int[0];
        int[] result = new int[nums1.length + 1];
        int carry = 0;
        int i = 0;
        while(i < nums1.length){
            result[i] = carry + nums1[i] + nums2[i];
            if(result[i] >= 2) {
                carry = 1;
                result[i] -= 2;
            }else{
                carry = 0;
            }
            i++;
        }
        if(carry == 1) result[i] = carry;
        return result;
    }

    public void insertionSortDe(int[] nums, int n){
        // in-place decreasing order
        n = Math.min(n, nums.length);
        for(int i = 1; i < n; i++) {
            int key = nums[i];
            int j = i - 1;
            while(j >= 0 && nums[j] < key){
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }

    // binary search template
    // https://leetcode.com/discuss/general-discussion/786126/Python-Powerful-Ultimate-Binary-Search-Template.-Solved-many-problems
    // TODO: nums sorted increasingly
    public static int binarySearch(int[] nums, int target){
        // if not found return -1
        // found, return the index
        // TODO: define search space
        int left = 0, right = nums.length - 1;

        // TODO: use left < right, then after the loop terminates, left == right true
        while(left < right){
            // TODO: calculate mid
            int mid = left + (right - left)/2;
            System.out.println("Mid: " + mid);
            // TODO: define the condition, if satisfied, assign right with mid
            if(nums[mid] >= target){
                right = mid;
                System.out.println("Right: " + right);
            }else{
                left = mid + 1;
                System.out.println("Left: " + left);
            }
        }
        return nums[left] == target ? left : -1;
    }

    public void selectionSort(int[] nums){
        // find the smallest element in A[i : n], i from 1 to n - 1
        // and exchange the smallest element and the element in A[i]
        // loop invariant, first i elements always sorted
        // i only need gets to n - 1
        for(int i = 0; i < nums.length - 1; i++){
            int tempMin = nums[i];
            int j = i;
            int minIdx = i;
            while(j < nums.length){
                if(nums[j] < tempMin) {
                    tempMin = nums[j];
                    minIdx = j;
                }
                j++;
            }
            if(minIdx != i){
                nums[minIdx] = nums[i];
                nums[i] = tempMin;
            }
        }
    }
    // recursive function call
    // need base case
    public static void mergeSort(int[] nums, int left, int right){
        if(left >= right) return;
        int mid = left + (right - left)/2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    // merge sort, not in-place sort because of the merge step
    // for the merge step, copy subarrays into left and right temp arrays
    // compare elements one by one, and put elements back to the combined subarray
    private static void merge(int[] nums, int l, int mid, int right){
        int nl = mid - l + 1;
        int nr = right - mid;
        int[] lArray = new int[nl];
        // mid is included in the left array
        int[] rArray = new int[nr];
        System.arraycopy(nums, l, lArray, 0, nl);
        System.arraycopy(nums, mid + 1, rArray, 0, nr);
        // compare and put ordered element back, also deal with remaining elements
        int i = 0, j = 0, k = 0;
        while(i < nl && j < nr){
            if(lArray[i] <= rArray[j]){
                nums[l + k] = lArray[i];
                i++;
            }else {
                nums[l + k] = rArray[j];
                j++;
            }
            k++;
        }
        while(i < nl){
            nums[l + k] = lArray[i];
            i++;
            k++;
        }
        while(j < nr){
            nums[l + k] = rArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args){
        int[] nums = {6, 2, 1, 5, -1, -5};
        MySorting ms = new MySorting();
        ms.insertionSort(nums, 4);
        System.out.println(Arrays.toString(nums));

        int[] nums1 = {6, 2, 1, 5, -1, -5};
        ms.insertionSortDe(nums1, 4);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {0, 1, 1, 0, 0, 1, 1};
        int[] nums3 = {0, 0, 1, 0, 1, 1, 1};
        System.out.println(Arrays.toString(ms.addBinInt(nums2, nums3)));

        int[] nums4 = {6, 2, 1, 5, -1, -5, -10};
        ms.selectionSort(nums4);
        System.out.println(Arrays.toString(nums4));

        int[] nums5 = {6, 2, 1, 5, -1, -5, -10, -100};
        mergeSort(nums5, 0, nums5.length - 1);
        System.out.println(Arrays.toString(nums5));

        int[] nums6 = {6, 2, 1, 5, -1, -5, -10, -100};
        insertionSortRe(nums6, 6);
        System.out.println(Arrays.toString(nums6));

        System.out.println(binarySearch(new int[]{1, 5, 7, 10, 29}, 5));
        System.out.println(binarySearch(new int[]{1, 5, 7, 10, 29}, 29));

        System.out.println(binarySearch(new int[]{1, 5, 7, 10, 29}, 30));
        System.out.println(binarySearch(new int[]{1, 5, 7, 10, 29}, 0));
    }
}
