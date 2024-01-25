package Game;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

import java.util.Scanner;

/**
 * The UserPlayer class represents a player controlled by a user in the game.
 * It extends the Player class and implements the Runnable interface.
 */
public class UserPlayer extends Player {
    // Scanner for user input
    public static Scanner input = new Scanner(System.in);

    /**
     * Constructs a UserPlayer object with the specified player type and game.
     *
     * @param type The type of the player.
     * @param game The game in which the player participates.
     */
    public UserPlayer(playerType type, Game game) {
        super(type, game);
    }

    /**
     * Makes a move for the user player based on user input.
     * This method is synchronized to ensure proper turn-taking.
     */
    @Override
    public synchronized void makeMove() {
        // Check if it's the turn of the current player
        if (getGame().getTurn().equals(this)) {
            Cell chosenCell;

            // Display a message for the user's turn
            System.out.println("""
                    Hi player! It's your turn now.
                    Here is the board and the available moves for you to play.
                    Choose wisely!!!
                    """);

            // Print the current state of the game board and the available moves
            getGame().printGameBoard();
            getGame().printFreeCells();

            // Get a valid user choice for the cell to fill
            boolean validChoice = false;
            int choice = -1;
            while (!validChoice) {
                try {
                    System.out.println("Which cell do you choose to fill?");
                    choice = input.nextInt() - 1;
                    if (choice >= 0 && choice < getGame().getFreeCells().length && getGame().getFreeCells()[choice] != null) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Please choose a valid cell.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    input.nextLine();
                }
            }

            // Update the chosen cell and the game state
            chosenCell = getGame().getFreeCells()[choice];
            updateChosenCell(chosenCell, choice);

            // Switch to the next player's turn
            getGame().setCurrentPlayer((this.equals(getGame().getPlayer1())) ? getGame().getPlayer2() : getGame().getPlayer1());
        }
    }

    /**
     * The run method that contains the main logic of the user player's actions during the game.
     */
    @Override
    public void run() {
        // Keep making moves until the game is over
        while (!getGame().gameOver()) {
            try {
                // Add a delay to simulate the user player's decision-making time
                Thread.sleep(500);
                makeMove();
            } catch (InterruptedException e) {
                // Handle any interruptions by throwing a runtime exception
                throw new RuntimeException(e);
            }
        }
    }
}
