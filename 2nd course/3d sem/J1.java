import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class J1 {
    public static class Scanner {
        String inputName;
        String charSet;
        BufferedReader input;
        char[] buffer = new char[1024];
        int quantity = 0;
        int position = 0;

        public Scanner(String inputName) {
            try {
                input = new BufferedReader(new StringReader(inputName));
                quantity = input.read(buffer, 0, buffer.length);
            } catch (IOException e) {
                System.out.println("Reading error: " + e.getMessage());
            }
        }

        public Scanner(InputStream inputName) {
            try {
                input = new BufferedReader(new InputStreamReader(
                        inputName, "UTF-8"));
                quantity = input.read(buffer, 0, buffer.length);
            } catch (IOException e) {
                System.out.println("Reading error: " + e.getMessage());
            }
        }

        private void rebuffering() {
            try {
                quantity = input.read(buffer, 0, buffer.length);
                position = 0;
            } catch (IOException e) {
                System.out.println("Reading error: " + e.getMessage());
            }
        }

        public String next() {
            boolean inNext = false;
            StringBuilder nextString = new StringBuilder();
            while (quantity > 0) {
                while (position < quantity) {
                    if (!Character.isWhitespace(buffer[position])) {
                        inNext = true;
                        nextString.append(buffer[position]);
                    } else {
                        if (inNext) {
                            return nextString.toString();
                        }
                    }
                    position++;
                }
                rebuffering();
            }
            return nextString.toString();
        }

        public String nextWord() {
            StringBuilder nextWord = new StringBuilder();
            boolean inNextWord = false;
            while (quantity > 0) {
                while (position < quantity) {
                    if (Character.isLetter(buffer[position]) || buffer[position] == '\''
                            || Character.getType(buffer[position]) == Character.DASH_PUNCTUATION) {
                        inNextWord = true;
                        nextWord.append(buffer[position]);
                        position++;
                    } else {
                        position++;
                        if (inNextWord) {
                            return nextWord.toString();
                        }
                    }
                }
                rebuffering();
            }
            return nextWord.toString();
        }

        public String nextLine() {
            StringBuilder nextLine = new StringBuilder();
            while (quantity > 0) {
                while (position < quantity) {
                    if (!(buffer[position] == System.lineSeparator().charAt(System.lineSeparator().length() - 1))) {
                        nextLine.append(buffer[position]);
                    } else {
                        position++;
                        return nextLine.delete(nextLine.length() - System.lineSeparator().length() + 1, nextLine.length()).toString();
                    }
                    position++;
                }
                rebuffering();
            }
            return nextLine.toString();
        }

        public boolean hasNext() {
            while (quantity > 0) {
                while (position < quantity) {
                    if (!Character.isWhitespace(buffer[position])) {
                        return true;
                    }
                    position++;
                }
                rebuffering();
            }
            return false;
        }

        public int nextInt() {
            String nextWord = next();
            do {
                try {
                    int nextWordInt = Integer.parseInt(nextWord);
                    return nextWordInt;
                } catch (NumberFormatException e) {
                    nextWord = next();
                }
            }
            while (hasNext());
            return 0;
        }

        public int nextIntabc() {
            String nextWord = next();
            int nextabc = 0;
            for (int i = 0; i < nextWord.length(); i++) {
                nextabc = nextabc * 10 + (int) nextWord.charAt(i) - (int) 'a';
            }
            return nextabc;
        }

        public boolean hasNextLine() {
            if (position >= quantity & quantity < buffer.length) {
                return false;
            }
            return true;
        }

        public boolean hasNextInt() {
            while (quantity > 0) {
                while (position < quantity) {
                    if (Character.isDigit(buffer[position]) | buffer[position] == '+' | buffer[position] == '-') {
                        return true;
                    }
                    position++;
                }
                rebuffering();
            }
            return false;
        }

        public boolean hasNextWord() {
            while (quantity > 0) {
                while (position < quantity) {
                    if (Character.isLetter(buffer[position]) || buffer[position] == '\''
                            || Character.getType(buffer[position]) == Character.DASH_PUNCTUATION) {
                        return true;
                    }
                    position++;
                }
                rebuffering();
            }
            return false;
        }

        public void close() {
            try {
                input.close();
            } catch (IOException e) {
                System.out.println("Scanner have been already closed " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = scanner.nextInt();
        }
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            g[i] = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                g[i].add(scanner.nextInt());
            }
        }
        long[] fullTime = new long[n];
        Arrays.fill(fullTime, -1);
        Stack<Integer> stack = new Stack<>();
        long answer = dfs(0, g, fullTime, stack, p);
        System.out.println(answer + " " + stack.size());
        StringBuilder ans = new StringBuilder();
        for (int i : stack) {
            ans.append(i).append(" ");
        }
        System.out.println(ans);
    }
    public static long dfs(int num, ArrayList<Integer>[] graph, long[] dp, Stack<Integer> stack, int[] p) {
        if (dp[num] != -1) {
            return 0;
        }
        long res = 0;
        for (int i : graph[num]) {
            res += dfs(i - 1, graph, dp, stack, p);
        }
        res += p[num];
        dp[num] = res;
        stack.push(num + 1);
        return res;
    }
}