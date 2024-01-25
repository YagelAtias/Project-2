package Game;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

import java.util.Random;

/**
 * The SelfPlayer class represents a player controlled by the computer (self) in the game.
 * It extends the Player class and implements the Runnable interface.
 */
public class SelfPlayer extends Player {
    // Random object for making random moves
    private final Random rand = new Random();

    /**
     * Constructs a SelfPlayer object with the specified player type and game.
     *
     * @param type The type of the player.
     * @param game The game in which the player participates.
     */
    public SelfPlayer(playerType type, Game game) {
        super(type, game);
    }

    /**
     * Makes a move for the self player by choosing a random cell on the game board.
     * This method is synchronized to ensure proper turn-taking.
     */
    @Override
    public synchronized void makeMove() {
        // Check if it's the turn of the current player and the game is not over
        if (getGame().getTurn().equals(this) && !getGame().gameOver()) {
            Cell chosenCell;

            // Print the current state of the game board
            getGame().printGameBoard();

            // Choose a random cell from the free cells
            int choice = rand.nextInt(getGame().getFreeCells().length);
            chosenCell = getGame().getFreeCells()[choice];

            // Update the chosen cell and the game state
            updateChosenCell(chosenCell, choice);

            // Switch to the next player's turn
            getGame().setCurrentPlayer((this.equals(getGame().getPlayer1())) ? getGame().getPlayer2() : getGame().getPlayer1());
        }
    }

    /**
     * The run method that contains the main logic of the self player's actions during the game.
     */
    @Override
    public void run() {
        // Keep making moves until the game is over
        while (!getGame().gameOver()) {
            try {
                // Add a delay to simulate the self player's decision-making time
                Thread.sleep(500);
                makeMove();
            } catch (InterruptedException e) {
                // Handle any interruptions by throwing a runtime exception
                throw new RuntimeException(e);
            }
        }
    }
}
