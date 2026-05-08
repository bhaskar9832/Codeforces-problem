import java.io.*;
import java.util.*;

public class FallenLeaves {

    static ArrayList<Integer>[] tree;
    static int[] degree;
    static int[] leafCount;
    static long answer;
    static int totalLeaves;

    static void dfs(int node, int parent) {

        // Original leaf
        if (degree[node] == 1) {
            leafCount[node] = 1;
        }

        for (int next : tree[node]) {

            if (next == parent) continue;

            dfs(next, node);

            leafCount[node] += leafCount[next];

            answer += Math.min(
                    leafCount[next],
                    totalLeaves - leafCount[next]
            );
        }
    }

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner(System.in);

        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {

            int n = fs.nextInt();

            tree = new ArrayList[n + 1];
            degree = new int[n + 1];
            leafCount = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < n - 1; i++) {

                int u = fs.nextInt();
                int v = fs.nextInt();

                tree[u].add(v);
                tree[v].add(u);

                degree[u]++;
                degree[v]++;
            }

            totalLeaves = 0;

            for (int i = 1; i <= n; i++) {
                if (degree[i] == 1) {
                    totalLeaves++;
                }
            }

            answer = 0;

            dfs(1, 0);

            out.append(answer).append('\n');
        }

        System.out.print(out);
    }

    // Fast Scanner
    static class FastScanner {

        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {

            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {

            int c;

            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }
}