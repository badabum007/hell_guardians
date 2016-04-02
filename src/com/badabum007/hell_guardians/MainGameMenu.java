package com.badabum007.hell_guardians;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGameMenu extends Application {

	private GameMenu gameMenu;
	private TowerMenu towerMenu;
	private Scores_n_money scores_n_money;

	public Stage theStage = new Stage();
	public Pane root = new Pane();

	public Pane gameRoot = new Pane();
	public Scene gameScene = new Scene(gameRoot);

	public MediaPlayer menuMp;

	@Override
	public void start(Stage primaryStage) throws Exception {

		theStage = primaryStage;

		//menu content adding
		Media media= new Media(new File("res/music/Gonzalo_Varela_-_06_-_Abandoned_Souls.mp3").toURI().toString());
		menuMp = new MediaPlayer(media);
		//menuMp.setAutoPlay(true);
		menuMp.setCycleCount(MediaPlayer.INDEFINITE);
		//menuMp.play();
		MediaView mediaView = new MediaView(menuMp);

		root.setPrefSize(800, 600);

		InputStream is = Files.newInputStream(Paths.get("res/images/main_menu.png"));
		Image img = new Image(is);
		is.close();

		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(800);
		imgView.setFitHeight(600);

		theStage.setMinWidth(800);        
		theStage.setMinHeight(600);

		theStage.setMaxWidth(800);        
		theStage.setMaxHeight(600);

		gameMenu = new GameMenu();
		gameMenu.setVisible(true);

		root.getChildren().addAll(mediaView, imgView, gameMenu);

		Scene scene = new Scene(root);

		theStage.setTitle("Hell Guardians");

		theStage.setScene(scene);
		theStage.getIcons().add(new Image("file:res/images/icon.png"));


		//game content adding		
		gameRoot.setPrefSize(800, 600);
		is = Files.newInputStream(Paths.get("res/images/field_texture.jpg"));
		img = new Image(is);
		is.close();

		imgView = new ImageView(img);
		imgView.setFitWidth(800);
		imgView.setFitHeight(600);

		towerMenu = new TowerMenu();
		towerMenu.setVisible(true);

		scores_n_money = new Scores_n_money();
		scores_n_money.setVisible(true);
		
		gameRoot.getChildren().addAll(imgView, towerMenu, scores_n_money);

		theStage.show();
	}

	private class TowerMenu extends Parent {
		public TowerMenu() throws IOException {
			HBox menu0 = new HBox(0);
			menu0.setTranslateX(50);

			InputStream is = Files.newInputStream(Paths.get("res/images/sarcher.png"));
			Image img = new Image(is);
			ImageView imgView = new ImageView(img);
			imgView.setFitWidth(70);
			imgView.setPreserveRatio(true);
			Button tower1 = new Button("100", imgView);
			tower1.setContentDisplay(ContentDisplay.TOP);
			
			is = Files.newInputStream(Paths.get("res/images/hellhound.png"));
			img = new Image(is);
			imgView = new ImageView(img);
			imgView.setFitWidth(70);
			imgView.setPreserveRatio(true);
			Button tower2 = new Button("150", imgView);
			tower2.setContentDisplay(ContentDisplay.TOP);
			/*MenuButton btnTower1 = new MenuButton("T1"); 
			MenuButton btnTower2 = new MenuButton("T2");
			btnTower1.setOnMouseClicked(event -> {
				try {
					menuMp.stop();
					theStage.setScene(gameScene);
					//GameWindow.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			MenuButton btnTower3 = new MenuButton("T3");
			btnTower3.setOnMouseClicked(event -> {
				//System.exit(0);
			});

			menu0.getChildren().addAll(btnTower1, btnTower2, btnTower3);*/
			is.close();
		    menu0.getChildren().addAll(tower1, tower2);
			getChildren().add(menu0);
		}
	}

	private class Scores_n_money extends Parent {
		public Scores_n_money() throws IOException {
			HBox menu0 = new HBox(0);
			menu0.setTranslateX(600);

			InputStream is = Files.newInputStream(Paths.get("res/images/souls.png"));
			Image img = new Image(is);
			ImageView imgView = new ImageView(img);
			imgView.setFitWidth(50);
			imgView.setPreserveRatio(true);
			String souls = "0";
			Button btnSoul = new Button(souls , imgView);
			btnSoul.setContentDisplay(ContentDisplay.TOP);
			
			is = Files.newInputStream(Paths.get("res/images/score.png"));
			img = new Image(is);
			imgView = new ImageView(img);
			imgView.setFitWidth(50);
			imgView.setPreserveRatio(true);
			String score = "0";
			Button btnScore = new Button(score, imgView);
			btnScore.setContentDisplay(ContentDisplay.TOP);
			
			is.close();
		    menu0.getChildren().addAll(btnSoul, btnScore);
			getChildren().add(menu0);
		}
	}

	private class GameMenu extends Parent {
		public GameMenu() throws FileNotFoundException {
			VBox menu0 = new VBox(10);

			menu0.setTranslateX(550);
			menu0.setTranslateY(450);

			MenuButton btnResume = new MenuButton("Resume"); 
			MenuButton btnNewGame = new MenuButton("New game");
			btnNewGame.setOnMouseClicked(event -> {
				try {
					menuMp.stop();
					theStage.setScene(gameScene);
					GameWindow.show(theStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

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

		public MenuButton(String name) throws FileNotFoundException {
			text = new Text(name);
			text.getFont();
			Font font;
			font = Font.loadFont(new FileInputStream(new File("res/fonts/Kankin.otf")), 20);
			text.setFont(font); 
			text.setFill(Color.WHITE);

			Rectangle bg = new Rectangle(250, 30);
			bg.setOpacity(0.5);
			bg.setFill(Color.BLACK);
			bg.setEffect(new GaussianBlur(3.5));

			setAlignment(Pos.CENTER_LEFT);
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