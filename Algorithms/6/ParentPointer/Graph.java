import java.util.*;

public class Graph {

    private Set<String> vertices;
    private Set<Edge> edges;

    public Graph () {
        vertices = new TreeSet<String>();
        edges = new HashSet<Edge>();
    }

    public void addVertex(String c) {
        if (!vertices.contains(c))
	    vertices.add(c);
    }

    public void addEdge(String c1, String c2) {
        if (!vertices.contains(c1) || !vertices.contains(c2)) 
	    return;
        edges.add(new Edge(c1, c2));
    }


    public Set<String> vertices() {
	return vertices;
    }

    public Set<Edge> edges() {
	return edges;
    }

    public String toString () {
	String result = "";
        for (String v : vertices)
	    result += v + " ";
        result += "\n";
        for (Edge e : edges)
            result += e + " ";
        return result;
    }
}

class Edge {
    public String v1;
    public String v2;

    public Edge (String v1, String v2) {
	this.v1 = v1;
	this.v2 = v2;
    }

    public String toString() {
	return "(" + v1 + "," + v2 + ")";
    }
}
