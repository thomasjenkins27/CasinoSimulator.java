package com.mycompany.casinosimulator;
import java.util.Scanner;
import java.util.Random;

public class SlotMachine {
    
double amountEntered;
	double amountWon;
	double totalAmountEntered;
	double totalAmountWon;

	Random rand;
	
	public SlotMachine() {
		amountEntered = 0;
		amountWon = 0;
		totalAmountEntered = 0;
		totalAmountWon = 0;
		rand = new Random();
	}
	
	//Prompt user enter the amount of money into the slot machine
	public void play(double balance) {
		
		Scanner keyboard = new Scanner(System.in);
		String input;
		boolean again = true;
		
		totalAmountEntered = 0;
		totalAmountWon = 0;
		
		System.out.println("Welcome to the Slot Machine\n");
		do {
			System.out.print("You have " + balance + " current balance. How much would you like to put in the machine? ");
			amountEntered = keyboard.nextDouble();
			totalAmountEntered += amountEntered;
			balance -= amountEntered;
			
			//generate three random numbers
			int n1 = rand.nextInt(5);
			int n2 = rand.nextInt(5);
			int n3 = rand.nextInt(5);
			String image1 = getWord(n1);
			String image2 = getWord(n2);
			String image3 = getWord(n3);
			System.out.println("Images are:\n" + image1 + ", " + image2 + ", " + image3);
			
			// Amount won based off of the image pairings
			//three matches = 3 times amount entered payout
			if (n1 == n2 && n2 == n3) {
				amountWon = amountEntered * 3;
			}
			//two matches = 2 times amount entered payout
			else if (n1 == n2 || n2 == n3 || n1 == 3) {
				amountWon = amountEntered * 2;
			}
			//no matches = 0 dollars
			else {
				amountWon = 0;
			}
			System.out.print("\nYou won $");
			System.out.printf("%.2f", amountWon);
			System.out.println(".");
			
			//add the amount won to total amount won
			totalAmountWon += amountWon;
			balance += amountWon;
			
			//Ask if the player wants to play again
			System.out.println("Do you want to play again (y/n)?");
			input = keyboard.next();
			if (input.equals("Y") || input.equals("y")) {
				again = true;
			}
			else if (input.equals("N") || input.equals("n")) {
				again = false;
			}
			else {
				System.out.println("\nInvalid choice, please try again!");
			}
		}
		while (again);
		
		System.out.print("Total amount entered: $");
		System.out.printf("%.2f", totalAmountEntered);
		System.out.println(".");
		System.out.print("Your total winnings: $");
		System.out.printf("%.2f", totalAmountWon);
		System.out.println(".");
	}
	
	public String getWord(int n) {
		switch (n) {
			case 0: return "Cherries";
			case 1: return "Oranges";
			case 2: return "Plums";
			case 3: return "Bells";
			case 4: return "Melons";
			default: return "Bars";
		}
	}
	
	public double getEarnings() {
		return totalAmountWon - totalAmountEntered;
	}
}
