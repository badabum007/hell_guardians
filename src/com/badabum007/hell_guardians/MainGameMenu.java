package com.badabum007.hell_guardians;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is representing title screen menu
 * @author badabum007
 */
public class MainGameMenu extends Application {

  /** define the stage as public for communication between interface classes */
  public Stage theStage;
  /** create object for our buttons */
  private GameMenu gameMenu;
  /** layout for our title screen menu */
  private Pane root;
  /** object of our class for all gaming stuff */
  public GameWindow gameWindow;
  /** music player object */
  private MediaPlayer menuMp;
  /** our stage parameters */
  public final static int height = 600, width = 800;

  @Override
  public void start(Stage primaryStage) throws Exception {

    root = new Pane();
    gameWindow = new GameWindow();
    theStage = new Stage();
    theStage = primaryStage;

    /** menu content adding */

    /** adding music */
    Media media= new Media(new File("res/music/Gonzalo_Varela_-_06_-_Abandoned_Souls.mp3")
        .toURI().toString());
    menuMp = new MediaPlayer(media);
    /** autostart when program is launched */
    menuMp.setAutoPlay(true);
    /** infinite song playing */
    menuMp.setCycleCount(MediaPlayer.INDEFINITE);
    menuMp.play();
    MediaView mediaView = new MediaView(menuMp);

    /** window size */
    root.setPrefSize(width, height);

    /** setting background */
    InputStream is = Files.newInputStream(Paths.get("res/images/main_menu.png"));
    Image img = new Image(is);
    ImageView imgView = new ImageView(img);
    imgView.setFitWidth(width);
    imgView.setFitHeight(height);

    /** disable window resize */
    theStage.setMinWidth(width);        
    theStage.setMinHeight(height);
    theStage.setMaxWidth(width);        
    theStage.setMaxHeight(height);

    /** initializing our menu */
    gameMenu = new GameMenu();
    gameMenu.setVisible(true);

    root.getChildren().addAll(mediaView, imgView, gameMenu);

    Scene scene = new Scene(root);
    theStage.setTitle("Hell Guardians");
    theStage.setScene(scene);
    theStage.getIcons().add(new Image("file:res/images/icon.png"));

    is.close();
    theStage.show();
  }

  /**
   * Title menu buttons container
   * @author badabum007
   */
  private class GameMenu extends Parent {

    /**
     * Adds all necessary buttons and sets their behavior
     * @throws FileNotFoundException
     */ 
    public GameMenu() throws  FileNotFoundException{
      int distBetweenButtons = 10;
      VBox menu0 = new VBox(distBetweenButtons);
      VBox menu1 = new VBox(distBetweenButtons);
      VBox menu2 = new VBox(distBetweenButtons);

      /** setting menu position */
      int menuTransX = 550, menuTransY = 450;
      /** offset for shuffling when menu is changed */
      final int offset = 800;
      menu0.setTranslateX(menuTransX);
      menu0.setTranslateY(menuTransY);
      
      menu1.setTranslateX(menuTransX);
      menu1.setTranslateY(menuTransY);
      
      menu2.setTranslateX(menuTransX);
      menu2.setTranslateY(menuTransY);
      
      double TransTtDur = 0.25;
      double TransTt1Dur = 0.5;

      MenuButton btnNewGame = new MenuButton("New game");

      btnNewGame.setOnMouseClicked(event -> {
        getChildren().add(menu1);

        TranslateTransition tt = new TranslateTransition(Duration.seconds(TransTtDur), menu0);
        tt.setToX(menu0.getTranslateX() - offset);
        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(TransTt1Dur), menu1);
        tt1.setToX(menu0.getTranslateX());

        tt.play();
        tt1.play();

        tt.setOnFinished(evt -> {
          getChildren().remove(menu0);
        });
      });
      
      MenuButton btnBack = new MenuButton("Back");
      btnBack.setOnMouseClicked(event -> {
          getChildren().add(menu0);

          TranslateTransition tt = new TranslateTransition(Duration.seconds(TransTtDur), menu1);
          tt.setToX(menu1.getTranslateX() + offset);

          TranslateTransition tt1 = new TranslateTransition(Duration.seconds(TransTt1Dur), menu0);
          tt1.setToX(menu1.getTranslateX());

          tt.play();
          tt1.play();

          tt.setOnFinished(evt -> {
              getChildren().remove(menu1);
          });
      });
      
      MenuButton btnBot = new MenuButton("Bot game");
      btnBot.setOnMouseClicked(event -> {
        getChildren().add(menu2);

        /** enable bot playing */
        GameRoot.GameMode = "Auto";
        TranslateTransition tt = new TranslateTransition(Duration.seconds(TransTtDur), menu1);
        tt.setToX(menu1.getTranslateX() - offset);
        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(TransTt1Dur), menu2);
        tt1.setToX(menu1.getTranslateX());

        tt.play();
        tt1.play();

        tt.setOnFinished(evt -> {
          getChildren().remove(menu1);
        });
    });
      
      MenuButton btnPlayer = new MenuButton("Player game");
      btnPlayer.setOnMouseClicked(event -> {
        getChildren().add(menu2);

        GameRoot.GameMode = "Normal";
        TranslateTransition tt = new TranslateTransition(Duration.seconds(TransTtDur), menu1);
        tt.setToX(menu1.getTranslateX() - offset);
        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(TransTt1Dur), menu2);
        tt1.setToX(menu1.getTranslateX());

        tt.play();
        tt1.play();

        tt.setOnFinished(evt -> {
          getChildren().remove(menu1);
        });
    });
      
      MenuButton btnHorror = new MenuButton("Horror");
      btnHorror.setOnMouseClicked(event -> {
        try {
          /** stop playing and change scene */
          Shot.damage = 25;
          menuMp.stop();
          gameWindow.show(theStage);
        } catch (Exception e) {
          e.printStackTrace();
        }
    });
      
      MenuButton btnNightmare = new MenuButton("Nightmare");
      btnNightmare.setOnMouseClicked(event -> {
        try {
          Shot.damage = 20;
          /** stop playing and change scene */
          menuMp.stop();
          gameWindow.show(theStage);
        } catch (Exception e) {
          e.printStackTrace();
        }
    });

      MenuButton btnExit = new MenuButton("Exit");
      btnExit.setOnMouseClicked(event -> {
        System.exit(0);
      });

      menu0.getChildren().addAll( btnNewGame, btnExit);
      getChildren().add(menu0);
      
      menu1.getChildren().addAll(btnBot, btnPlayer, btnBack);
      menu2.getChildren().addAll(btnHorror, btnNightmare);
    }
  }

  /**
   * Creates a single menu button
   * @author badabum007
   */ 
  private static class MenuButton extends StackPane {
    /** button label */
    private Text text;

    /**
     * Creates a single button and adds button effects
     * @throws FileNotFoundException
     * @param name - button name
     */ 
    public MenuButton(String name) throws FileNotFoundException {
      /** setting font */
      text = new Text(name);
      text.getFont();
      /** load custom font */
      Font font;
      font = Font.loadFont(new FileInputStream(new File("res/fonts/Kankin.otf")), 20);
      text.setFont(font); 
      text.setFill(Color.WHITE);

      /** adding rectangle over button */
      int rectWidth = 250, rectHeight = 30;
      Rectangle bg = new Rectangle(rectWidth, rectHeight);
      double opacityLevel = 0.5, blurLevel = 3.5;
      bg.setOpacity(opacityLevel);
      bg.setFill(Color.BLACK);
      bg.setEffect(new GaussianBlur(blurLevel));

      /** setting effects */
      setAlignment(Pos.CENTER_LEFT);
      getChildren().addAll(bg, text);

      /** rectangle shuffling on mouse entering/exiting */
      setOnMouseEntered(event -> {
        int translateButtonForwardX = 10;
        bg.setTranslateX(translateButtonForwardX);
        text.setTranslateX(translateButtonForwardX);
        bg.setFill(Color.WHITE);
        text.setFill(Color.BLACK);
      });

      setOnMouseExited(event -> {
        int translateButtonBackwardX = 0;
        bg.setTranslateX(translateButtonBackwardX);
        text.setTranslateX(translateButtonBackwardX);
        bg.setFill(Color.BLACK);
        text.setFill(Color.WHITE);
      });

      /** glow effect on mouse press */
      int dropShadowRange = 50;
      DropShadow drop = new DropShadow(dropShadowRange, Color.WHITE);
      drop.setInput(new Glow());
      setOnMousePressed(event -> setEffect(drop));
      setOnMouseReleased(event -> setEffect(null));
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}