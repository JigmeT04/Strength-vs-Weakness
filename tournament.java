package StrengthVsWeakness;

import java.util.Scanner;


public class Tournament {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		//start the program by asking for number of players and rounds
		System.out.print("Enter the number of participants: ");
		int players = scanner.nextInt();
		System.out.print("Enter the number of tournament rounds: ");
		int rounds = scanner.nextInt();
		//make a 1d array to store players
		Player[] player = new Player[players];
		//create the number of players needed
		for (int i=0; i<players; i++) {
			player[i] = new Player();
		}
		
		//start rounds
		for (int round=1; round<=rounds; round++) {
			sortPlayersByWins(player); //players play in order with who has most wins first
			
			for (int i=0; i<players-1; i+=2) { //go through every player by 2 since its a 1v1.
				Player p1 = player[i];
				Player p2 = player[i+1];
				p1.play(p2);
			}
			if (players%2==1) { //if there is an odd number of players the last player in the sorted array (one with the least wins) gets a bye win
				Player p0 = player[players-1];
				p0.play(null);
			}
		}
		//now we find who the winner is
		int winner = 0; //set winner to index 0
		//compare every index in player array against player[winner] which is 0 and if the player index at i has more wins replace winner with i.
		for (int i=0; i<players; i++) {
			if (player[i].getWins()>player[winner].getWins()) { //if  player i has more wins than player winner, i is the new winner
				winner=i;
			}
			else if (player[i].getWins()==player[winner].getWins()){ //if player i has same number of wins as winner
				if (player[i].getOpponentWins()>player[winner].getOpponentWins()) { //if player i has more opponent wins, i is the new winner
					winner=i;
				} else if (player[i].getOpponentWins()==player[winner].getOpponentWins()){ //if they have same number of opponent wins
					double random = Math.random();
					if (random>=0.5) { //50% chance of either player winning
						winner=i;
					}
				}
			}
		}
		System.out.println("Winner: " + player[winner].toString() + " with " + player[winner].getWins() + " wins"); //winner message
		scanner.close();
	}
	
	//static helper method to organize array with who has the most wins in the front
	private static void sortPlayersByWins(Player[] player) {
		for (int i=0; i<player.length-1; i++) {
			for (int j=0; j<player.length-i-1; j++) {
				if (player[j].getWins()<player[j+1].getWins()) {
					Player temp = player[j];
					player[j]=player[j+1];
					player[j+1]=temp;
				}
			}
		}
		
	}

	
}
