package com.badabum007.hell_guardians;

import java.io.IOException;

/**
 * Класс,  реализующий бота
 * @author pixxx
 */
public class Bot {
  /** Координаты вышек */
  int[] CoordX;
  int[] CoordY;

  /** Количество возможных вышек */
  int Count = GameRoot.rows * GameRoot.columns;

  /** Количество уже построенных вышек */
  int Iterator = 0;

  /** Метод, создающий бота */
  public Bot(){
    //for (int i = 0; i < )
  }       

  /**
   * Метод создания вышки
   * @throws IOException 
   */
  void createTower() throws IOException{
    Tower tower = new Tower(CoordY[Iterator] * GameWindow.BLOCK_SIZE, 
        CoordX[Iterator] * GameWindow.BLOCK_SIZE);
    GameWindow.gameRoot.Towers.add(tower);
    Iterator++;
  }
}