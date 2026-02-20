import java.io.FileNotFoundException;
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
    	for(boolean[] mine:isMine) {
    		System.out.println(Arrays.toString(mine));
    	}
    	displayGameboard(isOpen,isMine,isFlagged);
    	displayMinesLeft(5);
    	displayMinesLeft(1);
    	displayMinesLeft(20);
    	
    	
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
		
		for(int i=1;i<SIZE;i++) {
			System.out.print(" "+i+"|");
			
			printLine();
			System.out.println();
		}
		
	}
	
	public static void displayMinesLeft(int numMinesLeft) {
		if(numMinesLeft==1) {
			System.out.println("There is 1 mine left to flag.");
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
	
	public static void openAdjacentCells(int r, int c, boolean[][] isOpen) {
		
	}
	
	public static boolean gameWon(boolean[][] isOpen, boolean[][] isFlagged) {
		return false;
	}
	
	public static void playGame(Scanner input, boolean[][] isMine) {
		
	}
	
	public static boolean[][] createGameboard() throws FileNotFoundException {
		return null;
    }
}
