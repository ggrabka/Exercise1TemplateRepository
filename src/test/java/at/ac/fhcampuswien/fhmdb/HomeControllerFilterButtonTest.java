package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class HomeControllerFilterButtonTest {

    private HomeController controller;
    private ObservableList<Movie> allMovies;

    @BeforeEach
    void setUp() {
        controller = new HomeController();

        allMovies = FXCollections.observableArrayList(
                new Movie("Inception", "A mind-bending thriller",Arrays.asList(Genre.ACTION)),
                new Movie("Titanic", "A tragic love story", Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                new Movie("Interstellar", "Space exploration epic", Arrays.asList(Genre.DRAMA)),
                new Movie("The Matrix",  "A computer hacker learns about reality", Arrays.asList(Genre.ACTION)),
                new Movie("The Notebook", "Romantic drama", Arrays.asList(Genre.ROMANCE, Genre.DRAMA))
        );

        controller.allMovies = allMovies;
        controller.observableMovies.setAll(allMovies);
    }

    @Test
    void testFilterByValidGenre() {

        controller.genreComboBox.setValue(Genre.ACTION);
        controller.filterByGenre();

        ObservableList<Movie> filtered = controller.observableMovies;

        assertEquals(3, filtered.size());
        assertTrue(filtered.stream().anyMatch(m -> m.getTitle().equals("Inception")));
        assertTrue(filtered.stream().anyMatch(m -> m.getTitle().equals("Interstellar")));
        assertTrue(filtered.stream().anyMatch(m -> m.getTitle().equals("The Matrix")));
    }

    @Test
    void testFilterByGenreWithNoMatches() {
        // Filter by HORROR (no matching movies)
        controller.genreComboBox.setValue(Genre.HORROR);
        controller.filterByGenre();

        ObservableList<Movie> filtered = controller.observableMovies;

        assertEquals(0, filtered.size());
    }

    @Test
    void testFilterByNullGenre() {
        // Simulate "All Genres" selection
        controller.genreComboBox.setValue(null);
        controller.filterByGenre();

        ObservableList<Movie> filtered = controller.observableMovies;

        assertEquals(allMovies.size(), filtered.size());
    }

    @Test
    void testFilterEnsuresNoDuplicates() {
        // Filter by SCI_FI and ensure no duplicates
        controller.genreComboBox.setValue(Genre.ACTION);
        controller.filterByGenre();

        List<String> titles = controller.observableMovies.stream()
                .map(Movie::getTitle)
                .toList();

        assertEquals(titles.size(), titles.stream().distinct().count());
    }

    @Test
    void testFilterByMultipleGenres() {
        // Filter by DRAMA
        controller.genreComboBox.setValue(Genre.DRAMA);
        controller.filterByGenre();

        ObservableList<Movie> filtered = controller.observableMovies;

        assertEquals(3, filtered.size());
        assertTrue(filtered.stream().anyMatch(m -> m.getTitle().equals("Titanic")));
        assertTrue(filtered.stream().anyMatch(m -> m.getTitle().equals("Interstellar")));
        assertTrue(filtered.stream().anyMatch(m -> m.getTitle().equals("The Notebook")));
    }
}
