import java.util.*;

public class krushkal {
    static final int V = 5;

    private int[] parent;

    public krushkal() {
        parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i; // Initialize parent array where each node is its own parent
        }
    }

    private int find(int i) {
        // Find the root of the subset containing node i
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    private void union(int i, int j) {
        // Union operation to merge two subsets (i and j)
        int rootI = find(i);
        int rootJ = find(j);
        parent[rootI] = rootJ; // Set the root of subset i to be under subset j
    }

    public void kruskalMST(int[][] cost) {
        int minCost = 0;
        List<int[]> mstEdges = new ArrayList<>();

        // Priority queue to store edges sorted by weight
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        // Add all edges to the priority queue
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (cost[i][j] != Integer.MAX_VALUE) {
                    pq.offer(new int[]{i, j, cost[i][j]});
                }
            }
        }

        int edgeCount = 0;
        while (edgeCount < V - 1 && !pq.isEmpty()) {
            int[] edge = pq.poll();
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            if (find(u) != find(v)) {
                union(u, v);
                mstEdges.add(new int[]{u, v, weight});
                minCost += weight;
                edgeCount++;
            }
        }

        if (edgeCount == V - 1) {
            System.out.println("Minimum Spanning Tree (MST) Edges:");
            for (int[] edge : mstEdges) {
                System.out.println("Edge " + edge[0] + " - " + edge[1] + " \tWeight: " + edge[2]);
            }
            System.out.println("Total Minimum Cost: " + minCost);
        } else {
            System.out.println("No MST found (Graph is disconnected or invalid).");
        }
    }

    public static void main(String[] args) {
        int[][] cost = {
            {Integer.MAX_VALUE, 2, Integer.MAX_VALUE, 6, Integer.MAX_VALUE},
            {2, Integer.MAX_VALUE, 3, 8, 5},
            {Integer.MAX_VALUE, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 7},
            {6, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 9},
            {Integer.MAX_VALUE, 5, 7, 9, Integer.MAX_VALUE}
        };

        krushkal kruskal = new krushkal();
        kruskal.kruskalMST(cost);
    }
}
