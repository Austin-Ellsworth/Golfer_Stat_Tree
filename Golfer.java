
public class Golfer implements Comparable<Golfer>{

	private String lastname;
	private int numberOfRounds;
	private double averageScore;
	private int handicap;
	
	public Golfer(String lastname, int numberOfRounds, double averageScore, int handicap) {
		this.lastname = lastname;
		this.numberOfRounds = numberOfRounds;
		this.averageScore = averageScore;
		this.handicap = handicap;
	}

	public Golfer(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public int getNumberOfRounds() {
		return numberOfRounds;
	}
	
	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}
	
	public double getAverageScore() {
		return averageScore;
	}
	
	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}
	
	public int getHandicap() {
		return handicap;
	}
	
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}
	
	public int compareTo(Golfer golfer){
		return this.lastname.compareTo(golfer.lastname);
	}
	
	public String toString() {
		return "Lastname: " + lastname + " | Numer of Rounds: " + numberOfRounds + " | Average Score: " + averageScore + " | Handicap: " + handicap;
	}
}
