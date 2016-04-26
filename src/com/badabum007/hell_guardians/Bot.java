package com.badabum007.hell_guardians;

import java.io.IOException;
import java.util.Random;

/**
 * Класс,  реализующий бота
 * @author pixxx
 */
public class Bot {
  /** Координаты вышек */
  int coordX;
  int coordY;

  /** Количество возможных вышек */
  int Count = GameRoot.rows * GameRoot.columns;

  /** Количество уже построенных вышек */
  int iterator;
  
  boolean[][] matrix;

  /** Метод, создающий бота */
  public Bot(){
    iterator = 0;
    coordX = 0;
    coordY = 0;
    matrix = new boolean[GameRoot.rows][GameRoot.columns];
    for (int i = 0; i < GameRoot.rows; i++){
      for (int j = 0; j < GameRoot.columns; j++){
        matrix[i][j] = false;
      }
    }
  }       

  /**
   * Метод создания вышки
   * @throws IOException 
   */
  void createTower() throws IOException{
    do {
      coordY = (int)(new Random().nextInt(GameRoot.rows));
      coordX = (int)(new Random().nextInt(GameRoot.columns));
    }
      while (matrix[coordY][coordX] == true);
    matrix[coordY][coordX] = true;
    Tower tower = new Tower(coordX * GameWindow.BLOCK_SIZE + GameWindow.offsetXY, 
        coordY * GameWindow.BLOCK_SIZE + GameWindow.offsetXY);
    GameWindow.gameRoot.Towers.add(tower);
    iterator++;
  }
}