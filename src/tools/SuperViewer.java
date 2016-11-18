package tools;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tshi on 17/11/2016.
 * Class Description:
 * Method Include:
 * Possible relevant Scenario:
 * PS:
 */
public class SuperViewer extends JFrame{

    private JPanel svPanel;
    private JPanel listPanel;
    private JPanel dkPanel;
    private JPanel viewerPanel;
    private JList fileList;
    private JDesktopPane fileDK;
    private JPanel btnPanel;
    private JButton btn1;
    private JTextPane viewerPane;



    public static void main(String[] args) {

        JFrame tFrame = new JFrame("SuperViewer");

        tFrame.setContentPane(new SuperViewer().svPanel);

        tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tFrame.pack();

        tFrame.setVisible(true);
    }



}
