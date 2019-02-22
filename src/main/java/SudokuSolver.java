import board.Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public abstract class SudokuSolver {
    public Board board;
    public SudokuSolver(String filePath) throws IOException {
        this.board = new Board(filePath);
    }
    public abstract Board solve();
}

class RecursiveSolver extends SudokuSolver {
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

    public Board solve() {
        return solve(this.board);
    }

    public RecursiveSolver(String filePath) throws IOException {
        super(filePath);
    }
}

class DFSSolver extends SudokuSolver {
    public Board solve() {
        Stack<Board> stack = new Stack<>();
        stack.push(board);

        while(!stack.empty()) {
            Board element = stack.pop();
            if (element.isSolved()) { return element;}
            ArrayList<Board> neigh = element.getNeighbors();
            for(Board b : neigh) {
                stack.push(b);
            }
        }
        return null;
    }

    public DFSSolver(String filePath) throws IOException {
        super(filePath);
    }
}