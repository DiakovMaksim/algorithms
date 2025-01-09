import java.io.*;
import java.util.ArrayList;

public class D1 {
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
        int m = scanner.nextInt();
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            g[a].add(b);
        }
        int[] mark = new int[n + 1];
        ArrayList<Integer>[] ans = new ArrayList[n + 1];
        int color = 1;
        for (int i = 1; i <= n; i++) {
            if (mark[i] == 0) {
                ans[color] = new ArrayList<>();
                if (dfs(i, mark, g, ans[color])) {
                    return;
                }
                color++;
            }
        }
        System.out.println(-1);
    }

    public static boolean dfs(int num, int[] mark, ArrayList<Integer>[] graph, ArrayList<Integer> res) {
        mark[num] = 1;
        res.add(num);
        for (int i : graph[num]) {
            if (mark[i] == 1) {
                StringBuilder ans = new StringBuilder();
                boolean flag = false;
                int counter = 0;
                for (int a : res) {
                    if (a == i) {
                        flag = true;
                    }
                    if (flag) {
                        counter++;
                        ans.append(a).append(" ");
                    }
                }
                System.out.println(counter);
                System.out.println(ans);
                return true;
            }
            if (mark[i] == 0) {
                if (dfs(i, mark, graph, new ArrayList<>(res))) {
                    return true;
                }
            }
        }
        mark[num] = 2;
        return false;
    }
}