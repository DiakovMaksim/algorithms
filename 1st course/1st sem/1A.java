import java.util.Arrays;
import java.util.Scanner;

public class A1 {
    static void sort(int[] p) {
        if (p.length == 1) {
            return;
        }
        int[] l = Arrays.copyOf(p,  p.length / 2);
        sort(l);
        int[] r = Arrays.copyOfRange(p, p.length / 2, p.length);
        sort(r);
        int i = 0;
        int j = 0;
        while (i + j < p.length) {
            if (i < l.length) {
                if (j < r.length) {
                    if (l[i] < r[j]) {
                        p[i + j] = l[i];
                        i++;
                    } else {
                        p[i + j] = r[j];
                        j++;
                    }
                } else {
                    p[i + j] = l[i];
                    i++;
                }
            } else {
                p[i + j] = r[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        sort(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
