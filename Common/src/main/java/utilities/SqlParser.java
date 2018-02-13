package utilities;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */


public class SqlParser  {

    public static java.sql.Date fromLocalToSql(LocalDate date)
    {
        Date sqlDate = Date.valueOf(date);
        return sqlDate;
    }

    public static LocalDate fromSqlToLocal(Date date)
    {
        return date.toLocalDate();
    }
}
