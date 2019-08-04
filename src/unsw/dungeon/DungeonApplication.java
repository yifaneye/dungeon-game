package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
        
        DungeonController controller = dungeonLoader.loadController(); //!
        
        //dungeonLoader.change_image();
        
        //controller = dungeonLoader.loadController();
        //controller.setText_bomb(new Text("1"));
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        
        //scene start
        Button buttonStart = new Button("Play!");
        buttonStart.setOnAction(e -> primaryStage.setScene(scene));
        
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(buttonStart);
        Scene scene2 = new Scene(layout2, 200, 200);
        
        primaryStage.setScene(scene2);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
