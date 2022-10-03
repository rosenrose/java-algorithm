import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int[] arr = createRandomArray();
            bubbleSort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    private static int[] createRandomArray() {
        Random rand = new Random();
        return IntStream.of(new int[rand.nextInt(3, 11)]).map(i -> rand.nextInt(31)).toArray();
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
}
