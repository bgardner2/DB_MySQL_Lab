/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db_mysql_lab;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ben
 */
public interface IMenuItemsDAO {
    public List<MenuItem> getAllMenuItems() throws SQLException;
}
