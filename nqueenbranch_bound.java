import java.util.*;

public class nqueenbranch_bound{

    static int N;

    // Method to print the solution board
    static void printSol(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                if (board[i][j] == 1)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            System.out.println();
        }
        System.out.println();
    }

    // Check if placing a queen at board[row][col] is safe
    static boolean isSafe(int row, int col, boolean[] rows, boolean[] leftDiagonals, boolean[] rightDiagonals) {
        return !rows[row] && !leftDiagonals[row + col] && !rightDiagonals[col - row + N - 1];
    }

    // Recursive method to solve N-Queens problem
    static void solve(int[][] board, int col, boolean[] rows, boolean[] leftDiagonals, boolean[] rightDiagonals) {
        if (col >= N) {
            printSol(board);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSafe(i, col, rows, leftDiagonals, rightDiagonals)) {
                rows[i] = true;
                leftDiagonals[i + col] = true;
                rightDiagonals[col - i + N - 1] = true;
                board[i][col] = 1;

                solve(board, col + 1, rows, leftDiagonals, rightDiagonals);

                rows[i] = false;
                leftDiagonals[i + col] = false;
                rightDiagonals[col - i + N - 1] = false;
                board[i][col] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the board (N): ");
        N = scanner.nextInt();

        int[][] board = new int[N][N];
        boolean[] rows = new boolean[N];
        boolean[] leftDiagonals = new boolean[2 * N - 1];
        boolean[] rightDiagonals = new boolean[2 * N - 1];

        solve(board, 0, rows, leftDiagonals, rightDiagonals);

        scanner.close();
    }
}

