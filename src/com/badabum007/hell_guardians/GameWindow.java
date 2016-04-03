package com.badabum007.hell_guardians;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameWindow {
	private   TowerMenu towerMenu;
	private  Scores_n_money scores_n_money;
	private  Pane gameRoot = new Pane();
	private  Scene gameScene = new Scene(gameRoot);

	public  void show(Stage primaryStage) throws IOException{	
		gameRoot.setPrefSize(800, 600);
		InputStream is = Files.newInputStream(Paths.get("res/images/field_texture.jpg"));
		Image img = new Image(is);

		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(800);
		imgView.setFitHeight(600);

		towerMenu = new TowerMenu();
		towerMenu.setVisible(true);

		scores_n_money = new Scores_n_money();
		scores_n_money.setVisible(true);

		Text t = new Text(150, 150, "This is a test");
		t.setFont(new Font(20));
		t.setFill(Color.GREY);

		is.close();

		gameRoot.getChildren().addAll(imgView,towerMenu, scores_n_money, t);

		primaryStage.setScene(gameScene);
		//drag_end2();
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
}
