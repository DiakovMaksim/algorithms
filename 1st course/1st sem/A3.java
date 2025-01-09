import java.util.Scanner;

public class A3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] first = new int[n];
        for (int i = 0; i < n; i++) {
            first[i] = scanner.nextInt();
        }
        for (int i = 0; i < k; i++) {
            if (binarySearch(first, scanner.nextInt())) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    static boolean binarySearch(int[] a, int key) {
        int l = 0;
        int r = a.length - 1;
        while (l < r - 1) {
            int m = (l + r) / 2;
            if (a[m] == key) {
                return true;
            }
            if (a[m] < key) {
                l = m;
            } else {
                r = m;
            }
        }
        return a[l] == key || a[r] == key;
    }
}