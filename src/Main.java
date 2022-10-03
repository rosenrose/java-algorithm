public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{20, 25, 26, 29, 33, 1, 3, 5, 6, 10, 11, 19};

        System.out.println(indexOfRotatedArray(arr, 0, arr.length - 1, 0));
        System.out.println(indexOfRotatedArray(arr, 0, arr.length - 1, 6));
        System.out.println(indexOfRotatedArray(arr, 0, arr.length - 1, 20));
        System.out.println(indexOfRotatedArray(arr, 0, arr.length - 1, 19));
        System.out.println(indexOfRotatedArray(arr, 0, arr.length - 1, 26));
    }

    private static int indexOfRotatedArray(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] == value) {
            return mid;
        }

        if (arr[left] <= arr[mid]) {    // left부터 mid까지 정렬된 경우
            if (arr[left] <= value && value <= arr[mid]) {
                return indexOfRotatedArray(arr, left, mid - 1, value);
            }

            return indexOfRotatedArray(arr, mid + 1, right, value);
        } else {    // mid부터 right까지 정렬된 경우
            if (arr[mid] <= value && value <= arr[right]) {
                return indexOfRotatedArray(arr, mid + 1, right, value);
            }

            return indexOfRotatedArray(arr, left, mid - 1, value);
        }
    }
}
