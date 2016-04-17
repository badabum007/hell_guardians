package com.badabum007.hell_guardians;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Creates a block for tower building
 * and controls block behavior
 * @author badabum007
 */
public class Block extends Pane{
  ImageView block;
  Image img_block;

  /** Creates a block in a specified point
   *  @param x - X coordinate
   *  @param y - Y coordinate 
   */
  public Block(int x, int y) throws IOException{
    /** block image adding */
    InputStream is = Files.newInputStream(Paths.get("res/images/block.jpg"));
    img_block = new Image (is);
    is.close();
    block = new ImageView();
    block.setFitHeight(GameWindow.BLOCK_SIZE);
    block.setFitWidth(GameWindow.BLOCK_SIZE);
    setTranslateX(x);
    setTranslateY(y);
    block.setImage(img_block);

    Line line1 = new Line(x, y, x+GameWindow.BLOCK_SIZE, y);
    Line line2 = new Line(x+GameWindow.BLOCK_SIZE, y,
        x+GameWindow.BLOCK_SIZE, y + GameWindow.BLOCK_SIZE);
    Line line3 = new Line(x + GameWindow.BLOCK_SIZE, y + GameWindow.BLOCK_SIZE, 
        x, y + GameWindow.BLOCK_SIZE);
    Line line4 = new Line(x, y + GameWindow.BLOCK_SIZE, x, y);
    /** Indent block on mouse entering */
    this.setOnMouseEntered(event->{
      GameWindow.gameRoot.getChildren().addAll(line1, line2, line3, line4);
    });
    /** Remove  block indenting */
    this.setOnMouseExited(event->{
      GameWindow.gameRoot.getChildren().removeAll(line1, line2, line3, line4);
    });
    /** Build a tower on mouse click */
    if (GameRoot.GameMode == "Normal"){
      this.setOnMouseClicked(event->{
        Tower tower;
        try {
          tower = new Tower(x, y);
          GameWindow.gameRoot.Towers.add(tower);
        } catch (Exception e) {
          System.out.println("Block error: tower image error");
          e.printStackTrace();
        }
      });
    }
    /**  
     * Add our block
     */
    getChildren().add(block);
    GameWindow.gameRoot.getChildren().add(this);
  }
}