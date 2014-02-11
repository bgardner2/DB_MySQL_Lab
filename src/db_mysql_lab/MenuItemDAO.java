package db_mysql_lab;

import db.accessor.DBAccessor;
import db.accessor.DB_MySQL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuItemDAO implements IMenuItemsDAO{
    private DBAccessor db;

    
    public MenuItemDAO() {
    }
    
    public MenuItemDAO(DBAccessor db) {
        this.db = db;
    }
    
    public void openLocalConnection() throws SQLException{
        db.openConnection("jdbc:mysql://localhost:3306/restaurant", "root", "");
    }
    
    
    
    @Override
    public List<MenuItem> getAllMenuItems() throws SQLException {
        this.openLocalConnection();
        List<Map> rawData = db.findRecords("menu", null);
        List<MenuItem> records = new ArrayList();
        
        MenuItem item = null;
        
        for(Map m : rawData){
            item = new MenuItem();
            item.setItemID((Integer)m.get("item_id"));
            item.setItemName(m.get("item_name").toString());
            item.setItemPrice((Double)m.get("item_price"));
            
            records.add(item);
        }
        
        return records;
    }
    
    public static void main(String[] args) {
        MenuItemDAO mid = new MenuItemDAO(new DB_MySQL());
        List<MenuItem> items = new ArrayList();
        try {
            items = mid.getAllMenuItems();
        } catch (SQLException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(MenuItem i : items){
            System.out.print(i.getItemID() + "\t");
            System.out.print(i.getItemName()+ "\t");
            System.out.println(i.getItemPrice() + "\t");
        }
    }
}
