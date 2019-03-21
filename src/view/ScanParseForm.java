package view;

import control.framework.MainController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScanParseForm extends JFrame {

    private JTextField inputField;
    private JTextArea outputField;

    private JRadioButton integerSprache;
    private JRadioButton einfacheMatheSprache;
    private JRadioButton lalSpracheRadioButton;

    private JPanel panel;
    private JButton checkButton;


    public ScanParseForm(MainController mainController) {

        add(panel);
        setTitle("Scanner und Parser");
        setSize(300,300);

        outputField.setEditable(false);
        checkButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(lalSpracheRadioButton.isSelected()) mainController.lalScanAndParse(inputField.getText());
                else if(einfacheMatheSprache.isSelected()) mainController.mathScanAndParse(inputField.getText());
                else if(integerSprache.isSelected()) mainController.integerScanAndParse(inputField.getText());
            }
        });
    }

    public JTextArea getOutputField() {

        return outputField;
    }
}
