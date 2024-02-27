public class dsa {
  
    public static int binarySearch(int arr[], int left, int right, int target) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

        
            if (arr[mid] == target)
                return mid;
            
            // If the element is not in the middle, recur for the left or right half
            if (arr[mid] > target)
                return binarySearch(arr, left, mid - 1, target);

            return binarySearch(arr, mid + 1, right, target);
        }

        // Element is not in the array
        return -1;
    }

    public static void main(String args[]) {
        int arr[] = {2, 3, 4, 10, 40};
        int target = 10;
        int result =  binarySearch(ar r, 0, arr.length - 1, target);

        if (result == -1) {
            System.out.println("Element not found in the array");
        } else {
            System.out.println("Element found at index " + result);
        }
    }
}
