import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TimeRecorderScreen implements ActionListener{
  private MyFrame frame;
  private JPanel mainPanel, swimmerEventTimePanel, menuBtnPanel, recordTimeBtnPanel;
  private SplitsPanel splitsPanel;
  private JComboBox selectSwimmer, selectEvent;
  private JTextField timeTextField;
  private JButton mainMenuBtn, recordTimeBtn;
  private ArrayList<String> swimmers;

  public TimeRecorderScreen() {
    frame = new MyFrame();
    frame.setUp();
    mainPanel = new JPanel(new GridLayout(2,1));
    mainPanel.setBounds(50,50,475,475);
    swimmerEventTimePanel = new JPanel(new GridLayout(3,1));
    menuBtnPanel = new JPanel();
    menuBtnPanel.setBounds(
      Constants.SCREEN_WIDTH - 125, 
      Constants.SCREEN_HEIGHT - 75,
      100,
      50
    );
    recordTimeBtnPanel = new JPanel();
    recordTimeBtnPanel.setBounds(0,Constants.SCREEN_HEIGHT - 75,200,100);

    swimmers = makeSwimmersList();
  
    selectSwimmer = new JComboBox<>(swimmers.toArray());
    selectEvent = new JComboBox<>(Constants.EVENTS);
    selectEvent.addActionListener(this);

    timeTextField = new JTextField();

    splitsPanel = new SplitsPanel();
    splitsPanel.setLayout(new GridLayout(6,3));
    splitsPanel.add_elements();

    mainMenuBtn = new JButton("Main menu");
    mainMenuBtn.addActionListener(this);

    recordTimeBtn = new JButton("Record time");
    recordTimeBtn.addActionListener(this);

    swimmerEventTimePanel.add(selectSwimmer);
    swimmerEventTimePanel.add(selectEvent);
    swimmerEventTimePanel.add(timeTextField);
    mainPanel.add(swimmerEventTimePanel);
    mainPanel.add(splitsPanel);
    menuBtnPanel.add(mainMenuBtn);
    recordTimeBtnPanel.add(recordTimeBtn);
    frame.add(mainPanel);
    frame.add(menuBtnPanel);
    frame.add(recordTimeBtnPanel);
  }

  private ArrayList<String> makeSwimmersList() {
    ArrayList<String> swimmers = new ArrayList<String>();
    try {
      File swimmersName = new File("Swimmers/SwimmersNames.txt");
      Scanner myReader = new Scanner(swimmersName);
      while (myReader.hasNextLine()) {
        String swimmerName = myReader.nextLine();
        swimmers.add(swimmerName);
      }
      myReader.close();

      for (String s:swimmers) {
        String directory = "Swimmers/Times/" + s;
        File dir = new File(directory);
        dir.mkdir();
        makeStrokeFile(new File("Swimmers/Times/" + s + "/free.csv"));
        makeStrokeFile(new File("Swimmers/Times/" + s + "/back.csv"));
        makeStrokeFile(new File("Swimmers/Times/" + s + "/breast.csv"));
        makeStrokeFile(new File("Swimmers/Times/" + s + "/fly.csv"));
        makeStrokeFile(new File("Swimmers/Times/" + s + "/im.csv"));
      }
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    } catch (IOException e) {
      e.printStackTrace();
    }
    swimmers.sort(null);
    return swimmers;
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == mainMenuBtn) {
      MainScreen m = new MainScreen();
        frame.dispose();
    }
    if (e.getSource() == recordTimeBtn) {
      String name = selectSwimmer.getSelectedItem().toString();
      String[] event = selectEvent.getSelectedItem().toString().split("\\s+");
      int distance = Integer.parseInt(event[0]);
      String stroke = event[1];
      String time = timeTextField.getText().replaceAll(" ", "");
      //formatting the time to me mm:ss.ms
      if (time.charAt(1) != ':') {
        time = "0:" + time;
      }
      TimeRecorder t = new TimeRecorder(name,distance,stroke,time);

    }
    if (e.getSource() == selectEvent) {
      //takes the event selected from the combo box
      String event = selectEvent.getSelectedItem().toString();
      //parses it to select the distance
      splitsPanel.remove_unneeded_textfields(event.split("\\s+")[0]);
    }
    
  }

  private void makeStrokeFile(File stroke) throws IOException{
    if(stroke.createNewFile()) {
      FileWriter fw = new FileWriter(stroke);
      if (stroke.getName().contains("free.csv")) {
        fw.write("50,100,200,400,800,1500\n\" \",\" \",\" \",\" \",\" \",\" \"");
      }else if (stroke.getName().contains("im.csv")) {
        fw.write("100,200,400\n\" \",\" \",\" \"");
      }else {
        fw.write("50,100,200\n\" \",\" \",\" \"");
      }
      fw.close();
    }
  }
}
