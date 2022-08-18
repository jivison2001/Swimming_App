import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeViewerScreen  implements ActionListener{
  private MyFrame frame;
  private JPanel mainPanel, menuBtnPanel, selSwimmerDisBtnPanel;
  private JButton mainMenuBtn, displayTimesBtn;
  private JComboBox selectSwimmer;
  private JTable tableOfTimes;
  private ArrayList<String> swimmers;

  public TimeViewerScreen() {
    frame = new MyFrame();
    frame.setUp();
    mainPanel = new JPanel(new GridLayout(2,1));
    mainPanel.setBounds(50,50,475,475);
    selSwimmerDisBtnPanel = new JPanel(new GridLayout(2,1));

    menuBtnPanel = new JPanel();
    menuBtnPanel.setBounds(
      Constants.SCREEN_WIDTH - 125, 
      Constants.SCREEN_HEIGHT - 75,
      100,
      50
    );
    mainMenuBtn = new JButton("Main menu");
    mainMenuBtn.addActionListener(this);

    swimmers = makeSwimmersList();
    selectSwimmer = new JComboBox<>(swimmers.toArray());

    displayTimesBtn = new JButton("Show times");
    displayTimesBtn.addActionListener(this);

    Object[][] tableData = {
      {"", "Free","Back","Breast","Fly","IM"},
      {50, "","","","","",""},
      {100, "","","","","",""},
      {200, "","","","","",""},
      {400, "","","","","",""},
      {800, "","","","","",""},
      {1500, "","","","","",""}
    };
    String[] columnHeading = {"","","","","",""};
    tableOfTimes = new JTable(tableData, columnHeading);
    tableOfTimes.setEnabled(false);

    selSwimmerDisBtnPanel.add(selectSwimmer);
    selSwimmerDisBtnPanel.add(displayTimesBtn);    
    mainPanel.add(selSwimmerDisBtnPanel);
    mainPanel.add(tableOfTimes);
    menuBtnPanel.add(mainMenuBtn);
    frame.add(menuBtnPanel);
    frame.add(mainPanel);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == mainMenuBtn) {
      MainScreen m = new MainScreen();
        frame.dispose();
    }
    if (e.getSource() == displayTimesBtn) {
      String name = selectSwimmer.getSelectedItem().toString();
      TimeGetter t = new TimeGetter(name,tableOfTimes);
    }
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
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    }
    swimmers.sort(null);
    return swimmers;
  }
}
