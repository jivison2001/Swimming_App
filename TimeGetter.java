import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTable;

/**
 * Class that retrieves the times of an individual swimmer and inserts them into the table
 * on the individual time viewer screen
 */
public class TimeGetter {
  private String name;         // name of the swimmer.
  private String filepath;     // the filepath of the swimmers times diretory.
  private JTable tableOfTimes; // the JTable displayed on the time viewer screen.

  public TimeGetter(String name, JTable timesTable) {
    this.name = name;
    this.tableOfTimes = timesTable;
    
    insertIntoTable("free");
    insertIntoTable("back");
    insertIntoTable("breast");
    insertIntoTable("fly");
    insertIntoTable("im");
  }

  /**
   * For each stroke of a swimmer, the times for each distance are insterted into the JTable
   * @param stroke the stroke which indicates which file to get the times from
   */
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
