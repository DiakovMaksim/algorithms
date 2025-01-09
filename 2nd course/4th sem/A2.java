import java.util.*;

public class A2 {
    static final int MAXN = 501; // максимально возможное число вершин во входном графе

    static int n, m;
    static ArrayList<Integer>[] g = new ArrayList[MAXN];
    static int[] match = new int[MAXN];
    static int[] p = new int[MAXN];
    static int[] base = new int[MAXN];
    static int[] q = new int[MAXN];
    static boolean[] used = new boolean[MAXN];
    static boolean[] blossom = new boolean[MAXN];

    static int lca(int a, int b) {
        boolean[] used = new boolean[MAXN];
        // поднимаемся от вершины a до корня, помечая все чётные вершины
        for (;;) {
            a = base[a];
            used[a] = true;
            if (match[a] == -1) {
                break; // дошли до корня
            }
            a = p[match[a]];
        }
        // поднимаемся от вершины b, пока не найдём помеченную вершину
        for (;;) {
            b = base[b];
            if (used[b]) {
                return b;
            }
            b = p[match[b]];
        }
    }

    static void markPath(int v, int b, int children) {
        while (base[v] != b) {
            blossom[base[v]] = blossom[base[match[v]]] = true;
            p[v] = children;
            children = match[v];
            v = p[match[v]];
        }
    }

    static int findPath(int root) {
        Arrays.fill(used, false);
        Arrays.fill(p, -1);
        for (int i = 0; i <= n; ++i) {
            base[i] = i;
        }

        used[root] = true;
        int qh = 0, qt = 0;
        q[qt++] = root;
        while (qh < qt) {
            int v = q[qh++];
            for (int to : g[v]) {
                if (base[v] == base[to] || match[v] == to) {
                    continue;
                }
                if (to == root || (match[to] != -1 && p[match[to]] != -1)) {
                    int curbase = lca(v, to);
                    Arrays.fill(blossom, false);
                    markPath(v, curbase, to);
                    markPath(to, curbase, v);
                    for (int i = 0; i <= n; ++i) {
                        if (blossom[base[i]]) {
                            base[i] = curbase;
                            if (!used[i]) {
                                used[i] = true;
                                q[qt++] = i;
                            }
                        }
                    }
                } else if (p[to] == -1) {
                    p[to] = v;
                    if (match[to] == -1) {
                        return to;
                    }
                    to = match[to];
                    used[to] = true;
                    q[qt++] = to;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g[u].add(v);
            g[v].add(u);
        }
        Arrays.fill(match, -1);
        for (int i = 0; i <= n; ++i) {
            if (match[i] == -1) {
                for (int j : g[i]) {
                    if (match[j] == -1) {
                        match[j] = i;
                        match[i] = j;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i <= n; ++i) {
            if (match[i] == -1) {
                int v = findPath(i);
                while (v != -1) {
                    int pv = p[v], ppv = match[pv];
                    match[v] = pv;
                    match[pv] = v;
                    v = ppv;
                }
            }
        }
        boolean[] get = new boolean[MAXN];
        int result = 0;
        ArrayList<int[]> answer = new ArrayList<>();
        for (int i = 0; i < MAXN; ++i) {
            if (match[i] != -1) {
                if (!get[i]) {

                    result++;
                    get[i] = true;
                    get[match[i]] = true;
                    answer.add(new int[]{i, match[i]});
                }
            }
        }
        System.out.println(result);
        for (int[] pair : answer) {
            System.out.println(pair[0] + " " + pair[1]);
        }
        scanner.close();
    }
}
