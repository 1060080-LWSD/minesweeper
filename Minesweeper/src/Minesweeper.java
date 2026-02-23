import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

/**
 * Minesweeper.java
 * 
 * Name: Aarushi Dhamija
 * Last Revised Date:2-22-2026
 * APCS Period:4th
 * 
 * Program Description: Mindsweeper game
 * 
 */
public class Minesweeper {
	public static final int MINES = 10;
	public static final int SIZE = 8;
	public static final String LEVEL = "Beginner";
		
	/**
	 * Main method were we are printing the instructions and playing the game
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Welcome to Minesweeper!");
		System.out.println();
		System.out.println("To win you must flag all the bombs and open all other cells.");
		System.out.println("An open cell will display the number of adjacent bombs to that cell.");
		System.out.println("A flagged cell will display an asterisk.");
		System.out.println();

    	boolean[][] isMine= createGameboard();
    	System.out.println();
    	Scanner console= new Scanner(System.in);
    	playGame(console,isMine);
    	System.out.println();
    	System.out.println("Thanks for playing!");
    }
	
	/**
	 * This method loadGameboard reads the input from the scanner and initializes 
	 * the isMine array with the mine locations
	 * @param input is a Scanner value that reads from a .csv file that has SIZE 
	 * lines of SIZE numbers separated by commas
	 * @param isMine is a 2D boolean array which will already be initialized to
	 *  have SIZE rows and SIZE columns using the provided class constant
	 */
	public static void loadGameboard(Scanner input, boolean[][] isMine) {
		int line=0;
		while(input.hasNextLine()) {
			String[]nums=input.nextLine().split(",");
			for(int i=0;i<SIZE;i++) {
				if(Integer.parseInt(nums[i])==1) {
					isMine[line][i]=true;
				}
				else {
					isMine[line][i]=false;
				}
			}
			line++;
		}
	}
	
	/**
	 * generateGameboard method adds mines at class constant MINES random locations 
	 * @param isMine an array of type boolean 2D array representing an empty board
	 */
	public static void generateGameboard(boolean[][] isMine) {
		int counter=0;
		while(counter<MINES) {
			int randomRow=(int)(Math.random()*SIZE);
			int randomCol= (int)(Math.random()*SIZE);
			if(isMine[randomRow][randomCol]==false) {
				counter++;
				isMine[randomRow][randomCol]=true;
			}
		}
	}
	
	/** 
	 * Prints the column numbers in a line based on the length of SIZE
	 */
	public static void printColumnHeader() {
		System.out.print("  |");
		for(int i=1;i<=SIZE;i++) {
			System.out.print(" "+i+" |");
		}
		System.out.println();
	}
	
	/**
	 * prints |---|---|---| and so on based on SIZE
	 */
	public static void printLine() {
		System.out.print("  |");
		for(int i=1;i<=SIZE;i++) {
			System.out.print("---|");
		}
		System.out.println();
	}
	
	/**
	 * This method displays how game board taking into account the open locations and the
	 * flagged locations
	 * For the open locations it prints the number of adjacent mines
	 * For the flagged locations it prints a * 
	 * Otherwise its just a space
	 * @param isOpen is an array of type boolean and shows the open locations
	 * @param isMine is an array of type boolean and returns true if the location is a mine and 
	 * false if it isn't
	 * @param isFlagged is an array of type boolean and turns true after when you want to mark
	 * a flag
	 */
	public static void displayGameboard(boolean[][] isOpen, boolean[][] isMine, 
			boolean[][] isFlagged) {
		
		printColumnHeader();
		printLine();
		
		for(int r=0;r<SIZE;r++) {
			System.out.print(" "+(r+1)+"|");
			for(int c=0;c<SIZE;c++){
				if(isOpen[r][c]) {
					System.out.print(" "+adjacentMines(r,c,isMine)+" |");
				}
				else if(isFlagged[r][c]) {
					System.out.print(" * |");
				}
				else {
					System.out.print("   |");
				}	
				
			}
			System.out.println();
			printLine();
		}
	
		
	}
	/**
	 * Prints a message of how many mines are left
	 * @param numMinesLeft of type int 
	 */
	public static void displayMinesLeft(int numMinesLeft) {
		if(numMinesLeft==1) {
			System.out.println("There is 1 mine left to flag.");
		}
		else if(numMinesLeft==-1) {
			System.out.println("You have too many mines flagged.");
		}
		else if(numMinesLeft>MINES) {
			System.out.println("You have too many mines flagged.");
		}
		else {
			System.out.println("There are "+numMinesLeft+" mines left to flag.");
		}
	}
	/** 
	 * Helper function used in adjacentMines. Checks if r and c are in valid bounds in isMine array 
	 * @param r of type int and is the row number
	 * @param c of type int and is the column number
	 * @param isMine an array of type boolean
	 * @return the isMine location using r and c 
	 */
	public static boolean checkValidMine(int r, int c, boolean[][] isMine) {
		if (r<0 || r==SIZE) {
			return false;
		}
		if (c<0 || c==SIZE) {
			return false;
		}
		return isMine[r][c];
	}
	
	/**
	 * This method tells shows how many mines are adjacent to the cell at (r,c)
	 * @param r is of type int and is the row number
	 * @param c is of type int and is the column number
	 * @param isMine an boolean array 
	 * @return the number of mines that are adjacent to the cell at (r,c)
	 */
	public static int adjacentMines(int r, int c, boolean[][] isMine) {
		int count=0;
		if (checkValidMine(r-1,c-1,isMine)) {
			count++;
		}
		if (checkValidMine(r-1,c,isMine)) {
			count++;
		}
		if (checkValidMine(r-1,c+1,isMine)) {
			count++;
		}
		if (checkValidMine(r,c-1,isMine)) {
			count++;
		}
		if (checkValidMine(r,c+1,isMine)) {
			count++;
		}
		if (checkValidMine(r+1,c-1,isMine)) {
			count++;
		}
		if (checkValidMine(r+1,c,isMine)) {
			count++;
		}
		if (checkValidMine(r+1,c+1,isMine)) {
			count++;
		}	
		
		return count;
	}
	/** 
	 * Helper function used in openAdjacentCells. Checks if r and c are in valid bounds in isOpen array  
	 * @param r of type int and is the row number
	 * @param c of type int and is the column number
	 * @param isOpen an array of type boolean
	 */
	public static void changeisOpen(int r, int c, boolean[][]isOpen) {
		if (r<0 || r==SIZE) {
			return;
		}
		if (c<0 || c==SIZE) {
			return;
		}
		isOpen[r][c]=true;
	}
	/**
	 * Opens the adjacent valid cells of the given location
	 * @param r of type int and is the row number
	 * @param c of type int and is the column number
	 * @param isOpen an array of type boolean and returns true or false if open or not
	 */
	
	public static void openAdjacentCells(int r, int c, boolean[][] isOpen) {
		changeisOpen(r-1,c-1,isOpen);
		changeisOpen(r-1,c,isOpen);
		changeisOpen(r-1,c+1,isOpen);
		changeisOpen(r,c-1,isOpen);
		changeisOpen(r,c+1,isOpen);
		changeisOpen(r+1,c-1,isOpen);
		changeisOpen(r+1,c,isOpen);
		changeisOpen(r+1,c+1,isOpen);
	}
	/**
	 * This method checks if you won the game or not 
	 * @param isOpen an array of type boolean which is true or false
	 * @param isFlagged an array of type boolean which is true or false
	 * @return true if all the cells in the game board are either marked as open or flagged but not both
	 */
	public static boolean gameWon(boolean[][] isOpen, boolean[][] isFlagged) {
		for(int r=0;r<SIZE;r++) {
			for(int c=0;c<SIZE;c++) {
				if(!isOpen[r][c] && !isFlagged[r][c]) {
					return false;
				}
				if(isOpen[r][c] && isFlagged[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * This method lets you play the game and asks for inputs from the user
	 * @param input asks the user questions of type scanner 
	 * @param isMine an array of type boolean 
	 */
	public static void playGame(Scanner input, boolean[][] isMine) {
		boolean[][] isOpen= new boolean[SIZE][SIZE];
		boolean[][] isFlagged= new boolean[SIZE][SIZE]; 
		int numMinesLeft=MINES;
		while(true) {
			displayGameboard(isOpen, isMine, isFlagged);
			displayMinesLeft(numMinesLeft);
			System.out.print("(O)pen, (F)lag, or (Q)uit? ");
			String userLetter= input.next();
			if(!userLetter.equals("O") && !userLetter.equals("F") && !userLetter.equals("Q")) {
				System.out.println("Invalid choice, try again.");
			}
			if(userLetter.equals("O") || userLetter.equals("F")) {
				int row =0;
				while(row<1 || row>SIZE) {
					System.out.printf("Enter the row number (1 - %d): ",SIZE);
					row = input.nextInt();
				}
				
				int col =0;
				while(col<1 || col>SIZE) {
					System.out.printf("Enter the column number (1 - %d): ",SIZE);
					col = input.nextInt();
				}
				row--;
				col--;
				
				if(userLetter.equals("F")) {
					// If the user chooses F and the chosen (row,col) is not already flagged and is
					//not open set (row,col) to be flagged and decrease the number of mines left
					if(!isFlagged[row][col] && !isOpen[row][col]){
						isFlagged[row][col]=true;
						numMinesLeft--;
					}
					else {
						System.out.println("Invalid choice, try again.");
					}
				}
				else {
                    // if the user chooses O and the chosen (row,col) is a mine then print "BOOM!."
					if(isMine[row][col]) {
						System.out.println("BOOM! You lose!");
						return;
					}
					if(!isOpen[row][col]) {
						isOpen[row][col]=true;
						if(isFlagged[row][col]) {
							numMinesLeft++;
							isFlagged[row][col]=false;
						}
						if(adjacentMines(row,col,isMine)==0) {
							openAdjacentCells(row,col,isOpen);
						}
					}
					else {
						System.out.println("Invalid choice, try again.");
					}
				}
			}
			else if(userLetter.equals("Q")){
				return;			
			}
			if(numMinesLeft==0 && gameWon(isOpen, isFlagged)) {
				System.out.println("You Win!");
				return;
			}
			
		}
	}
	
	/**
	 * Has a 50% chance of loading a game board from one of the random files available
	 * for the LEVEL or generating a random game board
	 * @return boolean [][] isMine
	 * @throws FileNotFoundException
	 */
	public static boolean[][] createGameboard() throws FileNotFoundException {
		int rando=(int)(Math.random()*2);
		boolean[][] isMine=new boolean[SIZE][SIZE];
		if(rando==0) {
			System.out.println("Loading the gameboard from a file...");
			int num= (int)(Math.random()*10+1);
			File f= new File("input/"+LEVEL+num+".csv");
	    	Scanner input= new Scanner(f);
	    	loadGameboard(input,isMine);
		}
		else if (rando==1) {
			System.out.println("Generating a random gameboard...");
			generateGameboard(isMine);
			
		}
		
		return isMine;
    }
}
