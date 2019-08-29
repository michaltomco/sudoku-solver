package solution;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private static SudokuBoard board;

    @BeforeAll
    static void init() {
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

        board = new EnumBoard(array);
    }

    @Test
    void Should_ThrowUnsupportedOperationException_When_CallingSolutionOnInterface() {
        assertThrows(UnsupportedOperationException.class, () -> {Solution.solve(board);});
    }

    @Test
    void Should_PrintBoard_When_PrintBoardMethodCalled() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Solution.printBoard(board);
        String expectedOutput =
                "-------------------------------\n" +
                        "| 5  3  . | .  7  . | .  .  . |\n" +
                        "| 6  .  . | 1  9  5 | .  .  . |\n" +
                        "| .  9  8 | .  .  . | .  6  . |\n" +
                        "-------------------------------\n" +
                        "| 8  .  . | .  6  . | .  .  3 |\n" +
                        "| 4  .  . | 8  .  3 | .  .  1 |\n" +
                        "| 7  .  . | .  2  . | .  .  6 |\n" +
                        "-------------------------------\n" +
                        "| .  6  . | .  .  . | 2  8  . |\n" +
                        "| .  .  . | 4  1  9 | .  .  5 |\n" +
                        "| .  .  . | .  8  . | .  7  9 |\n" +
                        "-------------------------------\n";

        assertEquals(expectedOutput, outContent.toString());
    }
}