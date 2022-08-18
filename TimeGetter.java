import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTable;

public class TimeGetter {
  private String name;
  private String filepath;
  private JTable tableOfTimes;

  public TimeGetter(String name, JTable timesTable) {
    this.name = name;
    this.tableOfTimes = timesTable;
    
    insertIntoTable("free");
    insertIntoTable("back");
    insertIntoTable("breast");
    insertIntoTable("fly");
    insertIntoTable("im");
  }

  private void insertIntoTable(String stroke) {
    String times;
    int arrayLength = 3, column = 1;
    String[] strokeTimes;
    if (stroke.equals("free")) {
      arrayLength = 6;
    }
    else if (stroke.equals("back")) column = 2;
    else if (stroke.equals("breast")) column = 3;
    else if (stroke.equals("fly")) column = 4;
    else if (stroke.equals("im")) column = 5;

    filepath = "Swimmers/Times/" + name;
    try {
      File freeFile = new File(filepath + "/" + stroke +".csv");
      BufferedReader br = new BufferedReader(new FileReader(freeFile));

      br.readLine();
      times = br.readLine().replaceAll("\"", "");
      strokeTimes = times.split(",");
      for (int i = 0; i < arrayLength; i++) {
        tableOfTimes.setValueAt(strokeTimes[i], i+1, column);
      }
      br.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
