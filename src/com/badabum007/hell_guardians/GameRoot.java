package com.badabum007.hell_guardians;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.layout.Pane;

/**
 * Класс,  описывающий движок игры
 * @author pixxx
 */
public class GameRoot extends Pane {

  /** Вышки, добавленные на карту*/
  ArrayList<Tower> Towers;

  /** Режим игры: автоматический(Auto) или обычный(Normal) */
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

  /** Имеющиеся на карте Спаунеры */
  Spawner[] Spawn = new Spawner[rows];

  /**
   * Метод, реализующий логику игры
   */

  public void StartGame()throws IOException{
    CreateMap();
    int enemyCount = 2;
    for (int i = 0; i < rows; i++){
      Spawn[i] = new Spawner(enemyCount, MainGameMenu.width,GameWindow.offsetXY + i*GameWindow.BLOCK_SIZE);
    }
    /** Описание таймера */
    ///final LongProperty CheckForShootTimer = new SimpleLongProperty();
    final LongProperty FrameTimer = new SimpleLongProperty(0);
    AnimationTimer timer = new AnimationTimer(){
      long EveryTick = 0;
      //long EveryTickForBot = 0;
      @Override
      public void handle(long now){
        EveryTick++;
        // 55 тиков ~= 1 сек
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
          // 165 тиков ~= 3 сек
          if (EveryTickForBot > 165){
            EveryTickForBot = 0;
            if (bot.Iterator < bot.Count) bot.createTower();
          }
        }*/
          /*// Проверка на выстрелы вышек с интервалом 0.1
        if (now / 100000000 != CheckForShootTimer.get()){
          CheckForShooting();
          // Уменьшение Cooldown-а каждой вышки
          for (int i = 0; i<Towers.size(); i++){
            Towers.get(i).TimeToShoot-= 0.1;
          }
        }*/
          //Обновление местоположения монстров с интервалом 0.01 сек
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
   * Метод прорисовывает карту
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