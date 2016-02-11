import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class HillClimbing {
	
	//Global variables
	static int[] board;
	static int n,state_change=0, rstate_change=0,h,temp_h;
	static Scanner scanner = new Scanner(System.in);
	static Random random= new Random();
	
	public HillClimbing() {
	}
	
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
	public static int pos_attacks(int []temp_board)
	{
		int i,j,attacks=0;
		for(i=0;i<n-1;i++)
		{
			for(j=i+1;j<n;j++)
			{
				if(temp_board[i]==temp_board[j])
					attacks++;
				if(Math.abs(i-j)==Math.abs(temp_board[i]-temp_board[j]))
					attacks++;
			}
		}
		
		return attacks;
	}
	 //Print the board
	public static void display_board(int[] temp)
	{
		for(int i=0;i<n;i++)
		{
			System.out.println("");
			for(int j=0;j<n;j++)
			{
				if(i==temp[j])
					System.out.print("*\t");
				else
					System.out.print("O\t");
			}
		}
	}

	//Move the Queen to the minimum conflict place
	public static int moveQueen()
	{
		int a_pos, a_pos1,row=0,col;
		col=board[0];
		
		int[] temp_board= new int[n];
		for(int k=0;k<n;k++)
		{
			
			temp_board[k]=board[k];
		}
		a_pos=pos_attacks(board);
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(board[i]!=j)
				{
					temp_board[i]=j;
					
					a_pos1=pos_attacks(temp_board);
					if(a_pos>a_pos1)
					{
						a_pos=a_pos1;
						row=i;
						col=j;
					}
				}
				
				temp_board[i]=board[i];
			}
		}
		board[row]=col;
		state_change++;
		return a_pos;
}
    // Main function
	public static void main(String[] args) {
		int h;
		HillClimbing.get_init();
		display_board(board);
		h=pos_attacks(board);
		System.out.println("\nConflicts: " + h);
		temp_h=h;
		while(h!=0)
		{
			do
			{
				h=temp_h;
				temp_h=HillClimbing.moveQueen();
			}while(temp_h!=h);
			
			if(temp_h==0)
			{
				System.out.println("\nSolution:\n");
				display_board(board);
				System.out.println("\nConflicts: " + temp_h);
				System.out.println("\nNumber of States changed: "+ state_change);
				System.out.println("\nNumber of Random restart: "+ rstate_change);
				System.exit(0);
			}
			display_board(board);
			System.out.println("\nConflicts: " + temp_h);
			System.out.println("\nNumber of States changed: "+ state_change);
			for(int i=0;i<n;i++)
				board[i]= random.nextInt(n);
			
			System.out.println("\nRandomly Generated State:\n");
			display_board(board);
			System.out.println("\n");
			rstate_change++;
		}
	}
}