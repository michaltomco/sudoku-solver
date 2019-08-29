package solution;

import java.util.*;

public enum SudokuCharacter {
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    EMPTY('.');

    private final char character;
    private static Map<Character, SudokuCharacter> characters = new HashMap<>();

    static {
        for (SudokuCharacter enumCharacter : EnumSet.allOf(SudokuCharacter.class)) {
            characters.put(enumCharacter.getChar(), enumCharacter);
        }
    }

    SudokuCharacter(char character) {
        this.character = character;
    }

    public char getChar() {
        return character;
    }

    public static Set<Character> getAllViableChars() {
        return Collections.unmodifiableSet(characters.keySet());
    }

    public static SudokuCharacter getEnumByChar(char character) {
        if (!getAllViableChars().contains(character)) {
            throw new IllegalArgumentException("No such sudoku character exists.");
        }
        return characters.get(character);
    }

    public static Set<SudokuCharacter> getNonEmptyCharacters() {
        return EnumSet.complementOf(EnumSet.of(SudokuCharacter.getEmptyCharacter()));
    }

    public static SudokuCharacter getEmptyCharacter() {
        return SudokuCharacter.EMPTY;
    }

    public static SudokuCharacter getNext(SudokuCharacter source) {
        switch (source) {
            case EMPTY:
                return SudokuCharacter.ONE;
            case ONE:
                return SudokuCharacter.TWO;
            case TWO:
                return SudokuCharacter.THREE;
            case THREE:
                return SudokuCharacter.FOUR;
            case FOUR:
                return SudokuCharacter.FIVE;
            case FIVE:
                return SudokuCharacter.SIX;
            case SIX:
                return SudokuCharacter.SEVEN;
            case SEVEN:
                return SudokuCharacter.EIGHT;
            case EIGHT:
                return SudokuCharacter.NINE;
            case NINE:
                return SudokuCharacter.EMPTY;
            default:
                throw new AssertionError("Not a viable Sudoku character.");
        }
    }
}
