package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	List<Integer> inState;
	public EightPuzzle(List<Integer> startingValues) {
		if(startingValues.size() != 9 || startingValues == null)throw new IllegalArgumentException();
		for(int i = 0; i< 9; i++) {
			if(!startingValues.contains(i))throw new IllegalArgumentException();
		}
		inState = startingValues;
	}

	@Override
	public List<Integer> getInitialState() {
		// TODO
		
		return inState;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
		List<List<Integer>> successors = new ArrayList<List<Integer>>();
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		List<Integer> list4 = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			list1.add(currentState.get(i));
			list2.add(currentState.get(i));
			list3.add(currentState.get(i));
			list4.add(currentState.get(i));
		}
		int emptyIndex = currentState.indexOf(0);

		// check indexSpace to set up and down to 0 preemtively
		int left = emptyIndex - 1;
		int right = emptyIndex + 1;
		int up = emptyIndex - 3;
		int down = emptyIndex + 3;

		if (emptyIndex == 3 || emptyIndex == 6)
			left = -1;
		if (emptyIndex == 2 || emptyIndex == 5 || emptyIndex == 8)
			right = -1;

		if (left >= 0 && left <= 7) {
			list1.set(emptyIndex, list1.get(left));
			list1.set(left, 0);
			successors.add(list1);
		}
		if (right >= 1 && right <= 8) {
			list2.set(emptyIndex, list2.get(right));
			list2.set(right, 0);
			successors.add(list2);
		}

		if (up >= 0 && up <= 5) {
			list3.set(emptyIndex, list3.get(up));
			list3.set(up, 0);
			successors.add(list3);
		}
		if (down >= 3 && down <= 8) {
			list4.set(emptyIndex, list4.get(down));
			list4.set(down, 0);
			successors.add(list4);
		}

		return successors;
	}


	@Override
	public boolean isGoal(List<Integer> state) {
		// TODO
		if (state.size() != 9 || state.indexOf(0) == -1)
			return false;
		List<Integer> solution = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 0}); 
		int in = 0;
		for (Integer i : state) {
			if (i != solution.get(in))
				return false;
			in++;
		}
		return true;
		
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}
