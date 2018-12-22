package board;

import board.reader.BoardReader;
import board.reader.BoardReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Board {
    private final List<List<Integer>> board;

    public Board(String filePath) throws IOException {
        BoardReader reader = BoardReaderFactory.getReader(filePath);
        this.board = reader.parseBoard(filePath);
    }

    Board(List<List<Integer>> board) {
        this.board = new ArrayList<>();

        for (int i = 0; i < board.size(); i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < board.get(i).size(); j++) {
                this.board.get(i).add(board.get(i).get(j));
            }
        }
    }

    List<List<Integer>> getBoard() {
        return board;
    }

    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (List<Integer> row : this.board) {
            StringBuilder rowString = new StringBuilder();
            for (Integer item : row) {
                if (item == null) {
                    rowString.append('.');
                } else {
                    rowString.append(item.toString());
                }
            }

            boardString.append(rowString);
            boardString.append("\n");
        }

        return boardString.toString();
    }

    private int getNumBlanks() {
        int blanks = 0;
        for (List<Integer> row : this.board) {
            for (Integer cell : row) {
                if (cell == null) {
                    blanks++;
                }
            }
        }

        return blanks;
    }

    boolean isValid() {
        HashSet<Integer> rowConstraint = new HashSet<>();
        HashSet<Integer> colConstraint = new HashSet<>();
        HashSet<Integer> gridConstraint = new HashSet<>();

        // Row and Column Constraints
        for (int i = 0; i < this.board.size(); i++) {
            for (int j = 0; j < this.board.get(i).size(); j++) {
                Integer value = this.board.get(i).get(j);
                if (rowConstraint.contains(value)) {
                    System.out.println("Row failure i:" + i + " j:" + j);
                    return false;
                }

                if (value != null) {
                    rowConstraint.add(value);
                }

                value = this.board.get(j).get(i);
                if (colConstraint.contains(value)) {
                    System.out.println("Column failure i:" + i + " j:" + j);
                    return false;
                }

                if (value != null) {
                    colConstraint.add(value);
                }
            }

            rowConstraint.clear();
            colConstraint.clear();
        }

        // Grid Constraint
        for (int i = 0; i < this.board.size(); i = i + 3) {
            for (int j = 0; j < this.board.get(i).size(); j = j + 3) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        Integer value = this.board.get(i + k).get(j + l);
                        if (gridConstraint.contains(value)) {
                            System.out.println("Grid constraint failure i:" + i + " j:" + j + " k:" + k + " l:" + l);
                            return false;
                        }
                        if (value != null) {
                            gridConstraint.add(value);
                        }
                    }
                }
                gridConstraint.clear();
            }

        }

        return true;
    }

    boolean isSolved() {
        return isValid() && getNumBlanks() == 0;
    }
}
