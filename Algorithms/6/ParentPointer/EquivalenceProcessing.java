import java.io.*;
import java.util.*;

public class EquivalenceProcessing {

    public static void main (String [] args) throws Exception {
	Graph g = readGraph(args[0]);
        System.out.println(g);
	ParPtrTree<String>tree  = buildInitialTree(g);        
	System.out.println(tree);
        connect(tree, g);   
        System.out.println(tree);
    }

    public static Graph readGraph(String fileName)  throws Exception{
        Scanner sc = new Scanner(new File(fileName));
        Scanner vScan = new Scanner(sc.nextLine());

        Graph g = new Graph();
	// read the vertices from the first line
        while (vScan.hasNext()) {
	    String vertex = vScan.next();
            g.addVertex(vertex);
	}
        // read the edges, one per line
        while (sc.hasNextLine()) {
            vScan = new Scanner(sc.nextLine());
            String v1 = vScan.next();
            String v2 = vScan.next();
            g.addEdge(v1, v2);
	}
        return g;
    }

    public static ParPtrTree<String> buildInitialTree(Graph g) {
        Set<String> vertices = g.vertices();
        ParPtrTree<String> tree = 
                   new ParPtrTree(vertices.size());
        for (String v : vertices) {
            tree.addNode(v, null);
	}
        return tree;
    }

    public static void connect(ParPtrTree<String> t, Graph g) {
	Set<Edge> edges = g.edges();
        for (Edge e: edges) {
            System.out.println("processing edge " + e);
            Integer vertexPtr1 = t.getIndex(e.v1);
            Integer vertexPtr2 = t.getIndex(e.v2);
            t.UNION(vertexPtr1, vertexPtr2);
	}
    }
}
