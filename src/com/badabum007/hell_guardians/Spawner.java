package com.badabum007.hell_guardians;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, описывающий место появления монстров
 * @author pixxx
 */
public class Spawner{
  /** Кол-во выходящих монстров(в сумме) */
  int count;

  int startPosX;
  int startPosY;

  /** Кол-во уже выпущенных монстров*/
  int iterator;

  /** Список всех созданных этим Spawner-ом монстров */
  ArrayList<Enemy> enemies;

  int X;
  int offset = 1;

  /**
   * Создает Spawner с заданными параметрами
   * @param count - Кол-во выходящих монстров
   * @param startPosX - Стартовая точка по X
   * @param startPosY - Стартовая точка по Y
   */
  public Spawner(int count, int startPosX, int startPosY){
    this.startPosX = startPosX;
    this.startPosY = startPosY;
    this.count = count;
    iterator = 0;
    enemies = new ArrayList<Enemy>();
  }

  /** Создание монстра 
   * @throws IOException */
  public void CreateMonster() throws IOException{
    enemies.add(new Enemy(startPosX, startPosY));
    iterator++;
  }

  /** Метод, обновляющий местоположение монстров */
  public void update(){
    for (int j=0; j<enemies.size(); j++){
      X = (int)(enemies.get(j).posX);
      if (X > GameWindow.offsetXY)
      {
        enemies.get(j).moveX(-offset);
        continue;
      }
      else{
        enemies.get(j).EnemyGoalRiched();
      }
    }
  }
}