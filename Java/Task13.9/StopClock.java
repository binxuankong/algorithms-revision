import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// A simple stop clock program. It has four output displays: the start time,
// stopped time, split time and elapsed time.
public class StopClock extends JFrame implements ActionListener
{
  // True if and only if the clock is running.
  private boolean isRunning = false;

  // The time when the clock is started
  // as milliseconds since midnight, January 1, 1970.
  private long startTime = 0;

  // The time when the clock is stopped
  // as milliseconds since midnight, January 1, 1970.
  private long stopTime = 0;

  // The split time.
  private long splitTime = 0;

  // A JTextField for showing the time.
  private final JTextField startTimeJTextField = new JTextField(30);
  private final JTextField stopTimeJTextField = new JTextField(30);
  private final JTextField elapsedTimeJTextField = new JTextField(30);
  private final JTextField splitTimeJTextField = new JTextField(30);

  // A button for starting/stopping the stop watch.
  private final JButton startStopJButton = new JButton("Start");

  // A button for splitting the stop watch.
  private final JButton splitJButton = new JButton("Split");

  // Constructor.
  public StopClock()
  {
    setTitle("Stop Clock");

    Container contents = getContentPane();
    // Use a grid layout with one column.
    contents.setLayout(new GridLayout(0, 1));

    contents.add(new JLabel("Started at:"));
    contents.add(startTimeJTextField);
    startTimeJTextField.setText("Not started");
    startTimeJTextField.setEnabled(false);

    contents.add(new JLabel("Stopped at:"));
    contents.add(stopTimeJTextField);
    stopTimeJTextField.setText("Not started");
    stopTimeJTextField.setEnabled(false);

    contents.add(new JLabel("Elapsed time (seconds):"));
    contents.add(elapsedTimeJTextField);
    elapsedTimeJTextField.setText("Not started");
    elapsedTimeJTextField.setEnabled(false);

    contents.add(new JLabel("Split time (seconds):"));
    contents.add(splitTimeJTextField);
    splitTimeJTextField.setText("Not started");
    splitTimeJTextField.setEnabled(false);

    startStopJButton.addActionListener(this);
    startStopJButton.setForeground(Color.red);
    contents.add(startStopJButton);

    splitJButton.addActionListener(this);
    splitJButton.setForeground(Color.red);
    contents.add(splitJButton);
    splitJButton.setEnabled(false);

    contents.setBackground(Color.green);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  } // StopClock

  // Perform action when the button is pressed.
  public void actionPerformed(ActionEvent event)
  {
    if (event.getSource() == startStopJButton)
    {
      if (!isRunning)
      {
        // Start the clock.
        startTime = System.currentTimeMillis();
        startTimeJTextField.setText("" + startTime);
        stopTimeJTextField.setText("Running...");
        elapsedTimeJTextField.setText("Running...");
        splitTimeJTextField.setText("Running...");
        splitTime = 0;
        startStopJButton.setText("Stop");
        splitJButton.setEnabled(true);
        isRunning = true;
      } // if
      else // isRunning
      {
        // Stop the clock and show the updated times.
        stopTime = System.currentTimeMillis();
        stopTimeJTextField.setText("" + stopTime);
        long elapsedMilliSeconds = stopTime - startTime;
        elapsedTimeJTextField.setText("" + elapsedMilliSeconds / 1000.0);
        splitTimeJTextField.setText("" + splitTime / 1000.0);
        startStopJButton.setText("Start");
        splitJButton.setEnabled(false);
        isRunning = false;
      } // else
    } // if
    else // splitJButton
    {
      if (isRunning)
      {
       	long currentTime = System.currentTimeMillis();
        splitTime = currentTime - startTime;
        splitTimeJTextField.setText("" + splitTime / 1000.0);
      } // if
    } // else

    pack();
  } // actionPerformed

  // Create a StopClock and make it appear on screen.
  public static void main(String[] args)
  {
    StopClock theStopClock = new StopClock();
    theStopClock.setVisible(true);
  } // main

} // class StopClock

