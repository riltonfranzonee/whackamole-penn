package WhackAMole;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WhackAMole {
	int score;
	int molesLeft;
	int attemptsLeft;
	char[][] moleGrid;
	
	
	WhackAMole(int numAttempts, int gridDimension) {
		this.attemptsLeft = numAttempts;
		this.moleGrid = new char[gridDimension][gridDimension];
	}
	
	boolean place(int x, int y) {
		this.moleGrid[x][y] = 'M';
		
		this.molesLeft++;
		
		return true;
	}
	
	boolean whack(int x, int y) {
		char location = this.moleGrid[x][y];
		
		this.attemptsLeft--;
		
		if(location == 'M') {
			this.score++;
			this.molesLeft--;
			
			this.moleGrid[x][y] = 'W';
						
			return true;
		}  else {
			return false;	
		}
		
	}
	
	void printGridTouser() {
		char[][] userGrid = this.moleGrid;
		
		for(int i = 0; i < userGrid[0].length; i++) {
			for (int j = 0; j < userGrid[1].length; j++) {
				if(userGrid[i][j] != 'W') {
					System.out.print("* ");
				} else {
					System.out.print(userGrid[i][j] + " ");
				}
			}
			
			System.out.println();
		}
		
		System.out.println(userGrid);
	}
	
	void printGrid() {
	char[][] completeGrid = this.moleGrid;
		
		for(int i = 0; i < completeGrid[0].length; i++) {
			for (int j = 0; j < completeGrid[1].length; j++) {
				if(!Character.isLetter(completeGrid[i][j]) || completeGrid[i][j] == 'M') {
					System.out.print("* ");
				} else {
					System.out.print(completeGrid[i][j] + " ");
				}
			}
			
			System.out.println();
		}
		
	}
	
	
	public static void main(String[] args) {
		WhackAMole whachAMole = new WhackAMole(50, 10);
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		
		for(int i = 0; i < 10; i++) {
			int randomMoleX = random.nextInt(0, 10);
			
			int randomMoleY = random.nextInt(0, 10);
			
			whachAMole.place(randomMoleX, randomMoleY);
		}

		
		Scanner scanner = new Scanner(System.in);
		
		
		int attempts = 0;
		boolean hasFinished = false;
		
		while(attempts < 50 && !hasFinished) {
			System.out.print("Enter the x coordinate: ");
			int x = scanner.nextInt();
			
			if(x > 9) {
				System.out.println("The coordinate cannot be greater than 9");
				return;
			}
			
			System.out.print("Enter the y coordinate: ");
			
			int y = scanner.nextInt();
			
			if(y > 9) {
				System.out.println("The coordinate cannot be greater than 9");
				return;
			}
			
			if(x == -1 && y == -1) {
				whachAMole.printGrid();
				
				System.out.println("Aborting the game");
				
				hasFinished = true;
				
				return;
			}
			
			boolean whached = whachAMole.whack(x, y);
			
			if(whached) {
				System.out.println("Whacked!");
			} else {
				System.out.println("Hmm, nothing there...");
			}
			
			attempts++;

			if(whachAMole.molesLeft == 0 || attempts == 50) {
				System.out.println("The game is over!");
				whachAMole.printGrid();
				hasFinished = true;
			};
			
		}
	}
	
	
}
