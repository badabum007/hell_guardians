package com.badabum007.hell_guardians;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
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

  final int rows = 4;
  final int columns = 6;
  final double timeToNextMob = 100;
  
  final int updateFrequence = 10000000;
  //10000000

  /** ��������� �� ����� �������� */
  Spawner[] Spawn = new Spawner[rows];

  /**
   * �����, ����������� ������ ����
   */

  public void StartGame()throws IOException{
    CreateMap();
    int enemyCount = 2;
    for (int i = 0; i < rows; i++){
      Spawn[i] = new Spawner(enemyCount, MainGameMenu.width,GameWindow.offsetXY + i*GameWindow.BLOCK_SIZE);
    }
    /** �������� ������� */
    ///final LongProperty CheckForShootTimer = new SimpleLongProperty();
    final LongProperty FrameTimer = new SimpleLongProperty(0);
    AnimationTimer timer = new AnimationTimer(){
      long EveryTick = 0;
      //long EveryTickForBot = 0;
      @Override
      public void handle(long now){
        EveryTick++;
        // 55 ����� ~= 1 ���
        if (EveryTick > timeToNextMob){
          EveryTick = 0;
          for (int i = 0; i < rows; i++){
            if (Spawn[i].iterator < Spawn[i].count)
              try {
                Spawn[i].CreateMonster();
                if (now / updateFrequence != FrameTimer.get()){
                    Spawn[i].update();
                }
                FrameTimer.set(now / updateFrequence);
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
          }
        }
          /*if (GameMode == "Auto"){
          EveryTickForBot++;
          // 165 ����� ~= 3 ���
          if (EveryTickForBot > 165){
            EveryTickForBot = 0;
            if (bot.Iterator < bot.Count) bot.createTower();
          }
        }*/
          /*// �������� �� �������� ����� � ���������� 0.1
        if (now / 100000000 != CheckForShootTimer.get()){
          CheckForShooting();
          // ���������� Cooldown-� ������ �����
          for (int i = 0; i<Towers.size(); i++){
            Towers.get(i).TimeToShoot-= 0.1;
          }
        }*/
          //���������� �������������� �������� � ���������� 0.01 ���
          if (now / updateFrequence != FrameTimer.get()){
            for (int i = 0; i < rows; i++){
              Spawn[i].update();
            }
          }
          FrameTimer.set(now / updateFrequence);
          //CheckForShootTimer.set(now / 100000000);
        }
    };
    timer.start();
  }
  /**
   * ����� ������������� �����
   * @throws IOException 
   * @see LevelData
   */

  public void CreateMap() throws IOException{
    for (int i = 0; i< rows ; i++){
      for (int j = 0; j < columns; j ++ ){
        Block bl = new Block(GameWindow.offsetXY + j*GameWindow.BLOCK_SIZE, GameWindow.offsetXY + i*GameWindow.BLOCK_SIZE);
      }
    }
  }
}