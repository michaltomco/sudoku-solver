package solution;

import org.junit.jupiter.api.Test;
import solution.Solution;
import solution.BacktrackingSolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BacktrackingSolutionTest {

    @Test
    void Should_ThrowIllegalArgumentException_When_SolveOnNull() {
        assertThrows(NullPointerException.class,
                     () ->  BacktrackingSolution.solve(null));
    }

    @Test
    void Should_GetSolvedSudoku_When_GetUnsolvedSudoku() {
        char[][] array = new char[9][9];
        array[0] = new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'};
        array[1] = new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'};
        array[2] = new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'};
        array[3] = new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'};
        array[4] = new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'};
        array[5] = new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'};
        array[6] = new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'};
        array[7] = new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'};
        array[8] = new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'};

        SudokuBoard resultBoard = BacktrackingSolution.solve(new EnumBoard(array));

        char[][] resultArray = new char[9][9];
        resultArray[0] = new char[]{'5', '3', '4', '6', '7', '8', '9', '1', '2'};
        resultArray[1] = new char[]{'6', '7', '2', '1', '9', '5', '3', '4', '8'};
        resultArray[2] = new char[]{'1', '9', '8', '3', '4', '2', '5', '6', '7'};
        resultArray[3] = new char[]{'8', '5', '9', '7', '6', '1', '4', '2', '3'};
        resultArray[4] = new char[]{'4', '2', '6', '8', '5', '3', '7', '9', '1'};
        resultArray[5] = new char[]{'7', '1', '3', '9', '2', '4', '8', '5', '6'};
        resultArray[6] = new char[]{'9', '6', '1', '5', '3', '7', '2', '8', '4'};
        resultArray[7] = new char[]{'2', '8', '7', '4', '1', '9', '6', '3', '5'};
        resultArray[8] = new char[]{'3', '4', '5', '2', '8', '6', '1', '7', '9'};

        SudokuBoard targetBoard = new EnumBoard(resultArray);
        assertEquals(targetBoard, resultBoard);
    }
}