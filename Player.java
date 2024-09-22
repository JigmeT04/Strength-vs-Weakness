package StrengthVsWeakness;


public class Player {
	public static int globalPlayerCount = 0;
	private int secret;
	private int strength;
	private int number = 1;
	private int wins;
	private int OpponentWins;
	
	//constructor for creating the object
	public Player() {
		globalPlayerCount++;
		this.number=globalPlayerCount;
		this.wins=0;
		this.OpponentWins=0;
		this.strength = (int)(Math.random()*(9)+1);
		this.secret = (int)(Math.random()*(9)+1);
	}
	public void play(Player opponent) {
		if (opponent==null) { //if there is no p2, p1 gets a free win
			this.wins++;
		} else { //otherwise play
			if (this.secret==opponent.strength && this.strength!=opponent.secret) { //if p1 secret equals p2 strength and p2 strength doesn't equal p1 secret
				this.wins++; //p1 wins
				opponent.OpponentWins++; //p2 opponent wins goes up by 1
			}
			else if (this.strength==opponent.secret && this.secret!=opponent.strength) { //if other way arround
				opponent.wins++; //p2 wins
				this.OpponentWins++; //p1 opponent wins goes up by 1
			} else { //if no matches randomize win accordingly
				double WinPercentage = (double)this.strength/(this.strength+opponent.strength);
				double randomDouble = Math.random();
				if (WinPercentage >= randomDouble) { //if win percentage is bigger than random number from 0-1
					this.wins++; //p1 wins
					opponent.OpponentWins++;
				} else { //else p2 wins
					opponent.wins++;
					this.OpponentWins++;
				}
			}
		}
	}
	public int getWins() {
		return wins;
	}
	public int getOpponentWins() {
		return OpponentWins;
	}
	public String toString() {
		return "Player " + number + " (Strength " + strength + ", Secret " + secret + ")";
	}
	public int getStrength() {
		return strength;
	}
	public int getSecret() {
		return secret;
	}
	public int getNumber() {
		return number;
	}
}
