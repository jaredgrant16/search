package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	
	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
        		// TODO
		Queue<T> path = new LinkedList<T>();
		List<T> preList = new ArrayList<T>();
		List<T> list = new ArrayList<T>();
		T pointer = null;
		T inState = searchProblem.getInitialState();
		
		path.add(inState);
		while(!path.isEmpty()) {
			T state = path.remove();
			
			if(searchProblem.isGoal(state)) { 
				pointer  = state;
				break;
			}
			
			visited.add(state);
			
			for(T next : searchProblem.getSuccessors(state)) {
				if(!visited.contains(next)) {
					visited.add(next);
					path.add(next);
					preList.add(state);
					list.add(next);
				}
			}
		}
		List<T> solution = new ArrayList<T>();
		solution.add(pointer);
		while(!pointer.equals(inState)) {
			solution.add(preList.get(list.indexOf(pointer)));
			pointer = preList.get(list.indexOf(pointer));
		}
		Collections.reverse(solution);
        return solution;
        		
	}
}

