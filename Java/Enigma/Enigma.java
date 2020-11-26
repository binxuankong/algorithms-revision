import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.*;
import java.text.*;

public class Enigma extends JFrame implements ActionListener
{
  private final JTextField enigmaCode1JTextField = new JTextField(3);
  private final JTextField enigmaCode2JTextField = new JTextField(3);
  private final JTextField enigmaCode3JTextField = new JTextField(3);
  private final JTextField enigmaCode4JTextField = new JTextField(3);
  private final JTextField enigmaCode5JTextField = new JTextField(3);

  private JLabel logJLabel = new JLabel();

  private final JTextArea normalJTextArea = new JTextArea(30, 30);
  private final JTextArea translatedJTextArea = new JTextArea(30, 30);

  private final JButton enigmaCodeJButton = new JButton("INSERT ENIGMA CODE");
  private final JButton translateJButton = new JButton("TRANSLATE");
  private final JButton decypherJButton = new JButton("DECYPHER");
  private final JButton resetJButton = new JButton("RESET");

  private String enigmaName;
  private int cipherShift;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Date date;

  public Enigma()
  {
    setTitle("E N I G M A");
    Container contents = getContentPane();
    contents.setLayout(new BorderLayout());

    JPanel inputTopJPanel = new JPanel();
    contents.add(inputTopJPanel, BorderLayout.NORTH);
    inputTopJPanel.setLayout(new GridLayout(0, 1));

    inputTopJPanel.add(new JLabel("Welcome to ENIGMA"));
    date = new Date();
    inputTopJPanel.add(new JLabel("ENIGMA runned at: " + dateFormat.format(date)));
    inputTopJPanel.add(new JLabel("Enter the ENIGMA code:"));

    JPanel inputCodeJPanel = new JPanel();
    inputTopJPanel.add(inputCodeJPanel);
    inputCodeJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputCodeJPanel.add(enigmaCode1JTextField);
    inputCodeJPanel.add(new JLabel("-"));
    inputCodeJPanel.add(enigmaCode2JTextField);
    inputCodeJPanel.add(new JLabel("-"));
    inputCodeJPanel.add(enigmaCode3JTextField);
    inputCodeJPanel.add(new JLabel("-"));
    inputCodeJPanel.add(enigmaCode4JTextField);
    inputCodeJPanel.add(new JLabel("-"));
    inputCodeJPanel.add(enigmaCode5JTextField);

    AbstractDocument d1 = (AbstractDocument) enigmaCode1JTextField.getDocument();
    d1.setDocumentFilter(new Field1Listener());
    AbstractDocument d2 = (AbstractDocument) enigmaCode2JTextField.getDocument();
    d2.setDocumentFilter(new Field1Listener());
    AbstractDocument d3 = (AbstractDocument) enigmaCode3JTextField.getDocument();
    d3.setDocumentFilter(new Field1Listener());
    AbstractDocument d4 = (AbstractDocument) enigmaCode4JTextField.getDocument();
    d4.setDocumentFilter(new Field1Listener());
    AbstractDocument d5 = (AbstractDocument) enigmaCode5JTextField.getDocument();
    d5.setDocumentFilter(new Field1Listener());

    inputTopJPanel.add(enigmaCodeJButton);
    enigmaCodeJButton.addActionListener(this);
    inputTopJPanel.add(logJLabel);

    JPanel textJPanel = new JPanel();
    contents.add(textJPanel, BorderLayout.CENTER);
    textJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    textJPanel.add(new JScrollPane(normalJTextArea));
    normalJTextArea.setEditable(false);
    normalJTextArea.setLineWrap(true);
    normalJTextArea.setWrapStyleWord(true);
    textJPanel.add(new JScrollPane(translatedJTextArea));
    translatedJTextArea.setEditable(false);
    translatedJTextArea.setLineWrap(true);
    translatedJTextArea.setWrapStyleWord(true);

    JPanel buttonsJPanel = new JPanel();
    contents.add(buttonsJPanel, BorderLayout.SOUTH);
    buttonsJPanel.setLayout(new FlowLayout());
    buttonsJPanel.add(translateJButton);
    translateJButton.setEnabled(false);
    translateJButton.addActionListener(this);
    buttonsJPanel.add(decypherJButton);
    decypherJButton.setEnabled(false);
    decypherJButton.addActionListener(this);
    buttonsJPanel.add(resetJButton);
    resetJButton.setEnabled(false);
    resetJButton.addActionListener(this);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
  }

  public void actionPerformed(ActionEvent event)
  {
    if (event.getSource() == enigmaCodeJButton)
    {
      if (enigmaCode1JTextField.getText().equals("ENI")
          && enigmaCode5JTextField.getText().equals("GMA"))
      {
        enigmaName = "ENIGMA " + enigmaCode2JTextField.getText() + "-" + enigmaCode3JTextField.getText() + "-" + enigmaCode4JTextField.getText();
        int number1 = Integer.parseInt(enigmaCode2JTextField.getText());
        int number2 = Integer.parseInt(enigmaCode3JTextField.getText());
        int number3 = Integer.parseInt(enigmaCode4JTextField.getText());
        cipherShift = (number1 % 7) + (number2 * number3) / 13;

        enigmaCode1JTextField.setEnabled(false);
        enigmaCode2JTextField.setEnabled(false);
        enigmaCode3JTextField.setEnabled(false);
        enigmaCode4JTextField.setEnabled(false);
        enigmaCode5JTextField.setEnabled(false);
        normalJTextArea.setEditable(true);
        enigmaCodeJButton.setEnabled(false);
        translateJButton.setEnabled(true);
        decypherJButton.setEnabled(true);
        resetJButton.setEnabled(true);

        date = new Date();
        logJLabel.setText(enigmaName + " accessed at: " + dateFormat.format(date));
      }
      else
      {
        date = new Date();
        logJLabel.setText("ERROR. Incorrect ENIGMA code accessed at: " + dateFormat.format(date));
      }
    }
    else if (event.getSource() == translateJButton)
    {
      String message = normalJTextArea.getText();
      CaeserCipher encoder = new CaeserCipher(cipherShift);
      translatedJTextArea.setText(encoder.translate(message));

      date = new Date();
      logJLabel.setText(enigmaName + " translated at: " + dateFormat.format(date));
    }
    else if (event.getSource() == decypherJButton)
    {
      String message = normalJTextArea.getText();
      CaeserCipher decoder = new CaeserCipher(-cipherShift);
      translatedJTextArea.setText(decoder.translate(message));

      date = new Date();
      logJLabel.setText(enigmaName + " decyphered at: " + dateFormat.format(date));
    }
    else if (event.getSource() == resetJButton)
    {
      enigmaCode1JTextField.setEnabled(true);
      enigmaCode2JTextField.setEnabled(true);
      enigmaCode3JTextField.setEnabled(true);
      enigmaCode4JTextField.setEnabled(true);
      enigmaCode5JTextField.setEnabled(true);
      normalJTextArea.setEditable(false);
      enigmaCodeJButton.setEnabled(true);
      translateJButton.setEnabled(false);
      decypherJButton.setEnabled(false);
      resetJButton.setEnabled(false);

      enigmaCode1JTextField.setText("");
      enigmaCode2JTextField.setText("");
      enigmaCode3JTextField.setText("");
      enigmaCode4JTextField.setText("");
      enigmaCode5JTextField.setText("");
      normalJTextArea.setText("");
      translatedJTextArea.setText("");

      date = new Date();
      logJLabel.setText(enigmaName + " logged out at: " + dateFormat.format(date));
    }
  }

  private class Field1Listener extends DocumentFilter
  {
    @Override  
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException  
    {
      if(fb.getDocument().getLength()+string.length()>3)
      {
        return;
      }
      fb.insertString(offset, string, attr);
    }

    @Override  
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException 
    {  
      fb.remove(offset, length);
    }  

    @Override  
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)throws BadLocationException 
    {  
      if(fb.getDocument().getLength()+text.length()>3)
      {
        return;
      }
      fb.insertString(offset, text, attrs);
    }
  }

  public static void main(String[] args)
  {
    new Enigma().setVisible(true);
  }

}
