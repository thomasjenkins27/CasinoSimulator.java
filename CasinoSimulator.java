package com.mycompany.casinosimulator;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CasinoSimulator {

    public static void main(String[] args) {
		
		
		String name;
		double bankRoll;
		double initialDeposit;
		int gameSelection;
		Scanner keyboard = new Scanner (System.in);
		
		SlotMachine slotmachine = new SlotMachine();
		Blackjack blackjack = new Blackjack();
		
		//Main menu header
		System.out.println("******************************************************");
		System.out.println("****");
		System.out.println("**Casino Simulator**");
		System.out.println("****");
		System.out.println("******************************************************\n");
		
		//Player name input
		System.out.print("What is your name? ");
		name = keyboard.nextLine();
		
		//Displays personal welcome message
		System.out.println("\n******************************************************\n");
		System.out.println("Welcome " + name + " to the Casino Simulator!\n");
		System.out.println("******************************************************\n");
		
		//Deposit requirement
		System.out.println("******************************************************\n");
		System.out.println("Deposit Requirements: \n Minimum -> $20 \n Maximum -> $1000\n");
		System.out.println("******************************************************\n");
		
		//User inputs deposit amount
		System.out.print("How much would you like to deposit? ");
		initialDeposit = keyboard.nextDouble();
		bankRoll = initialDeposit;
		
		//Ensures the amount is between $20-$1000
		if (bankRoll < 20 || bankRoll > 1000)
		{
			do {
				if (bankRoll < 20)
					System.out.println("\nPlease enter a deposit above $20.\n\n**The ATM is located next to the restroom if you need more money.**\n");
				else if (bankRoll > 1000)
					System.out.println("\nThe bank would gladly take your money, but the bank enjoys taking it at the value below $1000.\n");
				System.out.println("How much would you like to deposit? ");
				bankRoll = keyboard.nextDouble();
			}
			while (bankRoll < 20 || bankRoll > 1000);
		}
		
		//Current balance message
		System.out.println("\n******************************************************\n");
		System.out.print(name + ", you have a current balance of: $");
		System.out.printf("%.2f", bankRoll);
		System.out.println(".");
		System.out.println("\n******************************************************\n");
		
		//Casino game selection
		do {
			System.out.println("Casino Game Selection");
			System.out.println("\n******************************************************\n");
			System.out.println("Slot Machine -> 1 \n Blackjack --> 2 \n Exit --> 3\n");
			gameSelection = keyboard.nextInt ();
			if (gameSelection == 3) {
				System.out.println("Thanks for playing!");
				System.out.print("Your initial deposit: $");
				System.out.printf("%.2f", initialDeposit);
				System.out.print(".\nYour total earnings: $");
				System.out.printf("%.2f", bankRoll);
				System.out.println(".\nCome again soon!");
				System.exit(0);
			}
			else if (gameSelection == 1) {
				slotmachine.play(bankRoll);
				bankRoll += slotmachine.getEarnings();
			}
			else if (gameSelection == 2) {
				blackjack.play(bankRoll);
				bankRoll += blackjack.getEarnings();
			}
			System.out.print("Your account balance is $");
			System.out.printf("%.2f", bankRoll);
			System.out.println(".");
			while (bankRoll <= 0) { //If balance is $0, it will request to deposit more $.
				System.out.println("Please deposit more money:");
				System.out.print("How much would you like to deposit? ");
				bankRoll = keyboard.nextDouble();
				if (bankRoll < 20 || bankRoll > 1000) {
					do {
						if (bankRoll < 20)
							System.out.println("\nPlease enter a deposit above $20.\n\n**So I can take all of your monies!!**\n");
						else if (bankRoll > 1000)
							System.out.println("\nToo much money! I can't count that high.  Please keep below $1000.\n");
						System.out.println("How much would you like to deposit? ");
						bankRoll = keyboard.nextDouble();
					}
					while (bankRoll < 20 || bankRoll > 1000);
				}
			}
		}
		while (gameSelection != 3);
		System.out.println("\n******************************************************\n");
	}
}
