// This code takes a sequence of 8X8 puzzle and outputs the steps to reach gaol by followinh
// Breadth First Search Algorithm.
/***********************************************************************
 * Version History
 * 
 * Version_No		Date	  	Reason for Modification
 * 1.0			27-Nov-2015     Initial Version
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//import AI_HW2.State;
// An atmoic structure to denote a state
class PuzzleState {
	String state;
	String pathToThisState = "";
}

public class BFS {
	static String goal = "012345678";

	public void findPath(String state) {
		String statestring;
		HashSet<String> explored = new HashSet<String>();
		Queue<PuzzleState> toexplore = new LinkedList<PuzzleState>();
		PuzzleState start_state = new PuzzleState();
		PuzzleState next_state = new PuzzleState();
		start_state.state = state;
		start_state.pathToThisState = state;
		toexplore.add(start_state);
		while (!toexplore.isEmpty()) {
			next_state = toexplore.remove();
			explored.add(next_state.state);
            // Moving Right and checking if it is goal and adding to toexplore list if it is not a goal and not yet explored
			statestring = moveRight(next_state.state);
			if (statestring.equals(goal)) {
				printPath(next_state.pathToThisState + "\n" + statestring);
				return;
			}
			if (!explored.contains(statestring)) {
				PuzzleState temp_state = new PuzzleState();
				temp_state.state = statestring;
				temp_state.pathToThisState = next_state.pathToThisState + "\n"
						+ statestring;
				toexplore.add(temp_state);
			}
           //Moving Right and checking if it is goal and adding to toexplore list if it is not a goal and not yet explored
			statestring = moveLeft(next_state.state);
			if (statestring.equals(goal)) {
				printPath(next_state.pathToThisState + "\n" + statestring);
				return;
			}
			if (!explored.contains(statestring)) {
				PuzzleState temp_state = new PuzzleState();
				temp_state.state = statestring;
				temp_state.pathToThisState = next_state.pathToThisState + "\n"
						+ statestring;
				toexplore.add(temp_state);
			}
            //Moving Right and checking if it is goal and adding to toexplore list if it is not a goal and not yet explored
			statestring = moveUp(next_state.state);
			if (statestring.equals(goal)) {
				printPath(next_state.pathToThisState + "\n" + statestring);
				return;
			}
			if (!explored.contains(statestring)) {
				PuzzleState temp_state = new PuzzleState();
				temp_state.state = statestring;
				temp_state.pathToThisState = next_state.pathToThisState + "\n"
						+ statestring;
				toexplore.add(temp_state);
			}
			//Moving Right and checking if it is goal and adding to toexplore list if it is not a goal and not yet explored
			statestring = moveDown(next_state.state);
			if (statestring.equals(goal)) {
				printPath(next_state.pathToThisState + "\n" + statestring);
				return;
			}
			if (!explored.contains(statestring)) {
				PuzzleState temp_state = new PuzzleState();
				temp_state.state = statestring;
				temp_state.pathToThisState = next_state.pathToThisState + "\n"
						+ statestring;
				toexplore.add(temp_state);
			}

		}
		System.out.println("fail " + explored.size());
	}
    // This method will print the sequence of steps to reach goal.
	public void printPath(String path) {
		System.out.println("The Path for given sequence is");
		for (String string : path.split("\n")) {
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
		System.out.println("depth is " + path.split("\n").length);
	}
    
	// This method will take current state and performs Left action and gives new state
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
	
// This method will take current state and performs Right action and gives new state
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

	// This method will take current state and performs Up action and gives new state
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
	
	// This method will take current state and performs down action and gives new state
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
		BFS bfs = new BFS();
		bfs.findPath(start_state);

	}
}
