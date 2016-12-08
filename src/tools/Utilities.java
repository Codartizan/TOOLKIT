package tools;


import org.openqa.selenium.WebDriver;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by tshi on 12/10/2016.
 * Class Description:
 * Method Include:
 * Possible relevant Scenario:
 * PS:
 */
public class Utilities {


    public static int getCheckNumber(String cardNumber){
        int totalNumber = 0;

        for (int i = cardNumber.length()-1; i >= 0; i-=2) {

            int tmpNumber = calculate(Integer.parseInt(String.valueOf(cardNumber.charAt(i))) *  2);

            if (i == 0) {

                totalNumber += tmpNumber;

            } else {

                totalNumber += tmpNumber + Integer.parseInt(String.valueOf(cardNumber.charAt(i-1)));

            }

        }

        if (totalNumber >= 0 && totalNumber < 9) {

            return (10 - totalNumber);

        } else {

            String str = String.valueOf(totalNumber);

            if (Integer.parseInt(String.valueOf(str.charAt(str.length()-1))) == 0) {

                return 0;

            } else {

                return (10 - Integer.parseInt(String.valueOf(str.charAt(str.length()-1))));

            }

        }

    }

    private static int calculate(int number) {

        String str = String.valueOf(number);

        int total = 0;

        for (int i = 0; i < str.length(); i++) {

            total += Integer.parseInt(String.valueOf(str.charAt(i)));

        }

        return total;

    }

    // Calculate Julian date. Keep last 4 digits.
    public static String convertToJulian(String endDate) throws ParseException {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

        java.util.Date sDate = sdf.parse(endDate + " 00:00:00");

        calendar.setTime(sDate);

        int year = calendar.get(Calendar.YEAR) - 1900;

        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        int iDate = year * 1000 + dayOfYear;

        String  julianDate = String.valueOf(iDate);

        return julianDate.substring(2);

    }

    //Convert Julian Date to ddmmyy
    public static String juLianToDate(String julianDate) {

        int date = Integer.parseInt("11" + julianDate);

        int year = (date / 1000) + 1900;

        int dayOfYear = date % 1000;

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);

        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        String calDate = calendar.getTime().toString();

        String cYear = calDate.substring(27);

        String cMon = calDate.substring(4, 7);

        String emonth = new Utilities().convertMonth(cMon);

        String cDate = calDate.substring(8, 10);

        return cDate + emonth + cYear;
    }


    //Convert yyyymmdd to Relative Date, keep 5 digits
    public String convertToRelative(String endDate) throws ParseException{

        String relativeDate;
        String baseDate = "19570101";
        boolean isDST = TimeZone.getDefault().inDaylightTime(new java.util.Date());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(baseDate));

        long time1 = cal.getTimeInMillis();

        cal.setTime(sdf.parse(endDate));

        long time2 = cal.getTimeInMillis();


        if ( isDST ) {

            long between_days = (time2 - time1 - 3600000)/(1000*3600*24);

            int relative = Integer.parseInt(String.valueOf(between_days));

            relativeDate = String.valueOf(relative + 1);

        } else {

            long between_days = (time2 - time1)/(1000*3600*24);

            int relative = Integer.parseInt(String.valueOf(between_days));

            relativeDate = String.valueOf(relative + 1);

        }

        //return relativeDate.substring(1);
        return relativeDate;

    }

    //Convert 5 digits Relative Date to ddmmyy
    public static  java.sql.Date getBeforeAfterDate(String relativeDate) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String baseDate = "1957-01-01";

        java.sql.Date olddate;

        try {

            df.setLenient(false);

            olddate = new java.sql.Date(df.parse(baseDate).getTime());

        } catch (ParseException e) {

            throw new RuntimeException("Convert failed");

        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(olddate);

        int Year = cal.get(Calendar.YEAR);

        int Month = cal.get(Calendar.MONTH);

        int Day = cal.get(Calendar.DAY_OF_MONTH);

        int NewDay = Day + Integer.parseInt(relativeDate);

        cal.set(Calendar.YEAR, Year);

        cal.set(Calendar.MONTH, Month);

        cal.set(Calendar.DAY_OF_MONTH, NewDay);

        return new java.sql.Date(cal.getTimeInMillis());

    }

    public String convertMonth(String month) {

        Month eMonth = Month.JAN;

        switch (month) {
            case "01":
                eMonth = Month.JAN;
                break;
            case "02":
                eMonth = Month.FEB;
                break;
            case "03":
                eMonth = Month.MAR;
                break;
            case "04":
                eMonth = Month.APR;
                break;
            case "05":
                eMonth = Month.MAY;
                break;
            case "06":
                eMonth = Month.JUN;
                break;
            case "07":
                eMonth = Month.JUL;
                break;
            case "08":
                eMonth = Month.AUG;
                break;
            case "09":
                eMonth = Month.SEP;
                break;
            case "10":
                eMonth = Month.OCT;
                break;
            case "11":
                eMonth = Month.NOV;
                break;
            case "12":
                eMonth = Month.DEC;
                break;
            case "Jan":
                eMonth = Month.JAN;
                break;
            case "Fed":
                eMonth = Month.FEB;
                break;
            case "Mar":
                eMonth = Month.MAR;
                break;
            case "Apr":
                eMonth = Month.APR;
                break;
            case "May":
                eMonth = Month.MAY;
                break;
            case "Jun":
                eMonth = Month.JUN;
                break;
            case "Jul":
                eMonth = Month.JUL;
                break;
            case "Aug":
                eMonth = Month.AUG;
                break;
            case "Sep":
                eMonth = Month.SEP;
                break;
            case "Oct":
                eMonth = Month.OCT;
                break;
            case "Nov":
                eMonth = Month.NOV;
                break;
            case "Dec":
                eMonth = Month.DEC;
                break;
        }

        return  eMonth.toString();
    }

    //Switch to new jump out Window.
    public static boolean switchToWindow(String windowTitle, WebDriver dr){

        boolean flag = false;

        try {

            //Get current window handle
            String currentHandle = dr.getWindowHandle();

            //Put all the windows handle into <Set>
            Set<String> handles = dr.getWindowHandles();

            //Travel all the element in <Set>
            for (String s : handles) {

                //If current window same as handle, jump out loop, continue next loop
                if (s.equals(currentHandle))

                {
                    continue;
                }

                else {

                    //If not same, then get new handle, or jump out loop
                    dr.switchTo().window(s);

                    if (dr.getTitle().contains(windowTitle)) {

                        flag = true;

                        //System.out.println("Switch to window: " + windowTitle + " successfully!");
                        break;

                    }

                }

            }

        } catch (Exception e) {

            System.out.printf("Window: " + windowTitle
                    + " could not found!", e.fillInStackTrace());

            flag = false;

        }

        return flag;

    }

    // Get results from MySql
    public String connDB(String sqlStmt, String col) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String result = null;

        String url = "jdbc:mysql://172.16.1.150:3306/TOOLKIT";
        String usr = "root";
        String pwd = "123456";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, usr, pwd);

            //if(conn != null)

            //{
                //System.out.println("MySql connected successful");

            //}else{

                //System.out.println("MySql connected failed");

            //}

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sqlStmt);

            while(rs.next()) {

                result = rs.getString(col);
                return result;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return result;

    }
    // Update Mysql
    public int updateDB(String sqlStmt) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        int state;


        String url = "jdbc:mysql://172.16.1.150:3306/TOOLKIT";
        String usr = "root";
        String pwd = "123456";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, usr, pwd);

            stmt = conn.createStatement();

            stmt.executeUpdate(sqlStmt);

            state = 1;


        } catch (Exception e) {

            e.printStackTrace();

            state = 0;

        }finally {

            try {

                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return state;

    }

    //Get result list from mysql
    public ArrayList<String> resultListFromDB(String sqlStmt, String col) throws SQLException {

        Connection conn;
        Statement stmt;
        ResultSet rs;
        String result;
        ArrayList<String> iList = new ArrayList<String>();

        String url = "jdbc:mysql://172.16.1.150:3306/TOOLKIT";
        String usr = "root";
        String pwd = "123456";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, usr, pwd);

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sqlStmt);

            while(rs.next()) {

                result = rs.getString(col);
                iList.add(result);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return iList;

    }

    public void setValueFormDBtoGUI(JComboBox component, String sqlStmt, String col) throws SQLException {

        Utilities util = new Utilities();
        ArrayList<String> rList = util.resultListFromDB(sqlStmt, col);
        int size = rList.size();
        String[] arrResult = rList.toArray(new String[size]);

        for (String anArrResult : arrResult) {

            component.addItem(anArrResult);
        }
    }

    public void displayEMP(String selected, JComboBox component){

        try {
            component.removeAllItems();
            Utilities util = new Utilities();
            ArrayList<String> rList = util.resultListFromDB("SELECT * FROM " + selected + ";", "EMP_CODE");
            int size = rList.size();
            String[] arrResult = rList.toArray(new String[size]);
            for (String anArrResult : arrResult) {

                component.addItem(anArrResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertDocument(JTextPane tp, String text , Color textColor)
    {
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setForeground(set, textColor);
        StyleConstants.setFontSize(set, 14);
        Document doc = tp.getStyledDocument();
        try
        {
            doc.insertString(doc.getLength(), text, set);
        }
        catch (BadLocationException ignored)
        {
        }
    }

}
