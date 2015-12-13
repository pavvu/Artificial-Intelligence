//This code takes a sequence of 8X8 puzzle and outputs the steps to reach goal by following Depth First search Algorithm
/***********************************************************************
 * Version History
 * 
 * Version_No		Date	  	Reason for Modification
 * 1.0			Sep-18-2015     Initial Version
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

//import AI_HW2.State;
//An atmoic structure to denote a state
class State {
	String state;
	State parent;
}

public class DFS {
	private static final State NULL = null;
	static String goal = "012345678";

	public void findPath(String state) {
		String statestring;
		HashSet<String> explored = new HashSet<String>();
		Stack<State> toexplore = new Stack<State>();
		State start_state = new State();
		State next_state = new State();
		start_state.state = state;
		start_state.parent = null;
		toexplore.push(start_state);
		while (!toexplore.isEmpty()) {
			next_state = toexplore.pop();
			explored.add(next_state.state);
//Moving Right and checking if it is goal and adding to toexplore list if it is not a goal and not yet explored
			statestring = moveRight(next_state.state);
			State temp_state = new State();
			temp_state.state = statestring;
			temp_state.parent = next_state;
			if (statestring.equals(goal)) {
				printPath(temp_state);
				return;
			}
			if (!explored.contains(statestring)) {

				toexplore.push(temp_state);
			}
//Moving Left and checking if it is goal and adding to toexplore list if it is not a goal and not yet explored
			statestring = moveLeft(next_state.state);
			temp_state = new State();
			temp_state.state = statestring;
			temp_state.parent = next_state;
			if (statestring.equals(goal)) {
				printPath(temp_state);
				return;
			}
			if (!explored.contains(statestring)) {

				toexplore.push(temp_state);
			}
//Moving Up and checking if it is goal and adding to toexplore list if it is not a goal and not yet explored
			statestring = moveUp(next_state.state);
			temp_state = new State();
			temp_state.state = statestring;
			temp_state.parent = next_state;
			if (statestring.equals(goal)) {
				printPath(temp_state);
				return;
			}
			if (!explored.contains(statestring)) {

				toexplore.push(temp_state);
			}
//Moving Down and checking if it is goal and adding to toexplore list if it is not a goal and not yet explored
			statestring = moveDown(next_state.state);
			temp_state = new State();
			temp_state.state = statestring;
			temp_state.parent = next_state;
			if (statestring.equals(goal)) {

				printPath(temp_state);
				return;
			}
			if (!explored.contains(statestring)) {

				toexplore.push(temp_state);
			}

		}
		System.out.println("fail " + explored.size());
	}
// Method to print step sequence. This path will just build the continuous paths form start state to goal state.	
	public void printPath(State curr_state) {
		StringBuilder path= new StringBuilder();
		while (curr_state != NULL) {
			path.insert(0, curr_state.state);
			curr_state = curr_state.parent;
		}
		printPath(path.toString());
	}

// This method takes string built by above method and breaks it in to each steps and sprints states.
	public void printPath(String path) {
		System.out.println("The Path for given sequence is");
		for (String string : path.split("(?<=\\G.{9})")) {
			int index = 0;
			for (int i = 0; i <= 2; i += 1) {
				for (int j = 0; j <= 2; j += 1) {
					System.out.print(string.charAt(index) + " ");
					index += 1;
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("depth is " + path.split("(?<=\\G.{9})").length);
	}
// Method to output state if left action is taken.
	public String moveLeft(String state) {
		String nextstate = state;
		int zeroposition = state.indexOf("0");
		if (zeroposition != 0 && zeroposition != 3 && zeroposition != 6) {
			nextstate = state.substring(0, zeroposition - 1) + "0"
					+ state.charAt(zeroposition - 1)
					+ state.substring(zeroposition + 1);
		}
		return nextstate;
	}
	// Method to output state if left action is taken.
	public String moveRight(String state) {
		String nextstate = state;
		int zeroposition = state.indexOf("0");
		if (zeroposition != 2 && zeroposition != 5 && zeroposition != 8) {
			nextstate = state.substring(0, zeroposition)
					+ state.charAt(zeroposition + 1) + "0"
					+ state.substring(zeroposition + 2);
		}
		return nextstate;
	}
	// Method to output state if left action is taken.
	public String moveUp(String state) {
		String nextstate = state;
		int zeroposition = state.indexOf("0");
		if (zeroposition != 0 && zeroposition != 1 && zeroposition != 2) {

			nextstate = state.substring(0, zeroposition - 3) + "0"
					+ state.substring(zeroposition - 2, zeroposition)
					+ state.charAt(zeroposition - 3)
					+ state.substring(zeroposition + 1);

		}
		return nextstate;
	}
	// Method to output state if left action is taken.
	public String moveDown(String state) {
		String nextstate = state;
		int zeroposition = state.indexOf("0");
		if (zeroposition != 6 && zeroposition != 7 && zeroposition != 8) {
			nextstate = state.substring(0, zeroposition)
					+ state.substring(zeroposition + 3, zeroposition + 4)
					+ state.substring(zeroposition + 1, zeroposition + 3) + "0"
					+ state.substring(zeroposition + 4);
		}
		return nextstate;
	}
// Driver method
	public static void main(String[] args) throws IOException {
		BufferedReader ip = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Give Start State : ");
		String start_state = ip.readLine();
		DFS bfs = new DFS();
		bfs.findPath(start_state);

	}
}
