import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Defence implements ActionListener{

    long MSE = 0;

    double[] Anzahl = new double[5];

    JTextField text;

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel question;
    JLabel MSELabel;

    JFrame frame;

    JPanel panel;

    DecimalFormat dotter;

    Scanner scanner;

    public Defence(){
        dotter = new DecimalFormat("###,###.#");
        dotter.setGroupingUsed(true);
        dotter.setGroupingSize(3);

        frame = new JFrame();
        frame.setMinimumSize(new Dimension(600,600));

        text = new JTextField();
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setText("Anzahl an Rockets:");
        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                text.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        JButton button = new JButton("Click me");
        button.addActionListener(this);
        JButton button2 = new JButton("Umwandeln");
        button2.addActionListener(this);

        label1 = new JLabel("Anzahl an Rockets: 0");
        label2 = new JLabel("Anzahl an leichten Lasern: 0");
        label3 = new JLabel("Anzahl an schweren Lasern: 0");
        label4 = new JLabel("Anzahl an Gaußkanonen: 0");
        label5 = new JLabel("Anzahl an Plasmawerfern: 0");
        question = new JLabel("Anzahl an Racks eingeben: ");
        question.setHorizontalAlignment(JLabel.CENTER);
        MSELabel = new JLabel("Die Def entspricht: " + calcMSE(Anzahl) + " Metallstandardeinheiten");
        MSELabel.setHorizontalAlignment(JLabel.RIGHT);


        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(question);
        panel.add(text);
        panel.add(button);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(MSELabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("DefenceRatios");
        frame.pack();
        frame.setVisible(true);

    }

    public long calcMSE(double[] Anzahl) {
        Anzahl[0] = Math.round(Anzahl[0]);
        Anzahl[1] = Math.round(Anzahl[1]);
        Anzahl[2] = Math.round(Anzahl[2]);
        Anzahl[3] = Math.round(Anzahl[3]);
        Anzahl[4] = Math.round(Anzahl[4]);
        MSE += 2000*Anzahl[0];
        MSE += 1500*Anzahl[1] + 2*500*Anzahl[1];
        MSE += 6000*Anzahl[2] + 2*2000*Anzahl[2];
        MSE += 20000*Anzahl[3] + 2*15000*Anzahl[3] + 2*2000*Anzahl[3];
        MSE += 50000*Anzahl[4] + 2*50000*Anzahl[4] + 2*30000*Anzahl[4];
        return MSE;
    }

    public static void main(String[] args) {
        new Defence();
    }

    public static double[] calculatedefence(double rockets){
        double ll = rockets;
        double sl = rockets/5000 * 1500;
        double gauß = rockets/5000 * 200;
        double plasma = rockets/5000 * 100;
        double[] result = {rockets,ll,sl,gauß,plasma};
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = text.getText();
        s = s.replaceAll("[\\D.]","");
        Anzahl = calculatedefence(Double.parseDouble(s));
        label1.setText("Anzahl an Rockets: " + dotter.format(Anzahl[0]));
        label2.setText("Anzahl an leichten Lasern: " + dotter.format(Anzahl[1]));
        label3.setText("Anzahl an schweren Lasern: " + dotter.format(Math.round(Anzahl[2])));
        label4.setText("Anzahl an Gaußkanonen: "+ dotter.format(Math.round(Anzahl[3])));
        label5.setText("Anzahl an Plasmawerfern: "+ dotter.format(Math.round(Anzahl[4])));
        MSE = 0;
        MSE = calcMSE(Anzahl);
        MSELabel.setText("Die Def entspricht: " + dotter.format(MSE) + " Metallstandardeinheiten");
    }

}