package com.badabum007.hell_guardians;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGameMenu extends Application {

	private GameMenu gameMenu;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new Pane();
		root.setPrefSize(800, 600);

		InputStream is = Files.newInputStream(Paths.get("res/images/main_menu.png"));
		Image img = new Image(is);
		is.close();

		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(800);
		imgView.setFitHeight(600);
		
		primaryStage.setMinWidth(800);        
		primaryStage.setMinHeight(600);

		primaryStage.setMaxWidth(800);        
		primaryStage.setMaxHeight(600);
		
		gameMenu = new GameMenu();
		gameMenu.setVisible(true);

		root.getChildren().addAll(imgView, gameMenu);

		Scene scene = new Scene(root);

		primaryStage.setTitle("Hell guardians");

		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("file:res/images/icon.png"));
		primaryStage.show();
	}

	private class GameMenu extends Parent {
		public GameMenu() throws FileNotFoundException {
			VBox menu0 = new VBox(10);

			menu0.setTranslateX(550);
			menu0.setTranslateY(450);

			MenuButton btnResume = new MenuButton("Resume"); 
			MenuButton btnNewGame = new MenuButton("New game");       

			MenuButton btnExit = new MenuButton("Exit");
			btnExit.setOnMouseClicked(event -> {
				System.exit(0);
			});

			menu0.getChildren().addAll(btnResume, btnNewGame, btnExit);
			getChildren().add(menu0);
		}
	}

	private static class MenuButton extends StackPane {
		private Text text;

		public MenuButton(String name) {
			text = new Text(name);
			text.getFont();
			Font font;
			try {
				font = Font.loadFont(new FileInputStream(new File("res/fonts/Kankin.otf")), 20);
				text.setFont(font); 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			text.setFill(Color.WHITE);

			Rectangle bg = new Rectangle(250, 30);
			bg.setOpacity(0.6);
			bg.setFill(Color.BLACK);
			bg.setEffect(new GaussianBlur(3.5));

			setAlignment(Pos.CENTER_LEFT);
			setRotate(-0.5);
			getChildren().addAll(bg, text);

			setOnMouseEntered(event -> {
				bg.setTranslateX(10);
				text.setTranslateX(10);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});

			setOnMouseExited(event -> {
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});

			DropShadow drop = new DropShadow(50, Color.WHITE);
			drop.setInput(new Glow());

			setOnMousePressed(event -> setEffect(drop));
			setOnMouseReleased(event -> setEffect(null));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}