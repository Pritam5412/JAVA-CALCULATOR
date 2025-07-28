import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Arial", Font.BOLD, 24);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setResizable(false);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFont(myFont);
            button.setFocusable(false);
        }

        negButton.addActionListener(this);
        negButton.setFont(myFont);
        negButton.setFocusable(false);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add buttons to panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        negButton.setBounds(50, 420, 100, 50);
        delButton.setBounds(150, 420, 100, 50);
        clrButton.setBounds(250, 420, 100, 50);

        frame.add(panel);
        frame.add(textField);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + i);
                return;
            }
        }

        if (e.getSource() == decButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
            }
            return;
        }

        if (e.getSource() == addButton || e.getSource() == subButton ||
            e.getSource() == mulButton || e.getSource() == divButton) {
            try {
                num1 = Double.parseDouble(textField.getText());
                operator = ((JButton) e.getSource()).getText().charAt(0);
                textField.setText("");
            } catch (Exception ex) {
                textField.setText("Error");
            }
            return;
        }

        if (e.getSource() == equButton) {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            textField.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            } catch (Exception ex) {
                textField.setText("Error");
            }
            return;
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
            return;
        }

        if (e.getSource() == delButton) {
            String text = textField.getText();
            if (!text.isEmpty()) {
                textField.setText(text.substring(0, text.length() - 1));
            }
            return;
        }

        if (e.getSource() == negButton) {
            try {
                double value = Double.parseDouble(textField.getText());
                value *= -1;
                textField.setText(String.valueOf(value));
            } catch (Exception ex) {
                textField.setText("Error");
            }
        }
    }
}