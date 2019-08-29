package solution;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumBoardTest {

    private char[][] unsolvedArray = new char[9][9];
    private SudokuBoard unsolvedBoard;

    @BeforeEach
    void setUp() {
        unsolvedArray[0] = new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'};
        unsolvedArray[1] = new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'};
        unsolvedArray[2] = new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'};
        unsolvedArray[3] = new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'};
        unsolvedArray[4] = new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'};
        unsolvedArray[5] = new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'};
        unsolvedArray[6] = new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'};
        unsolvedArray[7] = new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'};
        unsolvedArray[8] = new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'};

        unsolvedBoard = new EnumBoard(unsolvedArray);
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_ArrayDimensions0x0() {
        char[][] empty = new char[0][0];
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(empty));
        assertTrue(thrown.getMessage().contains("Illegal board dimensions. Sudoku board needs to be of 9x9 dimensions."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_ArrayDimensions0x9() {
        char[][] empty = new char[0][9];
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(empty));
        assertTrue(thrown.getMessage().contains("Illegal board dimensions. Sudoku board needs to be of 9x9 dimensions."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_ArrayDimensions9x0() {
        char[][] empty = new char[9][0];
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(empty));
        assertTrue(thrown.getMessage().contains("Illegal board dimensions. Sudoku board needs to be of 9x9 dimensions."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_ArrayDimensions9x10() {
        char[][] empty = new char[9][10];
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(empty));
        assertTrue(thrown.getMessage().contains("Illegal board dimensions. Sudoku board needs to be of 9x9 dimensions."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_ArrayDimensions10x9() {
        char[][] empty = new char[10][9];
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(empty));
        assertTrue(thrown.getMessage().contains("Illegal board dimensions. Sudoku board needs to be of 9x9 dimensions."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_ArrayDimensions10x10() {
        char[][] empty = new char[10][10];
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(empty));
        assertTrue(thrown.getMessage().contains("Illegal board dimensions. Sudoku board needs to be of 9x9 dimensions."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_ArrayOnlyZeros() {
        char[][] array = new char[9][9];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = '0';
            }
        }
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(array));
        assertTrue(thrown.getMessage().contains("Input board contains illegal characters."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_ArrayOfOnesContainsZero() {
        char[][] array = new char[9][9];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = '1';
            }
        }
        array[0][0] = '0';
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(array));
        assertTrue(thrown.getMessage().contains("Input board contains illegal characters."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_SudokuIsLegalButNotSolvableBecauseOfSameNumbersInRowZero() {
        char[][] array = new char[9][9];
        array[0] = new char[]{'5', '3', '.', '.', '7', '.', '5', '.', '.'};
        array[1] = new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'};
        array[2] = new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'};
        array[3] = new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'};
        array[4] = new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'};
        array[5] = new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'};
        array[6] = new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'};
        array[7] = new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'};
        array[8] = new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'};
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(array));
        assertTrue(thrown.getMessage().contains("The sudoku provided is not solvable."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_SudokuIsLegalButNotSolvableBecauseOfSameNumbersInColumnThree() {
        char[][] array = new char[9][9];
        array[0] = new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'};
        array[1] = new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'};
        array[2] = new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'};
        array[3] = new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'};
        array[4] = new char[]{'4', '.', '.', '1', '.', '3', '.', '.', '1'};
        array[5] = new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'};
        array[6] = new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'};
        array[7] = new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'};
        array[8] = new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'};
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(array));
        assertTrue(thrown.getMessage().contains("The sudoku provided is not solvable."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_SudokuIsLegalButNotSolvableBecauseOfSameNumbersInBottomRightBox() {
        char[][] array = new char[9][9];
        array[0] = new char[]{'5', '.', '.', '.', '7', '.', '5', '.', '.'};
        array[1] = new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'};
        array[2] = new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'};
        array[3] = new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'};
        array[4] = new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'};
        array[5] = new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'};
        array[6] = new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'};
        array[7] = new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '2'};
        array[8] = new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'};
        Exception thrown =
                assertThrows(IllegalArgumentException.class, () -> new EnumBoard(array));
        assertTrue(thrown.getMessage().contains("The sudoku provided is not solvable."));
    }

    @Test
    void Should_NotEqual_When_ComparingBoardsWithDifferentChars() {
        unsolvedArray[0][0] = '.';
        SudokuBoard second = new EnumBoard(unsolvedArray);

        assertNotEquals(unsolvedBoard, second);
    }

    @Test
    void Should_PrintBoard_When_PrintBoardMethodCalled() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Solution.printBoard(unsolvedBoard);
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

    @Test
    void Should_BeEqual_When_EqualsOnItself() {
        assertEquals(unsolvedBoard, unsolvedBoard);
    }

    @Test
    void Should_BeFalse_When_EqualsOnDifferentClass() {
        assertNotEquals(unsolvedBoard, new Point(0, 0));
    }

    @Test
    void Should_BeEqualAndSameHashCode_When_HasEqualBoards() {
        SudokuBoard secondBoard = new EnumBoard(unsolvedArray);
        assertEquals(unsolvedBoard, secondBoard);
        assertEquals(unsolvedBoard.hashCode(), secondBoard.hashCode());
    }

    @Test
    void Should_ThrowNullPointerException_When_GetIncrementedCoordinatesIsNull() {
        Exception thrown =
                assertThrows(NullPointerException.class, () -> unsolvedBoard.getIncrementedCharacter(null));
        assertTrue(thrown.getMessage().contains("Coordinates cannot be null."));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_WriteToBoardValueIsNull() {
        Exception thrown =
                assertThrows(NullPointerException.class,
                             () -> unsolvedBoard.writeCharacterTo(null, new Point(0, 0)));
        assertTrue(thrown.getMessage().contains("Input character cannnot be null."));
    }

    @Test
    void Should_ThrowNullPointerException_When_WriteToBoardCoordinatesIsNull() {
        Exception thrown =
                assertThrows(NullPointerException.class,
                             () -> unsolvedBoard.writeCharacterTo(SudokuCharacter.EIGHT, null));
        assertTrue(thrown.getMessage().contains("Coordinates cannnot be null."));
    }

    @Test
    void Should_ThrowNullPointerException_When_WriteToBoardCoordinatesIsOutOfBounds() {
        Exception thrown =
                assertThrows(IllegalArgumentException.class,
                             () -> unsolvedBoard.writeCharacterTo(SudokuCharacter.EIGHT, new Point(10, 10)));
        assertTrue(thrown.getMessage().contains("Coordinates [10,10] are not writable."));
    }
}