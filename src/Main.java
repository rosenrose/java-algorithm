import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int[] arr = createRandomArray();
            quickSort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(arr));
        }

    }

    private static int[] createRandomArray() {
        Random rand = new Random();
        return IntStream.of(new int[rand.nextInt(1, 11)]).map(i -> rand.nextInt(31)).toArray();
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
}
