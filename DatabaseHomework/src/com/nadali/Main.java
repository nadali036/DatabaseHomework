package com.nadali;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	private CentralPane centralPane;
	
	@Override
	public void start(Stage primaryStage) {
		
		VBox root = new VBox();
		this.centralPane = new CentralPane();
		root.getChildren().add(this.centralPane);
		
		Scene scene = new Scene(root, 500, 650);
		primaryStage.setTitle("Databases and CRUD operations");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
