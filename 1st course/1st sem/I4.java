import java.util.*;

public class I4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Deque<Long> queue = new ArrayDeque<>(n);
        Deque<Long> agression = new ArrayDeque<>(n);
        long firstAgression = 0;
        long sum = 0;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int type = scanner.nextInt();
            switch (type) {
                case (1) -> {
                    queue.add(scanner.nextLong());
                    agression.add(0L);
                }
                case (2) -> {
                    firstAgression += scanner.nextInt();
                    int y = scanner.nextInt();
                    sum += y;
                    agression.add(agression.removeLast() + y);
                    firstAgression -= y;
                }
                case (3) -> {
                    answer.append(sum + firstAgression + queue.poll()).append(System.lineSeparator());
                    sum -= agression.poll();
                    firstAgression = 0;
                }
            }
        }
        System.out.println(answer);
    }
}