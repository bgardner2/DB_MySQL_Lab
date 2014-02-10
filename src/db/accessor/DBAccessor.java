/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.accessor;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ben
 */
public interface DBAccessor {
    
    public void openConnection(String URL, String username, String password) throws SQLException;
    
    public List<Map> findRecords(String tableName, List<String> columnNames, boolean sortAsc);
    
    public void closeConnection();
}
