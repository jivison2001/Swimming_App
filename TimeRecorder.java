import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TimeRecorder {
  private int enteredMinutes; // minutes of the entered time
  private float enteredSeconds; // seconds of the entered time
  private String time, name; //entered time in format mm:ss.ms
  private String[] timesArray; // current times on the csv file

  public TimeRecorder(String name, int distance, String stroke, String time) {
    this.time = time;
    this.name = name;

    // creating a file path directing to the correct swimmer and stroke
    String filePathSwimmer = "Swimmers/Times/" + name + "/" + stroke +".csv";
    String times = "";

    // split the string time into integer minutes and float seconds
    String[] timeSplitToMinSecs = time.split(":");
    enteredMinutes = Integer.parseInt(timeSplitToMinSecs[0]);
    enteredSeconds = Float.parseFloat(timeSplitToMinSecs[1]);

    File swimmersTimesFile = new File(filePathSwimmer);

    try {
      BufferedReader br = new BufferedReader(new FileReader(swimmersTimesFile));

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

      br.close();

      FileWriter fileWriterForSwimmers = new FileWriter(filePathSwimmer);
 
      if (stroke.equals("free")) {
        fileWriterForSwimmers.write("50,100,200,400,800,1500\n");
        fileWriterForSwimmers.write(
          timesArray[0] + "," + timesArray[1] + "," + timesArray[2] + "," + timesArray[3] + "," + timesArray[4] + "," + timesArray[5]
        );
      }else if (stroke.equals("im")){
        fileWriterForSwimmers.write("100,200,400\n");
        fileWriterForSwimmers.write(
          timesArray[0] + "," + timesArray[1] + "," + timesArray[2]
        );
      }else {
        fileWriterForSwimmers.write("50,100,200\n");
        fileWriterForSwimmers.write(
          timesArray[0] + "," + timesArray[1] + "," + timesArray[2]
        );
      }
    
      fileWriterForSwimmers.close();

      /* read the whole file and put it into a string ArrayList
       * make an string array containing each line
       * loop through the array to see if someones name is already there
       *  for each line in array
       *  check if line contains name
       *  if a line contains name
       *    compare the times
       *    if the entered time is faster
       *      replace current time
       *    else don't add the entered time
       *  else don't add entered time
       */
      String filePathEvent = "Swimmers/Times/ByEvent/" + Integer.toString(distance) + stroke + ".csv";
      File eventFile = new File(filePathEvent);
      BufferedReader br2 = new BufferedReader(new FileReader(eventFile));
      String line = "";
      ArrayList<String> eventFileLines = new ArrayList<String>();

      boolean swimmerAlreadyOnFile = false;
      br2.readLine();
      while ((line = br2.readLine()) != null) {
        eventFileLines.add(line + "\n");
        if (line.contains(name)) swimmerAlreadyOnFile = true;
      }
      br2.close();

      if(swimmerAlreadyOnFile) {
        for (int i = 0; i < eventFileLines.size(); i++) {
          eventFileLines.set(i, checkContainsName(eventFileLines.get(i)));
        }
      } else eventFileLines.add(time + "," + name + "\n");
      eventFileLines.sort(null);

      FileWriter eventFileWriter = new FileWriter(eventFile);
      eventFileWriter.write("time,name\n");
      for (int i = 0; i < eventFileLines.size(); i++) {
        eventFileWriter.write(eventFileLines.get(i));
      }
      eventFileWriter.close();
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sees whether each line of the csv file for the event contains the person time that is being entered name
   * If the name is on the line then check to see if their entered time is faster than the current one on the cv.
   * @param line a row of a ByStroke csv file that has a swimmers time and name.
   * @return returns either the same line passed as a parameter or a new line that contains the updated time.
   */
  private String checkContainsName(String line) {
    if (line.contains(name)) {
      return checkTimeIsFaster(line);
    } else {
      return line;
    }
  }

  /**
   * Check whether a time entered is faster than the one currently
   * one the stroke sheet
   * @param line a row of a ByStroke csv file that has a swimmers time and name
   * @return returns the line which may have changed if the entered time was faster than the one already on the file
   */
  private String checkTimeIsFaster(String line) {
    String lineWithoutQuotations = line.replaceAll("\"", "");
    String currentTime = lineWithoutQuotations.split(",")[0];
    String[] currentTimeSplitMinSecs = currentTime.split(":");
    int currentMinutes = Integer.parseInt(currentTimeSplitMinSecs[0]);
    float currentSeconds = Float.parseFloat(currentTimeSplitMinSecs[1]);
    if (enteredMinutes == currentMinutes) {
      if (enteredSeconds < currentSeconds) {
        line = time + "," + name;
        return line;
      }
    }else if (enteredMinutes < currentMinutes) line =  time + "," + name + "\n";
    return line;
  }

  /**
   * checks to see whether the current time needs to be changed with the one that has been entered
   * @param index the index of the value that might need to be change in the array of current times
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