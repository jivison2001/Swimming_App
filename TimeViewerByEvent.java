import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.*;

public class TimeViewerByEvent implements ActionListener{
  private MyFrame frame;
  private JPanel mainPanel, selectorPanel, timesPanel, menuBtnPanel;
  private JButton mainMenuBtn, displayTimesBtn;
  private JComboBox selectEvent, selectNoTimesToDisplay;
  private JTable tableOfTimes;
  private JScrollPane tablePane;

  public TimeViewerByEvent() {
    frame = new MyFrame();
    frame.setUp();

    mainPanel = new JPanel(new GridLayout(2,1));
    mainPanel.setBounds(50,50,475,475);
    selectorPanel = new JPanel(new GridLayout(3,1));
    timesPanel = new JPanel();
    
    menuBtnPanel = new JPanel();
    menuBtnPanel.setBounds(
      Constants.SCREEN_WIDTH - 125, 
      Constants.SCREEN_HEIGHT - 75,
      100,
      50
    );
    mainMenuBtn = new JButton("Main menu");
    mainMenuBtn.addActionListener(this);

    selectEvent = new JComboBox<String>(Constants.EVENTS);

    String[] optionsForQuantityOfTimes = {"10","20","30","40"};
    selectNoTimesToDisplay = new JComboBox<String>(optionsForQuantityOfTimes);

    displayTimesBtn = new JButton("Display Times");
    displayTimesBtn.addActionListener(this);

    Object[][] tableData = {
      {"Time","Name"},
      {"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},
      {"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},
      {"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},
      {"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""}
    };
    String[] columnHeading = {"",""};
    tableOfTimes = new JTable(tableData, columnHeading);
    tablePane = new JScrollPane(tableOfTimes);
    tablePane.setViewportView(tableOfTimes);
    tableOfTimes.setFillsViewportHeight(true);
    tableOfTimes.setEnabled(false);

    menuBtnPanel.add(mainMenuBtn);
    selectorPanel.add(selectEvent);
    selectorPanel.add(selectNoTimesToDisplay);
    selectorPanel.add(displayTimesBtn);
    mainPanel.add(selectorPanel);
    mainPanel.add(tablePane);
    frame.add(menuBtnPanel);
    frame.add(mainPanel);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == mainMenuBtn) {
      MainScreen m = new MainScreen();
      frame.dispose();
    }
    if (e.getSource() == displayTimesBtn) {
      String event = selectEvent.getSelectedItem().toString();
      int noToDisplay = Integer.parseInt(selectNoTimesToDisplay.getSelectedItem().toString());
      clearTableValues();
      TimeGetterByEvent timeGetterByEvent = new TimeGetterByEvent(event, noToDisplay, tableOfTimes);
    }
  }

  private void clearTableValues() {
    for(int i = 1; i <= 40; i++) {
      tableOfTimes.setValueAt("", i, 0);
      tableOfTimes.setValueAt("", i, 1);
    }
  }
}
