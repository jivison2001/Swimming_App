import javax.swing.JFrame;

public class MyFrame extends JFrame{
  public void setUp() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    this.setResizable(false);
    this.setVisible(true);
    this.setLayout(null);
  }
}
