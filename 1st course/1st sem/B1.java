import java.util.Scanner;

public class B1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        a[0] = scanner.nextLong();
        int k = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            a[i] = (1103515245 * a[i - 1] + 12345) % (long) Math.pow(2, 31);
        }
        System.out.println(statistic(a, k));
    }
    static int partition(long[] a, int l, int r) {
        long v = a[l];
        int i = l;
        int j = r;
        while (i <= j) {
            while (a[i] < v) {
                i++;
            }
            while (a[j] > v) {
                j--;
            }
            if (i >= j) {
                break;
            }
            long temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return j;
    }
    static long statistic(long[] a, int k) {
        int left = 0;
        int right = a.length - 1;
        while (true) {
            int mid = partition(a, left, right);
            if (mid == k) {
                return a[mid];
            } else if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }
}