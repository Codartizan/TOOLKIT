package tools;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;

import static tools.Utilities.*;

/**
 * Created by tshi on 11/10/2016.
 * Class Description:
 * Method Include:
 * Possible relevant Scenario:
 * PS:
 */
public class ToolKit {
    private JPanel jPanelToolkit;
    private JLabel jLCheckDigit, jLJulianDate, jLRelativeDate, jLLaunchCad, jLPort, jLRecyclePort, jLCurrUsr, usrLabel;
    private JTextField textCheckDigit, textJulianDate, textRelativeDate, textPort;
    private JButton btnCheckDigit, btnJulianDate, btnRelativeDate, btnLaunchCad, btnPort, btnRPort, btnCredentials, btnMngCad;
    private JComboBox comboVM, comboUser;
    private JCheckBox checkYes;
    private JTextField typeYourNameTextField;
    private JTextPane textPane;


    public ToolKit() {


        Utilities util = new Utilities();


        try {
            util.setValueFormDBtoGUI(comboVM, "SELECT * FROM VMLIST;", "VM_LIST");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboVM.addActionListener(e -> {
            String vmString = comboVM.getSelectedItem().toString();
            util.displayEMP(vmString, comboUser);

        });

        btnCheckDigit.addActionListener(e -> {

            if (e.getSource() == btnCheckDigit || e.getSource() == textCheckDigit) {

                String dArea;

                if (textCheckDigit.getText().length() != 0) {

                    dArea = textCheckDigit.getText();

                    String sArea = String.valueOf(dArea);

                    int digit = getCheckNumber(sArea);

                    textPane.setText(" ");
                    util.insertDocument(textPane, ("\nCheck Digit: " + String.valueOf(digit)), Color.green);
                    util.insertDocument(textPane, ("\nCard Number: " + sArea + String.valueOf(digit)), Color.cyan);

                } else {

                    textPane.setText(" ");
                    util.insertDocument(textPane, ("\nPlease Enter a string of Numbers!!"), Color.white);
                }
            }

        });

        btnJulianDate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btnJulianDate || e.getSource() == textJulianDate) {

                    if (textJulianDate.getText().length() == 8){

                        String jArea = textJulianDate.getText();

                        String julianDate = null;

                        try {

                            julianDate = convertToJulian(jArea);

                        } catch (ParseException e1) {

                            e1.printStackTrace();

                        }
                        textPane.setText(" ");
                        //textAreaResult.setText("Julian Date of " + jArea + " is: " + "\n" + julianDate);
                        util.insertDocument(textPane, (("\nJulian Date of " + jArea + " is: " + "\n" + julianDate)), Color.white);

                    } else if (textJulianDate.getText().length() == 4){

                        String jArea = textJulianDate.getText();

                        String calDate = null;

                        calDate = juLianToDate(jArea);

                        textPane.setText(" ");
                        //textAreaResult.setText("The Calender Date of " + jArea + " is: " + "\n" + calDate);
                        util.insertDocument(textPane, (("\nThe Calender Date of " + jArea + " is: " + "\n" + calDate)), Color.white);

                    } else {

                        textPane.setText(" ");
                        //textAreaResult.setText("Please enter 4 digits Julian Date or 8 digits Calendar Date");
                        util.insertDocument(textPane, ("\nPlease enter 4 digits Julian Date or 8 digits Calendar Date"), Color.white);
                    }

                }

            }

        });

        btnRelativeDate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btnRelativeDate || e.getSource() == textRelativeDate) {

                    if (textRelativeDate.getText().length() == 8) {

                        String rArea = textRelativeDate.getText();

                        String relativeDate = null;

                        try {

                            relativeDate = convertToRelative(rArea);

                        } catch (ParseException e1) {

                            e1.printStackTrace();

                        }

                        textPane.setText(" ");
                        //textAreaResult.setText("Relative Date of " + rArea + " is " + "\n" + relativeDate);
                        util.insertDocument(textPane, (("\nRelative Date of " + rArea + " is " + "\n" + relativeDate)), Color.white);

                    } else if (textRelativeDate.getText().length() == 5) {

                        String rArea = textRelativeDate.getText();

                        String cDate = null;

                        cDate = getBeforeAfterDate(rArea).toString();

                        String year = cDate.substring(2, 4);
                        String month = cDate.substring(5, 7);
                        String emonth = new Utilities().convertMonth(month);
                        String pDate = cDate.substring(8);

                        textPane.setText(" ");
                        //textAreaResult.setText("The Calender Date of " + rArea + " is: " +"\n" +  pDate + emonth + year + "\nor " + "\n" + cDate);
                        util.insertDocument(textPane, (("\nThe Calender Date of " + rArea + " is: " +"\n" +  pDate + emonth + year + "\nor " + "\n" + cDate)), Color.white);

                    } else {

                        textPane.setText(" ");
                        //textAreaResult.setText("Please enter 5 digits Relative Date or 8 digits Calendar Date");
                        util.insertDocument(textPane, ("\nPlease enter 5 digits Relative Date or 8 digits Calendar Date"), Color.white);

                    }

                }

            }

        });

        textCheckDigit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                super.keyTyped(e);

                int keyChar = e.getKeyChar();


                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

            } else {

                    e.consume();

                }

            }

        });

        textJulianDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                super.keyTyped(e);

                int keyChar = e.getKeyChar();

                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                } else {

                    e.consume();

                }

            }
        });

        textRelativeDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                super.keyTyped(e);

                int keyChar = e.getKeyChar();

                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                } else {

                    e.consume();

                }

            }
        });

        textPort.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                super.keyTyped(e);

                int keyChar = e.getKeyChar();

                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                } else {

                    e.consume();

                }

            }
        });

        btnPort.addActionListener(e -> {

            if (e.getSource() == btnPort) {

                String portNo = null;

                try {

                    portNo = util.connDB("SELECT * FROM REC_PORT;", "REC_PORT_NBR");

                } catch (SQLException e1) {

                    e1.printStackTrace();

                }

                if (portNo != null) {

                    textPane.setText(" ");
                    //textAreaResult.setText("Your Port Number is: " + "\n" + portNo + "\nPS: The port number which displayed on this screen will be treated as used!!" + "\nPlease do not press button if you don't need more port numbers!!");
                    util.insertDocument(textPane, (("\nYour Port Number is: " + "\n" + portNo + "\nPS: The port number which displayed on this screen will be treated as used!!" + "\nPlease do not press button if you don't need more port numbers!!")), Color.white);

                    try {

                        util.updateDB("DELETE FROM REC_PORT WHERE REC_PORT_NBR = '" + portNo + "';");

                    } catch (SQLException e1) {

                        e1.printStackTrace();

                    }

                } else {

                    try {

                        portNo = util.connDB("SELECT * FROM PORT;", "PORT_NBR");

                        textPane.setText(" ");
                        //textAreaResult.setText("Your Port Number is: " + "\n" + portNo + "\nPS: The port number which displayed on this screen will be treated as used!!" + "\nPlease do not press button if you don't need more port numbers!!");
                        util.insertDocument(textPane, (("\nYour Port Number is: " + "\n" + portNo + "\nPS: The port number which displayed on this screen will be treated as used!!" + "\nPlease do not press button if you don't need more port numbers!!")), Color.white);


                    } catch (SQLException e1) {

                        e1.printStackTrace();

                    }
                    int newPortNo = Integer.parseInt(portNo) + 1;

                    try {

                        util.updateDB("UPDATE PORT SET PORT_NBR = '" + String.valueOf(newPortNo) + "' WHERE PORT_NBR = '" + portNo + "';");

                    } catch (SQLException e1) {

                        e1.printStackTrace();
                    }

                }

            }

        });

        btnRPort.addActionListener(e -> {
            if (e.getSource() == btnRPort || e.getSource() == textPort) {

                String recyclePort = textPort.getText();

                if (recyclePort.length() == 5) {

                    try {

                        int iResult = util.updateDB("INSERT INTO REC_PORT (REC_PORT_NBR) VALUES ('" + recyclePort + "');");

                        if (iResult == 1) {

                            //textAreaResult.setText("Recycle succeed!");
                            textPane.setText(" ");
                            util.insertDocument(textPane, ("\nRecycle succeed!"), Color.white);

                        } else if (iResult == 0) {

                            //textAreaResult.setText("This port has been recycled already");
                            textPane.setText(" ");
                            util.insertDocument(textPane, ("\nThis port has been recycled already"), Color.white);

                        }

                    } catch (SQLException e1) {

                        e1.printStackTrace();

                    }

                } else {

                    //textAreaResult.setText("Data enter error, Port number should be 5 digits");
                    textPane.setText(" ");
                    util.insertDocument(textPane, ("\nData enter error, Port number should be 5 digits"), Color.white);

                }

            }

        });

        btnLaunchCad.addActionListener(e -> {

            if (e.getSource() == btnLaunchCad || e.getSource() == comboVM || e.getSource() == comboUser) {

                Cadencie cad = new Cadencie();

                try {

                    cad.LoginCad(comboVM.getSelectedItem().toString(), comboUser.getSelectedItem().toString());

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }

        });

        btnCredentials.addActionListener(e -> {

            if (e.getSource() == btnCredentials || e.getSource() == comboVM || e.getSource() == comboUser){

                String vm = comboVM.getSelectedItem().toString();
                String usr = comboUser.getSelectedItem().toString();
                String baseUrl = null;
                String bankNo = null;
                String emp = null;
                String pwd = null;
                String des = null;
                String dbUsr = null;
                String dbPwd = null;
                String dbServ = null;
                String unixUsr = null;
                String unixPwd = null;
                String phServ = null;
                String phClient = null;
                String currUser = null;
                String host = null;

                try {

                    baseUrl = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "VM_URL");
                    bankNo = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_NBR");
                    emp = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_EMP");
                    pwd = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_PWD");
                    des = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "VM_DES");
                    host = util.connDB(("SELECT * FROM VMLIST WHERE VM_LIST = '" + vm + "';"), "HOST_MACHINE");
                    dbUsr = util.connDB(("SELECT * FROM VMLIST WHERE VM_LIST = '" + vm + "';"), "DB_USR");
                    dbPwd = util.connDB(("SELECT * FROM VMLIST WHERE VM_LIST = '" + vm + "';"), "DB_PWD");
                    dbServ = util.connDB(("SELECT * FROM VMLIST WHERE VM_LIST = '" + vm + "';"), "DB_SERV");
                    unixUsr = util.connDB(("SELECT * FROM VMLIST WHERE VM_LIST = '" + vm + "';"), "UNIX_USR");
                    unixPwd = util.connDB(("SELECT * FROM VMLIST WHERE VM_LIST = '" + vm + "';"), "UNIX_PWD");
                    phServ = util.connDB(("SELECT * FROM VMLIST WHERE VM_LIST = '" + vm + "';"), "PH_SERV");
                    phClient = util.connDB(("SELECT * FROM VMLIST WHERE VM_LIST = '" + vm + "';"), "PH_CLIENT");
                    currUser = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "CURR_USR");

                } catch (SQLException e1) {

                    e1.printStackTrace();
                }

                if (currUser == null) {

                    typeYourNameTextField.setText("Available");
                    checkYes.setSelected(false);

                } else {

                    typeYourNameTextField.setText(currUser);
                    checkYes.setSelected(true);

                }

                //textAreaResult.setText("URL: " + baseUrl + "\nBank: " + bankNo + "\nEmployee: " + emp + "\nPassword: " + pwd + "\nVM Description: " + des + "\nHost Machine: " + host +
                //        "\nDatabase User: " + dbUsr + "\nDatabase Password: " + dbPwd + "\nDatabase Serve Name: " + dbServ + "\nUnix User: " + unixUsr + "\nUnix Password: " + unixPwd +
                //        "\nPaymentHub Server: " + phServ + "\nPaymentHub Client: " + phClient);
                textPane.setText(" ");
                util.insertDocument(textPane, ("\nURL: " + baseUrl + "\nBank: " + bankNo + "\nEmployee: " + emp + "\nPassword: " + pwd + "\nVM Description: " + des + "\nHost Machine: " + host), Color.cyan);
                util.insertDocument(textPane, ("\nDatabase User: " + dbUsr + "\nDatabase Password: " + dbPwd + "\nDatabase Serve Name: " + dbServ), Color.orange);
                util.insertDocument(textPane, ("\nUnix User: " + unixUsr + "\nUnix Password: " + unixPwd), Color.red);
                util.insertDocument(textPane, ("\nPaymentHub Server: " + phServ + "\nPaymentHub Client: " + phClient), Color.yellow);

            }

        });


        btnMngCad.addActionListener(e -> {
            if (e.getSource() == btnMngCad) {

                MngCad mc = new MngCad();

                mc.startMngCad();

            }
        });

        checkYes.addActionListener(e -> {

            String vm = comboVM.getSelectedItem().toString();
            String usr = comboUser.getSelectedItem().toString();
            String currUsr = typeYourNameTextField.getText();

            if (checkYes.isSelected()) {

                try {
                    util.updateDB("UPDATE " + vm + " SET CURR_USR = '" + currUsr + "' WHERE EMP_CODE = '" + usr + "';");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } else {

                try {
                    util.updateDB("UPDATE " + vm + " SET CURR_USR = NULL WHERE EMP_CODE = '" + usr + "';");
                    typeYourNameTextField.setText("Available");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        comboUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String currUser = null;
                String vm = comboVM.getSelectedItem().toString();
                String usr = comboUser.getSelectedItem().toString();

                try {
                    currUser = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "CURR_USR");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if (currUser == null) {

                    typeYourNameTextField.setText("Available");
                    checkYes.setSelected(false);

                } else {

                    typeYourNameTextField.setText(currUser);
                    checkYes.setSelected(true);

                }
            }
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        JFrame tFrame = new JFrame("ToolKit");

        tFrame.setContentPane(new ToolKit().jPanelToolkit);

        tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tFrame.pack();

        tFrame.setVisible(true);

    }

}
