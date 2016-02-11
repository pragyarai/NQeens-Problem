
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MinConflict {

    
	//Global variables
        static Random random = new Random();
        static int[] board;
    	static int n;
    	static int state_change = 0;
    	static int counter=0;
    	static int rearrange=0;
    	static Scanner scanner = new Scanner(System.in);

     
             
       //Set the board
	   	public static void get_init() {
	   		System.out.println("\nEnter the number of rows and column (n*n) : ");
	   		n= scanner.nextInt();
	   		board = new int[n];

	
	   		System.out.println("\nEnter the initial state of queens(0-"+(n-1)+"): ");
	   		for(int i=0;i<n;i++)
	   			{
	   			System.out.println("Enter row number for queen in "+ (i+1) + " column: ");
	   			board[i] = scanner.nextInt();
	   			
	   			}
	   	}
	   	//Count the total conflicts 
	   	public static int attacks()
    	{
    		int i,j,attacks=0;
    		for(i=0;i<n-1;i++)
    		{
    			for(j=i+1;j<n;j++)
    			{
    				if(board[i]==board[j])
    					attacks++;
    				if(Math.abs(i-j)==Math.abs(board[i]-board[j]))
    					attacks++;
    			}
    		}
    		
    		return attacks;
    	}

        // Randomly fills the board with one queen in each column.
        public static void rearrangeBoard() {
            for (int i = 0; i < n; i++) {
                board[i] = i;
            }
            for (int i = 0, n = board.length; i < n; i++) {
                int j = random.nextInt(n);
                int temp = board[i];
                board[i] = board[j];
                board[j] = temp;
            }
        }

        // Find the conflict for a queen
        public static int pos_attacks(int row, int col) {
            int count = 0;
            for (int cl = 0; cl < n; cl++) {
                if (cl == col) continue;
                int rw = board[cl];
                if (rw == row || Math.abs(rw-row) == Math.abs(cl-col)) 
                	count++;
            }
            return count;
        }
            	
        //Move the Queen to the minimum conflict place
        public static void moveQueen() {
            
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            display_board();

            while (true) {

                // Find the queen with maximum conflicts
                int maxConflicts = 0;
                tempList.clear();
                for (int c = 0; c < n; c++) {
                    int conflicts = pos_attacks(board[c], c);
                    if (conflicts == maxConflicts) {
                        tempList.add(c);
                    } else if (conflicts > maxConflicts) {
                        maxConflicts = conflicts;
                        tempList.clear();
                        tempList.add(c);
                    }
                }

                if (maxConflicts == 0) {
                    return;
                }
                

                int conflicQueenCol = tempList.get(random.nextInt(tempList.size()));

                // Move the Queen to the least conflict place.
                int minConf = n;
                tempList.clear();
                for (int r = 0; r < n; r++) {
                    int conflicts = pos_attacks(r, conflicQueenCol);
                    if (conflicts == minConf) {
                        tempList.add(r);
                    } else if (conflicts < minConf) {
                        minConf = conflicts;
                        tempList.clear();
                        tempList.add(r);
                    }
                }
                

                if (!tempList.isEmpty()) {
                    board[conflicQueenCol] = tempList.get(random.nextInt(tempList.size()));
                }
                
                display_board();
                counter++;
                state_change++;
                if (state_change == n * 2) {
                    rearrangeBoard();
                    rearrange++;
                    state_change = 0;
                }
            }
           
        }
        //Print the board
        public static void display_board()
    	{
    		for(int i=0;i<n;i++)
    		{
    			System.out.println("");
    			for(int j=0;j<n;j++)
    			{
    				if(i==board[j])
    					System.out.print("*\t");
    				else
    					System.out.print("O\t");
    			}
    		}
    		System.out.println("\n");
    		
    	}
    
    // Main function
    public static void main(String[] args) {
    	
         get_init();
         moveQueen();
         System.out.println("\nSolution:\n");
         display_board();
         System.out.println("\nConflicts: " + attacks());
         System.out.println("\nNumber of Random restart: "+ rearrange);
         System.out.println("\nNumber of States changed: "+ counter);
    }
}