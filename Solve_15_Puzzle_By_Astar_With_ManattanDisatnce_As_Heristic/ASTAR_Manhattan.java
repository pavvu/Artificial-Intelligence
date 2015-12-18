//This code takes a sequence of 15 puzzle and outputs the steps 
//to reach goal by following A*  search Algorithm by using Manhattan distance heuristic

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

//An atmoic structure to denote a state
class State {
	int[] state;
	State parent;
	int depth;
	int heuristic;
}

class HeuristicComparatorMan implements Comparator<State>
{
    @Override
    public int compare(State x, State y)
    {
        
        if (x.heuristic < y.heuristic)
        {
            return -1;
        }
        if (x.heuristic > y.heuristic)
        {
            return 1;
        }
        return 0;
    }
}
public class ASTAR_Manhattan {
	private static final State NULL = null;
	static int[] goal = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

	public boolean findPath(int[] state, int depth) {
		int[] statearray;
		int noofexplorednodes=0;
		HashSet<String> explored = new HashSet<String>();
		Comparator<State> comparator = new HeuristicComparatorMan();
        PriorityQueue<State> toexplore = 
            new PriorityQueue<State>(100, comparator);
		State start_state = new State();
		State next_state = new State();
		start_state.state = state;
		start_state.parent = null;
		start_state.depth = 1;
		start_state.heuristic = start_state.depth+ noOfMisplaced(state);
		toexplore.add(start_state);
		while (!toexplore.isEmpty()) {
			noofexplorednodes+=1;
			next_state = toexplore.remove();
			explored.add(returnStateString(next_state.state));
			State temp_state = new State();

			// Moving Left and checking if it is goal and adding to toexplore
			// list if it is not a goal and not yet explored
			statearray = moveLeft(next_state.state);
			temp_state = new State();
			temp_state.state = statearray;
			temp_state.parent = next_state;
			temp_state.depth = next_state.depth + 1;
			temp_state.heuristic = temp_state.depth + manHattanDistance(statearray);
			if (Arrays.equals(statearray, goal)) {
				System.out.println("depth is " + temp_state.depth);
				printPath(temp_state);
				return true;
			}
			//if (!explored.contains(returnStateString(statearray)))
				toexplore.add(temp_state);
			// Moving Right and checking if it is goal and adding to toexplore
			// list if it is not a goal and not yet explored
			statearray = moveRight(next_state.state);
			temp_state = new State();
			temp_state.state = statearray;
			temp_state.parent = next_state;
			temp_state.depth = next_state.depth + 1;
			temp_state.heuristic = temp_state.depth + manHattanDistance(statearray);
			if (Arrays.equals(statearray, goal)) {
				System.out.println("depth is " + temp_state.depth);
				printPath(temp_state);
				return true;
			}
			//if (!explored.contains(returnStateString(statearray)))
				toexplore.add(temp_state);

			// Moving Up and checking if it is goal and adding to toexplore list
			// if it is not a goal and not yet explored
			statearray = moveUp(next_state.state);
			temp_state = new State();
			temp_state.state = statearray;
			temp_state.parent = next_state;
			temp_state.depth = next_state.depth + 1;
			temp_state.heuristic = temp_state.depth + manHattanDistance(statearray);
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
			temp_state.heuristic = temp_state.depth + manHattanDistance(statearray);
			if (Arrays.equals(statearray, goal)) {
				System.out.println("depth is " + temp_state.depth);
				printPath(temp_state);
				return true;
			}
			//if (!explored.contains(returnStateString(statearray)))
				toexplore.add(temp_state);

		}
		return false;
	}
	
    //Manhattan distance heurstic
	public int manHattanDistance(int [] state)
	  {
		  int mandistance = 0;
		  int[][] goalarray, statearray = new int[4][4];
		  goalarray = build2darray(goal);
		  statearray = build2darray(state);
		  int currelement;
		  for(int goalrow=0; goalrow<=3; goalrow+=1)
		  {
	        for(int goalcol=0; goalcol<=3; goalcol+=1)
	        {
	        	currelement = goalarray[goalrow][goalcol];
	        	for(int staterow=0; staterow<=3; staterow+=1)
	        	{
	        		for(int statecol=0; statecol<=3; statecol+=1)
	        		{
	        			if(currelement == statearray[staterow][statecol])
	        			{
	        				mandistance +=  Math.abs(goalrow - staterow) + Math.abs(goalcol - statecol);	
	        			}
	        		}
	        	}
	        }
			  
		  }
		  return mandistance;
	  }
	  
	  public int[][] build2darray(int[] state)
	  {
		  int[][] conevrtedarray = new int[4][4];
		  int index =0;
		  for(int i=0; i<=3; i+=1)
		  {
			  for(int j=0; j<=3; j+=1)
			  {
				  conevrtedarray[i][j]=state[index];
				  index+=1;
			  }
			  
		  }
		  return conevrtedarray;
	  }
	// calculate misplaced tiles
	public int noOfMisplaced(int[] stateArray)
	{
		  int noOfMisplaced=0;
		  for (int index=0; index<=15; index+=1) 
		  {
			if(goal[index]!=stateArray[index])
				noOfMisplaced+=1;
//			System.out.print(goal[index]+", ");
		  }
		  
		  return noOfMisplaced-1;
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
		ASTAR_Manhattan bfs = new ASTAR_Manhattan();
		bfs.IterativeDFS(state, 1000);

	}
}
