package Game;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

import java.util.Random;

/**
 * The SelfGame class extends the Game class and implements the Runnable interface.
 * It represents a game where two SelfPlayer objects compete against each other.
 */
public class SelfGame extends Game implements Runnable {

    /**
     * Constructs a SelfGame object with two SelfPlayer participants.
     *
     * @param player1 The first SelfPlayer participating in the game.
     * @param player2 The second SelfPlayer participating in the game.
     */
    public SelfGame(SelfPlayer player1, SelfPlayer player2) {
        super(player1, player2);

        // Randomly assign player types (X or O)
        Random rand = new Random();
        int randomInt = rand.nextInt(2); // 0 or 1
        getPlayer1().setType((randomInt == 0) ? playerType.X : playerType.O);
        getPlayer2().setType((getPlayer1().getType() == playerType.X) ? playerType.O : playerType.X);

        // Set the initial player as the current player
        setCurrentPlayer(getPlayer1());
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
