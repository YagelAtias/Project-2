package Game;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

import java.util.Random;

/**
 * The UserGame class extends the Game class and implements the Runnable interface.
 * It represents a game where two players (including a user) compete against each other.
 */
public class UserGame extends Game implements Runnable {

    /**
     * Constructs a UserGame object with two players (including a user).
     *
     * @param player1 The first player participating in the game.
     * @param player2 The second player participating in the game.
     */
    public UserGame(Player player1, Player player2) {
        super(player1, player2);

        // Randomly assign player types (X or O)
        Random rand = new Random();
        int randomInt = rand.nextInt(2); // 0 or 1
        player1.setType((randomInt == 0) ? playerType.X : playerType.O);
        player2.setType((player1.getType() == playerType.X) ? playerType.O : playerType.X);

        // Set the initial player as the current player
        setCurrentPlayer(player1);
    }

    /**
     * Starts the game by initiating the threads for both players.
     */
    @Override
    public void run() {
        getPlayer1().start();
        getPlayer2().start();
    }
}
