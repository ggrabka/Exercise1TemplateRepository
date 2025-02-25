package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private HomeController controller;
    private ObservableList<Movie> testMovies;

    /**
     * Initialisiert eine Testumgebung mit Dummy-Daten.
     */
    @BeforeEach
    void setUp() {
        controller = new HomeController();
        testMovies = FXCollections.observableArrayList(
                new Movie("Titanic", "A love story", List.of(Genre.DRAMA, Genre.ROMANCE)),
                new Movie("Inception", "A mind-bending thriller", List.of(Genre.SCIENCE_FICTION, Genre.ACTION)),
                new Movie("The Matrix", "A sci-fi classic", List.of(Genre.SCIENCE_FICTION, Genre.ACTION))
        );
    }

    @Test
    void testAscendingSort() {
        // Arrange
        HomeController controller = new HomeController();
        ObservableList<Movie> testList = FXCollections.observableArrayList(
                new Movie("C Title", "acs", List.of(Genre.FAMILY)),
                new Movie("A Title", "asc", List.of(Genre.COMEDY)),
                new Movie("B Title", "asc", List.of(Genre.DRAMA))
        );

        // Act

        FXCollections.sort(testList, controller.ascendingComparator);

        // Assert
        assertEquals("A Title", testList.get(0).getTitle());
        assertEquals("B Title", testList.get(1).getTitle());
        assertEquals("C Title", testList.get(2).getTitle());
    }

    @Test
    void testAscendingSortNegative() {
        // Arrange
        HomeController controller = new HomeController();
        ObservableList<Movie> testList = FXCollections.observableArrayList(
                new Movie("C Title", "acs", List.of(Genre.FAMILY)),
                new Movie("A Title", "asc", List.of(Genre.COMEDY)),
                new Movie("B Title", "asc", List.of(Genre.DRAMA))
        );

        // Act

        FXCollections.sort(testList, controller.ascendingComparator);

        // Assert
        assertNotEquals("C Title", testList.get(0).getTitle());
        assertNotEquals("A Title", testList.get(2).getTitle());
    }

    @Test
    void testDescendingSort() {
        // Arrange
        HomeController controller = new HomeController();
        ObservableList<Movie> testList = FXCollections.observableArrayList(
                new Movie("C Title", "desc", List.of(Genre.FAMILY)),
                new Movie("A Title", "desc", List.of(Genre.COMEDY)),
                new Movie("B Title", "desc", List.of(Genre.DRAMA))
        );

        // Act

        FXCollections.sort(testList, controller.descendingComparator);

        // Assert
        assertEquals("C Title", testList.get(0).getTitle());
        assertEquals("B Title", testList.get(1).getTitle());
        assertEquals("A Title", testList.get(2).getTitle());
    }

    @Test
    void testDescendingSortNegative() {
        // Arrange
        HomeController controller = new HomeController();
        ObservableList<Movie> testList = FXCollections.observableArrayList(
                new Movie("C Title", "desc", List.of(Genre.FAMILY)),
                new Movie("A Title", "desc", List.of(Genre.COMEDY)),
                new Movie("B Title", "desc", List.of(Genre.DRAMA))
        );

        // Act

        FXCollections.sort(testList, controller.descendingComparator);

        // Assert
        assertNotEquals("A Title", testList.get(0).getTitle());
        assertNotEquals("C Title", testList.get(2).getTitle());
    }

    @Test
    void testDescendingSortEmptyList() {
        // Arrange
        HomeController controller = new HomeController();
        ObservableList<Movie> testList = FXCollections.observableArrayList(
        );

        // Act

        FXCollections.sort(testList, controller.descendingComparator);

        // Assert
        assertTrue(testList.isEmpty());

    }

    @Test
    void testAscendingSortEmptyList() {
        // Arrange
        HomeController controller = new HomeController();
        ObservableList<Movie> testList = FXCollections.observableArrayList(
        );

        // Act

        FXCollections.sort(testList, controller.ascendingComparator);

        // Assert
        assertTrue(testList.isEmpty());

    }

    @Test
    void testSortSingleElementList() {
        // Arrange
        HomeController controller = new HomeController();
        ObservableList<Movie> testList = FXCollections.observableArrayList(
                new Movie("A Title", "Single item", List.of(Genre.COMEDY))
        );

        // Act - you can test ascending or descending, it should behave the same with one item
        FXCollections.sort(testList, controller.ascendingComparator);

        // Assert - the list should still have exactly one element
        assertEquals(1, testList.size());
        assertEquals("A Title", testList.get(0).getTitle());
    }

    //Testet das Filtern nach Gengres
    @Test
    void testFilterByGenre() {
        controller.allMovies = List.of(
                new Movie("Titanic", "A love story", List.of(Genre.DRAMA, Genre.ROMANCE)),
                new Movie("Inception", "A thriller", List.of(Genre.SCIENCE_FICTION, Genre.ACTION))
        );

        controller.genreComboBox.setValue(Genre.ACTION);
        controller.handleFilter();

        assertEquals(1, controller.movieListView.getItems().size());
        assertEquals("Inception", ((Movie) controller.movieListView.getItems().get(0)).getTitle());
    }

    //Testet die Filterung nach einem Suchbegriff im Titel.

    @Test
    void testFilterByTitle() {
        controller.allMovies = List.of(
                new Movie("Titanic", "A love story", List.of(Genre.DRAMA, Genre.ROMANCE)),
                new Movie("Inception", "A thriller", List.of(Genre.SCIENCE_FICTION, Genre.ACTION))
        );

        controller.searchField.setText("Titanic");
        controller.handleFilter();

        assertEquals(1, controller.movieListView.getItems().size());
        assertEquals("Titanic", ((Movie) controller.movieListView.getItems().get(0)).getTitle());
    }

     //Testet das Zurücksetszen des Filters.

    @Test
    void testResetFilters() {
        controller.allMovies = List.of(
                new Movie("Titanic", "A love story", List.of(Genre.DRAMA, Genre.ROMANCE)),
                new Movie("Inception", "A thriller", List.of(Genre.SCIENCE_FICTION, Genre.ACTION))
        );

        controller.searchField.setText("Titanic");
        controller.genreComboBox.setValue(Genre.DRAMA);
        controller.handleFilter();

        controller.handleReset();

        assertEquals(2, controller.movieListView.getItems().size());
    }






}