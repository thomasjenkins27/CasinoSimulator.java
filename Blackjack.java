package com.mycompany.casinosimulator;
import java.util.Scanner;
import java.util.Random;

public class Blackjack {
    
public final int NUMBER_OF_SIDES = 6;
	public int value;
	
	double amountEntered;
	double amountWon;
	double totalAmountEntered;
	double totalAmountWon;
	
	int computerTotal;
	int playerTotal;
	
	Random rand;
	
	//Constructor will call the roll method to set the value of the die
	Blackjack() {
		rand = new Random();
	}
	
	public void play(double balance) {
		Scanner keyboard = new Scanner(System.in);
		boolean again = true;
		boolean accumulate;
		boolean roll;
		
		totalAmountEntered = 0;
		totalAmountWon = 0;
		computerTotal = 0;
		playerTotal = 0;
		
		System.out.println("Welcome to Blackjack\n");
		do {
			System.out.print("Your current balance is $");
			System.out.printf("%.2f", balance);
			System.out.print(".\nHow much would you like to bet? ");
			amountEntered = keyboard.nextDouble();
			totalAmountEntered += amountEntered;
			balance -= amountEntered;
			
			computerTotal = 0;
			playerTotal = 0;
			roll = true;
			
			while (playerTotal <= 21 && roll) {
				computerTotal += rollDice();
				System.out.println("Your total is " + playerTotal);
				System.out.print("Do you want to roll (y/n)? ");
				String input = keyboard.next();
				if (input.equals("y")) {
					playerTotal += rollDice();
				}
				else if (input.equals("n")) {
					roll = false;
				}
			}
			
			// Display the computer and user's points.
			System.out.println("\nGame Over\n");
			System.out.println("User's Points: " + playerTotal);
			System.out.println("Computer's Points: " + computerTotal);
			String message = getWinnerMessage(computerTotal, playerTotal);
			System.out.println(message);
			if (message.charAt(0) == 'C') {
				amountWon = amountEntered * 3;
			}
			else if (message.substring(0, 4).equals("This") || message.substring(0, 3).equals("Tie")) {
				amountWon = amountEntered;
			}
			else {
				amountWon = 0;
			}
			System.out.print("\nYou won $");
			System.out.printf("%.2f", amountWon);
			System.out.println(".");
			totalAmountWon += amountWon;
			balance += amountWon;
			again = playAgain();
		}
		while (again);
		System.out.print("Total amount entered: $");
		System.out.printf("%.2f", totalAmountEntered);
		System.out.println(".");
		System.out.print("Your total winnings: $");
		System.out.printf("%.2f", totalAmountWon);
		System.out.println(".");
	}
	
	public int rollDice() {
		int die1 = rand.nextInt(NUMBER_OF_SIDES) + 1;
		int die2 = rand.nextInt(NUMBER_OF_SIDES) + 1;
		return (die1 + die2);
	}
	
	public static boolean isUnderGameLimit(int value)
	{
		return (value <= 21);
	}
	
	public static boolean playAgain() {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Play again? (y/n) : ");
		String letter = keyboard.nextLine(); // Get a line of input.
		return (letter.equals("Y") || letter.equals("y"));
	}

	public static String getWinnerMessage(int computerScore, int userScore)
	{
		if (userScore > computerScore && isUnderGameLimit(userScore)) {
			return "Congrats, you win!!!";
		}
		else if (isUnderGameLimit(userScore) && !isUnderGameLimit(computerScore)) {
			return "Congrats, you win!!!";
		}
		else if (userScore == 21 && computerScore != 21) {
			return "Congrats, you win!!!";
		}
		else if (userScore == computerScore) {
			return "Tie game!";
		}
		else if (!isUnderGameLimit(userScore) && !isUnderGameLimit(computerScore)) {
			return "This game has ended without a winner.";
		}
		else {
			return "The computer wins!";
		}
	}
	
	public double getEarnings() {
		return totalAmountWon - totalAmountEntered;
	}
}
