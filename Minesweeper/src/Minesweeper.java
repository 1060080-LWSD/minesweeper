import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

/**
 * Minesweeper.java
 * 
 * Name:
 * Last Revised Date:
 * APCS Period:
 * 
 * Program Description:
 * 
 */
public class Minesweeper {
	public static final int MINES = 10;
	public static final int SIZE = 8;
	public static final String LEVEL = "Beginner";
		
	public static void main(String[] args) throws FileNotFoundException {
		boolean[][] isMine=new boolean[SIZE][SIZE];
		boolean[][] isOpen= new boolean[SIZE][SIZE];
		boolean[][] isFlagged= new boolean[SIZE][SIZE];
		
		File f= new File("input/"+"Beginner1.csv");
    	Scanner input= new Scanner(f);
    	loadGameboard(input,isMine);
//    	for(boolean[] mine:isMine) {
//    		System.out.println(Arrays.toString(mine));
//    	}
    	Scanner console= new Scanner(System.in);
    	playGame(console,isMine);
    	
    	
    }
	
	/**
	 * This method loadGameboard reads the input and initializes the isMine array with the mine locations
	 * @param input is a Scanner value that reads from a .csv file that has SIZE lines of SIZE numbers separated by commas
	 * @param isMine is a 2D boolean array which will already be initialized to have SIZE rows and SIZE columns using the provided class constant
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
	
	public static void printColumnHeader() {
		System.out.print("  |");
		for(int i=1;i<=SIZE;i++) {
			System.out.print(" "+i+" |");
		}
		System.out.println();
	}
	
	public static void printLine() {
		System.out.print("  |");
		for(int i=1;i<=SIZE;i++) {
			System.out.print("---|");
		}
		System.out.println();
	}
	
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
	
	public static boolean checkValidMine(int r, int c, boolean[][] isMine) {
		if (r<0 || r==SIZE) {
			return false;
		}
		if (c<0 || c==SIZE) {
			return false;
		}
		return isMine[r][c];
	}
	
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
	
	public static void changeisOpen(int r, int c, boolean[][]isOpen) {
		if (r<0 || r==SIZE) {
			return;
		}
		if (c<0 || c==SIZE) {
			return;
		}
		isOpen[r][c]=true;
	}
	
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
	
	public static boolean gameWon(boolean[][] isOpen, boolean[][] isFlagged) {
		for(int r=0;r<SIZE;r++) {
			for(int c=0;c<SIZE;c++) {
				if(!isOpen[r][c] && !isFlagged[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
	
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
	
	public static boolean[][] createGameboard() throws FileNotFoundException {
		return null;
    }
}
