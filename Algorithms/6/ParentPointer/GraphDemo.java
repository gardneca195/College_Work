public class GraphDemo {

    public static void main (String [] args) {
	Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("H");
        g.addVertex("E");
        g.addVertex("D");
        g.addVertex("F");
        g.addVertex("G");
        g.addVertex("I");
        g.addVertex("J");
        g.addEdge("A", "C");
        g.addEdge("A", "B");
        g.addEdge("A", "H");
        g.addEdge("C", "H");
        g.addEdge("B", "H");
        g.addEdge("H", "E");
        g.addEdge("E", "D");
        g.addEdge("E", "F");
        g.addEdge("E", "G");
        g.addEdge("D", "F");
        g.addEdge("F", "G");
        g.addEdge("F", "I");

        System.out.println(g);
    }
}
