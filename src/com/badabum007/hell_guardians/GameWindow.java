package com.badabum007.hell_guardians;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Rectangle2D;
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
	private   Scores_n_money scores_n_money;
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

		is.close();

		scores_n_money = new Scores_n_money();
		scores_n_money.setVisible(true);

		gameRoot.getChildren().addAll(imgView, towerMenu, scores_n_money);
		primaryStage.setScene(gameScene);
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
			drag_end2();

			menu0.getChildren().addAll(tower1, tower2);
			getChildren().add(menu0);
		}
	}

	/*private void drag_end() throws IOException{
	Point p = MouseInfo.getPointerInfo().getLocation();

	InputStream is = Files.newInputStream(Paths.get("res/images/sarcher_sprites.png"));
	Image img = new Image(is);

	ImageView imgView = new ImageView(img);
	imgView.setViewport(new Rectangle2D(0, 125, 90, 125));
	imgView.setTranslateX(p.getX());
	imgView.setTranslateY(p.getY());

	System.out.println("Drag completed");

	is.close();

	gameRoot.getChildren().add(imgView);
}*/

	private void drag_end2() throws IOException{
		InputStream is = Files.newInputStream(Paths.get("res/images/sarcher_sprites.png"));
		Image img = new Image(is);

		ImageView imgView = new ImageView(img);
		imgView.setViewport(new Rectangle2D(0, 125, 90, 125));
		imgView.setTranslateX(300);
		imgView.setTranslateY(200);
		imgView.toFront();

		is.close();

		gameRoot.getChildren().add(imgView);
	}

	private class Scores_n_money extends Parent {
		private Integer scores, money;
		Text sc, m;
		private Pane sm;
		
		public Scores_n_money() throws IOException {
			sm = new Pane();
			
			InputStream is = Files.newInputStream(Paths.get("res/images/souls.png"));
			Image img = new Image(is);
			ImageView imgView = new ImageView(img);
			imgView.setFitWidth(50);
			imgView.setPreserveRatio(true);
			imgView.setTranslateX(620);

			is = Files.newInputStream(Paths.get("res/images/score.png"));
			img = new Image(is);
			ImageView imgView2 = new ImageView(img);
			imgView2.setFitWidth(50);
			imgView2.setPreserveRatio(true);
			imgView2.setTranslateX(680);

			is.close();
			
			scores = 0;
			money = 0;
			
			sc = new Text(680, 70, scores.toString());
			sc.setFont(new Font(20));
			sc.setFill(Color.GREY);
			
			m = new Text(620, 70, money.toString());
			m.setFont(new Font(20));
			m.setFill(Color.GREY);
			
			sm.getChildren().addAll(imgView, imgView2, sc, m);
			getChildren().add(sm);
		}
		
		public void setMoney(Integer x){
			money = x;
			m.setText(x.toString());
		}
		
		public void setScores(Integer x){
			scores = x;
			sc.setText(x.toString());			
		}
	}
}
