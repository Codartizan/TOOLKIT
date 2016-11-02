package tools;

import java.sql.SQLException;
import java.text.ParseException;

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
