The folder DFS contain a DFS.java file which is implementation of Depth First Search Algorithm. Please execute that file by running it on eclipse or anyother IDE. The code will ask a input state of 8 X 8 puzzle. please type the state as continous characters without any other special characters. 
For example your input should be like this 
ip : 120345678 if the state is 
     1 2 0
     3 4 5
     6 7 8
The code will ouput a sequence of steps from given state to goal state and depth at which goal is found.
This DFS can take a bit loneger time than BFS (usually about 2-3 minuites)
------------------------------------------------------------------------------------------------------------------------
Sample Input and OutPut
Give Start State :
123450678
The Path for given sequence is
1 2 3
4 5 0
6 7 8

1 2 3
4 0 5
6 7 8

1 2 3
0 4 5
6 7 8

0 2 3
1 4 5
6 7 8

2 0 3
1 4 5
6 7 8

2 3 0
1 4 5
6 7 8

2 3 5
1 4 0
6 7 8

2 3 5
1 0 4
6 7 8

2 0 5
1 3 4
6 7 8

0 2 5
1 3 4
6 7 8

1 2 5
0 3 4
6 7 8

1 2 5
3 0 4
6 7 8

1 2 5
3 4 0
6 7 8

1 2 0
3 4 5
6 7 8

1 0 2
3 4 5
6 7 8

0 1 2
3 4 5
6 7 8

depth is 16
In DFS for the given output, the goal is reached at a depth of 16.

