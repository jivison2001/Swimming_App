import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTable;

public class TimeGetterByEvent {
  public TimeGetterByEvent(String event, int noToDisplay, JTable tableOfTimes) {
    try {
      String[] eventSplitDistStroke = event.split(" ");
      String filePathEvent = "Swimmers/Times/ByEvent/" + eventSplitDistStroke[0] + eventSplitDistStroke[1] + ".csv";
      File eventFile = new File(filePathEvent);
      BufferedReader br = new BufferedReader(new FileReader(eventFile));

      br.readLine();

      String line;
      int i = 1;

      while (((line = br.readLine()) != null) && i <= noToDisplay) {
        if (!line.equals("") & !line.equals(",")) {
          String[] timeAndNameArray = line.split(",");
          //adding time
          tableOfTimes.setValueAt(timeAndNameArray[0], i, 0);
          //adding name
          tableOfTimes.setValueAt(timeAndNameArray[1], i, 1);
          i++;
        }else {
          i++;
        }
      }
      br.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
