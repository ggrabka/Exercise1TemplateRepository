package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    Comparator<Movie> ascendingComparator =
            (m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle());
    Comparator<Movie> descendingComparator =
            ascendingComparator.reversed();// automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.setOnAction(actionEvent -> filterByGenre());

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            handleSortButton();
        });
        searchBtn.setOnAction(actionEvent -> {
            applyFilter();
        });
    }

    public void handleSortButton() {
        if (sortBtn.getText().equals("Sort (asc)")) {
            // TODO sort observableMovies ascending
            sortMovies(observableMovies, ascendingComparator);
            sortBtn.setText("Sort (desc)");
        } else {
            // TODO sort observableMovies descending
            sortMovies(observableMovies, descendingComparator);
            sortBtn.setText("Sort (asc)");
        }
    }

    public void filterByGenre() {
        Genre selectedGenre = genreComboBox.getValue();

        List<Movie> filteredMovies = allMovies.stream()
                .filter(movie -> movie.getGenres().contains(selectedGenre))
                .distinct()
                .collect(Collectors.toList());

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
        movieListView.refresh();
    }

    public void sortMovies(ObservableList<Movie> observableMovies, Comparator<Movie> comparator) {
        FXCollections.sort(observableMovies, comparator);
    }

    public void applyFilter() {
        String searchQuery = searchField.getText();
        Genre selectedGenre = genreComboBox.getValue();

        List<Movie> filteredMovies = filterMovies(allMovies, searchQuery, selectedGenre);

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
        movieListView.refresh();
    }

    public List<Movie> filterMovies(List<Movie> movies, String query, Genre genre) {
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : movies) {

            if (applyQueryFilter(movie, query) && applyGenreFilter(movie, genre)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

    private boolean applyQueryFilter(Movie movie, String query) {
        if (query == null || query.isEmpty()) {
            return true;
        }

        String queryLowerCase = query.toLowerCase();
        return movie.getTitle().toLowerCase().contains(queryLowerCase) ||
                movie.getDescription().toLowerCase().contains(queryLowerCase);
    }

    private boolean applyGenreFilter(Movie movie, Genre genre) {
        if (genre == null || genre == Genre.ALL) {
            return true;
        }

        return movie.getGenres().contains(genre);
    }
}