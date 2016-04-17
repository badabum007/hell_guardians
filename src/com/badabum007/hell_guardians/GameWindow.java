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
<<<<<<< HEAD
  public static final int BLOCK_SIZE = 100;
  
  private   TowerMenu towerMenu;
  private   Scores_n_money scores_n_money;

  static GameRoot gameRoot;
  private  Scene gameScene;
  static Pane All;
  
  public  void show(Stage primaryStage) throws IOException{
    gameRoot = new GameRoot();
    Pane All = new Pane();
    gameScene = new Scene(All);
    
    //setting field
    int height = 600, width = 800;
    All.setPrefSize(width, height);
    InputStream is = Files.newInputStream(Paths.get("res/images/field_texture.jpg"));
    Image img = new Image(is);
    ImageView imgView = new ImageView(img);
    imgView.setFitWidth(width);
    imgView.setFitHeight(height);

    towerMenu = new TowerMenu();
    towerMenu.setVisible(true);

    is.close();

    scores_n_money = new Scores_n_money();
    scores_n_money.setVisible(true);
    
    GameRoot.GameMode = "Normal";
    gameRoot.setVisible(true);
    gameRoot.StartGame();

    All.getChildren().addAll(imgView, towerMenu, scores_n_money, gameRoot);
    primaryStage.setScene(gameScene);
  }

  //tower buttons container
  private class TowerMenu extends Parent {
    public TowerMenu() throws IOException {

      int distBetweenButtons = 0, menuTransX = 50;
      HBox menu0 = new HBox(distBetweenButtons);
      menu0.setTranslateX(menuTransX);

      int imgWidth = 70;

      //skeleton archer tower
      InputStream is = Files.newInputStream(Paths.get("res/images/sarcher.png"));
      Image img = new Image(is);
      ImageView imgView = new ImageView(img);
      imgView.setFitWidth(imgWidth);
      Integer tower1Cost = 100;
      //set Height according to Width
      imgView.setPreserveRatio(true);
      Button tower1 = new Button(tower1Cost.toString(), imgView);
      //display text below image
      tower1.setContentDisplay(ContentDisplay.TOP);			      

      is = Files.newInputStream(Paths.get("res/images/hellhound.png"));
      img = new Image(is);
      imgView = new ImageView(img);
      imgView.setFitWidth(imgWidth);
      imgView.setPreserveRatio(true);
      Integer tower2Cost = 150;
      Button tower2 = new Button(tower2Cost.toString(), imgView);
      tower2.setContentDisplay(ContentDisplay.TOP);

      is.close();

      menu0.getChildren().addAll(tower1, tower2);
      getChildren().add(menu0);
    }
  }

  //scores - our resulting scores
  //money - resources (souls) for building towers
  private class Scores_n_money extends Parent {

    //make values as Integers to convert them into String later
    private Integer scores, money;
    Text sc, m;
    //create new Pane to overlap the background
    private Pane sm;

    public Scores_n_money() throws IOException {
      sm = new Pane();
      int imgWidth = 50;

      //setting money picture
      int moneyTransX = 620;
      InputStream is = Files.newInputStream(Paths.get("res/images/souls.png"));
      Image img = new Image(is);
      ImageView imgView = new ImageView(img);
      imgView.setFitWidth(imgWidth);
      imgView.setPreserveRatio(true);
      imgView.setTranslateX(moneyTransX);

      //setting scores picture
      int scoresTransX = moneyTransX + 60;
      is = Files.newInputStream(Paths.get("res/images/score.png"));
      img = new Image(is);
      ImageView imgView2 = new ImageView(img);
      imgView2.setFitWidth(imgWidth);
      imgView2.setPreserveRatio(true);
      imgView2.setTranslateX(scoresTransX);

      is.close();

      scores = 0;
      money = 0;

      //setting money and scores text
      int fontSize = 20, textTransY = 70;

      m = new Text(moneyTransX, textTransY, money.toString());
      m.setFont(new Font(fontSize));
      m.setFill(Color.GREY);

      sc = new Text(scoresTransX, textTransY, scores.toString());
      sc.setFont(new Font(fontSize));
      sc.setFill(Color.GREY);

      sm.getChildren().addAll(imgView, imgView2, sc, m);
      getChildren().add(sm);
    }

    //methods to change value of money and scores 
    public void setMoney(Integer x){
      money = x;
      m.setText(x.toString());
    }

    public void setScores(Integer x){
      scores = x;
      sc.setText(x.toString());			
    }
  }
=======
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
>>>>>>> bd6bcffb201cd8a134e72d572ce313ec6249f0b3
}
