/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kenn
 */
public class ViewClient extends JInternalFrame {

    private String[] languages = {"name", "id", "place"};
    private JComboBox combo = new JComboBox();
    private JLabel byWhatLabel;
    private JPanel bigPanel, topPanel, bottomPanel;
    private int count = 0;
    private static ViewClient myInstance;
    private final int xoffset = 450, yoffset = 65;

    public ViewClient() {
        super("View clients",
                true,//resizable
                true,//closable
                true,//maxizable
                true
        );
        setSize(500, 410);
        setLocation(xoffset, yoffset);

        for (int i = 0; i < languages.length; i++) {
            combo.addItem(languages[count++]);
        }
        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //big parent Panel
        bigPanel = new JPanel(null);
        bigPanel.setBounds(5, 5, 470, 390);
        //toppanel containing combobox
        topPanel = new JPanel(null);
        topPanel.setBounds(5, 5, 450, 100);
        //bottompanel below top panel
        bottomPanel = new JPanel(null);
        bottomPanel.setBounds(5, 110, 450, 100);

        //combobox label
        byWhatLabel = new JLabel("View client by: ");
        byWhatLabel.setBounds(20, 10, 50, 20);
        topPanel.add(byWhatLabel);
        bigPanel.add(topPanel);
        bigPanel.add(bottomPanel);
        add(bigPanel);

    }

    public static ViewClient getInstance() {
        if (myInstance == null) {
            myInstance = new ViewClient();
        }
        return myInstance;
    }
}
