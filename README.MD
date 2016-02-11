# About
Solved the NQueens problem — 
It is the problem of placing n queens on the board in such a way that no two queens threaten each other. The solution requires that no two queens can share the same row, column, or diagonal.

Implemented hill climbing and minimum conflict algorithm to solve NQeens problem.

In hill climbing algorithm, one by one each queen is moved to a place in the column that gives minimum conflicts. This process is repeated till the algorithm finds a solution but sometimes the algorithm gets stuck. To get out of that state it generates a random state and continues with the process till it finds the solution.
In Minimum Conflict algorithm, maximum conflict queen is get selected and moved to a place in a row in the same column that gives minimum conflicts. After moving each queen if the algorithm gets stuck it will randomly select a new state and continue with the algorithm till it find a solution.
