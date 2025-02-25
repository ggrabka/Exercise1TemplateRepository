package at.ac.fhcampuswien.fhmdb;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;

//https://www.youtube.com/watch?v=PoUlJplInkg
//https://github.com/TestFX/TestFX

public class HomeControllerSortingButtonTestJavaFx extends ApplicationTest {

    private HomeController controller;

    /*Start is used for initialization of the JavaFx Application for testing
      With the FXMLLoader we load the FXML File / UI definition File
      Parent root parses the FXML file and constructs the JavaFX scene graph from it.
      Controller retrieves the controller associated with the FXML, It allows the test class to directly interact with the controller for testing purposes.
      Stage.setScene wraps the loaded UI (root) into a Scene object, which is required for displaying content in JavaFX.
      Stage.show displays the stage on the screen. In TestFX tests, this step makes the UI elements available for interaction (clicks, typing, etc.).
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("/at/ac/fhcampuswien/fhmdb/home-view.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        Scene scene = new Scene(root, 890, 620);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("FHMDb");
        stage.setScene(scene);
       stage.show();
   }

    @Test
    void testSortButtonFunctionality() {
        verifyThat("#sortBtn", hasText("Sort (asc)"));
        verifyThat("#movieListView", hasItems(22));

        clickOn("#sortBtn");
        verifyThat("#sortBtn", hasText("Sort (desc)"));
        assertEquals("Avengers: Endgame", controller.observableMovies.get(0).getTitle());

        //For Demonstration purposes
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        clickOn("#sortBtn");
        verifyThat("#sortBtn", hasText("Sort (asc)"));
        assertEquals("Toy Story", controller.observableMovies.get(0).getTitle());
    }
}
