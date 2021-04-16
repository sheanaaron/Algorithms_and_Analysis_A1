import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class BreadthFirstSearch {
    public Set<Integer> BFS (AdjacencyList graph, int root){
        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int vertex = queue.poll();
            if(!visited.contains(vertex)){
                visited.add(vertex);
                for(Integer temp : graph.getEdge(vertex)){
                    queue.offer(temp);
                }
            }
        }
        return visited;
    }
}