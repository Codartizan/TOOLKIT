package tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private JLabel jLCheckDigit, jLJulianDate, jLRelativeDate, jLLaunchCad, jLPort, jLRecyclePort;
    private JTextField textCheckDigit, textJulianDate, textRelativeDate, textPort;
    private JButton btnCheckDigit, btnJulianDate, btnRelativeDate, btnLaunchCad, btnPort, btnRPort, btnCredentials, btnMngCad;
    private JTextArea textAreaResult;
    private JComboBox comboVM, comboUser;
    private JLabel jLCurrUsr;
    private JCheckBox checkYes;
    private JTextField typeYourNameTextField;


    public ToolKit() {

        Utilities util = new Utilities();

        try {
            util.setValueFormDBtoGUI(comboVM, "SELECT * FROM VMLIST;", "VM_LIST");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboVM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vmString = comboVM.getSelectedItem().toString();
                util.displayEMP(vmString, comboUser);

            }
        });

        btnCheckDigit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btnCheckDigit || e.getSource() == textCheckDigit) {

                    String dArea;

                    if (textCheckDigit.getText().length() != 0) {

                        dArea = textCheckDigit.getText();

                        String sArea = String.valueOf(dArea);

                        int digit = getCheckNumber(sArea);

                        textAreaResult.setText("Check Digit: " + String.valueOf(digit) + "\nCard Number: " + sArea + String.valueOf(digit));

                    } else {

                        textAreaResult.setText("Please Enter a string of Numbers!!");
                    }
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

                        textAreaResult.setText("Julian Date of " + jArea + " is: " + "\n" + julianDate);

                    } else if (textJulianDate.getText().length() == 4){

                        String jArea = textJulianDate.getText();

                        String calDate = null;

                        calDate = juLianToDate(jArea);

                        textAreaResult.setText("The Calender Date of " + jArea + " is: " + "\n" + calDate);

                    } else {

                        textAreaResult.setText("Please enter 4 digits Julian Date or 8 digits Calendar Date");

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

                        textAreaResult.setText("Relative Date of " + rArea + " is " + "\n" + relativeDate);

                    } else if (textRelativeDate.getText().length() == 5) {

                        String rArea = textRelativeDate.getText();

                        String cDate = null;

                        cDate = getBeforeAfterDate(rArea).toString();

                        String year = cDate.substring(2, 4);
                        String month = cDate.substring(5, 7);
                        String emonth = new Utilities().convertMonth(month);
                        String pDate = cDate.substring(8);

                        textAreaResult.setText("The Calender Date of " + rArea + " is: " +"\n" +  pDate + emonth + year + "\nor " + "\n" + cDate);

                    } else {

                        textAreaResult.setText("Please enter 5 digits Relative Date or 8 digits Calendar Date");

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

                    textAreaResult.setText("Your Port Number is: " + "\n" + portNo + "\nPS: The port number which displayed on this screen will be treated as used!!" + "\nPlease do not press button if you don't need more port numbers!!");

                    try {

                        util.updateDB("DELETE FROM REC_PORT WHERE REC_PORT_NBR = '" + portNo + "';");

                    } catch (SQLException e1) {

                        e1.printStackTrace();

                    }

                } else {

                    try {

                        portNo = util.connDB("SELECT * FROM PORT;", "PORT_NBR");

                        textAreaResult.setText("Your Port Number is: " + "\n" + portNo + "\nPS: The port number which displayed on this screen will be treated as used!!" + "\nPlease do not press button if you don't need more port numbers!!");

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

                            textAreaResult.setText("Recycle succeed!");

                        } else if (iResult == 0) {

                            textAreaResult.setText("This port has been recycled already");

                        }

                    } catch (SQLException e1) {

                        e1.printStackTrace();

                    }

                } else {

                    textAreaResult.setText("Data enter error, Port number should be 5 digits");

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

                try {

                    baseUrl = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "VM_URL");
                    bankNo = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_NBR");
                    emp = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_EMP");
                    pwd = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_PWD");
                    des = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "VM_DES");
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

                textAreaResult.setText("URL: " + baseUrl + "\nBank: " + bankNo + "\nEmployee: " + emp + "\nPassword: " + pwd + "\nVM Description: " + des +
                    "\nDatabase User: " + dbUsr + "\nDatabase Password: " + dbPwd + "\nDatabase Serve Name: " + dbServ + "\nUnix User: " + unixUsr + "\nUnix Password: " + unixPwd +
                    "\nPaymentHub Server: " + phServ + "\nPaymentHub Client: " + phClient);


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
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        JFrame tFrame = new JFrame("ToolKit");

        tFrame.setContentPane(new ToolKit().jPanelToolkit);

        tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tFrame.pack();

        tFrame.setVisible(true);

    }

}
