package BAB2;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class UniformCostSearch {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Node T = new Node("T");
        Node U = new Node("U");
        Node S = new Node("S");
        Node V = new Node("V");
        Node R = new Node("R");
        Node A = new Node("A");
        Node X = new Node("X");
        Node Q = new Node("Q");
        Node I = new Node("I");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node J = new Node("J");
        Node K = new Node("K");
        Node E = new Node("E");
        Node M = new Node("M");
        Node L = new Node("L");
        Node N = new Node("N");
        Node P = new Node("P");
        Node O = new Node("O");
        Node F = new Node("F");
        Node G = new Node("G");
        Node H = new Node("H");

        T.adjacencies = new Edge[]{
            new Edge(U, 75),
            new Edge(V, 25),
            new Edge(S, 112)

        };
        U.adjacencies = new Edge[]{
            new Edge(T, 75),
            new Edge(V, 85)
        };
        S.adjacencies = new Edge[]{
            new Edge(T, 112),
            new Edge(R, 93)
        };
        V.adjacencies = new Edge[]{
            new Edge(U, 85),
            new Edge(T, 25),
            new Edge(A, 167)
        };
        R.adjacencies = new Edge[]{
            new Edge(S, 93),
            new Edge(X, 57)
        };
        A.adjacencies = new Edge[]{
            new Edge(V, 167),
            new Edge(B, 145),
            new Edge(I, 148),
            new Edge(X, 55)
        };
        X.adjacencies = new Edge[]{
            new Edge(R, 57),
            new Edge(A, 55),
            new Edge(Q, 30)
        };
        Q.adjacencies = new Edge[]{
            new Edge(X, 30),
            new Edge(I, 25)
        };
        I.adjacencies = new Edge[]{
            new Edge(A, 148),
            new Edge(Q, 25),
            new Edge(J, 75),};
        B.adjacencies = new Edge[]{
            new Edge(A, 145),
            new Edge(C, 98)
        };
        C.adjacencies = new Edge[]{
            new Edge(B, 98),
            new Edge(D, 212)
        };
        D.adjacencies = new Edge[]{
            new Edge(C, 212),
            new Edge(K, 102),
            new Edge(E, 102)
        };
        J.adjacencies = new Edge[]{
            new Edge(I, 75),
            new Edge(K, 95)
        };
        K.adjacencies = new Edge[]{
            new Edge(J, 95),
            new Edge(D, 102)
        };
        E.adjacencies = new Edge[]{
            new Edge(D, 102),
            new Edge(M, 73),
            new Edge(N, 97),
            new Edge(F, 152),
            new Edge(L, 95)
        };
        M.adjacencies = new Edge[]{
            new Edge(E, 73)
        };
        L.adjacencies = new Edge[]{
            new Edge(E, 95)
        };
        N.adjacencies = new Edge[]{
            new Edge(E, 97),
            new Edge(P, 65),
            new Edge(O, 25)
        };
        P.adjacencies = new Edge[]{
            new Edge(N, 65)
        };
        O.adjacencies = new Edge[]{
            new Edge(N, 25)
        };
        F.adjacencies = new Edge[]{
            new Edge(E, 152),
            new Edge(G, 83)
        };
        G.adjacencies = new Edge[]{
            new Edge(H, 75),
            new Edge(F, 83)
        };
        H.adjacencies = new Edge[]{
            new Edge(G, 75)
        };
        
        Node Start = I;
        Node Goal = A;

        System.out.println("Dari Kota " + Start + " ke Kota " + Goal);
        UniformCostSearch(Start, Goal);
        List<Node> path = printPath(Goal);

        System.out.println("Rute: " + path);
        System.out.println("Total Jarak:" + total);

    }

    public static void UniformCostSearch(Node source, Node goal) {
        source.pathCost = 0;
        PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>() {
            public int compare(Node i, Node j) {
                if (i.pathCost > j.pathCost) {
                    return 1;
                } else if (i.pathCost < j.pathCost) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
        );

        queue.add(source);
        Set<Node> explored = new HashSet<Node>();
        boolean found = false;

        do {
            Node current = queue.poll();
            explored.add(current);

            if (current.value.equals(goal.value)) {
                found = true;
            }

            for (Edge e : current.adjacencies) {
                Node child = e.target;
                double cost = e.cost;
                child.pathCost = current.pathCost + cost;

                if (!explored.contains(child) && !queue.contains(child)) {
                    child.parent = current;
                    queue.add(child);

                    System.out.print(child + " -->");
                    System.out.println(queue);
                } else if ((queue.contains(child)) && (child.pathCost > current.pathCost)) {
                    child.parent = current;
                    current = child;
                }
            }
        } while (!queue.isEmpty());
    }

    static double total;

    public static List<Node> printPath(Node target) {
        total = 0;
        List<Node> path = new ArrayList<Node>();
        for (Node node = target; node != null; node = node.parent) {
            path.add(node);
            for (int i = 0; i < node.adjacencies.length; i++) {
                if (node.adjacencies[i].target == node.parent) {
                    total += node.adjacencies[i].cost;
                }
            }
        }
        Collections.reverse(path);
        return path;
    }
}

class Node {

    public final String value;
    public double pathCost;
    public Edge[] adjacencies;
    public Node parent;
    
    public Node(String val) {
        value = val;
    }

    public String toString() {
        return value;
    }
}

class Edge {

    public final double cost;
    public final Node target;

    public Edge(Node targetNode, double costVal) {
        cost = costVal;
        target = targetNode;
    }
}