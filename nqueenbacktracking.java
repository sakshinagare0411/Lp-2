import java.util.Scanner;

public class nqueenbacktracking {
    private int N;

    // Method to print the chessboard with queens placed
    private void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if placing a queen at board[row][col] is safe
    private boolean isSafe(int[][] board, int row, int col) {
        // Check the row on the left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal on left side
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Recursive method to solve the N-Queens problem
    private boolean solveNQUtil(int[][] board, int col) {
        // If all queens are placed, return true
        if (col >= N) {
            printSolution(board);
            return true;
        }

        boolean isValid = false;
        // Try placing a queen in each row of the current column
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1; // Place the queen
                isValid = isValid | solveNQUtil(board, col + 1); // Recur for next column
                board[i][col] = 0; // Backtrack (remove queen)
            }
        }
        return isValid;
    }

    // Method to solve the N-Queens problem
    public void solveNQ() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of queens:");
        N = scanner.nextInt();
        scanner.close();

        int[][] board = new int[N][N];

        // Initialize board with all zeros
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }

        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution does not exist");
        }
    }

    public static void main(String[] args) {
        nqueenbacktracking nQueens = new  nqueenbacktracking();
        nQueens.solveNQ();
    }
}
