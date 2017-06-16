import java.util.*;
import java.io.*;

public class MST {

    public static void main (String [] args) throws FileNotFoundException {

	// open the file and get the graph, g
        Scanner sc = new Scanner (new File(args[0]));
        Graph g = new Graph(sc);


        // get a minimum spanning tree of g using Prim's MST alg.
	Graph mst = Prim.getMST(g);
	// save the MST in a file
	fileGraph(mst, "pmst");
	// report its total weight
	System.out.println("Total Weight: " + totalWeight(mst.edges()));

	// mark all vertices as unvisited
        g.clear();


        // get a minimum spanning tree of g using Prim's MST alg.
	Graph mst2 = Kruskal.getMST(g);
	// save the MST in a file
	fileGraph(mst2, "kmst");
	// report its total weight
	System.out.println("Total Weight: " + totalWeight(mst2.edges()));

    }  

    public static void fileGraph(Graph g, String ext)
                              throws FileNotFoundException {
	Vertex [] va = new Vertex[g.vertices.size()];
	for (int i = 0; i < va.length; i++)
	    va[i] = g.vertices.get(i);
	Arrays.sort(va);

	Edge [] ea = new Edge[g.edges.size()];
	for (int i =0; i < ea.length; i++)
	    ea[i] = g.edges.get(i);
	Arrays.sort(ea);


	PrintStream ps =
	    new PrintStream (new File(g.vertices().size() + "."+ ext));
	for (int i =0; i < va.length; i++)
	    ps.print(va[i] + " ");
	ps.println();
	for (int i =0; i < ea.length; i++){
            Edge e = ea[i];
	    ps.println(e.source + " " + e.dest + " " + e.weight);
	}
    }

    public static int totalWeight(ArrayList<Edge> edges) {
	int sum = 0;
	for (Edge e: edges)
	    sum += e.weight;
	return sum;
    }

}
