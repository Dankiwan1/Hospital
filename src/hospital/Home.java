/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.RichTooltip;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntrySecondary;
import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.MEDIUM;
import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.TOP;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;
import test.svg.transcoded.document_print;
import test.svg.transcoded.document_print_preview;
import test.svg.transcoded.printer;

/**
 *
 * @author Dankiwan
 */
public class Home extends JRibbonFrame {
Connection con=null;
ResultSet rs=null;
PreparedStatement pst=null;
JDesktopPane desktop;
ImageIcon img = new ImageIcon("images/user.png");
JPanel centerpanel, leftpanel, rightpanel;
JLabel time, date,logdetail;
public ResizableIcon appIcon;
public String me;
JCommandButton button1, button2;
    //   setting ribbon buttons with images
    public static ResizableIcon getResizableIconFromResource(String resource) {
        return ImageWrapperResizableIcon.getIcon(Home.class.getClassLoader().getResource(resource), new Dimension(48, 48));
    }
      //icon image
   
    
    
    public Home(String loggedin_user) {
        super();
        con=javaconnect.ConnectDb();
        //Screen size
        int inset = 30;
        Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, 20,
                dime.width - inset * 2,
                dime.height - inset * 2);
        setVisible(true);
        setTitle("m.name()");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent we) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent we) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_CANCEL_OPTION);
                 if(p == JOptionPane.YES_OPTION) {
                    try {
                        String sqql = "Update user set Status='no' where USER_ID='"+me+"'";
                        Statement stm = con.createStatement();
                        stm.executeUpdate(sqql);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    System.exit(0);
                   // Login bn=new Login();
                  //  bn.setVisible(true);
                }
                 else{
                     //do nothing
                 }
           
            }

            @Override
            public void windowClosed(WindowEvent we) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent we) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
            
        setIconImage(img.getImage());
        compo();
        datenandtime();
        me=loggedin_user;
       loggedin();  
    }
  

    public void compo() {

        //APPLICATION MENU     
        RibbonApplicationMenuEntryPrimary amEntryPrint = new RibbonApplicationMenuEntryPrimary(
                new document_print(), "Print", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked printing document");
                    }
                }, JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        amEntryPrint.setActionKeyTip("P");
        amEntryPrint.setPopupKeyTip("W");

        RibbonApplicationMenuEntrySecondary amEntryPrintSelect = new RibbonApplicationMenuEntrySecondary(
                new printer(), "Print", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
        amEntryPrintSelect
                .setDescriptionText("Select a printer, number of copies and other printing options before printing");
        amEntryPrintSelect.setActionKeyTip("P");
        RibbonApplicationMenuEntrySecondary amEntryPrintDefault = new RibbonApplicationMenuEntrySecondary(
                new document_print(), "Quick Print", null,
                JCommandButton.CommandButtonKind.ACTION_ONLY);
        amEntryPrintDefault
                .setDescriptionText("Send the document directly to the default printer without making changes");
        amEntryPrintDefault.setActionKeyTip("Q");
        RibbonApplicationMenuEntrySecondary amEntryPrintPreview = new RibbonApplicationMenuEntrySecondary(
                new document_print_preview(), "Print Preview", null,
                JCommandButton.CommandButtonKind.ACTION_ONLY);
        amEntryPrintPreview
                .setDescriptionText("Preview and make changes to the pages before printing");
        amEntryPrintPreview.setActionKeyTip("V");

        amEntryPrint.addSecondaryMenuGroup("Preview and print the document",
                amEntryPrintSelect, amEntryPrintDefault, amEntryPrintPreview);

        RibbonApplicationMenuEntryPrimary save = new RibbonApplicationMenuEntryPrimary(
                new document_print(), "Save", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked printing document");
                    }
                }, JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        save.setActionKeyTip("s");
        save.setPopupKeyTip("v");

        RibbonApplicationMenuEntrySecondary PrintSelect = new RibbonApplicationMenuEntrySecondary(
                new printer(), "Print", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
        PrintSelect
                .setDescriptionText("Select a printer, number of copies and other printing options before printing");
        PrintSelect.setActionKeyTip("P");
        RibbonApplicationMenuEntrySecondary PrintDefault = new RibbonApplicationMenuEntrySecondary(
                new document_print(), "Quick Print", null,
                JCommandButton.CommandButtonKind.ACTION_ONLY);
        PrintDefault
                .setDescriptionText("Send the document directly to the default printer without making changes");
        PrintDefault.setActionKeyTip("Q");
        RibbonApplicationMenuEntrySecondary PrintPreview = new RibbonApplicationMenuEntrySecondary(
                new document_print_preview(), "Print Preview", null,
                JCommandButton.CommandButtonKind.ACTION_ONLY);
        PrintPreview
                .setDescriptionText("Preview and make changes to the pages before printing");
        PrintPreview.setActionKeyTip("V");

        save.addSecondaryMenuGroup("save and print the document",
                PrintSelect, PrintDefault, PrintPreview);

        RibbonApplicationMenuEntryPrimary Open = new RibbonApplicationMenuEntryPrimary(
                new document_print(), "open", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked printing document");
                    }
                }, JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Open.setActionKeyTip("o");
        Open.setPopupKeyTip("v");

        RibbonApplicationMenuEntrySecondary Select = new RibbonApplicationMenuEntrySecondary(
                new printer(), "Print", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
        Select
                .setDescriptionText("Select a printer, number of copies and other printing options before printing");
        Select.setActionKeyTip("P");
        RibbonApplicationMenuEntrySecondary Default = new RibbonApplicationMenuEntrySecondary(
                new document_print(), "Quick Print", null,
                JCommandButton.CommandButtonKind.ACTION_ONLY);
        Default
                .setDescriptionText("Send the document directly to the default printer without making changes");
        Default.setActionKeyTip("Q");
        RibbonApplicationMenuEntrySecondary Preview = new RibbonApplicationMenuEntrySecondary(
                new document_print_preview(), "Print Preview", null,
                JCommandButton.CommandButtonKind.ACTION_ONLY);
        Preview
                .setDescriptionText("Preview and make changes to the pages before printing");
        Preview.setActionKeyTip("V");

        Open.addSecondaryMenuGroup("save and print the document",
                Select, Default, Preview);
        RibbonApplicationMenuEntryPrimary Saveas = new RibbonApplicationMenuEntryPrimary(
                new document_print(), "Save As", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked printing document");
                    }
                }, JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Saveas.setActionKeyTip("s");
        Saveas.setPopupKeyTip("v");

        RibbonApplicationMenuEntrySecondary lect = new RibbonApplicationMenuEntrySecondary(
                new printer(), "Print", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
        lect
                .setDescriptionText("Select a printer, number of copies and other printing options before printing");
        lect.setActionKeyTip("P");
        RibbonApplicationMenuEntrySecondary efault = new RibbonApplicationMenuEntrySecondary(
                new document_print(), "Quick Print", null,
                JCommandButton.CommandButtonKind.ACTION_ONLY);
        efault
                .setDescriptionText("Send the document directly to the default printer without making changes");
        efault.setActionKeyTip("Q");
        RibbonApplicationMenuEntrySecondary review = new RibbonApplicationMenuEntrySecondary(
                new document_print_preview(), "Print Preview", null,
                JCommandButton.CommandButtonKind.ACTION_ONLY);
        review
                .setDescriptionText("Preview and make changes to the pages before printing");
        review.setActionKeyTip("V");

        Saveas.addSecondaryMenuGroup("save and print the document",
                lect, efault, review);
//application menu footer
        RibbonApplicationMenuEntryFooter footer = new RibbonApplicationMenuEntryFooter(getResizableIconFromResource("images/logout.png"), "Logout", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_CANCEL_OPTION);
                if (p == 0) {
                    try {
                        String sqql = "Update user set Status='no' where USER_ID='"+me+"'";
                        Statement stm = con.createStatement();
                        stm.executeUpdate(sqql);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    System.exit(0);
                }
            }
        });
    //END OF APPLICATION MENU

        JRibbonBand band1 = new JRibbonBand("Reception ", null);

        button1 = new JCommandButton(" New Patient Admission", getResizableIconFromResource("images/addicon.png"));
        RichTooltip newpatient = new RichTooltip();
        newpatient.setTitle("New Patient Admission");
        newpatient.addDescriptionSection("Click on this button  to add a new patient....");
        newpatient.addFooterSection("Press F1 for help");
        button1.setActionRichTooltip(newpatient);
        button1.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                newpatient();
            }
        });

        button2 = new JCommandButton("Patient Records", getResizableIconFromResource("images/patientrecords.png"));
        RichTooltip patientrecord = new RichTooltip();
        patientrecord.setTitle("Patient Records");
        patientrecord.addDescriptionSection("Click on this button to view the patient records....");
        patientrecord.addFooterSection("Press F1 for help");
        button2.setActionRichTooltip(patientrecord);
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                patientrecord();

                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button3 = new JCommandButton("Search Patient", getResizableIconFromResource("images/patient-search-icon.png"));
        RichTooltip searchpatient = new RichTooltip();
        searchpatient.setTitle("Search Patient");
        searchpatient.addDescriptionSection("Click on this button to search a patient using the Patient ID......");
        searchpatient.addFooterSection("Press F1 for help");
        button3.setActionRichTooltip(searchpatient);
        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                patientsearch();
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button4 = new JCommandButton("Assign Doctor", getResizableIconFromResource("images/doctor-icon.png"));
        RichTooltip assigndoctor = new RichTooltip();
        assigndoctor.setTitle("Assign Doctor");
        assigndoctor.addDescriptionSection("Click on this button  to assign a patient to a doctor....");
        assigndoctor.addFooterSection("Press F1 for help");
        button4.setActionRichTooltip(newpatient);
        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                assigndoctor();

                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        band1.addCommandButton(button1, TOP);
        band1.addCommandButton(button2, MEDIUM);
        band1.addCommandButton(button3, MEDIUM);
        band1.addCommandButton(button4, MEDIUM);
        band1.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band1.getControlPanel()),
                new IconRibbonBandResizePolicy(band1.getControlPanel())));
        RibbonTask task1 = new RibbonTask("Reception", band1);
        /**
         * ........................................*
         */

        JRibbonBand band3 = new JRibbonBand("Doctors Module", null);
        JCommandButton button9 = new JCommandButton("Observation", getResizableIconFromResource("images/stethoscope.png"));
        button9.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                prediagnosis();
            }
        });
        JCommandButton button10 = new JCommandButton("Diagnosis", getResizableIconFromResource("images/Stethoscope-icon.png"));
        button10.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Patientdiag();
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button11 = new JCommandButton("Prescription", getResizableIconFromResource("images/prescription.png"));
        button11.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                prescriptions();
            }
        });
        JCommandButton button12 = new JCommandButton("Patient History", getResizableIconFromResource("images/man-icon.png"));
        button12.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Patienthisto();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton dfollowup = new JCommandButton("Panient Follow_up", getResizableIconFromResource("images/patient-follow.png"));
        dfollowup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                pfollowup();  //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        band3.addCommandButton(button9, TOP);
        band3.addCommandButton(button10, MEDIUM);
        band3.addCommandButton(button11, MEDIUM);
        band3.addCommandButton(button12, MEDIUM);
        band3.addCommandButton(dfollowup, MEDIUM);
        band3.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band3.getControlPanel()),
                new IconRibbonBandResizePolicy(band3.getControlPanel())));
        RibbonTask task3 = new RibbonTask("Doctors Module", band3);

        JRibbonBand band4 = new JRibbonBand("Lab & Exam", null);
        JCommandButton button13 = new JCommandButton("Lab Prescription", null);
        button13.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                LabPresc();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button14 = new JCommandButton("Lab History", null);
        button14.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Labhistory();//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button15 = new JCommandButton("Blood Test", getResizableIconFromResource("images/Test-icon.png"));
        button15.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Bloodtest();//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button16 = new JCommandButton("Stool Test", null);
        button16.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Stooltest(); //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        band4.addCommandButton(button13, TOP);
        band4.addCommandButton(button14, MEDIUM);
        band4.addCommandButton(button15, MEDIUM);

        band4.addCommandButton(button16, MEDIUM);

        band4.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band4.getControlPanel()),
                new IconRibbonBandResizePolicy(band4.getControlPanel())));
        RibbonTask task4 = new RibbonTask("Lab & Exam", band4);

        JRibbonBand band5 = new JRibbonBand("Billing", null);
        JCommandButton button17 = new JCommandButton("P", null);
        button17.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button18 = new JCommandButton("L", null);
        button18.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button19 = new JCommandButton("g", null);

        button19.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button20 = new JCommandButton("Star", null);
        button20.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        band5.addCommandButton(button17, TOP);
        band5.addCommandButton(button18, MEDIUM);
        band5.addCommandButton(button19, MEDIUM);
        band5.addCommandButton(button20, MEDIUM);

        band5.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band5.getControlPanel()),
                new IconRibbonBandResizePolicy(band5.getControlPanel())));
        RibbonTask task5 = new RibbonTask("Billing", band5);

        JRibbonBand band6 = new JRibbonBand("Reports", null);
        JCommandButton button21 = new JCommandButton("Patient", getResizableIconFromResource("images/patient-reports-icon.png"));
        button21.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Preport();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button22 = new JCommandButton("Lab", getResizableIconFromResource("images/Lab-report-icon.png"));
        button22.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Lreport();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button23 = new JCommandButton("Billing", getResizableIconFromResource("images/billing-report-icon.png"));
        button23.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Breport();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button24 = new JCommandButton("Star", null);

        band6.addCommandButton(button21, TOP);
        band6.addCommandButton(button22, MEDIUM);
        band6.addCommandButton(button23, MEDIUM);
        band6.addCommandButton(button24, MEDIUM);

        band6.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band6.getControlPanel()),
                new IconRibbonBandResizePolicy(band6.getControlPanel())));
        RibbonTask task6 = new RibbonTask("Reports", band6);

        JRibbonBand band2 = new JRibbonBand("Admin", null);
        JCommandButton button5 = new JCommandButton("Add User", getResizableIconFromResource("images/adduser-icon.png"));
        button5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Adduser();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button6 = new JCommandButton("Edit User", getResizableIconFromResource("images/Edit-Users-icon.png"));
        button6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Edituser();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button7 = new JCommandButton("Delete User", getResizableIconFromResource("images/deleteuser-icon.png"));
        button7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Deleteuser();//       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton button8 = new JCommandButton("View Users", getResizableIconFromResource("images/view-users.png"));
        button8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewUser();    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton backup=new JCommandButton("Back Up",getResizableIconFromResource("images/download-icon.png"));
                backup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
             Backup();
             //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton add = new JCommandButton("ADD", null);
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                AdminAdd();  //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        band2.addCommandButton(button5, TOP);
        band2.addCommandButton(button8, MEDIUM);
        band2.addCommandButton(button6, MEDIUM);
        band2.addCommandButton(button7, MEDIUM);
        band2.addCommandButton(add, MEDIUM);
        band2.addCommandButton(backup, MEDIUM);

        band2.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band2.getControlPanel()),
                new IconRibbonBandResizePolicy(band2.getControlPanel())));
        RibbonTask task2 = new RibbonTask("Admin", band2);
 //help
//appIcon=getResizableIconFromResource("Help-icon.png");

        RibbonApplicationMenu bb = new RibbonApplicationMenu();
        bb.addMenuSeparator();
        bb.addMenuEntry(amEntryPrint);
        bb.addMenuEntry(save);
        bb.addMenuEntry(Open);
        bb.addMenuEntry(Saveas);
        bb.addFooterEntry(footer);

        getRibbon().setApplicationMenu(bb);
        //getRibbon().addTaskbarComponent();
        getRibbon().configureHelp(getResizableIconFromResource("images/Help-icon.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
               Saidia();

//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        getRibbon().addTask(task1);
        getRibbon().addTask(task3);
        getRibbon().addTask(task4);
        getRibbon().addTask(task5);
        getRibbon().addTask(task6);
        getRibbon().addTask(task2);

       //getRibbon().addTaskbarComponent(this);
        
       
        /////container
        BorderLayout container = new BorderLayout();
        desktop = new JDesktopPane();
        desktop.setLayout(container);
        getContentPane().add(desktop);
//Top panel component
        JPanel toppanel = new JPanel();
        desktop.add(toppanel, BorderLayout.PAGE_START);

//Make the center component big, since that's the
//typical usage of BorderLayout.
        centerpanel = new JPanel();
        desktop.add(centerpanel, BorderLayout.CENTER); 
       //left panel component
        leftpanel = new JPanel();
        leftpanel.setBorder(BorderFactory.createTitledBorder("Hospital Information"));
         leftpanel.add(new JLabel("Field 1"));
        leftpanel.add(new JTextField(10));
        leftpanel.setBackground(Color.LIGHT_GRAY);
        desktop.add(leftpanel, BorderLayout.LINE_START);
       
        // footer component
        
        JPanel foot = new JPanel();
        foot.setBackground(Color.LIGHT_GRAY);
        logdetail=new JLabel();
        time = new JLabel();
        date = new JLabel();
        JLabel space = new JLabel("                                                                                                                                                              ");
        foot.add(logdetail);
        foot.add(time);
        foot.add(space);
        foot.add(date);
        desktop.add(foot, BorderLayout.PAGE_END);
                
        //Right component
        rightpanel = new JPanel();
        desktop.add(rightpanel, BorderLayout.LINE_END);
    }

    //Methods
    
    //Login method
  public void loggedin() {
    String sql="select * from user where USER_ID='"+me+"'";
    try {
        pst=con.prepareStatement(sql);
          rs=pst.executeQuery( );
           if(rs.next()){
                String utitle=rs.getString("TITLE");
                String ufname=rs.getString("FIRST_NAME");
                 String ulname=rs.getString("LAST_NAME");
                logdetail.setText(utitle+" "+ufname+" "+ulname+"                                                                                                                                                                                      ");
           }        
    } catch (SQLException ex) {
     JOptionPane.showMessageDialog(null, ex); 
     // Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
    }    
   
    } 
  
  public void hosiinfo() {
   HospitalInfo hinfo = new HospitalInfo();
       leftpanel.add(hinfo);
        hinfo.setVisible(true);
    }
    public void patientrecord() {
        Patientrecords leff = new Patientrecords();
        centerpanel.add(leff);
        leff.setVisible(true);
    }

    public void patientdetail() {
        Patientdetail leffh = new Patientdetail();
        rightpanel.add(leffh);
        leffh.setVisible(true);
    }

    public void newpatient() {
        NewPatient df = new NewPatient().NewPatientInstance();
        if(df.isVisible()){
           try{
           df.setSelected(true);
           }
           catch(Exception e){
               
           }
           return;
        }
        else{
        centerpanel.add(df);
        df.setVisible(true);
    }
    }
public void Saidia(){
    Help help=new Help(this, rootPaneCheckingEnabled);
    help.setVisible(true);
}
    public void patientsearch() {
        SearchPatient df = new SearchPatient();
        if (df.isVisible()) {
            try {
                df.setSelected(true);

            } catch (java.beans.PropertyVetoException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        } else {
            centerpanel.add(df);
            df.setVisible(true);
        }
        try {
            df.setSelected(true);

        } catch (java.beans.PropertyVetoException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void assigndoctor() {
        DoctorAssignment fdf = new DoctorAssignment();
        centerpanel.add(fdf);
        fdf.setVisible(true);
    }

    public void prediagnosis() {
        PreDiagnostictest tests = new PreDiagnostictest();
        centerpanel.add(tests);
        tests.setVisible(true);
    }

    public void prescriptions() {
        Prescription pres = new Prescription();
        centerpanel.add(pres);
        pres.setVisible(true);
    }

    public void Adduser() {
        Adduser adduser = new Adduser();
        centerpanel.add(adduser);
        adduser.setVisible(true);
    }

    public void Edituser() {
        Edituser edituser = new Edituser();
        centerpanel.add(edituser);
        edituser.setVisible(true);
    }

    public void Deleteuser() {
        Deleteuser deluser = new Deleteuser();
        centerpanel.add(deluser);
        deluser.setVisible(true);
    }

    public void Patientdiag() {
        PatientDiagnosis pdiag = new PatientDiagnosis();
        centerpanel.add(pdiag);
        pdiag.setVisible(true);
    }

    public void Patienthisto() {
        PatientHistory phisto = new PatientHistory();
        centerpanel.add(phisto);
        phisto.setVisible(true);
    }

    public void ViewUser() {
        Viewusers viewuser = new Viewusers();
        centerpanel.add(viewuser);
        viewuser.setVisible(true);
    }

    public void Bloodtest() {
        BloodTest btest = new BloodTest();
        centerpanel.add(btest);
        btest.setVisible(true);
    }

    public void Stooltest() {
        StoolTest STtest = new StoolTest();
        centerpanel.add(STtest);
        STtest.setVisible(true);
    }

    public void LabPresc() {
        LabPres labp = new LabPres();
        centerpanel.add(labp);
        labp.setVisible(true);
    }

    public void Labhistory() {
        labHistory labh = new labHistory();
        centerpanel.add(labh);
        labh.setVisible(true);
    }

    public void pfollowup() {
        PatientFollowup pfollow = new PatientFollowup();
        centerpanel.add(pfollow);
        pfollow.setVisible(true);
    }

    public void Preport() {
        PatientReport preport = new PatientReport();
        centerpanel.add(preport);
        preport.setVisible(true);
    }

    public void Lreport() {
        LabReport lreport = new LabReport();
        centerpanel.add(lreport);
        lreport.setVisible(true);
    }

    public void Breport() {
        BillingReport breport = new BillingReport();
        centerpanel.add(breport);
        breport.setVisible(true);
    }

    public void AdminAdd() {
        Adminadd adminadd = new Adminadd();
        centerpanel.add(adminadd);
        adminadd.setVisible(true);
    }
     public void Backup() {
        Backingup backuup = new Backingup();
        centerpanel.add(backuup);
        backuup.setVisible(true);
    }

    public void datenandtime() {
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    date.setText(day + "/" + (month + 1) + "/" + year);

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);

                    time.setText(hour + ":" + (minute) + ":" + second);
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        clock.start();
    }
}


















































































/*
JTabbedPane jtp = new JTabbedPane();
          

        //This creates the template on the windowed application that we will be using
      // getContentPane().Newpatient(jtp);

       JPanel jp1 = new JPanel();//This will create the first tab
       jp1.setBackground(Color.lightGray);

       JPanel jp2 = new JPanel();//This will create the second tab
        
         //This creates a non-editable label, sets what the label will read
        //and adds the label to the first tab
       JLabel label1 = new JLabel();
       label1.setText("This is Tab 1");
       jp1.Newpatient(label1);

       //This adds the first and second tab to our tabbed pane object and names it
       jtp.addTab("Tab1", jp1);
       jtp.addTab("Tab2", jp2);
     

        //This creates a new button called "Press" and adds it to the second tab
       JButton test = new JButton("Press");
       JInternalFrame palette = new JInternalFrame("Palette", true, false, true, false);
    palette.setBounds(650, 550, 900, 1000);
    palette.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
    palette.setVisible(true);
       jp2.Newpatient(test);
       jp2.Newpatient(palette);
       */
