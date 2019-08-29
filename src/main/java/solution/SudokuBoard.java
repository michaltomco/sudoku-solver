package solution;

import java.util.List;

public interface SudokuBoard {
    boolean isNumberCorrectlyPlaced(Point coordinates);
    void printBoard();
    List<Point> getAllVariableCoordinates();
    void writeCharacterTo(SudokuCharacter character, Point coordinates);
    SudokuCharacter getCharacter(Point coordinates);
    SudokuCharacter getIncrementedCharacter(Point coordinates);
    SudokuCharacter getEmptyCharacter();
}
