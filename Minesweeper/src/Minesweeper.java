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
		generateGameboard(isMine);
		printColumnHeader();
//		File f= new File("input/"+"Beginner2.csv");
//    	Scanner input= new Scanner(f);
//    	loadGameboard(input,isMine);
    	for(boolean[] mine:isMine) {
    		System.out.println(Arrays.toString(mine));
    	}
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
		System.out.print("|");
		for(int i=1;i<=SIZE;i++) {
			System.out.print(" "+i+" |");
		}
		System.out.println();
	}
	
	public static void printLine() {
		
	}
	
	public static void displayGameboard(boolean[][] isOpen, boolean[][] isMine, 
			boolean[][] isFlagged) {
		
	}
	
	public static void displayMinesLeft(int numMinesLeft) {
		
	}
	
	public static int adjacentMines(int r, int c, boolean[][] isMine) {
		return -1;
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
