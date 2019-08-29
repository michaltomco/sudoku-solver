package solution;

import java.util.EnumSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SudokuCharacterTest {

    @Test
    void Should_CycleThroughAllCharacters_When_GettingNextCharactersStartingFromEmptyCharacter() {
        SudokuCharacter character = SudokuCharacter.getEmptyCharacter();
        EnumSet<SudokuCharacter> cycledCharacters = EnumSet.noneOf(SudokuCharacter.class);
        do {
            cycledCharacters.add(character);
            character = SudokuCharacter.getNext(character);
        } while (!character.equals(SudokuCharacter.getEmptyCharacter()));
        assertEquals(EnumSet.allOf(SudokuCharacter.class), cycledCharacters);
    }


    @Test
    void Should_NotGetNull_When_GetEmptyCharacter() {
        assertDoesNotThrow((Executable) () -> SudokuCharacter.getEmptyCharacter());
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_GetEnumByCharWithNonViableCharacter() {
        char queried = Character.MIN_VALUE;
        Set<Character> viableChars = SudokuCharacter.getAllViableChars();
        if (!viableChars.contains(queried)) {
            while (viableChars.contains(queried)) {
                queried += 1;
            }
        }
        char unenumerated = queried;
        assertThrows(IllegalArgumentException.class, () -> {
            SudokuCharacter.getEnumByChar(unenumerated);
        });
    }
}