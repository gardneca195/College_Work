import java.util.*;


public class Prim{


		public static Graph getMST(Graph G){
			G.getVertex(0).visited = true;
			// Make treeSets
			Set<Vertex> vertexes = new TreeSet<Vertex>();
			vertexes.add(G.getVertex(0));
			Set<Edge> edges = new TreeSet<Edge>();
			
			while(vertexes.size() < G.vertices.size()){
				

				ArrayList<Edge> edgeOut = new ArrayList<Edge>();
				for(Vertex vertex : vertexes){
					ArrayList<Edge> array = G.edges(vertex);
					for(int i = 0; i< array.size(); i++){
						if(!array.get(i).dest.visited)
							edgeOut.add(array.get(i));	
					}
				}
				
				Edge [] temp = new Edge[edgeOut.size()];
				edgeOut.toArray(temp);
				MinHeap<Edge> heap = new MinHeap(temp, temp.length,
															   temp.length);
				Edge edge = heap.removeMin();
				Vertex dest = edge.dest;
				dest.visited = true;
				vertexes.add(dest);
				edges.add(edge);		
			}		
			ArrayList<Vertex> Vertex = new ArrayList<Vertex>();
			ArrayList<Edge> Edge = new ArrayList<Edge>();
			for(Vertex vertex: vertexes)
				Vertex.add(vertex);
			for(Edge e :edges)
				Edge.add(e);
			Graph n = new Graph(Vertex,Edge);
			return n;
			
		}



}