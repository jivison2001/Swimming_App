import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class TimeRecorder{
  private MyFrame frame;
  private JPanel mainPanel, swimmerEventTimePanel;
  private SplitsPanel splitsPanel;
  private JComboBox selectSwimmer, selectEvent;
  private JTextField timeTextField;

  public TimeRecorder() {
    frame = new MyFrame();
    frame.set_up();
    mainPanel = new JPanel(new GridLayout(2,1));
    swimmerEventTimePanel = new JPanel(new GridLayout(3,1));
    mainPanel.setBounds(50,50,475,475);

    ArrayList<String> swimmers = new ArrayList<String>();
    swimmers.add("Jack");
    swimmers.add("Emma");
    swimmers.sort(null);
  
    selectSwimmer = new JComboBox<>(swimmers.toArray());
    selectSwimmer.setPreferredSize(new Dimension(475, 50));
    selectEvent = new JComboBox<>(Constants.EVENTS);
    selectEvent.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String event = selectEvent.getSelectedItem().toString();
        splitsPanel.remove_unneeded_textfields(event.split("\\s+")[0]);
      }
    });

    timeTextField = new JTextField();

    splitsPanel = new SplitsPanel();
    splitsPanel.setLayout(new GridLayout(6,3));
    splitsPanel.add_elements();

    swimmerEventTimePanel.add(selectSwimmer);
    swimmerEventTimePanel.add(selectEvent);
    swimmerEventTimePanel.add(timeTextField);
    mainPanel.add(swimmerEventTimePanel);
    mainPanel.add(splitsPanel);
    frame.add(mainPanel);
  }

}
