import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

// Program to show the possible values that can be weighed using three
// weights given by the user.
public class ThreeWeights extends JFrame implements ActionListener
{
  // A text field for the user to enter the weight.
  private final JTextField weight1JTextField = new JTextField(10);
  private final JTextField weight2JTextField = new JTextField(10);
  private final JTextField weight3JTextField = new JTextField(10);

  // A text area for the resulting values, 20 lines of 10 characters.
  private final JTextArea displayJTextArea = new JTextArea(20, 10);

  // Constructor.
  public ThreeWeights()
  {
    setTitle("Three Weights");

    Container contents = getContentPane();
    contents.setLayout(new BorderLayout());

    // A JPanel for the three text fields.
    // It will be a GridLayout of three times three,
    // at the top of the JFrame contents.
    JPanel weightsPanel = new JPanel();
    contents.add(weightsPanel, BorderLayout.NORTH);
    weightsPanel.setLayout(new GridLayout(3, 3));

    // Add three JLabels and three JTextFields to the weightsPanel.
    weightsPanel.add(new JLabel("Weight 1:"));
    weightsPanel.add(weight1JTextField);
    weightsPanel.add(new JLabel("Weight 2:"));
    weightsPanel.add(weight2JTextField);
    weightsPanel.add(new JLabel("Weight 3:"));
    weightsPanel.add(weight3JTextField);

    // The result JScrollPane/JTextArea goes in the centre.
    contents.add(new JScrollPane(displayJTextArea), BorderLayout.CENTER);

    // The JButton goes at the bottom.
    JButton displayJButton = new JButton("Display");
    contents.add(displayJButton, BorderLayout.SOUTH);
    displayJButton.addActionListener(this);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  } // ThreeWeights

  // Act upon the button being pressed.
  public void actionPerformed(ActionEvent event)
  {
    // Empty the text area to remove any previous result.
    displayJTextArea.setText("");

    int weight1 = Integer.parseInt(weight1JTextField.getText());
    int weight2 = Integer.parseInt(weight2JTextField.getText());
    int weight3 = Integer.parseInt(weight3JTextField.getText());

    displayJTextArea.append("------------------------------" + "\n");
    displayJTextArea.append("Possible values:" + "\n");
    displayJTextArea.append("------------------------------" + "\n");

    for (int w1 = -1; w1 <= 1; w1++)
      for (int w2 = -1; w2 <= 1; w2++)
        for (int w3 = -1; w3 <= 1; w3++)
          {
            int value = w1 * weight1 + w2 * weight2 + w3 * weight3;
            displayJTextArea.append(value + "\n");
          }

    displayJTextArea.append("------------------------------" + "\n");
  } // actionPerformed

  // Create a ThreeWeights and make it appear on the screen.
  public static void main(String[] args)
  {
    new ThreeWeights().setVisible(true);
  } // main

} // class ThreeWeights
