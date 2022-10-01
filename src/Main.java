public class Main {
    public static void main(String[] args) {
        System.out.println(sum(10));
        System.out.println(sum(100));
        System.out.println(sum(1000));
        System.out.println(sum(10000));
        System.out.println(sum(100000));
    }

    private static int sum(int n) {
        return sumTailRecur(n, 0);
    }

    private static int sumTailRecur(int n, int accumulated) {
        return n <= 0 ? accumulated : sumTailRecur(n - 1, n + accumulated);
    }
}
