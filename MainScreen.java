import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class MainScreen implements ActionListener{
  private MyFrame frame;
  private JPanel mainPanel;
  private JButton recordTime;
  private JButton viewIndTimes;
  private JButton viewEventTimes;
  private JButton viewTeamSheet;
  
  public MainScreen() {
    frame = new MyFrame();
    mainPanel = new JPanel(new GridLayout(4,1));
    mainPanel.setBounds(50,50,475,300);
    recordTime = new JButton("Record Time");
    recordTime.addActionListener(this);
    viewIndTimes = new JButton("View Indivudal Times");
    viewIndTimes.addActionListener(this);
    viewEventTimes = new JButton("View Times By Event");
    viewEventTimes.addActionListener(this);
    viewTeamSheet = new JButton("Generate Team Sheet");
    viewTeamSheet.addActionListener(this);
    
    frame.setUp();
    frame.add(mainPanel);

    mainPanel.add(recordTime);
    mainPanel.add(viewIndTimes);
    mainPanel.add(viewEventTimes);
    mainPanel.add(viewTeamSheet);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == recordTime) {
      TimeRecorderScreen t = new TimeRecorderScreen();
      frame.dispose();
    }
    if (e.getSource() == viewIndTimes) {
      TimeViewerScreen t = new TimeViewerScreen();
      frame.dispose();
    }
    if (e.getSource() == viewEventTimes) {
      TimeViewerByEvent t = new TimeViewerByEvent();
      frame.dispose();
    }
  }
}
