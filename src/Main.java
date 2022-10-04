import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int[] arr = createRandomArray();
            selectionSort(arr);
            System.out.println(Arrays.toString(arr));
        }

    }

    private static int[] createRandomArray() {
        Random rand = new Random();
        return IntStream.of(new int[rand.nextInt(1, 11)]).map(i -> rand.nextInt(31)).toArray();
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
}
