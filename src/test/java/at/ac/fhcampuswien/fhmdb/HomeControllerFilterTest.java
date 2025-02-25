package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeControllerFilterTest {
    @Test
    void testFilterMoviesByTitle() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "game", null);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getTitle().toLowerCase().contains("game")));
    }

    @Test
    void testFilterMoviesByDescription() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "afi", null);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getDescription().toLowerCase().contains("afi")));
    }

    @Test
    void testFilterMoviesByGenre() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, null, Genre.ACTION);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getGenres().contains(Genre.ACTION)));
    }

    @Test
    void testFilterMoviesByTitleAndGenre() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "STAR", Genre.SCIENCE_FICTION);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie ->
                movie.getTitle().toLowerCase().contains("star") &&
                        movie.getGenres().contains(Genre.SCIENCE_FICTION)));
    }

    @Test
    void testFilterMoviesByDescriptionAndGenre() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "FILT", Genre.THRILLER);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie ->
                movie.getDescription().toLowerCase().contains("filt") &&
                        movie.getGenres().contains(Genre.THRILLER)));
    }

    @Test
    void testFilterMoviesWithNoResults() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "xyznonexistent", Genre.COMEDY);

        // Then
        assertTrue(result.isEmpty());
    }
    @Test
    void testFilterMoviesByTitleWithNonexistentQuery() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "nonexistenttitle", null);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterMoviesByDescriptionWithNonexistentQuery() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "nonexistentdescription", null);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterMoviesByTitleAndGenreWithNonexistentQuery() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "nonexistenttitle", Genre.ACTION);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterMoviesByDescriptionAndGenreWithNonexistentQuery() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "nonexistentdescription", Genre.DRAMA);

        // Then
        assertTrue(result.isEmpty());
    }
    @Test
    void testFilterMoviesByTitleWithSpecialCharacters() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "Wars: A", null);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getTitle().toLowerCase().contains("wars: a")));
    }

    @Test
    void testFilterMoviesByDescriptionWithDifferentCase() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "HaVoC", null);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getDescription().toLowerCase().contains("havoc")));
    }

    @Test
    void testFilterMoviesByMultipleGenres() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, null, Genre.ACTION);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getGenres().contains(Genre.ACTION) || movie.getGenres().contains(Genre.THRILLER)));
    }

    @Test
    void testFilterMoviesByTitleWithWhitespace() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, " Lion ", null);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getTitle().toLowerCase().contains(" lion ")));
    }

    @Test
    void testFilterMoviesByDescriptionWithWhitespace() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, " a princess ", null);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getDescription().toLowerCase().contains("a princess")));
    }

    @Test
    void testFilterMoviesByGenreAndNoQuery() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> movies = Movie.initializeMovies();

        // When
        List<Movie> result = controller.filterMovies(movies, "", Genre.DRAMA);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(movie -> movie.getGenres().contains(Genre.DRAMA)));
    }
}
