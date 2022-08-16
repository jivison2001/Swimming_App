import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SplitsPanel extends JPanel{
  private JTextField[] split;
  private JLabel fifty = new JLabel("50");
  private JLabel oneHundred = new JLabel("100");
  private JLabel oneHundredFifty = new JLabel("150");
  private JLabel twoHundred = new JLabel("200");
  private JLabel twoHundredFifty = new JLabel("250");
  private JLabel threeHundred = new JLabel("300");
  private JLabel threeHundredFfity = new JLabel("350");
  private JLabel fourHundred = new JLabel("400");
  
  public void add_elements() {
    split = new JTextField[8];
    for (int i = 0; i < 8; i++) {
      split[i] = new JTextField();
      split[i].setEnabled(false);
    }
    this.add(fifty);
    this.add(oneHundred);
    this.add(oneHundredFifty);
    this.add(split[0]);
    this.add(split[1]);
    this.add(split[2]);

    this.add(twoHundred);
    this.add(twoHundredFifty);
    this.add(threeHundred);
    this.add(split[3]);
    this.add(split[4]);
    this.add(split[5]);

    this.add(threeHundredFfity);
    this.add(fourHundred);
    this.add(new JLabel(""));
    this.add(split[6]);
    this.add(split[7]);
  }

  public void remove_unneeded_textfields(String dist) {
    int distance = Integer.parseInt(dist);
    if (distance == 50) {
      for (int i = 0; i < 8; i++) {
        split[i].setEnabled(false);
      }
    }else if (distance == 100) {
      for (int i = 0; i < 2; i++) {
        split[i].setEnabled(true);
      }
      for (int i = 2; i < 8; i++) {
        split[i].setEnabled(false);
      }
    }else if (distance == 200) {
      for (int i = 0; i < 4; i++) {
        split[i].setEnabled(true);
      }
      for (int i = 4; i < 8; i++) {
        split[i].setEnabled(false);
      }
    }else {
      for (int i = 0; i < 8; i++) {
        split[i].setEnabled(true);
      }
    }
  }
}
