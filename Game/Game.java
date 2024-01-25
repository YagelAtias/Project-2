package Game;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

public abstract class Game {
    // Constants for the game board size
    private final int length = 5;

    // 2D array representing the game board
    private final playerType[][] gameBoard = new playerType[length][length];

    // Players in the game
    private final Player player1;
    private final Player player2;

    // Current player making a move
    private Player currentPlayer;

    // Array to store free cells on the game board
    private Cell[] freeCells = new Cell[length * length];

    // Flag indicating whether the game is over
    private boolean isGameOver = false;

    public Game(Player player1, Player player2) {
        synchronized (gameBoard) {
            createBoard();
        }
        this.player1 = player1;
        this.player2 = player2;
    }

    public synchronized void printGameBoard() {
        System.out.println("Game Board:");
        for (playerType[] row : gameBoard) {
            for (int j = 0; j < row.length; j++) {
                if (row[j] == null) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" " + row[j] + " ");
                }
                if (j < row.length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            // Print horizontal separator
            for (int j = 0; j < row.length * 4 - 1; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();
    }


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private void createBoard() {
        int counter = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                gameBoard[i][j] = null;
                Cell cell = new Cell(i, j);
                if (cell.isEmpty(gameBoard)) {
                    freeCells[counter] = cell;
                    counter++;
                }
            }
        }
    }


    public synchronized Cell[] getFreeCells() {
        return freeCells;
    }

    public synchronized void setFreeCells(Cell[] freeCells) {
        this.freeCells = freeCells;
    }

    public synchronized void printFreeCells() {
        System.out.println("Empty cells:");
        for (int i = 0; i < freeCells.length; i++) {
            if (freeCells[i] != null) System.out.println(i + 1 + ". " + freeCells[i]);
        }
        System.out.println();
    }

    public int countNullCells() {
        int counter = 0;
        for (Cell cell : freeCells) {
            if (cell == null) counter++;
        }
        return counter;
    }

    public synchronized Cell[] updateFreeCells() {
        int j = 0;
        int newArrayLength = freeCells.length - countNullCells();
        Cell[] newCells = new Cell[newArrayLength];
        for (Cell cell : freeCells) {
            if (cell != null) {
                newCells[j] = cell;
                j++;
            }
        }
        return newCells;
    }

    /**
     * Fills the specified cell on the game board with the current player's type.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     */
    public void fillCell(int x, int y) {
        Cell cell = new Cell(x, y);
        synchronized (gameBoard) {
            if (cell.isEmpty(gameBoard)) {
                currentPlayer = getTurn();
                gameBoard[x][y] = currentPlayer.getType();
            } else {
                System.out.println("This cell is already full bruv.");
            }
        }
    }

    /**
     * Checks if the game is over (either a player has won or the board is full).
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean gameOver() {
        if (isGameOver) {
            return true;
        }
        if (isWinner(player1.getType())) {
            printGameBoard();
            System.out.println(player1.getType() + " Wins, EZ GAME THANKS FOR TUTORIAL.");
            isGameOver = true;
            return true;
        }

        if (isWinner(player2.getType())) {
            printGameBoard();
            System.out.println(player2.getType() + " Wins, FATALITY!");
            isGameOver = true;
            return true;
        }

        if (isFullBoard()) {
            printGameBoard();
            System.out.println("The board is full");
            isGameOver = true;
            return true;
        }

        return false;
    }

    /**
     * Sets the current player making a move.
     *
     * @param currentPlayer The current player.
     */
    public synchronized void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private boolean isFullBoard() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                Cell cell = new Cell(i, j);
                if (cell.isEmpty(gameBoard)) return false;
            }
        }
        return true;
    }

    private boolean isWinner(playerType player) {
        // Check rows
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= length - 4; j++) {
                boolean rowWin = true;
                for (int k = 0; k < 4; k++) {
                    if (gameBoard[i][j + k] != player) {
                        rowWin = false;
                        break;
                    }
                }
                if (rowWin) {
                    return true;
                }
            }
        }

        // Check columns
        for (int i = 0; i <= length - 4; i++) {
            for (int j = 0; j < length; j++) {
                boolean colWin = true;
                for (int k = 0; k < 4; k++) {
                    if (gameBoard[i + k][j] != player) {
                        colWin = false;
                        break;
                    }
                }
                if (colWin) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i <= length - 4; i++) {
            for (int j = 0; j <= length - 4; j++) {
                boolean diagonalWin1 = true;
                boolean diagonalWin2 = true;
                for (int k = 0; k < 4; k++) {
                    if (gameBoard[i + k][j + k] != player) {
                        diagonalWin1 = false;
                    }
                    if (gameBoard[i + k][j + 3 - k] != player) {
                        diagonalWin2 = false;
                    }
                }
                if (diagonalWin1 || diagonalWin2) {
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized Player getTurn() {
        return currentPlayer;
    }

}
