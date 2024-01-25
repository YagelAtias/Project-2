package Game;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

public class Cell {
    private int xValue;
    private int yValue;

    /**
     * Constructs a Cell object with specified x and y coordinates.
     *
     * @param xValue The x-coordinate of the cell.
     * @param yValue The y-coordinate of the cell.
     */
    public Cell(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public int getxValue() {
        return xValue;
    }

    public int getyValue() {
        return yValue;
    }

    /**
     * Returns a string representation of the cell in the format "(x, y)".
     *
     * @return A string representation of the cell.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(xValue).append(", ").append(yValue).append(")");
        return sb.toString();
    }

    /**
     * Checks if the cell is empty in the given character array.
     *
     * @param charArray The 2D array representing the game grid.
     * @return True if the cell is empty, false otherwise.
     */
    public boolean isEmpty(playerType[][] charArray) {
        return charArray[getxValue()][getyValue()] == null;
    }
}
