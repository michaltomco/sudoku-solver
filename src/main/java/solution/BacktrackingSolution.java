package solution;

import java.util.List;

class BacktrackingSolution implements Solution {

    private BacktrackingSolution() throws InstantiationException {
    }

    static SudokuBoard solve(SudokuBoard board) {
        if (board == null) {
            throw new NullPointerException("Board cannot be null.");
        }
        System.out.println("Initial board.");
        printBoard(board);
        SudokuBoard result = fillInSudoku(board);
        System.out.println("Result board.");
        printBoard(board);
        return result;
    }

    private static SudokuBoard fillInSudoku(SudokuBoard board) {
        List<Point> allVariableCoordinates = board.getAllVariableCoordinates();
        int iterator = 0;
        while (iterator < allVariableCoordinates.size()) {
            Point coordinates = allVariableCoordinates.get(iterator);
            SudokuCharacter newCharacter = board.getIncrementedCharacter(coordinates);
            board.writeCharacterTo(newCharacter, coordinates);
            if (newCharacter.equals(board.getEmptyCharacter())) {
                iterator -= 1;
            } else if (board.isNumberCorrectlyPlaced(coordinates)) {
                iterator += 1;
            }
        }
        return board;
    }

    public static void printBoard(SudokuBoard board) {
        board.printBoard();
    }
}
