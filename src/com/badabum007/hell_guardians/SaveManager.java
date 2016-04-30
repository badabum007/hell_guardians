package com.badabum007.hell_guardians;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class, that controls game saving operations
 * 
 * @author badabum007
 */
public class SaveManager {
  /** temp save file */
  String tempSave;
  
  String tempFileName;
  String dateFormat ="yyyyMMdd_HHmmss";
  String extension = ".sav";
  String saveDir = "saves/";
  
  SaveManager(String name){
    tempFileName = name;
  }

  /**
   * Creates a file with a specified name
   * 
   * @param fileName - name of file
   */
  public void createTempFile(String fileName) {
    File tempFile = new File(fileName);
    try {
      FileWriter out = new FileWriter(tempFile.getAbsoluteFile());
      out.write("");
      out.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    try {
      tempFile.createNewFile();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes string to a file
   * 
   * @param fileName - name of file
   * @param stringToAdd - string to add
   */
  public void addToFile(String fileName, String stringToAdd) {
    File file = new File(fileName);
    try {
      FileWriter out = new FileWriter(file.getAbsoluteFile(), true);
      out.write(stringToAdd);
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a game save file
   * 
   * @throws IOException
   */
  public void createSaveFile() throws IOException {
    String fileName = saveDir +  
        new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime()) + "_"
            + GameWindow.gameRoot.towers.size() + extension;
    File file = new File(fileName);
    try {
      file.createNewFile();
      BufferedReader reader = new BufferedReader(new FileReader(tempFileName));
      FileWriter out = new FileWriter(new File(fileName).getAbsoluteFile(), true);
      String line;
      out.write(Shot.damage + "\n");
      while ((line = reader.readLine()) != null) {
        out.write(line + "\n");
      }
      out.close();
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}

/*  *//**
   * Метод, возвращающий все файлы с расширением extension
   * 
   * @return saveFiles - массив возвращаемых файлов
   *//*
  public static File[] getSaveList() {
    File[] allFiles;
    File[] saveFiles;
    File filesPath = new java.io.File(new File(".").getAbsolutePath());
    allFiles = filesPath.listFiles();
    int j = 0;
    for (int i = 0; i < allFiles.length; i++) {
      String tempNameOfFile = allFiles[i].getName();
      if (tempNameOfFile.endsWith("..sav")) {
        j++;
      }
    }
    saveFiles = new File[j];
    j = 0;
    for (int i = 0; i < allFiles.length; i++) {
      String tempNameOfFile = allFiles[i].getName();
      if (tempNameOfFile.endsWith("..sav")) {
        saveFiles[j] = allFiles[i];
        j++;
      }
    }
    return saveFiles;
  }

  *//**
   * Метод сортирует список файлов - Java Quick Sort;
   *//*
  public File[] getSortedJavaList() {
    File[] saveFiles = getSaveList();
    int[] countOfTowers = new int[saveFiles.length];
    for (int i = 0; i < saveFiles.length; i++) {
      countOfTowers[i] = getAmountOfTowers(saveFiles[i].getName());
    }
    quickSort(countOfTowers, saveFiles, 0, countOfTowers.length - 1);
    return saveFiles;
  }

  *//**
   * Метод сортирует список файлов - Scala Quick Sort;
   *//*
  public File[] getSortedScalaList() {
    File[] saveFiles = getSaveList();
    int[] countOfTowers = new int[saveFiles.length];
    for (int i = 0; i < saveFiles.length; i++) {
      countOfTowers[i] = getAmountOfTowers(saveFiles[i].getName());
    }
    QuickSort qSortObject = new QuickSort();
    qSortObject.sort(countOfTowers, saveFiles);
    return saveFiles;
  }

  *//**
   * Метод возвращает кол-во вышек в .sav файле
   * 
   * @param fileName - имя файла
   *//*
  private int getAmountOfTowers(String fileName) {
    String[] mas = fileName.split("_");
    mas[2] = mas[2].replace("..sav", "");
    return (Integer.parseInt(mas[2]));
  }

  *//**
   * Java Qsort
   * 
   * @param arr - массив с числом вышек
   * @param files - массив файлов
   *//*
  int partition(int arr[], File files[], int left, int right) {
    int i = left, j = right;
    int tmp;
    File temp;
    int pivot = arr[(left + right) / 2];
    while (i <= j) {
      while (arr[i] < pivot) {
        i++;
      }
      while (arr[j] > pivot) {
        j--;
      }
      if (i <= j) {
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        temp = files[i];
        files[i] = files[j];
        files[j] = temp;
        i++;
        j--;
      }
    } ;
    return i;
  }

  *//**
   * Java Qsort
   * 
   * @param arr - массив с числом вышек
   * @param files - массив файлов
   *//*
  void quickSort(int arr[], File files[], int left, int right) {
    int index = partition(arr, files, left, right);
    if (left < index - 1) {
      quickSort(arr, files, left, index - 1);
    }
    if (index < right) {
      quickSort(arr, files, index, right);
    }
  }

  *//**
   * Метод генерирует 1000 ..sav файлов
   * 
   * @throws IOException
   *//*
  void generateRandomFiles() throws IOException {
    Random rnd = new Random();
    int x, y;
    long time, i, n;
    for (int j = 0; j < COUNT_OF_FILES; j++) {
      time = 55;
      n = rnd.nextInt(20);
      String fileName =
          new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + j + "_"
              + n + "..sav";
      File file = new File(fileName);
      file.createNewFile();
      FileWriter out;
      out = new FileWriter(new File(fileName).getAbsoluteFile(), true);
      for (i = 0; i < n; i++) {
        x = rnd.nextInt(18) + 1;
        y = rnd.nextInt(14) + 1;
        String line = LevelData.levels[0][y];
        if (line.charAt(x) == '0') {
          out.write(x * Main.BLOCK_SIZE + " " + y * Main.BLOCK_SIZE + " " + time + "\n");
          time += 50;
        } else {
          i--;
        }
      }
      out.close();
    }
  }
}

*/