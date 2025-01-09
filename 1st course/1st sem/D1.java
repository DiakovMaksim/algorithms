import java.util.Arrays;
import java.util.Scanner;

public class D1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] temp = Arrays.copyOf(a, a.length);
        System.out.println(sort(a, temp, 0, a.length - 1));
    }
    static long merge(int[] p, int[] temp, int mid, int l, int r) {
        long inversions = 0;
        int i = l;
        int j = mid + 1;
        int k = l;
        while (i <= mid && j <= r) {
            if (p[i] <= p[j]) {
                temp[k] = p[i];
                k++;
                i++;
            } else {
                temp[k] = p[j];
                k++;
                j++;
                inversions += mid - i + 1;
            }
        }
        while (i <= mid) {
            temp[k] = p[i];
            k++;
            i++;
        }
        for (i = l; i <= r; i++) {
            p[i] = temp[i];
        }
        return inversions;
    }
    static long sort(int[] p, int[] temp, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) / 2;
        long inversions = 0;
        inversions += sort(p, temp, l, mid);
        inversions += sort(p, temp, mid + 1, r);
        inversions += merge(p, temp, mid, l, r);
        return inversions;
    }
}