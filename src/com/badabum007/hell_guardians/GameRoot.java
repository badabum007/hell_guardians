package com.badabum007.hell_guardians;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Класс, описывающий движок игры
 * 
 * @author pixxx
 */
public class GameRoot extends Pane {

  /** Вышки, добавленные на карту */
  ArrayList<Tower> Towers;

  /** Режим игры: автоматический(Auto) или обычный(Normal) */
  public static String GameMode;

  /** Бот для автоматического режима */
  Bot bot;

  // music player object
  private MediaPlayer menuMp;

  public GameRoot() {
    Towers = new ArrayList<Tower>();
    this.setVisible(false);
    bot = new Bot();
  }

  public static final int rows = 4;
  public static final int columns = 6;

  final int updateFrequence = 10000000;

  /** Имеющиеся на карте Спаунеры */
  Spawner[] Spawn = new Spawner[rows];

  /**
   * Метод, реализующий логику игры
   */

  int enemyCount = 2;
  long TimeToNextWave = 500;
  final long timeToNextMob = 100;

  public void StartGame() throws IOException {
    // adding music
    Media media = new Media(
        new File("res/music/Gonzalo_Varela_-_03_-_Underwater_Lab.mp3").toURI().toString());
    menuMp = new MediaPlayer(media);
    // autostart when program is launched
    menuMp.setAutoPlay(true);
    // infinite song playing
    menuMp.setCycleCount(MediaPlayer.INDEFINITE);
    menuMp.play();
    MediaView mediaView = new MediaView(menuMp);
    getChildren().add(mediaView);
    CreateMap();
    for (int i = 0; i < rows; i++) {
      Spawn[i] = new Spawner(enemyCount, MainGameMenu.width,
          GameWindow.offsetXY + i * GameWindow.blockSize);
    }
    /** Описание таймера */
    final LongProperty CheckForShootTimer = new SimpleLongProperty();
    final LongProperty FrameTimer = new SimpleLongProperty(0);
    AnimationTimer timer = new AnimationTimer() {
      long EveryTick = 0;
      long WaveTick = 0;
      long EveryTickForBot = 0;

      @Override
      public void handle(long now) {
        EveryTick++;
        WaveTick++;
        // 55 тиков ~= 1 сек
        if (EveryTick > timeToNextMob) {
          EveryTick = 0;
          if (WaveTick > (int) (new Random().nextInt((int) TimeToNextWave))) {
            TimeToNextWave += timeToNextMob;
            enemyCount += 1;
            for (int i = 0; i < rows; i++) {
              if ((int) (new Random().nextInt(3)) == 0) {
                Spawn[i].count += (int) (new Random().nextInt((int) enemyCount));
              }
            }
            WaveTick = 0;
          }
          for (int i = 0; i < rows; i++)
            if (Spawn[i].iterator < Spawn[i].count)
              try {
                if ((int) (new Random().nextInt(3)) == 0) {
                Spawn[i].CreateMonster();
                }
                if (now / updateFrequence != FrameTimer.get()) {
                  if (Spawn[i].update() == -1) {
                    InputStream is;
                    try {
                      is = Files.newInputStream(Paths.get("res/images/game_over.jpg"));
                      Image img = new Image(is);
                      ImageView imgView = new ImageView(img);
                      getChildren().add(imgView);
                      this.stop();
                      is.close();
                    } catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                    }
                  } ;
                }
                FrameTimer.set(now / updateFrequence);
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
        }
        if (GameMode == "Auto") {
          EveryTickForBot++;
          // 165 тиков ~= 3 сек
          if (EveryTickForBot > (int)(Math.random()*70+50)) {
            EveryTickForBot = 0;
            if (bot.currentCount < bot.maxCount) {
              try {
                bot.createTower();
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
          }
        }
        // Проверка на выстрелы вышек с интервалом 0.1
        if (now / updateFrequence != CheckForShootTimer.get()) {
          CheckForShooting();
          // Уменьшение Cooldown-а каждой вышки
          for (int i = 0; i < Towers.size(); i++) {
            Towers.get(i).TimeToShoot -= 0.1;
          }
        }
        // Обновление местоположения монстров с интервалом 0.01 сек
        if (now / updateFrequence != FrameTimer.get()) {
          for (int i = 0; i < rows; i++) {
            if (Spawn[i].update() == -1) {
              InputStream is;
              try {
                is = Files.newInputStream(Paths.get("res/images/game_over.jpg"));
                Image img = new Image(is);
                ImageView imgView = new ImageView(img);
                getChildren().add(imgView);
                this.stop();
                is.close();
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            } ;
          }
        }
        FrameTimer.set(now / updateFrequence);
        CheckForShootTimer.set(now / updateFrequence);
      }
    };
    timer.start();
  }

  /**
   * Метод прорисовывает карту
   * 
   * @throws IOException
   */

  public void CreateMap() throws IOException {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Block bl = new Block(GameWindow.offsetXY + j * GameWindow.blockSize,
            GameWindow.offsetXY + i * GameWindow.blockSize);
      }
    }
  }

  /** Метод, реализующий поиск целей и генерации выстрела */
  public void CheckForShooting() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < Spawn[i].enemies.size(); j++) {
        if (Spawn[i].enemies.get(j).health <= 0) {
          Spawn[i].enemies.remove(j);
          continue;
        }
        for (int k = 0; k < Towers.size(); k++) {
          double EnemyPosX = Spawn[i].enemies.get(j).getTranslateX();
          double EnemyPosY = Spawn[i].enemies.get(j).getTranslateY();
          double TowerPosX = Towers.get(k).getTranslateX();
          double TowerPosY = Towers.get(k).getTranslateY();
          if ((EnemyPosX - TowerPosX > 0) && (TowerPosY - EnemyPosY == 0)
              && (EnemyPosX < MainGameMenu.width - GameWindow.offsetXY))
            // Условие проверки на Cooldown
            if (Towers.get(k).TimeToShoot <= 0) {
            // Установка Cooldown
            Towers.get(k).TimeToShoot = Towers.get(k).ShootCooldown;
            // Создание выстрела
            Towers.get(k).shots = new Shot(Spawn[i].enemies.get(j), Towers.get(k).posX + GameWindow.blockSize / 2, Towers.get(k).posY + GameWindow.blockSize / 2);
            }
        }
      }
    }
  }
}
