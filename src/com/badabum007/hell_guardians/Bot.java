package com.badabum007.hell_guardians;

import java.io.IOException;

/**
 * �����,  ����������� ����
 * @author pixxx
 */
public class Bot {
  /** ���������� ����� */
  int[] CoordX;
  int[] CoordY;

  /** ���������� ��������� ����� */
  int Count = GameRoot.rows * GameRoot.columns;

  /** ���������� ��� ����������� ����� */
  int Iterator = 0;

  /** �����, ��������� ���� */
  public Bot(){
    //for (int i = 0; i < )
  }       

  /**
   * ����� �������� �����
   * @throws IOException 
   */
  void createTower() throws IOException{
    Tower tower = new Tower(CoordY[Iterator] * GameWindow.BLOCK_SIZE, 
        CoordX[Iterator] * GameWindow.BLOCK_SIZE);
    GameWindow.gameRoot.Towers.add(tower);
    Iterator++;
  }
}