import java.util.Scanner;

public class Connect4 {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY_SLOT = '-';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';
    private static char[][] board = new char[ROWS][COLS];

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        boolean gameWon = false;
        boolean playerOneTurn = true;
        Scanner scanner = new Scanner(System.in);

        while (!gameWon) {
            if (playerOneTurn) {
                System.out.println("Player 1 (X), choose a column (0-6): ");
            } else {
                System.out.println("Player 2 (O), choose a column (0-6): ");
            }
            int column = scanner.nextInt();

            if (column < 0 || column >= COLS || board[0][column] != EMPTY_SLOT) {
                System.out.println("Invalid move, try again.");
                continue;
            }

            dropDisc(column, playerOneTurn ? PLAYER_ONE : PLAYER_TWO);
            printBoard();
            gameWon = checkWin(playerOneTurn ? PLAYER_ONE : PLAYER_TWO);

            if (gameWon) {
                if (playerOneTurn) {
                    System.out.println("Player 1 (X) wins!");
                } else {
                    System.out.println("Player 2 (O) wins!");
                }
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            playerOneTurn = !playerOneTurn;
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = EMPTY_SLOT;
            }
        }
    }

    private static void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void dropDisc(int column, char disc) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == EMPTY_SLOT) {
                board[row][column] = disc;
                break;
            }
        }
    }

    private static boolean checkWin(char disc) {
        // Check horizontal, vertical, and diagonal (both directions)
        return checkHorizontal(disc) || checkVertical(disc) || checkDiagonal(disc);
    }

    private static boolean checkHorizontal(char disc) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == disc && board[row][col + 1] == disc &&
                    board[row][col + 2] == disc && board[row][col + 3] == disc) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkVertical(char disc) {
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row <= ROWS - 4; row++) {
                if (board[row][col] == disc && board[row + 1][col] == disc &&
                    board[row + 2][col] == disc && board[row + 3][col] == disc) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonal(char disc) {
        // Check for diagonals from bottom-left to top-right
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == disc && board[row - 1][col + 1] == disc &&
                    board[row - 2][col + 2] == disc && board[row - 3][col + 3] == disc) {
                    return true;
                }
            }
        }
        // Check for diagonals from bottom-right to top-left
        for (int row = 3; row < ROWS; row++) {
            for (int col = 3; col < COLS; col++) {
                if (board[row][col] == disc && board[row - 1][col - 1] == disc &&
                    board[row - 2][col - 2] == disc && board[row - 3][col - 3] == disc) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }
}

