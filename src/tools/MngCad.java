package tools;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

/**
 * Created by tshi on 25/10/2016.
 * Class Description:
 * Method Include:
 * Possible relevant Scenario:
 * PS:
 */
public class MngCad {
    private JPanel jPMngCad, jPBtns;
    private JLabel jLvmNbr, jLEmpCode, jLURL, jLEgVmNbr, jLEgEmpCode, jLEmpNbr, jLEgEmpNbr, jLPwd, jLEgPwd, jLBnkNbr, jLEgBnkNbr, jLDes, jLEgDes;
    private JTextField http172161TextField, textEmpNbr, textPwd, textBnkNbr, textDes;
    private JButton btnNew, btnUpdate, btnInquire;
    private JTextArea textWrn;
    private JComboBox comboVMNbr, comboEmpCode;

    public MngCad() {

        Utilities util = new Utilities();

        try {
            util.setValueFormDBtoGUI(comboVMNbr, "SELECT * FROM VMLIST;", "VM_LIST");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboVMNbr.addActionListener(e -> {

            String vmString = comboVMNbr.getSelectedItem().toString();

            util.displayEMP(vmString, comboEmpCode);
        });

        textBnkNbr.addKeyListener(new KeyAdapter() {
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
        textEmpNbr.addKeyListener(new KeyAdapter() {
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

        btnInquire.addActionListener(e -> {
            if (e.getSource() == btnInquire) {
                String urlString = null;
                String bnkNo = null;
                String empNo = null;
                String pwd = null;
                String des = null;
                String vmString = comboVMNbr.getSelectedItem().toString();
                String usrString = comboEmpCode.getSelectedItem().toString();
                try {
                    urlString = util.connDB("SELECT * FROM " + vmString + " WHERE EMP_CODE = '" + usrString + "';", "VM_URL");
                    bnkNo = util.connDB("SELECT * FROM " + vmString + " WHERE EMP_CODE = '" + usrString + "';", "BANK_NBR");
                    empNo = util.connDB("SELECT * FROM " + vmString + " WHERE EMP_CODE = '" + usrString + "';", "BANK_EMP");
                    pwd = util.connDB("SELECT * FROM " + vmString + " WHERE EMP_CODE = '" + usrString + "';", "BANK_PWD");
                    des = util.connDB("SELECT * FROM " + vmString + " WHERE EMP_CODE = '" + usrString + "';", "VM_DES");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                http172161TextField.setText(urlString);
                textEmpNbr.setText(empNo);
                textPwd.setText(pwd);
                textBnkNbr.setText(bnkNo);
                textDes.setText(des);

            }
        });

        btnUpdate.addActionListener(e -> {

            String vmString = comboVMNbr.getSelectedItem().toString();
            String usrString = comboEmpCode.getSelectedItem().toString();
            String urlString = http172161TextField.getText();
            String bnkNo = textBnkNbr.getText();
            String empNo = textEmpNbr.getText();
            String pwd = textPwd.getText();
            String des = textDes.getText();

            try {

                util.updateDB("UPDATE " + vmString + " SET BANK_NBR = '" + bnkNo + "', BANK_EMP = '" + empNo + "', BANK_PWD = '" + pwd + "', VM_URL = '" + urlString + "', VM_DES = '" + des
                + "' WHERE EMP_CODE = '" + usrString + "';");

            } catch (SQLException e1) {

                e1.printStackTrace();
            }

            textWrn.setText("Record updated successful");

        });
        btnNew.addActionListener(e -> {
            String vmString = comboVMNbr.getSelectedItem().toString();
            String usrString = comboEmpCode.getSelectedItem().toString();
            String urlString = http172161TextField.getText();
            String bnkNo = textBnkNbr.getText();
            String empNo = textEmpNbr.getText();
            String pwd = textPwd.getText();
            String des = textDes.getText();

            try {

                util.updateDB("INSERT INTO " + vmString + "(BANK_NBR, BANK_EMP, BANK_PWD, EMP_CODE, VM_URL, VM_DES) VALUES ('" + bnkNo + "', '" + empNo + "', '" + pwd + "', '" + usrString
                + "', '" + urlString + "', '" + des + "');");

            } catch (SQLException e1) {

                e1.printStackTrace();
            }

            textWrn.setText("New Record for this MV has been created successful");
        });
    }

    public void startMngCad(){

        JFrame cFrame = new JFrame("MngCad");

        cFrame.setContentPane(new MngCad().jPMngCad);

        cFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cFrame.pack();

        cFrame.setVisible(true);
    }
}
