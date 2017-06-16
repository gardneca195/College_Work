import java.util.*;
public class Kruskal{


	public static Graph getMST(Graph g){		
		Set<Set<Vertex >> hs = new HashSet<Set<Vertex>>();

		for(int i = 0; i <g.vertices.size(); i++){
			Set<Vertex> s = new HashSet<Vertex>();
			s.add(g.vertices.get(i));
			hs.add(s);	
		}
		
		// Sort the Edges

		MinHeap<Edge> heap = new MinHeap<Edge>(g.edges.size());
		for(int i = 0; i < g.edges.size(); i++)
			heap.insert(g.edges.get(i));

		// Store edges to process in new graph
			
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		/* While all subsets have not merged */
		while(hs.size() > 1){
			Edge edge = heap.removeMin();

			Set<Vertex> first = find(hs,edge.source);
			Set<Vertex> second = find(hs, edge.dest);
			
			Set<Vertex> cross = new HashSet<Vertex>(first);
			cross.retainAll(second);
		
			if(cross.isEmpty()){
				Set<Vertex> join = new HashSet<Vertex>(first);
				join.addAll(second);
				hs.remove(first);
				hs.remove(second);
				hs.add(join);
				edges.add(edge);
			}
			
		}
		
		return new Graph(g.vertices, edges);		
	}

	public static Set<Vertex> find(Set<Set<Vertex>> hs, Vertex vertex){
		Set<Vertex> set = new HashSet<Vertex>();
		for(Set<Vertex> innerSet: hs)
			if(innerSet.contains(vertex))
				return innerSet;
		return set;
	}

}
	