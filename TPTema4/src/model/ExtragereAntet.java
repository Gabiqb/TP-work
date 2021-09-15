package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExtragereAntet {
    private static final int DEFAULT_MAX_ROWS = 10;
    private static final int DEFAULT_MAX_TEXT_COL_WIDTH = 650;
    private static class Column {
        private String label;
        private int width = 0;
        public Column (String label, int type, String typeName) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
        public int getWidth() {
            return width;
        }
        public void setWidth(int width) {
            this.width = width;
        }

    }
    public static String[] getHeader(Connection conn, String tableName){
        String[] header= getHeader(conn, tableName, DEFAULT_MAX_ROWS, DEFAULT_MAX_TEXT_COL_WIDTH);
        return header;
    }

    public static String[] getHeader(Connection conn, String tableName, int maxRows, int maxStringColWidth) {
        String[] header=null;
        if (conn == null) {
            System.err.println("Error: No connection to database (Connection is null)!");
            return null;
        }
        if (tableName == null) {
            System.err.println("Error: No table name (tableName is null)!");
            return null;
        }
        if (tableName.length() == 0) {
            System.err.println("Error: Empty table name!");
            return null;
        }
        if (maxRows < 1) {
            System.err.println("Info: Invalid max. rows number. Using default!");
            maxRows = DEFAULT_MAX_ROWS;
        }

        Statement stmt = null;
        ResultSet rs =null;
        try {
            if (conn.isClosed()) {
                System.err.println(" Error: Connection is closed!");
                return null;
            }
           stmt= conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
           // PreparedStatement s= conn.prepareStatement("select * from " + tableName);
            rs= stmt.executeQuery("select * from " + tableName);

            header=resultSet(rs, maxStringColWidth);

        } catch (SQLException e) {
            System.err.println("SQL exception Message:");
            System.err.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ignore) {
                // ignore
            }
        }
        return  header;
    }

     public static void resultSet(ResultSet rs) {
        resultSet(rs, DEFAULT_MAX_TEXT_COL_WIDTH);
    }
    public static String[] resultSet(ResultSet rs, int maxStringColWidth) {

        try {

            if (rs == null) {
                System.err.println(" Error: Result set is null!");
                return null;
            }
            if (rs.isClosed()) {
                System.err.println("Error: Result Set is closed!");
                return null;
            }
            if (maxStringColWidth < 1) {
                System.err.println("Invalid max. varchar column width. Using default!");
                maxStringColWidth = DEFAULT_MAX_TEXT_COL_WIDTH;
            }

            ResultSetMetaData rsmd;
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<Column> columns = new ArrayList<>(columnCount);
            List<String> tableNames = new ArrayList<>(columnCount);

            for (int i = 1; i <= columnCount; i++) {
                Column c = new Column(rsmd.getColumnLabel(i),
                        rsmd.getColumnType(i), rsmd.getColumnTypeName(i));
                c.setWidth(c.getLabel().length());
                columns.add(c);

                if (!tableNames.contains(rsmd.getTableName(i))) {
                    tableNames.add(rsmd.getTableName(i));
                }
            }

            int rowCount = 0;

            String[] printformatstring=new String[rsmd.getColumnCount()];
            int nrc=0;

            for (Column c : columns) {
                int width = c.getWidth();
                String name = c.getLabel();
                int diff = width - name.length();
                printformatstring[nrc]=name;
                nrc++;

            }
            String[] copyprint=new String[nrc];
            for(int nrc1=0;nrc1<nrc;nrc1++)
            {
                copyprint[nrc1]=printformatstring[nrc1];
            }

            return copyprint;

        } catch (SQLException e)
        {
            System.err.println("SQL exception. Message:");
            System.err.println(e.getMessage());
        }
        return null;
    }



}