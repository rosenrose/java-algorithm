import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int[] arr = createRandomArray();
            countingSort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    private static int[] createRandomArray() {
        Random rand = new Random();
        return IntStream.of(new int[rand.nextInt(1, 11)]).map(i -> rand.nextInt(31)).toArray();
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
