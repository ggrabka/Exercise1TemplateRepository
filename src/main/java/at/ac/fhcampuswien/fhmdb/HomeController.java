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
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn; // Button zum Anwenden des Filters

    @FXML
    public JFXButton resetBtn; // Button zum Zurücksetzen des Filters

    @FXML
    public TextField searchField; // Eingabefeld für die Suchanfrage

    @FXML
    public JFXListView movieListView; // Liste zur Anzeige der Filme

    @FXML
    public JFXComboBox genreComboBox; // Dropdown zur Auswahl eines Genres

    @FXML
    public JFXButton sortBtn; // Button zum Sortieren der Filme

    private boolean ascendingOrder = true; // Zustand für die Sortierung (aufsteigend/absteigend)

    public List<Movie> allMovies = Movie.initializeMovies(); // Gesamte Filmliste

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    // Comparatoren für die Sortierung

    Comparator<Movie> ascendingComparator =
            (m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle());
    Comparator<Movie> descendingComparator =
            ascendingComparator.reversed();// automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // //initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        //// TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setPromptText("Filter by Genre");

        // TODO add event handlers to buttons and call the regarding methods
        searchBtn.setOnAction(actionEvent -> handleFilter());
        resetBtn.setOnAction(actionEvent -> handleReset()); // Event für Reset-Button

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            handleSortButton();
        });
    }




    //Filtert die Filme basierend auf dem Suchbegriff und/oder dem gewählten Genre.
    public void handleFilter() {
        String query = searchField.getText().trim().toLowerCase(); // Case-Insensitive Query
        Genre selectedGenre = (Genre) genreComboBox.getValue();

        List<Movie> filteredMovies = allMovies.stream()
                .filter(movie -> {
                    boolean matchesQuery = query.isEmpty() ||
                            movie.getTitle().toLowerCase().contains(query) ||
                            movie.getDescription().toLowerCase().contains(query);

                    boolean matchesGenre = (selectedGenre == null) || movie.getGenres().contains(selectedGenre);

                    return matchesQuery && matchesGenre;
                })
                .collect(Collectors.toList());

        observableMovies.setAll(filteredMovies);
    }

    public void handleSortButton() {
        sortMovies(observableMovies, ascendingOrder ? ascendingComparator : descendingComparator);
        ascendingOrder = !ascendingOrder;
        sortBtn.setText(ascendingOrder ? "Sort (asc)" : "Sort (desc)");
    }

    public void handleReset() {
        searchField.clear(); // Eingabefseld zurücksetzen
        genreComboBox.setValue(null); // Genre-Filter zurücksetzen
        observableMovies.setAll(allMovies); // Alle Filme wieder anzeigen
    }

    //Sortiert die übergebene Liste mit dem angegebenen Comparator.

    public void sortMovies(ObservableList<Movie> observableMovies, Comparator<Movie> comparator) {
        FXCollections.sort(observableMovies, comparator);
    }
}