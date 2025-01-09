import java.util.Scanner;

public class H1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.next();
        }
        for (int i = m - 1; i >= m - k; i--) {
            String[] b = new String[n];
            int[] c = new int[100000];
            for (int j = 0; j < n; j++) {
                c[array[j].charAt(i)]++;
            }
            int count = 0;
            for (int j = 0; j < c.length; j++) {
                count += c[j];
                c[j] = count - c[j];
            }
            for (int j = 0; j < n; j++) {
                int temp = array[j].charAt(i);
                b[c[temp]] = array[j];
                c[temp]++;
            }
            array = b;
        }
        for (String i : array) {
            System.out.println(i);
        }
    }
}