import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TimeRecorder {
  private int enteredMinutes;
  private float enteredSeconds;
  private String time;
  private String[] timesArray;

  public TimeRecorder(String name, int distance, String stroke, String time) {
    this.time = time;

    // creating a file path directing to the correct swimmer and stroke
    String filePath = "Swimmers/Times/" + name + "/" + stroke +".csv";
    String times = "";

    // split the string time into integer minutes and float seconds
    String[] timeSplitToMinSecs = time.split(":");
    enteredMinutes = Integer.parseInt(timeSplitToMinSecs[0]);
    enteredSeconds = Float.parseFloat(timeSplitToMinSecs[1]);

    File file = new File(filePath);

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));

      br.readLine();
      times = br.readLine().replaceAll("\"", "");
      timesArray = times.split(",");

      if (distance == 50) {
        if (timesArray[0].equals(" ")) timesArray[0] = time;
        else checkChangeCurrentTime(0);
      }
      if (distance == 100) {
        if (timesArray[1].equals(" ")) timesArray[1] = time;
        else checkChangeCurrentTime(1);
      }
      if (distance == 200) {
        if (timesArray[2].equals(" ")) timesArray[2] = time;
        else checkChangeCurrentTime(2);
      }
      if (distance == 400) {
        if (timesArray[3].equals(" ")) timesArray[3] = time;
        else checkChangeCurrentTime(3);
      }
      if (distance == 800) {
        if (timesArray[4].equals(" ")) timesArray[4] = time;
        else checkChangeCurrentTime(4);
      }
      if (distance == 1500) {
        if (timesArray[5].equals(" ")) timesArray[5] = time;
        else checkChangeCurrentTime(5);
      }
      System.out.println(timesArray[0] + " " + timesArray[1] + " " + timesArray[2]);

      br.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * checks to see whether the current time needs to be changed with the one that has been entered
   * @param index
   */
  private void checkChangeCurrentTime(int index) {
    /* if (entered time number of minutes is the same the the current time) {
      if (entered time number of seconds is les than current time) {
        change current time to the entered time (entered time is faster)
      }
    }else if (the minutes of the entered time is less than that of the current time) {
      change current time to the entered time (entered time is faster)
    }
    otherwise the current time is faster than the entered time so don't change it
    */
    String[] currentTimeSplitMinSecs = timesArray[index].split(":");
    int currentMinutes = Integer.parseInt(currentTimeSplitMinSecs[0]);
    float currentSeconds = Float.parseFloat(currentTimeSplitMinSecs[1]);
    if (enteredMinutes == currentMinutes) {
      if (enteredSeconds < currentSeconds) {
        timesArray[index] = time;
      }
    }else if (enteredMinutes < currentMinutes) timesArray[index] = time;
  }
}