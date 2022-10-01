public class Main {
    public static void main(String[] args) {
        System.out.println(sumRecur(10));
        System.out.println(sumRecur(100));
        System.out.println(sumRecur(1000));
        System.out.println(sumRecur(10000));
    }

    private static int sumRecur(int n) {
        return n <= 1 ? n : n + sumRecur(n - 1);
    }
}
