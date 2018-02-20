
package interfaces;

import java.sql.SQLException;

/**
 *
 * @author Passant
 */
public interface LoginInterface {
    
    /**
    * 
    * @throws SQLException
    **/
    public boolean login() throws SQLException;
}
