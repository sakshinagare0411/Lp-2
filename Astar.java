import java.util.*;

class Astar {
    private static Map<String, List<Node>> graph = new HashMap<>();

    private static class Node {
        String name;
        int cost;

        public Node(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    public static List<String> aStarAlgorithm(String startNode, String stopNode) {
        Set<String> openSet = new HashSet<>();
        Set<String> closedSet = new HashSet<>();
        Map<String, Integer> g = new HashMap<>();
        Map<String, String> parents = new HashMap<>();

        openSet.add(startNode);
        g.put(startNode, 0);
        parents.put(startNode, startNode);

        while (!openSet.isEmpty()) {
            String currentNode = null;
            for (String v : openSet) {
                if (currentNode == null || (g.getOrDefault(v, Integer.MAX_VALUE) + heuristic(v)) < (g.getOrDefault(currentNode, Integer.MAX_VALUE) + heuristic(currentNode))) {
                    currentNode = v;
                }
            }

            if (currentNode.equals(stopNode) || !graph.containsKey(currentNode)) {
                break;
            }

            for (Node neighbor : graph.get(currentNode)) {
                String neighborName = neighbor.name;
                int weight = neighbor.cost;

                int tentativeGScore = g.getOrDefault(currentNode, Integer.MAX_VALUE) + weight;
                if (!openSet.contains(neighborName) && !closedSet.contains(neighborName)) {
                    openSet.add(neighborName);
                } else if (tentativeGScore >= g.getOrDefault(neighborName, Integer.MAX_VALUE)) {
                    continue;
                }

                parents.put(neighborName, currentNode);
                g.put(neighborName, tentativeGScore);
            }

            openSet.remove(currentNode);
            closedSet.add(currentNode);
        }

        if (!parents.containsKey(stopNode)) {
            System.out.println("Path does not exist!");
            return null;
        }

        List<String> path = new ArrayList<>();
        String currentNode = stopNode;
        while (!currentNode.equals(startNode)) {
            path.add(currentNode);
            currentNode = parents.get(currentNode);
        }
        path.add(startNode);
        Collections.reverse(path);

        System.out.println("Path found: " + path);
        return path;
    }

    private static int heuristic(String nodeName) {
        Map<String, Integer> heuristicDistances = new HashMap<>();
        heuristicDistances.put("Arad", 366);
        heuristicDistances.put("Bucharest", 0);
        heuristicDistances.put("Craiova", 160);
        heuristicDistances.put("Dobreta", 242);
        heuristicDistances.put("Eforie", 161);
        heuristicDistances.put("Fagaras", 176);
        heuristicDistances.put("Giurgiu", 77);
        heuristicDistances.put("Hirasova", 151);
        heuristicDistances.put("Iasi", 226);
        heuristicDistances.put("Lugoj", 244);
        heuristicDistances.put("Mehadia", 241);
        heuristicDistances.put("Neamt", 234);
        heuristicDistances.put("Oradea", 380);
        heuristicDistances.put("Pitesti", 100);
        heuristicDistances.put("Rimnicu Vilcea", 193);
        heuristicDistances.put("Sibiu", 253);
        heuristicDistances.put("Timisoara", 329);
        heuristicDistances.put("Urziceni", 80);
        heuristicDistances.put("Vaslui", 199);
        heuristicDistances.put("Zerind", 374);

        return heuristicDistances.getOrDefault(nodeName, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        graph.put("Arad", Arrays.asList(new Node("Sibiu", 140), new Node("Timisoara", 118), new Node("Zerind", 75)));
        graph.put("Bucharest", Arrays.asList(new Node("Fagaras", 211), new Node("Giurgiu", 90), new Node("Pitesti", 101), new Node("Urziceni", 85)));
        graph.put("Craiova", Arrays.asList(new Node("Dobreta", 120), new Node("Pitesti", 138), new Node("Rimnicu Vilcea", 146)));
        // Add other nodes and connections...

        List<String> path = aStarAlgorithm("Rimnicu Vilcea", "Timisoara");
    }
}
