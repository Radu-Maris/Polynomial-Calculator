package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfata extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton additionButton;
    private JButton subtractionButton;
    private JButton divisionButton;
    private JButton multiplicationButton;
    private JButton integrationButton;
    private JButton derivativeButton;
    private JTextField textField3;
    private JButton helpButton;

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setDimension(int w, int h) {
        add(panel1);
        setBounds(400, 400, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public Interfata() {
        setDimension(600, 400);
    }

    public void addVerificaButtonPlus(ActionListener listener){
        this.additionButton.addActionListener(listener);
    }
    public void addVerificaButtonMinus(ActionListener listener){
        this.subtractionButton.addActionListener(listener);
    }
    public void addVerificaButtonMultiply(ActionListener listener){
        this.multiplicationButton.addActionListener(listener);
    }

    public void addVerificaButtonDeriv(ActionListener listener){
        this.derivativeButton.addActionListener(listener);
    }

    public void addVerificaButtonInteg(ActionListener listener){
        this.integrationButton.addActionListener(listener);
    }
    public void addVerificaButtonHelp(ActionListener listener){
        this.helpButton.addActionListener(listener);
    }
    public  void addVerificaButtonDiv(ActionListener listener){
        this.divisionButton.addActionListener(listener);
    }
}
