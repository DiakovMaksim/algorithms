import java.util.Scanner;

public class B3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] first = new int[n];
        for (int i = 0; i < n; i++) {
            first[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int l = leftBinarySearch(first, scanner.nextInt());
            if (l > -1) {
                System.out.println((l + 1) + " " + (rightBinarySearch(first, first[l]) + 1));
            } else {
                System.out.println(0);
            }
        }
    }
    static int leftBinarySearch(int[] a, int key) {
        int l = 0;
        int r = a.length - 1;
        while (l < r - 1) {
            int m = (l + r) / 2;
            if (a[m] < key) {
                l = m;
            } else {
                r = m;
            }
        }
        if (a[l] == key) {
            return l;
        }
        if (a[r] == key) {
            return r;
        }
        return -1;
    }
    static int rightBinarySearch(int[] a, int key) {
        int l = 0;
        int r = a.length - 1;
        while (l < r - 1) {
            int m = (l + r) / 2;
            if (a[m] <= key) {
                l = m;
            } else {
                r = m;
            }
        }
        if (a[r] == key) {
            return r;
        }
        if (a[l] == key) {
            return l;
        }
        return -1;
    }
}