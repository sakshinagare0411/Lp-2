import java.util.Arrays;

public class dijikstra {
    static final int V = 9;

    // Utility function to find the vertex with minimum key value,
    // from the set of vertices not yet included in the shortest path tree
    int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Function to print the constructed distance array
    void printDijkstra(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[parent[i]][i]);
        }
    }

    // Function implementing Dijkstra's algorithm
    void dijkstra(int[][] graph) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);

        key[0] = 0; // Distance of source vertex from itself is always 0
        parent[0] = -1; // Start node has no parent

        // Finding shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum key vertex from the set of vertices
            // not yet included in the shortest path tree
            int u = minKey(key, mstSet);

            // Mark the picked vertex as processed
            mstSet[u] = true;

            // Update key value and parent index of the adjacent vertices
            // of the picked vertex
            for (int v = 0; v < V; v++) {
                // Update key[v] only if it's not in mstSet, there is an edge
                // from u to v, and the weight of the edge is smaller than
                // the current value of key[v]
                if (!mstSet[v] && graph[u][v] != 0 &&
                        key[u] != Integer.MAX_VALUE &&
                        (key[u] + graph[u][v] < key[v])) {
                    parent[v] = u;
                    key[v] = key[u] + graph[u][v];
                }
            }
        }

        printDijkstra(parent, graph);
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        dijikstra da = new dijikstra();
        da.dijkstra(graph);
    }
}


