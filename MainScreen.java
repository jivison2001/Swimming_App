import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class MainScreen {
  private JFrame frame;
  private JPanel mainPanel;
  private JButton recordTime;
  private JButton viewIndTimes;
  private JButton viewEventTimes;
  private JButton viewTeamSheet;
  public MainScreen() {
    frame = new JFrame();
    mainPanel = new JPanel(new GridLayout(4,1));
    mainPanel.setBounds(50,50,475,300);
    recordTime = new JButton("Record Time");
    viewIndTimes = new JButton("View Indivudal Times");
    viewEventTimes = new JButton("View Times By Event");
    viewTeamSheet = new JButton("Generate Team Sheet");
    
    frame_settings();

    mainPanel.add(recordTime);
    mainPanel.add(viewIndTimes);
    mainPanel.add(viewEventTimes);
    mainPanel.add(viewTeamSheet);
  }

  private void frame_settings() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setLayout(null);
    frame.add(mainPanel);
  }
}
