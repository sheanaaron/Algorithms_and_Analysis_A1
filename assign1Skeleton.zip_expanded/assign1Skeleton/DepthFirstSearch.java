//import java.util.LinkedHashSet;
//import java.util.Set;
//import java.util.Stack;
//
//public class DepthFirstSearch {
//    public Set<Integer> dfs(AdjacencyList graph, int root){
//        Set<Integer> visited = new LinkedHashSet<>();
//        Stack<Integer> stack = new Stack<>();
//        stack.add(root);
//        while(!stack.isEmpty()){
//            int vertex = stack.pop();
//            if(!visited.contains(vertex)){
//                visited.add(vertex);
//                for(Integer node : graph.get(vertex)){
//                    stack.push(node);
//                }
//            }
//        }
//        return visited;
//    }
//}