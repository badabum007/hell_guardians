package com.badabum007.hell_guardians;

import java.io.IOException;
import java.util.ArrayList;

/**
 * �����, ����������� ����� ��������� ��������
 * @author pixxx
 */
public class Spawner{
  /** ���-�� ��������� ��������(� �����) */
  int count;

  int startPosX;
  int startPosY;

  /** ���-�� ��� ���������� ��������*/
  int iterator;

  /** ������ ���� ��������� ���� Spawner-�� �������� */
  ArrayList<Enemy> enemies;

  int X;
  int offset = 1;

  /**
   * ������� Spawner � ��������� �����������
   * @param count - ���-�� ��������� ��������
   * @param startPosX - ��������� ����� �� X
   * @param startPosY - ��������� ����� �� Y
   */
  public Spawner(int count, int startPosX, int startPosY){
    this.startPosX = startPosX;
    this.startPosY = startPosY;
    this.count = count;
    iterator = 0;
    enemies = new ArrayList<Enemy>();
  }

  /** �������� ������� 
   * @throws IOException */
  public void CreateMonster() throws IOException{
    enemies.add(new Enemy(startPosX, startPosY));
    iterator++;
  }

  /** �����, ����������� �������������� �������� */
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