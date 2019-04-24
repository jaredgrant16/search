package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
        		// TODO
		
		Stack<T> path = new Stack<T>();
		List<T> preList = new ArrayList<T>();
		List<T> list = new ArrayList<T>();
		T pointer = null;
		T inState = searchProblem.getInitialState();
		
		path.push(inState);
		while(!path.isEmpty()) {
			T state = path.pop();
			
			if(searchProblem.isGoal(state)) { 
				pointer  = state;
				break;
			}
			
			visited.add(state);
			
			for(T next : searchProblem.getSuccessors(state)) {
				if(!visited.contains(next)) {
					visited.add(next);
					path.push(next);
					preList.add(state);
					list.add(next);
				}
			}
		}
		Stack<T> solution = new Stack<T>();
		solution.push(pointer);
		while(!pointer.equals(inState)) {
			solution.push(preList.get(list.indexOf(pointer)));
			pointer = preList.get(list.indexOf(pointer));
		}
		
        return solution;
	}
}
