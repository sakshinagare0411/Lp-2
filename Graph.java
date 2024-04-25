import java.util.*;

class Graph {
    private int[][] adjacencyMatrix;
    private int vertices;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyMatrix = new int[vertices][vertices];
    }

    public void addEdge(int src, int dest) {
        adjacencyMatrix[src][dest] = 1;
        adjacencyMatrix[dest][src] = 1; // Assuming undirected graph
    }

    public void display() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS traversal starting from vertex " + start + ": ");
        dfsUtil(start, visited);
        System.out.println();
    }

    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int i = 0; i < vertices; i++) {
            if (adjacencyMatrix[v][i] == 1 && !visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        System.out.print("BFS traversal starting from vertex " + start + ": ");

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");
            for (int i = 0; i < vertices; i++) {
                if (adjacencyMatrix[v][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int n = scanner.nextInt();

        Graph graph = new Graph(n);

        System.out.println("Enter edges (vertex pairs, -1 to stop):");
        while (true) {
            int src = scanner.nextInt();
            if (src == -1) break;
            int dest = scanner.nextInt();
            graph.addEdge(src, dest);
        }

        graph.display();

        System.out.print("Enter the starting vertex for DFS: ");
        int startDFS = scanner.nextInt();
        graph.dfs(startDFS);

        System.out.print("Enter the starting vertex for BFS: ");
        int startBFS = scanner.nextInt();
        graph.bfs(startBFS);

        scanner.close();
    }
}
