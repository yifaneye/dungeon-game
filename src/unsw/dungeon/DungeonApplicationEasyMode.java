package unsw.dungeon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DungeonApplicationEasyMode extends Application {

	public String file = "open.json";

	/**
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Dungeon");

		Random r = new Random();
		file = r.nextBoolean() ? "open.json" : "explode.json";
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(file);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				DungeonController controller;
				try {
					controller = dungeonLoader.loadController();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
					loader.setController(controller);
					Parent root;
					try {
						root = loader.load();
						Scene scene = new Scene(root);
						root.requestFocus();
						primaryStage.setScene(scene);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
			}
		};

		// scene start
		FileInputStream input = new FileInputStream("/import/glass/3/z5210859/Kobe/images/start.png");
		Image i = new Image(input);
		ImageView iv = new ImageView(i);
		Button b = new Button("", iv);
		b.setOnAction(event);
		Scene scene2 = new Scene(b, 640, 608);

		primaryStage.setScene(scene2);
		primaryStage.show();
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		launch(args);
	}

}