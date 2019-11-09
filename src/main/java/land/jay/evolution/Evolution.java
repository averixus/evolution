package land.jay.evolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Evolution extends Application {
        
    private static ControlPanel control;
    private static CreatureGrid grid;
    
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Evolution");
        
        GridPane root = new GridPane();
        
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        double width = screen.getWidth();
        double height = screen.getHeight();
        double controlHeight = 70;
        double gridHeight = height - controlHeight;
        
        control = new ControlPanel(width, controlHeight);
        grid = new CreatureGrid(4, 4, width, gridHeight);
        
        root.add(control, 0, 0);
        root.add(Evolution.grid, 0, 1);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setPrefSize(width, height);
        stage.show();
    }
    
    public static void release() {
        CreaturePane[] selected = grid.getSelected();
        for (CreaturePane pane : selected) {
            pane.release();
        }
        control.message("Released " + selected.length);
        grid.clearSelection();
    }
    
    public static void breed() {
        CreaturePane[] selected = grid.getSelected();
        CreaturePane[] empty = grid.getEmpty();
        if (selected.length != 2) {
            control.message("Must select exactly 2 to breed");
            return;
        }
        if (empty.length == 0) {
            control.message("Must have empty space to breed");
            return;
        }
        empty[0].breed(selected[0], selected[1]);
        control.message("Bred");
    }
}
