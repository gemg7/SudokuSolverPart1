import java.io.IOException;

public class main {
    public static void main(String [] args) throws IOException {
        RecursiveSolver solver = new SudokuSolver("src/test/resources/trivial_puzzle.sdk");
        System.out.println(solver.board);
        System.out.println(solver.solve());
    }
}
