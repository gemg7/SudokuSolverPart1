import board.Board;

import java.io.IOException;

public class RecursiveSolver extends SudokuSolver {

    public Board solve(Board board) {
        if (board.isSolved()) {
            return board;
        } else {
            for (Board neighbor : board.getNeighbors()) {
                Board potentialSolution = solve(neighbor);
                if (potentialSolution != null) {
                    return potentialSolution;
                }
            }
        }

        return null;
    }

    public Board solve() {
        return solve(this.board);
    }

    public void SudokuSolver(String filePath) throws IOException {
        this.board = new Board(filePath);
    }
}
