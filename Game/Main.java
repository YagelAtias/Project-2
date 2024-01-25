package Game;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Main class contains the main method and menu logic for the game.
 */
public class Main {
    // Scanner for user input
    public static Scanner input = new Scanner(System.in);

    /**
     * The main method that starts the program by activating the menu.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        activateMenu();
    }

    /**
     * Displays the menu and handles user input to initiate different game scenarios.
     */
    public static void activateMenu() {
        String menuText = """
                Please choose an option from the menu:
                1. Bot VS Bot
                2. Human vs Bot
                3. Exit the program.""";
        System.out.println(menuText);

        int choice;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1 -> {
                        SelfPlayer player1 = new SelfPlayer(playerType.X, null);
                        SelfPlayer player2 = new SelfPlayer(playerType.O, null);
                        SelfGame selfGame = new SelfGame(player1, player2);
                        player1.setGame(selfGame);
                        player2.setGame(selfGame);
                        Thread gameThread = new Thread(selfGame);
                        gameThread.start();
                        validInput = true;
                    }
                    case 2 -> {
                        UserPlayer player1 = new UserPlayer(playerType.X, null);
                        SelfPlayer player2 = new SelfPlayer(playerType.O, null);
                        UserGame userGame = new UserGame(player1, player2);
                        player1.setGame(userGame);
                        player2.setGame(userGame);
                        Thread gameThread = new Thread(userGame);
                        gameThread.start();
                        gameThread.join();
                        validInput = true;
                    }
                    case 3 -> {
                        System.out.println("Exiting");
                        validInput = true;
                    }
                    default -> System.out.println("Wrong input, please try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
