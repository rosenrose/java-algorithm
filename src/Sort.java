import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Sort {
    public static void main() {
        for (int i = 0; i < 5; i++) {
            int[] arr = createRandomArray();
            // bubbleSort(arr);
            // selectionSort(arr);
            // insertionSort(arr);
            // quickSort(arr, 0, arr.length - 1);
            // mergeSort(arr, 0, arr.length - 1);
            countingSort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    private static int[] createRandomArray() {
        Random rand = new Random();
        return IntStream.of(new int[rand.nextInt(1, 11)]).map(i -> rand.nextInt(31)).toArray();
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {  // 한번 훑을 때마다 방문하는 요소 수가 1씩 줄어듦
                if (arr[j] <= arr[j + 1]) { // 왼쪽 > 오른쪽일 때만 교환하니 안정적
                    continue;
                }

                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[j + 1]) {
                    break;
                }

                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        int j = right;
        int pivotValue = arr[(i + j) / 2];

        while (i <= j) {
            while (arr[i] < pivotValue) {
                i++;
            }
            while (arr[j] > pivotValue) {
                j--;
            }

            if (i > j) {
                break;
            }

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }

        quickSort(arr, left, j);
        quickSort(arr, i, right);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        int len = right - left + 1;

        if (len <= 1) {
            return;
        }

        int pivot = (left + right) / 2;

        mergeSort(arr, left, pivot);
        mergeSort(arr, pivot + 1, right);

        int a = left;
        int b = pivot + 1;
        int[] temp = new int[len];

        for (int i = 0; i < len; i++) {
            if (a < pivot + 1 && b < right + 1) {
                if (arr[a] < arr[b]) {
                    temp[i] = arr[a];
                    a++;
                } else {
                    temp[i] = arr[b];
                    b++;
                }
            } else {
                if (a == pivot + 1) {
                    temp[i] = arr[b];
                    b++;
                } else {
                    temp[i] = arr[a];
                    a++;
                }
            }
        }

        System.arraycopy(temp, 0, arr, left, len);
    }

    private static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(-1);

        int[] count = new int[max + 1];

        for (int num : arr) {
            count[num]++;
        }
        for (int i = 0; i < count.length - 1; i++) {
            count[i + 1] += count[i];
        }

        int[] temp = new int[arr.length];

        for (int num : arr) {
            int index = count[num];

            temp[index - 1] = num;
            count[num]--;
        }

        System.arraycopy(temp, 0, arr, 0, arr.length);
    }
}
