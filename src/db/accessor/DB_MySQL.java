package db.accessor;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_MySQL implements DBAccessor {

    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private Connection conn = null;

    @Override
    public void openConnection(String URL, String username, String password) throws SQLException {
        try {
            conn = DriverManager.getConnection(URL, username, password);
        } catch (SQLException ex) {
            throw new SQLException("Url, username, or password is incorrect");
        }
    }

    /**
     *
     * @param tableName
     * @param columnNames - This list can be null. If it is, all columns will be
     * used in the SQL Query (e.g. "SELECT * FROM")
     * @param sortAsc
     * @return
     */
    @Override
    public List<Map> findRecords(String tableName, List<String> columnNames, boolean sortAsc) {
        String sortOrder = (sortAsc) ? " ASC " : " DESC ";
        String columns = (columnNames == null) ? " * " : getColumnsFromList(columnNames);
        Statement statement = null;
        ResultSet results = null;
        ResultSetMetaData metaData = null;
        Map<String, Object> record = null;
        List records = new LinkedList();

        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append(columns)
                .append(" FROM ")
                .append(tableName)
                .append(" ORDERBY ")
                .append(sortOrder);
        try {
            statement = conn.createStatement();

            results = statement.executeQuery(query.toString());
            metaData = results.getMetaData();

            while (results.next()) {
                record = new HashMap();
                for (int i = 1; i < metaData.getColumnCount(); i++) {
                    record.put(metaData.getColumnName(i), results.getObject(i));
                }
                records.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB_MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                statement.close();
            }catch(Exception e){
                
            }
        }
        return records;
    }

    private String getColumnsFromList(List<String> columnList) {
        StringBuilder columns = new StringBuilder();
        for (String s : columnList) {
            columns.append(s)
                    .append(',');
        }

        columns.substring(0, columns.length() - 1);

        return columns.toString();
    }

    @Override
    public void closeConnection() {

    }

    public static void main(String[] args) {
        DB_MySQL my = new DB_MySQL();
        try {
            my.openConnection("jdbc:mysql://localhost:3306/restaurant", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DB_MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Map> records = my.findRecords("menu", null, true);
        Set<String> keys = records.get(1).keySet();
        for(String s : keys){
            System.out.println(s);
            
        }
        
    }
}
