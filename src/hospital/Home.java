/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
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

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    JDesktopPane desktop;
   // ImageIcon img = new ImageIcon("images/user.png");
    //ImageIcon rpa = new ImageIcon("images/Get-Info-icon.png");
    JPanel centerrpanel, leftpanel, rightpanel;
    JLabel time, date, logdetail, user, hosi;
    public String me, ufname, ulname,p_title,h_adress,h_location;
    JButton clickbutton;
    JCommandButton Receptionbutton,
            PatientRButton, SearchPButton, DoctorAssignbtn,
            consultationbtn, observationbtn, Diagnosisbtn,
            Prescriptionbtn, PatientHistbtn, PatientFollbtn,
            Labpresbtn, Bloodtstbtn, othertstbtn, labhistbtn,
            labtstbtn, Patientdrugbtn, druglstbtn,
            billdashbbtn, patientbillbtn, invoicesbtn,
            paymentsbtn, receiptsbtn, patientAdmtnrbtn,
            patienttstrbtn,
            patientbillrbtn, druglistbtn,
            labtstsrbtn, userlogrbtn, adduserbtn, edituserbtn, deluserbtn, viewuserbtn, backupbtn, testpricebtn;

    private JPanel pnlMain;
    private JPanel pnlTools;
    JTextArea jarea;
    int id;
    private int tabCounter = 0;
    JTabbedPane tab, centerpanel;
  //  Icon CLOSE_TAB_ICON = new ImageIcon(("images/Stethoscope-icon.png"));
  //  Icon PAGE_ICON = new ImageIcon(("images/Save-icon.png"));
  //private int tabCount = 0;

    //  String title;
    // Icon icon;
    //   setting ribbon buttons with images
    public static ResizableIcon getResizableIconFromResource(String resource) {
        return ImageWrapperResizableIcon.getIcon(Home.class.getClassLoader().getResource(resource), new Dimension(48, 48));
    }

    public static ResizableIcon getSmallIconFromResource(String resource) {
        return ImageWrapperResizableIcon.getIcon(Home.class.getClassLoader().getResource(resource), new Dimension(18, 18));
    }

    public static final String PROPERTY_APPLICATION_ICON = "images/Hospital-icon.png";

//icon image
    public Home(String loggedin_user,String h_name,String hosi_address,String hosiloc) {
        super();
        p_title=h_name;
        h_adress=hosi_address;
        h_location=hosiloc;
        con = javaconnect.ConnectDb();
        

        //Screen size
        int inset = 10;
        Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, 10,
                dime.width - inset * 2,
                dime.height - inset * 2);
        setVisible(true);
        setTitle(p_title);

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
                if (p == JOptionPane.YES_OPTION) {
                    try {
                        String sqql = "Update user set Status='no' where USER_ID='" + me + "'";
                        String sqqll = "Update user_log set Status='Logged out' where  Status='Loggedin'and USER_ID='" + me + "'";

                        Statement stm = con.createStatement();
                        stm.executeUpdate(sqql);
                        Statement stmm = con.createStatement();
                        stmm.executeUpdate(sqqll);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    System.exit(0);
                    // Login bn=new Login();
                    //  bn.setVisible(true);
                } else {
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
        compo();
      
        datenandtime();
        me = loggedin_user;
        loggedin();
        logedin_user();
        id = Integer.parseInt(me);
        privaleges();
       
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
                new document_print(), "Settings", new ActionListener() {
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
        RibbonApplicationMenuEntryFooter otheruser = new RibbonApplicationMenuEntryFooter(getResizableIconFromResource("images/logout.png"), "Login as Another User", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_CANCEL_OPTION);
                if (p == 0) {
                    try {
                        String sqql = "Update user set Status='no' where USER_ID='" + me + "'";
                        String sqqll = "Update user_log set Status='Logged out' where  USER_ID='" + me + "'";
                        Statement stm = con.createStatement();
                        stm.executeUpdate(sqql);
                        Statement stmm = con.createStatement();
                        stmm.executeUpdate(sqqll);
                  //close();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                }
                Login l = new Login();
                l.setVisible(true);
                dispose();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        RibbonApplicationMenuEntryFooter footer = new RibbonApplicationMenuEntryFooter(getResizableIconFromResource("images/logout.png"), "Logout", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_CANCEL_OPTION);
                if (p == 0) {
                    try {
                        String sqql = "Update user set Status='no' where USER_ID='" + me + "'";
                        String sqqll = "Update user_log set Status='Logged out' where  USER_ID='" + me + "'";
                        Statement stm = con.createStatement();
                        stm.executeUpdate(sqql);
                        Statement stmm = con.createStatement();
                        stmm.executeUpdate(sqqll);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    System.exit(0);
                }
            }
        });
        //END OF APPLICATION MENU

//reception
        JRibbonBand Reception = new JRibbonBand("Reception ", null);
        Receptionbutton = new JCommandButton(" New Patient Admission", getResizableIconFromResource("images/addicon.png"));
        RichTooltip newpatientadd = new RichTooltip();
        newpatientadd.setTitle("New Patient Admission");
        newpatientadd.addDescriptionSection("Click on this button  to add a new patient....");
        newpatientadd.addFooterSection("Press F1 for help");

        Receptionbutton.setActionRichTooltip(newpatientadd);
        Receptionbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                newpatient();

            }
        });

        PatientRButton = new JCommandButton("Patient Records",getResizableIconFromResource("images/Patientrecords.png"));
        RichTooltip patientrecord = new RichTooltip();
        patientrecord.setTitle("Patient Records");
        patientrecord.addDescriptionSection("Click on this button to view the patient records....");
        patientrecord.addFooterSection("Press F1 for help");
        PatientRButton.setActionRichTooltip(patientrecord);
        PatientRButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                patientrecord();

                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        SearchPButton = new JCommandButton("Search Patient", getResizableIconFromResource("images/patient-search-icon.png"));
        RichTooltip searchpatient = new RichTooltip();
        searchpatient.setTitle("Search Patient");
        searchpatient.addDescriptionSection("Click on this button to search a patient using the Patient ID......");
        searchpatient.addFooterSection("Press F1 for help");
        SearchPButton.setActionRichTooltip(searchpatient);
        SearchPButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                patientsearch();
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //doctor assign
        DoctorAssignbtn = new JCommandButton("Assign Doctor", getResizableIconFromResource("images/doctor-icon.png"));
        RichTooltip assigndoctor = new RichTooltip();
        assigndoctor.setTitle("Assign Doctor");
        assigndoctor.addDescriptionSection("Click on this button  to assign a patient to a doctor....");
        assigndoctor.addFooterSection("Press F1 for help");
        DoctorAssignbtn.setActionRichTooltip(assigndoctor);

        DoctorAssignbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                assigndoctor();

                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Reception.addCommandButton(Receptionbutton, TOP);
        Reception.addCommandButton(PatientRButton, MEDIUM);
        Reception.addCommandButton(SearchPButton, MEDIUM);
        // Reception.addCommandButton(DoctorAssignbtn, MEDIUM);
        Reception.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(Reception.getControlPanel()),
                new IconRibbonBandResizePolicy(Reception.getControlPanel())));
        RibbonTask recep = new RibbonTask("Reception", Reception);
        /**
         * ........................................*
         */
///Doctors module
        JRibbonBand Doctor = new JRibbonBand("Doctors Module", null);
        observationbtn = new JCommandButton("Observation", getResizableIconFromResource("images/stethoscope.png"));
        observationbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                prediagnosis();
            }
        });
        consultationbtn = new JCommandButton("Consultation", getResizableIconFromResource("images/people-icon.png"));
        consultationbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Consultation(); //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Diagnosisbtn = new JCommandButton("Diagnosis", getResizableIconFromResource("images/Stethoscope-icon.png"));
        Diagnosisbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Patientdiag();
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Prescriptionbtn = new JCommandButton("Prescription", getResizableIconFromResource("images/prescription.png"));
        Prescriptionbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                prescriptions();
            }
        });
        PatientHistbtn = new JCommandButton("Patient History", getResizableIconFromResource("images/man-icon.png"));
        PatientHistbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Patienthisto();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        PatientFollbtn = new JCommandButton("Injection Follow_up", getResizableIconFromResource("images/syringe-icon.png"));
        PatientFollbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                pfollowup();  //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Doctor.addCommandButton(observationbtn, TOP);
        Doctor.addCommandButton(consultationbtn, MEDIUM);
        Doctor.addCommandButton(Diagnosisbtn, MEDIUM);
        Doctor.addCommandButton(Prescriptionbtn, MEDIUM);
        Doctor.addCommandButton(PatientHistbtn, MEDIUM);
        Doctor.addCommandButton(PatientFollbtn, MEDIUM);
        Doctor.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(Doctor.getControlPanel()),
                new IconRibbonBandResizePolicy(Doctor.getControlPanel())));
        RibbonTask doc = new RibbonTask("Doctors Module", Doctor);

        //lab&exam
        JRibbonBand Lab = new JRibbonBand("Lab & Exam", null);
        Labpresbtn = new JCommandButton("Lab Prescription", getResizableIconFromResource("images/tube-icon.png"));
        Labpresbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                LabPresc();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Bloodtstbtn = new JCommandButton("Blood Test", getResizableIconFromResource("images/Test-iconn.png"));
        Bloodtstbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Bloodtest();//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        othertstbtn = new JCommandButton("Other Test", getResizableIconFromResource("images/other-test-icon.png"));
        othertstbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Othertest(); //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        labhistbtn = new JCommandButton("Lab History", getResizableIconFromResource("images/History-icon.png"));
        labhistbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                labhisto();
            }
        });

        labtstbtn = new JCommandButton("Lab Tests", getResizableIconFromResource("images/Test-icon.png"));
        labtstbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                labtests();
            }
        });

        Lab.addCommandButton(Labpresbtn, TOP);

        Lab.addCommandButton(Bloodtstbtn, MEDIUM);

        Lab.addCommandButton(othertstbtn, MEDIUM);
        Lab.addCommandButton(labhistbtn, MEDIUM);
        Lab.addCommandButton(labtstbtn, MEDIUM);

        Lab.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(Lab.getControlPanel()),
                new IconRibbonBandResizePolicy(Lab.getControlPanel())));
        RibbonTask laboratory = new RibbonTask("Lab & Exam", Lab);

        ///pharmacy
        JRibbonBand Pharmacy = new JRibbonBand("Pharmacy", null);
        Patientdrugbtn = new JCommandButton("Patient Drugs", getResizableIconFromResource("images/pill-icon.png"));
        Patientdrugbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                PatientDrug();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        druglstbtn = new JCommandButton("Drug List", getResizableIconFromResource("images/medical-icon.png"));
        druglstbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                druglist();//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        Pharmacy.addCommandButton(Patientdrugbtn, TOP);
        Pharmacy.addCommandButton(druglstbtn, MEDIUM);

        Pharmacy.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(Pharmacy.getControlPanel()),
                new IconRibbonBandResizePolicy(Pharmacy.getControlPanel())));
        RibbonTask pharma = new RibbonTask("Pharmacy", Pharmacy);

        ///billing
        JRibbonBand Billing = new JRibbonBand("Billing", null);
        billdashbbtn = new JCommandButton("Dash Board", getResizableIconFromResource("images/dashboard-icon.png"));
        billdashbbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Dashboard();
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        patientbillbtn = new JCommandButton("Patient", getResizableIconFromResource("images/Patient-icon.png"));
        patientbillbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                patientbill();
            }               // PatientReceipt();   //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        });

//        invoicesbtn = new JCommandButton("Invoices", getResizableIconFromResource("images/Medical-invoice-icon.png"));
//        invoicesbtn.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                invoice(); // PatientReceipt();// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
        paymentsbtn = new JCommandButton("Payments", getResizableIconFromResource("images/pay-icon.png"));
        paymentsbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                payment();  // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        receiptsbtn = new JCommandButton("Receipts", getResizableIconFromResource("images/Bill-icon.png"));
        receiptsbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                receipt();   //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Billing.addCommandButton(billdashbbtn, TOP);
        Billing.addCommandButton(patientbillbtn, MEDIUM);
        // Billing.addCommandButton(invoicesbtn, MEDIUM);
        Billing.addCommandButton(paymentsbtn, MEDIUM);
        Billing.addCommandButton(receiptsbtn, MEDIUM);

        Billing.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(Billing.getControlPanel()),
                new IconRibbonBandResizePolicy(Billing.getControlPanel())));
        RibbonTask bill = new RibbonTask("Billing", Billing);

        //reports
        JRibbonBand Report = new JRibbonBand("Reports", null);
        patientAdmtnrbtn = new JCommandButton("Patient Admissions", getResizableIconFromResource("images/patient-reports-icon.png"));
        patientAdmtnrbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Preport();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        patienttstrbtn = new JCommandButton("Patient Tests", getResizableIconFromResource("images/Test-icon.png"));
        patienttstrbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                TestsReport();//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        patientbillrbtn = new JCommandButton("Patient Bill", getResizableIconFromResource("images/pay-icon.png"));
        patientbillrbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Breport();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        druglistbtn = new JCommandButton("Drug List", getResizableIconFromResource("images/medical-icon.png"));
        druglistbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                DrugListRepo();//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        labtstsrbtn = new JCommandButton("Lab Tests", getResizableIconFromResource("images/Lab-report-icon.png"));
        labtstsrbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Lreport();//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        userlogrbtn = new JCommandButton("Users Log", getResizableIconFromResource("images/log-icon.png"));
        userlogrbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                userlog();// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Report.addCommandButton(patientAdmtnrbtn, TOP);
        Report.addCommandButton(patienttstrbtn, MEDIUM);
       // Report.addCommandButton(patientbillrbtn, MEDIUM);

        Report.addCommandButton(labtstsrbtn, MEDIUM);
        Report.addCommandButton(druglistbtn, MEDIUM);
        Report.addCommandButton(userlogrbtn, MEDIUM);

        Report.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(Report.getControlPanel()),
                new IconRibbonBandResizePolicy(Report.getControlPanel())));
        RibbonTask repor = new RibbonTask("Reports", Report);

        //Admin
        JRibbonBand Admin = new JRibbonBand("Admin", null);
        adduserbtn = new JCommandButton("Add User", getResizableIconFromResource("images/adduser-icon.png"));
        adduserbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Adduser();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        edituserbtn = new JCommandButton("Edit User", getResizableIconFromResource("images/Edit-Users-icon.png"));
        edituserbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Edituser();//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        deluserbtn = new JCommandButton("Delete User", getResizableIconFromResource("images/deleteuser-icon.png"));
        deluserbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Deleteuser();//       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        viewuserbtn = new JCommandButton("View Users", getResizableIconFromResource("images/view-users.png"));
        viewuserbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewUser();    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        testpricebtn = new JCommandButton("Test Price", getResizableIconFromResource("images/Testcode-icon.png"));
        testpricebtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                AdminAdd();  //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        backupbtn = new JCommandButton("Back Up", getResizableIconFromResource("images/download-icon.png"));
        backupbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Backup();
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        Admin.addCommandButton(adduserbtn, TOP);
        //  Admin.addCommandButton(edituserbtn, MEDIUM);
        Admin.addCommandButton(deluserbtn, MEDIUM);
        Admin.addCommandButton(viewuserbtn, MEDIUM);
        Admin.addCommandButton(testpricebtn, MEDIUM);
        Admin.addCommandButton(backupbtn, MEDIUM);

        Admin.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(Admin.getControlPanel()),
                new IconRibbonBandResizePolicy(Admin.getControlPanel())));
        RibbonTask admin = new RibbonTask("Admin", Admin);
        //help

        RibbonApplicationMenu bb = new RibbonApplicationMenu();

        bb.addMenuSeparator();
        //comment
//        bb.addMenuEntry(amEntryPrint);
//           bb.addMenuEntry(save);
//            bb.addMenuEntry(Open);
//           bb.addMenuEntry(Saveas);
        bb.addFooterEntry(otheruser);
        bb.addFooterEntry(footer);

        getRibbon().setApplicationMenu(bb);

        //getRibbon().add(PROPERTY_APPLICATION_ICON);
        //Taskbar
        FlowLayout g = new FlowLayout(FlowLayout.CENTER, 5, 0);

        JPanel up = new JPanel(g);
        up.setOpaque(false);
        JCommandButton settings = new JCommandButton(null, getSmallIconFromResource("images/control-panel-icon.png"));
        RichTooltip sett = new RichTooltip();
        sett.setTitle("Settings");
        sett.addDescriptionSection("Click on this button to open settings dialogue");
        sett.addFooterSection("Press F1 for help");
        settings.setActionRichTooltip(sett);
        settings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Settings();// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JCommandButton ings = new JCommandButton(null, getSmallIconFromResource("images/control-panel-icon.png"));
        RichTooltip in = new RichTooltip();
        in.setTitle("Settings");
        in.addDescriptionSection("Click on this button to open settings dialogue");
        in.addFooterSection("Press F1 for help");
        ings.setActionRichTooltip(sett);
        ings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Settings();// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        up.add(settings);
        up.add(ings);
        getRibbon().addTaskbarComponent(settings);

        //end of taskbar
        getRibbon().configureHelp(getResizableIconFromResource("images/Help-icon.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Saidia();

//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        getRibbon().addTask(recep);
        getRibbon().addTask(doc);
        getRibbon().addTask(laboratory);
        getRibbon().addTask(pharma);
        getRibbon().addTask(bill);
        getRibbon().addTask(repor);
        getRibbon().addTask(admin);
        // getRibbon().addTaskbarComponent(comp);
        /////container
        //end jribbon
        BorderLayout container = new BorderLayout();
        desktop = new JDesktopPane();
        // desktop.setBackground(Color.DARK_GRAY);
        desktop.setLayout(container);
        getContentPane().add(desktop);

//Make the center component big, since that's the
//typical usage of BorderLayout.
        centerpanel = new JTabbedPane();
        desktop.add(centerpanel, BorderLayout.CENTER);
        centerpanel.addTab(null, null);
        centerpanel.addTab(null, null);

//right panel
        rightpanel = new JPanel();

        pnlTools = createToolsPanel();

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setOpaque(true);
        clickbutton = new JButton();
        //  clickbutton.setOpaque(false);
        clickbutton.setIcon(getResizableIconFromResource("images/Get-Info-icon.png"));
        clickbutton.setBorder(null);
        // clickbutton.setFocusable(false);
        clickbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                boolean visible = pnlTools.isVisible();
                pnlTools.setVisible(!visible);

            }

        });
        contentPane.add(clickbutton, BorderLayout.NORTH);
        contentPane.add(pnlTools, BorderLayout.EAST);

        pnlTools.setVisible(false);

        rightpanel.add(contentPane);
        desktop.add(rightpanel, BorderLayout.LINE_END);

        JPanel left = new JPanel();
        HospitalInfo hf = new HospitalInfo(p_title,h_adress,h_location);
        hf.setVisible(true);
        left.add(hf);
        desktop.add(left, BorderLayout.LINE_START);

        // footer component
        BorderLayout footage=new BorderLayout();
        JPanel foot = new JPanel(footage);
        foot.setBackground(Color.LIGHT_GRAY);
        logdetail = new JLabel();
        logdetail.setIcon(getSmallIconFromResource("images/user-icon.png"));
        time = new JLabel();
        time.setIcon(getSmallIconFromResource("images/clock-icon.png"));
        date = new JLabel();
        date.setIcon(getSmallIconFromResource("images/calendar-icon.png"));
    
        foot.add(logdetail,BorderLayout.LINE_START);
        //foot.add(time,BorderLayout.CENTER);
     
        foot.add(date,BorderLayout.LINE_END);

        desktop.add(foot, BorderLayout.PAGE_END);

    }

    private void addClosableTab(final JTabbedPane centerpanel, final JComponent c, final String title,
            final Icon icon) {

        // Add the tab to the pane without any label
        centerpanel.addTab(null, c);
        //int pos=0;
        int pos = centerpanel.indexOfComponent(c);

        // Create a FlowLayout that will space things 5px apart
        FlowLayout f = new FlowLayout(FlowLayout.CENTER, 5, 0);

        // Make a small JPanel with the layout and make it non-opaque
        JPanel pnlTab = new JPanel(f);
        pnlTab.setOpaque(false);

        // Add a JLabel with title and the left-side tab icon
        JLabel lblTitle = new JLabel("title");
        lblTitle.setIcon(icon);

        // Create a JButton for the close tab button
        JButton btnClose = new JButton(getSmallIconFromResource("images/exit-icon.png"));
        btnClose.setOpaque(false);

        // Configure icon and rollover icon for button
        // btnClose.setRolloverIcon(CLOSE_TAB_ICON);
        btnClose.setRolloverEnabled(true);
        // btnClose.setIcon(RGBGrayFilter.getDisabledIcon(btnClose, CLOSE_TAB_ICON));

        // Set border null so the button doesn't make the tab too big
        btnClose.setBorder(null);

        // Make sure the button can't get focus, otherwise it looks funny
        btnClose.setFocusable(false);

        // Put the panel together
        pnlTab.add(lblTitle);
        pnlTab.add(btnClose);

        // Add a thin border to keep the image below the top edge of the tab
        // when the tab is selected
        pnlTab.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

        // Now assign the component for the tab
        centerpanel.setTabComponentAt(pos, pnlTab);

        // Add the listener that removes the tab
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // The component parameter must be declared "final" so that it can be
                // referenced in the anonymous listener class like this.
                //   System.out.println(centerpanel.indexOfComponent(c));

                centerpanel.remove(c);

            }
        };
        btnClose.addActionListener(listener);

        // Optionally bring the new tab to the front
        centerpanel.setSelectedComponent(c);

        //-------------------------------------------------------------
        // Bonus: Adding a <Ctrl-W> keystroke binding to close the tab
        //-------------------------------------------------------------
        AbstractAction closeTabAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerpanel.remove(c);
            }
        };

        // Create a keystroke
        KeyStroke controlW = KeyStroke.getKeyStroke("control W");

        // Get the appropriate input map using the JComponent constants.
        // This one works well when the component is a container. 
        InputMap inputMap = c.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Add the key binding for the keystroke to the action name
        inputMap.put(controlW, "closeTab");

        // Now add a single binding for the action name to the anonymous action
        c.getActionMap().put("closeTab", closeTabAction);

//////  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//Methods
//
//    public synchronized void  addTaskbarComponent(Component comp) {
// 		if (comp instanceof AbstractCommandButton) {
//			AbstractCommandButton button = (AbstractCommandButton) comp;
//			button.setDisplayState(CommandButtonDisplayState.SMALL);
// 			button.setGapScaleFactor(0.5); 			button.setFocusable(false);
// 		}
//		taskbarComponents.add(comp);
//		fireStateChanged();
// 	}
    private JPanel createToolsPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBounds(10, 20, 700, 300);
        panel.setBackground(Color.LIGHT_GRAY);
        Border b1 = BorderFactory.createTitledBorder(p_title);
        Border b2 = BorderFactory.createLineBorder(Color.BLUE, 2);
        panel.setBorder(BorderFactory.createCompoundBorder(b2, b1));
        // hosii = new ImageIcon("/images/Hospital-icon .png");
        hosi = new JLabel();
        hosi.setIcon(getResizableIconFromResource("images/Hospital-icon .png"));
        user = new JLabel();

        jarea = new JTextArea();

        panel.add(hosi);
        panel.add(user);
        //  panel.add(jarea);

        return panel;
    }
//
    public void privaleges() {

        if (id == 12) {
                    //RECEPTION

//doctor
//                    //rece
//                    hoomie.Receptionbutton.setEnabled(false);
//                    hoomie.PatientRButton.setEnabled(false);
//                    hoomie.SearchPButton.setEnabled(false);
//                    hoomie.DoctorAssignbtn.setEnabled(false);
            //doc
            observationbtn.setEnabled(false);
            consultationbtn.setEnabled(false);
            Diagnosisbtn.setEnabled(false);
            Prescriptionbtn.setEnabled(false);
            PatientHistbtn.setEnabled(false);
            PatientFollbtn.setEnabled(false);
            //lab
            Labpresbtn.setEnabled(false);
            Bloodtstbtn.setEnabled(false);
            othertstbtn.setEnabled(false);
            labhistbtn.setEnabled(false);
            labtstbtn.setEnabled(true);
            //phar
            Patientdrugbtn.setEnabled(false);
            druglstbtn.setEnabled(true);
            //bill
            billdashbbtn.setEnabled(false);
            patientbillbtn.setEnabled(false);
          //  invoicesbtn.setEnabled(false);
            paymentsbtn.setEnabled(false);
            receiptsbtn.setEnabled(false);
            //repo
            patientAdmtnrbtn.setEnabled(false);
            patienttstrbtn.setEnabled(false);
            patientbillrbtn.setEnabled(false);
            druglistbtn.setEnabled(false);
            labtstsrbtn.setEnabled(false);
            userlogrbtn.setEnabled(false);
            //admin
            adduserbtn.setEnabled(false);
            viewuserbtn.setEnabled(true);
            edituserbtn.setEnabled(false);
            deluserbtn.setEnabled(false);
            testpricebtn.setEnabled(false);
            backupbtn.setEnabled(false);
        } else if (id == 14) {
                    //DOCTOR

            //rec
            Receptionbutton.setEnabled(false);
            PatientRButton.setEnabled(true);
            SearchPButton.setEnabled(true);
            DoctorAssignbtn.setEnabled(false);
            //lab
            Labpresbtn.setEnabled(false);
            Bloodtstbtn.setEnabled(false);
            othertstbtn.setEnabled(false);
            labhistbtn.setEnabled(true);
            labtstbtn.setEnabled(true);
            //phar
            Patientdrugbtn.setEnabled(false);
            druglstbtn.setEnabled(true);
            //bill
            billdashbbtn.setEnabled(false);
            patientbillbtn.setEnabled(false);
//                   hoomie.invoicesbtn.setEnabled(false);
            paymentsbtn.setEnabled(false);
            receiptsbtn.setEnabled(false);
            //repor
            patientAdmtnrbtn.setEnabled(false);
            patienttstrbtn.setEnabled(false);
            patientbillrbtn.setEnabled(false);
            druglistbtn.setEnabled(false);
            labtstsrbtn.setEnabled(false);
            userlogrbtn.setEnabled(false);
            //admin
            adduserbtn.setEnabled(false);
            viewuserbtn.setEnabled(true);
            edituserbtn.setEnabled(false);
            deluserbtn.setEnabled(false);
            testpricebtn.setEnabled(false);
            backupbtn.setEnabled(false);
        } else if (id == 16) {
                    //LAB TECH  

            //doc
            observationbtn.setEnabled(false);
            consultationbtn.setEnabled(false);
            Diagnosisbtn.setEnabled(false);
            Prescriptionbtn.setEnabled(false);
            PatientHistbtn.setEnabled(false);
            PatientFollbtn.setEnabled(false);
            //phar
            Patientdrugbtn.setEnabled(false);
            druglstbtn.setEnabled(true);
            //bill
            billdashbbtn.setEnabled(false);
            patientbillbtn.setEnabled(false);
         //   invoicesbtn.setEnabled(false);
            paymentsbtn.setEnabled(false);
            receiptsbtn.setEnabled(false);
            //repo
            patientAdmtnrbtn.setEnabled(false);
            patienttstrbtn.setEnabled(false);
            patientbillrbtn.setEnabled(false);
            druglistbtn.setEnabled(false);
            labtstsrbtn.setEnabled(false);
            userlogrbtn.setEnabled(false);
            //admin
            adduserbtn.setEnabled(false);
            viewuserbtn.setEnabled(true);
            edituserbtn.setEnabled(false);
            deluserbtn.setEnabled(false);
            testpricebtn.setEnabled(false);
            backupbtn.setEnabled(false);
        } else if (id == 18) {
                    //PHARMACIST

            //doc
            observationbtn.setEnabled(false);
            consultationbtn.setEnabled(false);
            Diagnosisbtn.setEnabled(false);
            Prescriptionbtn.setEnabled(false);
            PatientHistbtn.setEnabled(false);
            PatientFollbtn.setEnabled(false);
            //lab
            Labpresbtn.setEnabled(false);
            Bloodtstbtn.setEnabled(false);
            othertstbtn.setEnabled(false);
            labhistbtn.setEnabled(false);
            labtstbtn.setEnabled(true);

            //repo
            patientAdmtnrbtn.setEnabled(false);

            patienttstrbtn.setEnabled(false);
            patientbillrbtn.setEnabled(false);
            druglistbtn.setEnabled(false);
            labtstsrbtn.setEnabled(false);
            userlogrbtn.setEnabled(false);
            //admin
            adduserbtn.setEnabled(false);
            viewuserbtn.setEnabled(true);
            edituserbtn.setEnabled(false);
            deluserbtn.setEnabled(false);
            testpricebtn.setEnabled(false);
            backupbtn.setEnabled(false);
        } else if (id == 20) {
                    //ADMIN
        }

    }

    public void logedin_user() {
        String sql = "select * from user";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {

                ufname = rs.getString("FIRST_NAME");
                ulname = rs.getString("LAST_NAME");

                jarea.setText("Firstname \t Lastname\n");
                jarea.append(ufname + "\t" + ulname + "\n");
                //jarea.append(String.valueOf(rs));
                //JOptionPane.showMessageDialog(null, rs[1]);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            // Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loggedin() {
        String sql = "select * from user where USER_ID='" + me + "'";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String utitle = rs.getString("TITLE");
                ufname = rs.getString("FIRST_NAME");
                ulname = rs.getString("LAST_NAME");
                logdetail.setText(utitle.toUpperCase() + " " + ufname.toUpperCase() + " " + ulname.toUpperCase());
                user.setText("<html>You are Logged in as:<font color=blue><i>" + ufname + " " + ulname + "</i></font></html>");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            // Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void patientrecord() {
        centerpanel.removeTabAt(1);
        Patientrecords precords = new Patientrecords();
//                .PatientRecordInstance();
//        if (precords.isVisible()) {
//            try {
//                precords.setSelected(true);
//            } catch (Exception e) {
//            }
//            return;
//
//        } else {
        precords.setVisible(true);
        JScrollPane scrolPane = new JScrollPane(precords);
        centerpanel.addTab("PATIENT RECORDS", null, scrolPane, null);
        centerpanel.setSelectedComponent(scrolPane);
//desktop.add(precords);

    }

    public void newpatient() {
        centerpanel.removeTabAt(1);
        NewPatient df = new NewPatient();
//           .NewPatientInstance();
//        if (df.isVisible()) {
//            try {
//                df.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
        df.setVisible(true);
        JScrollPane scrolPane = new JScrollPane(df);
        centerpanel.addTab("NEW PATIENT", null, scrolPane, "Enter new patient");
//            JScrollPane scrollPane = new JScrollPane(df);
//           addClosableTab(centerpanel, scrollPane, "Tab ", null);
        centerpanel.setSelectedComponent(scrolPane);
    }

    public void Settings() {
        Settings stsi = new Settings(this, rootPaneCheckingEnabled);
        stsi.setVisible(true);
    }

    public void Saidia() {
        Help help = new Help(this, rootPaneCheckingEnabled);
        help.setVisible(true);
    }

    public void patientsearch() {
        centerpanel.removeTabAt(1);
        SearchPatient ps = new SearchPatient();
//                .SearchPatientInstance();
//        if (ps.isVisible()) {
//            try {
//                ps.setSelected(true);
//
//            } catch (java.beans.PropertyVetoException ex) {
//                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return;
//        } else {

        ps.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(ps);
        centerpanel.addTab("PATIENT SEARCH", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void assigndoctor() {
        centerpanel.removeTabAt(1);
        DoctorAssignment DocAssign = new DoctorAssignment();
//                .DoctorAssignmentInstance();
//        if (DocAssign.isVisible()) {
//            try {
//                DocAssign.setSelected(true);
//            } catch (PropertyVetoException e) {
//
//            }
//            return;
//        } else {
        DocAssign.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(DocAssign);
        centerpanel.addTab("ASSIGN DOCTOR", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void prediagnosis() {
        centerpanel.removeTabAt(1);
        PreDiagnostictest ptests = new PreDiagnostictest();
//        .PreDiagTestInstance();
//        if (ptests.isVisible()) {
//            try {
//                ptests.setSelected(true);
//
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
        ptests.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(ptests);
        centerpanel.addTab("OBSERVATION", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void Consultation() {
        centerpanel.removeTabAt(1);
        Consultation consul = new Consultation();
//                .ConsultationInstance();
//        if (consul.isVisible()) {
//            try {
//                consul.setSelected(true);
//            } catch (Exception e) {
//            }
//            return;
//        } else {
        consul.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(consul);
        centerpanel.addTab("CONSULTATION", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void TestsReport() {
        centerpanel.removeTabAt(1);
        PtestReport ptrepo = new PtestReport();
//        if (ptrepo.isVisible()) {
//            try {
//                ptrepo.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {

        ptrepo.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(ptrepo);
        centerpanel.addTab("TESTS REPORT", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void prescriptions() {
        centerpanel.removeTabAt(1);
        Prescription pres = new Prescription();
//        .PrescriptionInstance();
//        if (pres.isVisible()) {
//            try {
//                pres.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//           
        pres.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(pres);
        centerpanel.addTab("PRESCRIPTION", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Adduser() {
        centerpanel.removeTabAt(1);
        Adduser adduser = new Adduser();
        //.AdduserInstance();
//        if (adduser.isVisible()) {
//            try {
//                adduser.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//         
        adduser.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(adduser);
        centerpanel.addTab("ADD USER", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void Edituser() {
        centerpanel.removeTabAt(1);
        Edituser edituser = new Edituser();
//                .EditUserInstance();
//        if (edituser.isVisible()) {
//            try {
//                edituser.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//         
        edituser.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(edituser);
        centerpanel.addTab("EDIT USER", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Deleteuser() {
        centerpanel.removeTabAt(1);
        Deleteuser deluser = new Deleteuser();
//        .DeleteUserInstance();
//        if (deluser.isVisible()) {
//            try {
//                deluser.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//           
        deluser.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(deluser);
        centerpanel.addTab("DELETE USER", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Patientdiag() {
        centerpanel.removeTabAt(1);
        PatientDiagnosis pdiag = new PatientDiagnosis();
//        .PatientDiagInstance();
//        if (pdiag.isVisible()) {
//            try {
//                pdiag.setSelected(true);
//            } catch (Exception e) {
//            }
//            return;
//        } else {
//           
        pdiag.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(pdiag);
        centerpanel.addTab("DIAGNOSIS", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Patienthisto() {
        centerpanel.removeTabAt(1);
        PatientHistory phisto = new PatientHistory();
//        .PatientHisInstance();
//        if (phisto.isVisible()) {
//            try {
//                phisto.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//           
        phisto.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(phisto);
        centerpanel.addTab("PATIENT HISTORY", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void DrugListRepo() {
        centerpanel.removeTabAt(1);
        DrugListReport drugli = new DrugListReport();
//        .PatientHisInstance();
//        if (phisto.isVisible()) {
//            try {
//                phisto.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//           
        drugli.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(drugli);
        centerpanel.addTab("DRUGS LIST", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void ViewUser() {
        centerpanel.removeTabAt(1);
        Viewusers viewuser = new Viewusers();
//        .ViewUsersInstance();
//        if (viewuser.isVisible()) {
//            try {
//                viewuser.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//           
        viewuser.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(viewuser);
        centerpanel.addTab("VIEW USERS", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void druglist() {

        centerpanel.removeTabAt(1);
        DrugList draglist = new DrugList();
        if (draglist.isVisible()) {
            try {
                draglist.setSelected(true);
            } catch (Exception e) {

            }
            return;
        } else {

            draglist.setVisible(true);
            JScrollPane sscrolPane = new JScrollPane(draglist);
            centerpanel.addTab("DRUG LIST", null, sscrolPane, null);
            centerpanel.setSelectedComponent(sscrolPane);
        }
    }

    public void Bloodtest() {
        centerpanel.removeTabAt(1);
        BloodTest btest = new BloodTest();

        btest.setVisible(true);

        JScrollPane sscrolPane = new JScrollPane(btest);
        centerpanel.addTab("BLOOD TEST", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Othertest() {
        centerpanel.removeTabAt(1);
        OtherTests STtest = new OtherTests();

        STtest.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(STtest);
        centerpanel.addTab("OTHER TESTS", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void labhisto() {
        centerpanel.removeTabAt(1);
        labHistory lbh = new labHistory();

        lbh.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(lbh);
        centerpanel.addTab("LAB HISTORY", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void LabPresc() {
        centerpanel.removeTabAt(1);
        LabPres labp = new LabPres();
//        

            labp.setVisible(true);

            JScrollPane sscrolPane = new JScrollPane(labp);
            centerpanel.addTab("LAB PRESCRIPTION", null, sscrolPane, null);
            centerpanel.setSelectedComponent(sscrolPane);
        
    }

    public void pfollowup() {
        centerpanel.removeTabAt(1);
        PatientFollowup pfollow = new PatientFollowup();

        pfollow.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(pfollow);
        centerpanel.addTab("PATIENT FOLLOW UP", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Preport() {
        centerpanel.removeTabAt(1);
        PatientReport preport = new PatientReport();

        preport.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(preport);
        centerpanel.addTab("PATIENT REPORT", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Lreport() {
        centerpanel.removeTabAt(1);
        LabReport lreport = new LabReport();

        lreport.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(lreport);
        centerpanel.addTab("LAB TESTS", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Breport() {
        centerpanel.removeTabAt(1);
        BillingReport breport = new BillingReport();

        breport.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(breport);
        centerpanel.addTab("BILL REPORT", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void AdminAdd() {
        centerpanel.removeTabAt(1);
        AdminTestAdding adminadd = new AdminTestAdding();

        adminadd.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(adminadd);
        //  Icon icon = PAGE_ICON;
        centerpanel.addTab("ADD TESTS", null, scrollPane, null);
        centerpanel.setSelectedComponent(scrollPane);

    }

    public void Dashboard() {
        centerpanel.removeTabAt(1);
        BillingDashBoard dashboard = new BillingDashBoard();
//        if (dashboard.isVisible()) {
//            try {
//                dashboard.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//           
        dashboard.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(dashboard);
        centerpanel.addTab("DASH BOARD", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void Backup() {
        Backingup backuup = new Backingup();
//                .BackingUpInstance();
//        if (backuup.isVisible()) {
//            try {
//                backuup.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//           
        backuup.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(backuup);
        centerpanel.addTab("BACK UP", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void patientbill() {
        centerpanel.removeTabAt(1);
        PatientBill pbill = new PatientBill();
//        if (pbill.isVisible()) {
//            try {
//                pbill.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {

        pbill.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(pbill);
        centerpanel.addTab("PATIENT BILL", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void PatientDrug() {
        centerpanel.removeTabAt(1);
        PatientDrug pdrug = new PatientDrug();
//        if (pdrug.isVisible()) {
//            try {
//                pdrug.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {

        pdrug.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(pdrug);
        centerpanel.addTab("PATIENT DRUG", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void payment() {
        centerpanel.removeTabAt(1);
        Payments pay = new Payments();
//        if (pay.isVisible()) {
//            try {
//                pay.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//            
        pay.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(pay);
        centerpanel.addTab("PAYMENTS", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void labtests() {
        centerpanel.removeTabAt(1);
        LabTestsList labtst = new LabTestsList();
//        if (labtst.isVisible()) {
//            try {
//                labtst.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//           
        labtst.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(labtst);
        centerpanel.addTab("LAB TESTS", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);

    }

    public void receipt() {
        centerpanel.removeTabAt(1);
        Receipt receipt = new Receipt();
//        if (receipt.isVisible()) {
//            try {
//                receipt.setSelected(true);
//            } catch (Exception e) {
//
//            }
//            return;
//        } else {
//            
        receipt.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(receipt);
        centerpanel.addTab("RECEIPTS", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void userlog() {
        centerpanel.removeTabAt(1);
        UserLog ulog = new UserLog();
        ulog.setVisible(true);
        JScrollPane sscrolPane = new JScrollPane(ulog);
        centerpanel.addTab("USER LOG", null, sscrolPane, null);
        centerpanel.setSelectedComponent(sscrolPane);
    }

    public void close() {
        WindowEvent windowClose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClose);
    }

    public void datenandtime() {
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");

                    // date.setText(day + "/" + (month + 1) + "/" + year);
                    date.setText(date_format.format(cal.getTime()));
                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);

                    SimpleDateFormat time_format = new SimpleDateFormat("HH:mm:ss");
                   // time.setAlignmentY(CENTER_ALIGNMENT);
                    time.setText(time_format.format(cal.getTime()));

                    //time.setText(hour + ":" + (minute) + ":" + second);
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        clock.start();
    }
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton createTabButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTabbedPane tabbedPane;

}

//public class MyAction implements ActionListener{
//		public void actionPerformed(ActionEvent e){
//			String str = e.getActionCommand();
//			if(str.equals("Add Tab")){
//				String st = JOptionPane.showInputDialog(null, "Enter Tab Name.");
//				if(!st.equals("")){
//					JPanel panel2 = new JPanel();
//                                        
//					JLabel label = new JLabel("Your program is working successfully.");
//					panel2.add(label);
//					tab.add(st, panel2);
//				}
//			}
//			else if(str.equals("Remove Tab")){
//				tab.remove(tab.getTabCount()-1);
//			}
//		}
//	
//}

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
