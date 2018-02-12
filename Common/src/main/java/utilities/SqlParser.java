package utilities;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */


public class SqlParser  {

    public static java.sql.Date fromUtilToSql(java.util.Date date)
    {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    public static java.util.Date fromSqlToUtil(java.sql.Date date)
    {
        java.util.Date utilDate = new java.util.Date(date.getTime());
        return utilDate;
    }
}
