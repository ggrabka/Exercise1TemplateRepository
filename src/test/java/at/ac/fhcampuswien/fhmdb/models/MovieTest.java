package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Die Klasse MovieTest testtet die Methoden der Movie-Klasse.

class MovieTest {
    private Movie movie;

    //Initialisiert ein Movie-Objekt vor jedem Testlauf.

    @BeforeEach
    void setUp() {
        movie = new Movie("Inception", "A mind-bending thriller about dream infiltration",
                List.of(Genre.SCIENCE_FICTION, Genre.ACTION, Genre.THRILLER));
    }

    //Testet, ob der  Titel korrekt zurückgegeben wird.

    @Test
    void testGetTitle() {
        assertEquals("Inception", movie.getTitle());
    }

    //Testet, ob die Beschreibung korrekt zurückgegeben wird.

    @Test
    void testGetDescription() {
        assertEquals("A mind-bending thriller about dream infiltration", movie.getDescription());
    }

    //Testet, ob die Genres korrekt zurückgegeben werden.

    @Test
    void testGetGenres() {
        assertEquals(List.of(Genre.SCIENCE_FICTION, Genre.ACTION, Genre.THRILLER), movie.getGenres());
    }

    //Testet die Funktion matches(), wenn ein passender Titel gesucht wird.

    @Test
    void testMatchesTitle() {
        assertTrue(movie.matches("inception", null)); // Case-Insensitive
    }

    //Testet die Funktion matches(), wenn eine nicht passende Suche durchgeführt wird.

    @Test
    void testMatchesNonExistingTitle() {
        assertFalse(movie.matches("Avatar", null));
    }

    //Testet die Funktion matches(), wenn nach einer Beschreibung gesucht wird.

    @Test
    void testMatchesDescription() {
        assertTrue(movie.matches("dream", null));
    }

    //Testet die Funktion matches(), wenn nach einem passenden Genre gesucht wird.

    @Test
    void testMatchesGenre() {
        assertTrue(movie.matches("", Genre.ACTION));
    }

    //Testet die Funktion matches(), wenn ein Genre gesucht wird, das nicht existiert.

    @Test
    void testMatchesNonExistingGenre() {
        assertFalse(movie.matches("", Genre.COMEDY));
    }
}
