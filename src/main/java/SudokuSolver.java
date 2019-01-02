import board.Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class SudokuSolver {
    private Board board;
    private Board solution;

    private SudokuSolver(String filePath) throws IOException {
        this.board = new Board(filePath);
        this.solution = new Board();
    }


    private void solve() {
        HashSet<String> visited = new HashSet<>();

        Stack<Board> stack = new Stack<>();
        stack.add(this.board);

        while (!stack.isEmpty()) {
            Board node = stack.pop();
            if (node.isSolved()) {
                this.solution = node;
                stack.clear();
            } else {
                visited.add(node.toString());
                ArrayList<Board> neighbors = node.getNeighbors();

                for (Board neighbor : neighbors) {
                    if (!visited.contains(neighbor.toString())) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException {
        SudokuSolver solver = new SudokuSolver("src/test/resources/trivial_puzzle.sdk");
        System.out.println(solver.board);

        solver.solve();

        System.out.println(solver.solution);
    }
}
