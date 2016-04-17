package com.badabum007.hell_guardians;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

/**
 * �����,  ����������� ������ ����
 * @author pixxx
 */
public class GameRoot extends Pane {

  /** �����, ����������� �� �����*/
  ArrayList<Tower> Towers;

  /** ����� ����: ��������������(Auto) ��� �������(Normal) */
  static String GameMode;

  public GameRoot (){
    Towers = new ArrayList<Tower>();
    this.setVisible(false);
  }

  /**
   * �����, ����������� ������ ����
   */
  int rows = 4;
  int columns = 6;
  int sizeXY = 200;
  int offsetXY = 100;
  public void StartGame()throws IOException{
    CreateMap();}
  /**
   * ����� ������������� ����� �� �������
   * @throws IOException 
   * @see LevelData
   */

  public void CreateMap() throws IOException{
    for (int i = 0; i< rows ; i++){
      for (int j = 0; j < columns; j ++ ){
        Block bl = new Block(offsetXY + j*GameWindow.BLOCK_SIZE, offsetXY + i*GameWindow.BLOCK_SIZE);
      }
    }
  }
}

