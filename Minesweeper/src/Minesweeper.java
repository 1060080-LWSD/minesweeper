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
		
    }
	
	public static void loadGameboard(Scanner input, boolean[][] isMine) {
		
	}
	
	public static void generateGameboard(boolean[][] isMine) {
		
	}
	
	public static void printColumnHeader() {
		
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
