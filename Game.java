import java.util.Scanner;
import java.util.*;

public class Game {
    static ArrayList<String> players;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int numPlayers = 0;
        players = new ArrayList<>();

        while (true) {
            System.out.println("Welcome to UNO");
            System.out.println("---------------------------------------");

            while (true) {
                System.out.print("Enter the number of Players (2–4): ");

                try {
                    numPlayers = userInput.nextInt();
                    userInput.nextLine(); // clear the buffer

                    if (numPlayers < 2 || numPlayers > 4) {
                        System.out.println("Invalid number of players! Please enter a number between 2 and 4.\n");
                    } else {
                        break; // valid number, exit inner loop
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number between 2 and 4.\n");
                    userInput.nextLine(); // clear the invalid input
                }
            }


        PlayerOrder playerOrder = new PlayerOrder();

            while (players.size() < numPlayers) {
                System.out.print("Enter the player name: ");
                String playerName = userInput.nextLine();

                if (players.contains(playerName)) {
                    System.out.println("That player already exists.\n");
                    continue;
                }

                players.add(playerName);
                Player player = new Player(playerName);
                playerOrder.addPlayer(player);
            }

            //start game
            GameLogic gameLogic = new GameLogic(players);
            gameLogic.setPlayerOrder(playerOrder);
            gameLogic.playUNOGame();







        }
    }
}