package tools;

import javax.swing.*;

/**
 * Created by tshi on 25/10/2016.
 * Class Description:
 * Method Include:
 * Possible relevant Scenario:
 * PS:
 */
public class MngCad {
    private JPanel jPMngCad;
    private JLabel jLvmNbr;
    private JTextField textVMNbr;
    private JLabel jLEmpCode;
    private JTextField textEmpCode;
    private JLabel jLURL;
    private JTextField http172161TextField;
    private JLabel jLEgVmNbr;
    private JLabel jLEgEmpCode;
    private JLabel jLEmpNbr;
    private JTextField textEmpNbr;
    private JLabel jLEgEmpNbr;
    private JLabel jLPwd;
    private JTextField textPwd;
    private JLabel jLEgPwd;
    private JLabel jLBnkNbr;
    private JTextField textBnkNbr;
    private JLabel jLEgBnkNbr;
    private JLabel jLDes;
    private JLabel jLEgDes;
    private JTextField textDes;
    private JPanel jPBtns;
    private JButton btnClean;
    private JButton btnUpdate;
    private JButton btnNew;
    private JTextArea textWrn;

    public void startMngCad(){

        JFrame cFrame = new JFrame("MngCad");

        cFrame.setContentPane(new MngCad().jPMngCad);

        cFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cFrame.pack();

        cFrame.setVisible(true);
    }
}
