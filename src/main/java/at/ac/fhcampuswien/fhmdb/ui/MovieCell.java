package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;
import java.util.stream.Collectors;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label(); // Label für Genres
    private final VBox layout = new VBox(title, detail, genres);

    //Aktualisiert das Layout der Zelle basierend auf dem übergebenen Film.
     //@param movie Der anzuzeigende Film.
    //@param empty Gibt an, ob die Zelle leer ist.


    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
// Titel setzen und formatieren
            title.setText(movie.getTitle());
            title.setFont(Font.font(20));
            title.setTextFill(Color.web("#FFD700")); // Gold für Titel
// Beschreibung setzen
            detail.setText(movie.getDescription() != null ? movie.getDescription() : "No description available");
            detail.setWrapText(true);
            detail.setTextFill(Color.WHITE); // Weiße Schrift für Beschreibung

            //Sicherstellen, dass Genres existieren, bevor sie angezeigt werden
            // Genres als String formatieren
            List<String> genreList = movie.getGenres().stream()
                    .map(Enum::name)
                    .collect(Collectors.toList());

            if (!genreList.isEmpty()) {
                genres.setText("Genres: " + String.join(", ", genreList));
            } else {
                genres.setText("Genres: Not available");
            }

            genres.setTextFill(Color.LIGHTGRAY);
            genres.setFont(Font.font(14));
            // Hintergrundfarbe der Zelle setzen
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));
            layout.setPadding(new Insets(10));
            layout.setSpacing(5);
            setGraphic(layout);

            // Debugging-Ausgabe
            System.out.println("Genres for " + movie.getTitle() + ": " + movie.getGenres());
        }
    }
}
