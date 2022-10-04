import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int[] arr = createRandomArray();
            insertionSort(arr);
            System.out.println(Arrays.toString(arr));
        }

    }

    private static int[] createRandomArray() {
        Random rand = new Random();
        return IntStream.of(new int[rand.nextInt(1, 11)]).map(i -> rand.nextInt(31)).toArray();
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
}
