public class Main {
    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 2, 3, 4}, 2));
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println(binarySearch(new int[]{2, 3, 6, 9, 10}, 10));
        System.out.println(binarySearch(new int[]{1}, 1));
        System.out.println(binarySearch(new int[]{1}, 3));
        System.out.println(binarySearch(new int[]{1, 2}, 1));
        System.out.println(binarySearch(new int[]{1, 2}, 2));
        System.out.println(binarySearch(new int[]{1, 2}, 3));
        System.out.println(binarySearchRecursive(new int[]{1, 2, 3, 4}, 0, 3, 2));
        System.out.println(binarySearchRecursive(new int[]{1, 2, 3, 4, 5}, 0, 4, 4));
        System.out.println(binarySearchRecursive(new int[]{2, 3, 6, 9, 10}, 0, 4, 10));
        System.out.println(binarySearchRecursive(new int[]{1}, 0, 0, 1));
        System.out.println(binarySearchRecursive(new int[]{1}, 0, 0, 3));
        System.out.println(binarySearchRecursive(new int[]{1, 2}, 0, 1, 1));
        System.out.println(binarySearchRecursive(new int[]{1, 2}, 0, 1, 2));
        System.out.println(binarySearchRecursive(new int[]{1, 2}, 0, 1, 3));
    }

    private static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int mid;

        while (true) {
            if (left > right) {
                return -1;
            }

            mid = (left + right) / 2;

            if (arr[mid] < value) {
                left = mid + 1;
            } else if (arr[mid] > value) {
                right = mid - 1;
            } else if (arr[mid] == value) {
                return mid;
            }
        }
    }

    private static int binarySearchRecursive(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] < value) {
            left = mid + 1;
        } else if (arr[mid] > value) {
            right = mid - 1;
        } else if (arr[mid] == value) {
            return mid;
        }

        return binarySearchRecursive(arr, left, right, value);
    }
}
