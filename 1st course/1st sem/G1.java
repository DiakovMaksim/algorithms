import java.util.Scanner;

public class G1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int p = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = n - 1; i >= 0; i--) {
            siftDown(a, i, n);
        }
        long answer = 0;
        while (a[0] > 0 && m > 0) {
            if (a[0] >= p) {
                answer += p;
                a[0] -= p;
            } else {
                answer += a[0];
                a[0] = 0;
            }
            siftDown(a, 0, n);
            m--;
        }
        System.out.println(answer);
    }
    static void siftDown(int[] heap, int i, int size) {
        while (2 * i + 1 < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int j = left;
            if (right < size && heap[right] > heap[left]) {
                j = right;
            }
            if (heap[i] > heap[j]) {
                break;
            }
            heap[i] += heap[j];
            heap[j] = heap[i] - heap[j];
            heap[i] -= heap[j];
            i = j;
        }
    }
}