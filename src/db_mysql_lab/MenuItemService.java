package db_mysql_lab;

import db.accessor.DB_MySQL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuItemService {
    private IMenuItemsDAO mid = null;

    public MenuItemService() {
        
    }
    
    public MenuItemService(IMenuItemsDAO mid){
        this.mid = mid;
    }
    
    public List<MenuItem> getAllMenuItems() throws SQLException{
        return mid.getAllMenuItems();
    }
    
    public static void main(String[] args) {
        MenuItemService mis = new MenuItemService(new MenuItemDAO(new DB_MySQL()));
        List<MenuItem> items = null;
        try {
            items = mis.getAllMenuItems();
        } catch (SQLException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(MenuItem item : items){
            System.out.print(item.getItemID() + "\t");
            System.out.print(item.getItemName() + "\t");
            System.out.println(item.getItemPrice() + "\t");
        }
    }
    
}
