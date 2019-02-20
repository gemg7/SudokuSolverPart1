// Eric Anthony
import board.Board;

import java.io.IOException;

public class SudokuSolver {
    private Board board;

    private SudokuSolver(String filePath) throws IOException {
        this.board = new Board(filePath);
    }

    private Board solve(Board board) {
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


    private Board solve() {
        return solve(this.board);
    }

    public static void main(String [] args) throws IOException {
        SudokuSolver solver = new SudokuSolver("src/test/resources/trivial_puzzle.sdk");
        System.out.println(solver.board);
        System.out.println(solver.solve());
    }
}
