package solution;

import java.util.*;
import java.util.List;

public class EnumBoard implements SudokuBoard {

    private static final Set<SudokuCharacter> NON_EMPTY_CHARACTERS = SudokuCharacter.getNonEmptyCharacters();
    private static final int GRID_DIMENSION = 9;
    private static final int BOX_DIMENSIONS = 3;
    private final List<Point> allVariableCoordinates = new ArrayList<>();
    private SudokuCharacter[][] board = new SudokuCharacter[GRID_DIMENSION][GRID_DIMENSION];

    EnumBoard(char[][] board)  {
        checkInputLegality(board);
        initialize(board);
        checkSolvability();
    }

    private void checkInputLegality(char[][] board) {
        if (!isOfLegalDimensions(board)) {
            throw new IllegalArgumentException(
                    "Illegal board dimensions. Sudoku board needs to be of "
                            + EnumBoard.GRID_DIMENSION + "x" + EnumBoard.GRID_DIMENSION + " dimensions."
            );
        }
        if (!isOnlyOfLegalCharacters(board)) {
            throw new IllegalArgumentException("Input board contains illegal characters.");
        }
    }

    private boolean isOfLegalDimensions(char[][] board) {
        if (board.length != EnumBoard.GRID_DIMENSION) {
            return false;
        }
        for (char[] chars : board) {
            if (chars.length != EnumBoard.GRID_DIMENSION) {
                return false;
            }
        }
        return true;
    }

    private boolean isOnlyOfLegalCharacters(char[][] board) {
        Set<Character> viableCharacters = SudokuCharacter.getAllViableChars();
        for (int i = 0; i < EnumBoard.GRID_DIMENSION; i++) {
            for (int j = 0; j < EnumBoard.GRID_DIMENSION; j++) {
                if (!viableCharacters.contains(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkSolvability() {
        if (!isSolvable()) {
            throw new IllegalArgumentException("The sudoku provided is not solvable.");
        }
    }

    private boolean isSolvable() {
        for (int i = 0; i < EnumBoard.GRID_DIMENSION; i++) {
            for (int j = 0; j < EnumBoard.GRID_DIMENSION; j++) {
                if (NON_EMPTY_CHARACTERS.contains(board[i][j]) && !isNumberCorrectlyPlaced(new Point(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNumberCorrectlyPlaced(Point variableCoordinate) {
        return isRowSuccessfullySolved(variableCoordinate) && isColumnSuccessfullySolved(variableCoordinate) && isBoxSuccessfullySolved(variableCoordinate);
    }

    private boolean isRowSuccessfullySolved(Point variableCoordinate) {
        return isOneOfEachNumberInArray(getRowCharacters(variableCoordinate));
    }

    private boolean isColumnSuccessfullySolved(Point variableCoordinate) {
        return isOneOfEachNumberInArray(getColumnCharacters(variableCoordinate));
    }

    private boolean isBoxSuccessfullySolved(Point variableCoordinate) {
        return isOneOfEachNumberInArray(getBoxCharacters(variableCoordinate));
    }

    private boolean isOneOfEachNumberInArray(SudokuCharacter[] array) {
        Set<SudokuCharacter> duplicateChecker = SudokuCharacter.getNonEmptyCharacters();
        for (SudokuCharacter c : array) {
            if (!c.equals(getEmptyCharacter())) {
                if (NON_EMPTY_CHARACTERS.contains(c) && duplicateChecker.contains(c)) {
                    duplicateChecker.remove(c);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private void initialize(char[][] board) {
        initializeBoard(board);
        initializeVariableNumberCoordinates();
    }

    private void initializeBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                this.board[i][j] = SudokuCharacter.getEnumByChar(board[i][j]);
            }
        }
    }

    private void initializeVariableNumberCoordinates() {
        for (int i = 0; i < GRID_DIMENSION; i++) {
            for (int j = 0; j < GRID_DIMENSION; j++) {
                if (board[i][j].equals(getEmptyCharacter())) {
                    allVariableCoordinates.add(new Point(i, j));
                }
            }
        }
    }

    public List<Point> getAllVariableCoordinates() {
        return Collections.unmodifiableList(allVariableCoordinates);
    }

    public SudokuCharacter getCharacter(Point coordinates) {
        return board[coordinates.getX()][coordinates.getY()];
    }

    private SudokuCharacter[] getRowCharacters(Point coordinates) {
        return board[coordinates.getX()];
    }

    private SudokuCharacter[] getColumnCharacters(Point coordinates) {
        SudokuCharacter[] columnCharacters = new SudokuCharacter[GRID_DIMENSION];
        int iterator = 0;

        for (int column = 0; column < GRID_DIMENSION; column++) {
            columnCharacters[iterator] = board[iterator][coordinates.getY()];
            iterator++;
        }
        return columnCharacters;
    }

    private SudokuCharacter[] getBoxCharacters(Point coordinates) {
        Point boxCoordinates = getBoxCoordinates(coordinates);
        SudokuCharacter[] boxCharacters = new SudokuCharacter[GRID_DIMENSION];
        int iterator = 0;

        for (int row = 0; row < BOX_DIMENSIONS; row++) {
            for (int column = 0; column < BOX_DIMENSIONS; column++) {
                boxCharacters[iterator] = board[row + boxCoordinates.getX()][column + boxCoordinates.getY()];
                iterator++;
            }
        }
        return boxCharacters;
    }

    private Point getBoxCoordinates(Point variableCoordinate) {
        return new Point(
                variableCoordinate.getX() / BOX_DIMENSIONS * BOX_DIMENSIONS,
                variableCoordinate.getY() / BOX_DIMENSIONS * BOX_DIMENSIONS
        );
    }

    public void writeCharacterTo(SudokuCharacter inputCharacter, Point coordinates) {
        if (inputCharacter == null) {
            throw new NullPointerException("Input character cannnot be null.");
        }
        if (coordinates == null) {
            throw new NullPointerException("Coordinates cannnot be null.");
        }
        if (!isCoordinatesLegal(coordinates)) {
            throw new IllegalArgumentException(
                    "Coordinates [" + coordinates.getX() + "," + coordinates.getY() + "] are not writable."
            );
        }
        board[coordinates.getX()][coordinates.getY()] = inputCharacter;
    }

    private boolean isCoordinatesLegal(Point potentialCoordinates) {
        return allVariableCoordinates.contains(potentialCoordinates);
    }

    public SudokuCharacter getIncrementedCharacter(Point coordinates) {
        if (coordinates == null) {
            throw new NullPointerException("Coordinates cannot be null.");
        }
        return SudokuCharacter.getNext(getCharacter(coordinates));
    }

    public SudokuCharacter getEmptyCharacter() {
        return SudokuCharacter.getEmptyCharacter();
    }

    public void printBoard() {
        System.out.println("-------------------------------");
        for (int i = 0; i < GRID_DIMENSION; i++) {
            for (int j = 0; j < GRID_DIMENSION; j++) {
                if (j % 3 == 0) {
                    System.out.print('|');
                }
                System.out.print(" " + board[i][j].getChar() + " ");
            }
            System.out.println("|");
            if (i % 3 == BOX_DIMENSIONS - 1) {
                System.out.println("-------------------------------");
            }
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnumBoard)) {
            return false;
        }
        final EnumBoard that = (EnumBoard) other;

        for (int i = 0; i < EnumBoard.GRID_DIMENSION; i++) {
            for (int j = 0; j < EnumBoard.GRID_DIMENSION; j++) {
                Point commonCoordinate = new Point(i, j);
                if (!that.getCharacter(commonCoordinate).equals(this.getCharacter(commonCoordinate))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        return 43 * Arrays.deepHashCode(board);
    }
}
