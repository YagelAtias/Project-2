package Game;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

import java.util.Objects;

/**
 * The abstract Player class represents a player in the game and extends the Thread class.
 */
public abstract class Player extends Thread {
    // The type of the player
    private playerType type;

    // The game in which the player participates
    private Game game;

    /**
     * Constructs a Player object with the specified player type and game.
     *
     * @param type The type of the player.
     * @param game The game in which the player participates.
     */
    public Player(playerType type, Game game) {
        this.type = type;
        this.game = game;
    }

    public playerType getType() {
        return type;
    }
    public void setType(playerType type) {
        this.type = type;
    }

    /**
     * Gets the game in which the player participates.
     *
     * @return The game in which the player participates.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the game in which the player participates.
     *
     * @param game The game in which the player participates.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Checks if two Player objects are equal based on their player types.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return Objects.equals(type, player.type);
    }

    /**
     * Updates the chosen cell during the game.
     *
     * @param cell   The cell chosen by the player.
     * @param choice The index of the chosen cell.
     */
    public synchronized void updateChosenCell(Cell cell, int choice) {
        getGame().fillCell(cell.getxValue(), cell.getyValue());
        getGame().getFreeCells()[choice] = null;
        getGame().setFreeCells(game.updateFreeCells());
    }

    /**
     * Abstract method representing the move-making logic for the player.
     */
    public abstract void makeMove();
}
