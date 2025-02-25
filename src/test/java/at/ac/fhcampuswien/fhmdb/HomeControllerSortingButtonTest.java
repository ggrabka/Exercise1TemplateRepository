package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerSortingButtonTest {

    @Test
    void testAscendingSort() {
        // Arrange
        HomeController controller = new HomeController();
        ObservableList<Movie> testList = FXCollections.observableArrayList(
                new Movie("C Title", "acs", List.of(Genre.FAMILY)),
                new Movie("A Title", "asc", List.of(Genre.COMEDY)),
                new Movie("B Title", "asc", List.of(Genre.DRAMA))
        );

        // Set up controller's movie list
        controller.allMovies = testList;
        controller.observableMovies.setAll(testList);

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
}