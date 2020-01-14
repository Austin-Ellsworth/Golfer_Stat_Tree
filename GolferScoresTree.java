import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GolferScoresTree {
	
	public static void main (String args[]) throws FileNotFoundException {
		Scanner keyboard = new Scanner(System.in);
		
		
		File inFile = new File("Golfer.txt");
		Scanner fromFile = new Scanner(inFile);
		
		File outFile = new File("Golfer.txt");
		FileOutputStream outstream = new FileOutputStream(outFile);
	    PrintWriter outText = new PrintWriter(outstream);
		
	    TreeBag<Golfer> database = new TreeBag<Golfer>();
	    String target;
	    Golfer player;
		while(fromFile.hasNextLine()) {
			player = new Golfer(fromFile.next());
			player.setNumberOfRounds(fromFile.nextInt());
			player.setHandicap(fromFile.nextInt());
			player.setAverageScore(fromFile.nextDouble());
			database.add(player);
		}
		
		int choice = 0;
		while(choice != 7) {
			choice = menu();
			switch(choice) {
			case 1: 
				System.out.println("1. Display listing to screen of all golfers stats(ordered by lastname)");
				System.out.println();
				database.display();
				System.out.println();
				break;
				
			case 2:
				System.out.println("2. Display the golfers in current tree format");
				database.displayAsTree();
				System.out.println();
				break;
				
			case 3:
				System.out.println("3. Find and display one individual golfers stats");
				System.out.print("Who would you like to locate and display? \nNOTE: First Letter of lastname MUST be capitalized!\n");
				target = keyboard.next();
				player = database.retrieve(new Golfer(target));
				if(player != null) {
					System.out.println(player.toString());
				} else {
					System.out.println("No golfer with name: " + target);
				}
				System.out.println();
				break;
				
			case 4:
				System.out.println("4. Update an individual golfers stats(by adding an additional score)");
				System.out.print("Which golfers score average would you like to add to?\nNOTE: First Letter of lastname MUST be capitalized!\n ");
				target = keyboard.next();
				System.out.println("What is the score you'd like to add? ");
				double update = keyboard.nextInt();
				player = database.retrieve(new Golfer(target));
				if(player != null) {
					player.setAverageScore(player.getAverageScore() * player.getNumberOfRounds());
					player.setAverageScore(player.getAverageScore() + update);
					player.setNumberOfRounds(player.getNumberOfRounds() + 1);
					player.setAverageScore(player.getAverageScore() / player.getNumberOfRounds());
					System.out.println("\nUpdated " + player.getLastname() + "' Stats");
					player.toString();
					System.out.println(player.toString());
				} else {
					System.out.println("No golfer with name: " + target);
				}
				System.out.println();
				break;
				
			case 5:
				System.out.println("5. Remove a golfer from the Database");
				System.out.print("Who would you like to delete from the database? \nNOTE: First Letter of lastname MUST be capitalized!\n");
				target = keyboard.next();
				player = database.retrieve(new Golfer(target));
				if(player != null) {
					database.remove(player);
					System.out.println(target + " has been removed!");
				} else {
					System.out.println("No golfer with name: " + target);
				}
				System.out.println();
				break;
				
			case 6:
				System.out.println("6. Add a new golfer to the Database");
				System.out.print("Who would you like to add to the database? \nNOTE: First Letter of lastname MUST be capitalized!\n");
				target = keyboard.next();
				player = new Golfer(target);
				database.add(player);
				System.out.println(target + " has been added!");
				System.out.println();
				break;
				
			case 7: 
				System.out.println("7. Quit and update datafile");
				BTNode<Golfer> cursor;
				cursor = database.getRoot();
				write(cursor, outText);
				outText.close();
				System.out.println("Thank you for using my program.");
				break;
					
			}
		}	
		keyboard.close();
		fromFile.close();
		outText.close();
	}//end main
	
	public static int menu() {
	    int choice = 0;
	    Scanner keyboard = new Scanner(System.in);
	    while(choice < 1 || choice > 7) {
	    	System.out.println("1. Display listing to screen of all golfers stats(ordered by lastname)");
	    	System.out.println("2. Display the golfers in current tree format");
	    	System.out.println("3. Find and display one individual golfers stats");
	    	System.out.println("4. Update an individual golfers stats(by adding an additional score)");
	    	System.out.println("5. Remove a golfer from the Database");
	    	System.out.println("6. Add a new golfer to the Database");
	    	System.out.println("7. Quit and update datafile");
	        System.out.print("Choice : ");
	       try {
	          choice = keyboard.nextInt();
	       }
	       catch(InputMismatchException e) {
	          System.out.println("Please enter a number!");
	       }
	       keyboard.nextLine(); //clear buffer
	       System.out.println();
	    }
	    return choice;
	}//end menu
	
	private static void write(BTNode<Golfer> cursor, PrintWriter outText) {
		BTNode<Golfer> right, left;
		
		outText.println(cursor.getData().toString());
		if (cursor.getLeft() != null){
			left = cursor.getLeft();
			write(left, outText);
		}
		if (cursor.getRight() != null){
			right = cursor.getRight();
			write(right, outText);
		}
	}//end write
}
