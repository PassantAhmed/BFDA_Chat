package utilities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */


public class SqlParser  {

    public static java.sql.Date fromLocalToSql(LocalDate date) {
        Date sqlDate = Date.valueOf(date);
        return sqlDate;
    }

    public static LocalDate fromSqlToLocal(Date date)
    {
        return date.toLocalDate();
    }

    public static java.sql.Timestamp fromLocalDateTimeToSql(LocalDateTime localDateTime) {
        return Timestamp.from(localDateTime.toInstant(ZoneOffset.ofHours(0)));
    }
}
