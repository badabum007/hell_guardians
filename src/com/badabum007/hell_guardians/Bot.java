package com.badabum007.hell_guardians;

import java.io.IOException;
import java.util.Random;

/**
 * �����,  ����������� ����
 * @author pixxx
 */
public class Bot {
  /** ���������� ����� */
  int coordX;
  int coordY;

  /** ���������� ��������� ����� */
  int Count = GameRoot.rows * GameRoot.columns;

  /** ���������� ��� ����������� ����� */
  int iterator;
  
  boolean[][] matrix;

  /** �����, ��������� ���� */
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
   * ����� �������� �����
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