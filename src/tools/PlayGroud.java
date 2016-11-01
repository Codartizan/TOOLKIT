package tools;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tshi on 14/10/2016.
 * Class Description:
 * Method Include:
 * Possible relevant Scenario:
 * PS:
 */
public class PlayGroud {

    public static void main(String[] args) throws SQLException, ParseException {
        Utilities u = new Utilities();
        String k = u.connDB("SELECT * FROM VM133 WHERE EMP_CODE = 'QA-2'", "EMP_CODE");

        System.out.print(k);

    }

}
