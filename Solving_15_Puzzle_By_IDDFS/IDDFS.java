//This code takes a sequence of 15 puzzle and outputs the steps to reach goal by following Iterative Depth First Deepening search Algorithm
/***********************************************************************
 * Version History
 * 
 * Version_No		Date	  	Reason for Modification
 * 1.0			Sep-25-2015     Initial Version
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//An atmoic structure to denote a state
class State {
	int[] state;
	State parent;
	int depth;
}

public class IDDFS {
	private static final State NULL = null;
	static int[] goal = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 };

	public boolean findPath(int[] state, int depth) {
		int[] statearray;
		HashSet<String> explored = new HashSet<String>();
		Queue<State> toexplore = new LinkedList<State>();
		State start_state = new State();
		State next_state = new State();
		start_state.state = state;
		start_state.parent = null;
		start_state.depth = 1;
		toexplore.add(start_state);
		while (!toexplore.isEmpty()) {
			next_state = toexplore.remove();
			// printState(next_state.state);
			if (next_state.depth > 20)
				continue;
			// System.out.println("level " + next_state.depth);
			explored.add(returnStateString(next_state.state));
			State temp_state = new State();

			// Moving Left and checking if it is goal and adding to toexplore
			// list if it is not a goal and not yet explored
			statearray = moveLeft(next_state.state);
			temp_state = new State();
			temp_state.state = statearray;
			temp_state.parent = next_state;
			temp_state.depth = next_state.depth + 1;
			if (Arrays.equals(statearray, goal)) {
				System.out.println("depth is " + temp_state.depth);
				printPath(temp_state);
				return true;
			}
			if (!explored.contains(returnStateString(statearray)))
				toexplore.add(temp_state);
			// Moving Right and checking if it is goal and adding to toexplore
			// list if it is not a goal and not yet explored
			statearray = moveRight(next_state.state);
			temp_state = new State();
			temp_state.state = statearray;
			temp_state.parent = next_state;
			temp_state.depth = next_state.depth + 1;
			if (Arrays.equals(statearray, goal)) {
				System.out.println("depth is " + temp_state.depth);
				printPath(temp_state);
				return true;
			}
			if (!explored.contains(returnStateString(statearray)))
				toexplore.add(temp_state);

			// Moving Up and checking if it is goal and adding to toexplore list
			// if it is not a goal and not yet explored
			statearray = moveUp(next_state.state);
			temp_state = new State();
			temp_state.state = statearray;
			temp_state.parent = next_state;
			temp_state.depth = next_state.depth + 1;
			if (Arrays.equals(statearray, goal)) {
				System.out.println("depth is " + temp_state.depth);
				printPath(temp_state);
				return true;
			}
			if (!explored.contains(returnStateString(statearray)))
				toexplore.add(temp_state);
			// Moving Down and checking if it is goal and adding to toexplore
			// list if it is not a goal and not yet explored
			statearray = moveDown(next_state.state);
			temp_state = new State();
			temp_state.state = statearray;
			temp_state.parent = next_state;
			temp_state.depth = next_state.depth + 1;
			if (Arrays.equals(statearray, goal)) {
				System.out.println("depth is " + temp_state.depth);
				printPath(temp_state);
				return true;
			}
			if (!explored.contains(returnStateString(statearray)))
				toexplore.add(temp_state);

		}
		System.out.println("fail " + explored.size());
		return false;
	}

	public String returnStateString(int[] statearray) {
		String state = "";
		for (int i = 0; i <= 15; i++) {
			state = state + statearray[i];
		}
		return state;
	}

	// Method to print step sequence. This path will just build the continuous
	// paths form start state to goal state.
	public void printPath(State curr_state) {
		ArrayList<int[]> path = new ArrayList<int[]>();
		while (curr_state != NULL) {
			path.add(0, curr_state.state);
			curr_state = curr_state.parent;
		}
		for (int[] p : path) {
			printState(p);
		}
	}

	// Method to output state if left action is taken.
	public int[] moveLeft(int[] state) {
		int[] nextstate = new int[15];
		nextstate = Arrays.copyOf(state, state.length);
		int zeroposition = findZeroPosition(state);
		if (zeroposition != 3 && zeroposition != 7 && zeroposition != 11
				&& zeroposition != 15) {

			nextstate[zeroposition] = state[zeroposition + 1];
			nextstate[zeroposition + 1] = 0;
		}
		return nextstate;
	}

	// Method to output state if right action is taken.
	public int[] moveRight(int[] state) {
		int[] nextstate = new int[15];
		nextstate = Arrays.copyOf(state, state.length);
		int zeroposition = findZeroPosition(state);
		if (zeroposition != 0 && zeroposition != 4 && zeroposition != 8
				&& zeroposition != 12) {

			nextstate[zeroposition] = state[zeroposition - 1];
			nextstate[zeroposition - 1] = 0;
		}
		return nextstate;
	}

	// Method to output state if up action is taken.
	public int[] moveUp(int[] state) {
		int[] nextstate = new int[15];
		nextstate = Arrays.copyOf(state, state.length);
		int zeroposition = findZeroPosition(state);
		if (zeroposition != 12 && zeroposition != 13 && zeroposition != 14
				&& zeroposition != 15) {

			nextstate[zeroposition] = state[zeroposition + 4];
			nextstate[zeroposition + 4] = 0;
		}
		return nextstate;
	}

	// Method to output state if down action is taken.
	public int[] moveDown(int[] state) {
		int[] nextstate = new int[15];
		nextstate = Arrays.copyOf(state, state.length);
		int zeroposition = findZeroPosition(state);
		if (zeroposition != 0 && zeroposition != 1 && zeroposition != 2
				&& zeroposition != 3) {

			nextstate[zeroposition] = state[zeroposition - 4];
			nextstate[zeroposition - 4] = 0;
		}
		return nextstate;
	}

	// to find zero position in array
	public int findZeroPosition(int[] statearray) {
		int zeroposition = 0;
		for (int j = 0; j <= 15; j += 1) {
			if (statearray[j] == 0)
				zeroposition = j;
		}
		return zeroposition;
	}

	// printing state
	public void printState(int[] statearray) {
		String state = "";
		int index = 0, previous;
		for (int i = 0; i <= 3; i++) {
			previous = 0;
			for (int j = 0; j <= 3; j += 1) {
				if (previous < 10)
					state = state + "  " + statearray[index];
				else
					state = state + " " + statearray[index];
				previous = statearray[index];
				index += 1;
			}
			state = state + "\n";
		}
		System.out.println(state);
	}

	// Iterative Deepening Depth first Search
	public void IterativeDFS(int[] state, int depth) {
		for (int i = 1; i <= depth; i += 1) {
			if (findPath(state, i))
				return;
		}
	}

	// Driver method
	public static void main(String[] args) throws IOException {
		BufferedReader ip = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Give Start State : ");
		String start_state = ip.readLine();
		int state[] = new int[16];
		int index = 0;
		for (String str : start_state.split(",")) {
			state[index] = Integer.parseInt(str.trim());
			index += 1;
		}
		IDDFS bfs = new IDDFS();
		bfs.IterativeDFS(state, 1000);

	}
}
