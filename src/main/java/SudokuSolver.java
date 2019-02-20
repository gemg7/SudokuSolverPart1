// Eric Anthony
import board.Board;

import java.io.IOException;

public abstract class SudokuSolver {
    public Board board;
   // public SudokuSolver() {}
//    public SudokuSolver(String filePath) throws IOException {
//        this.board = new Board(filePath);
//    }
    public abstract Board solve();

}
