package business_logic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExecProc {

    private static final int DEFAULT_MAX_ROWS = 10;
    private static final int DEFAULT_MAX_TEXT_COL_WIDTH = 650;
    private static List<String> values = new ArrayList<>();
    private static class Column {
        private String label;
        private int width = 0;
        private String value;
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
        public void addValue(String value) {
            values.add(value);
        }
        public String getValue(int i) {
            return values.get(i);
        }
    }


    public static String[][] getData(Connection conn, String sq){
        String[][] header= getData(conn, sq, DEFAULT_MAX_ROWS, DEFAULT_MAX_TEXT_COL_WIDTH);
        return header;
    }

    public static void getData(Connection conn, String sq, int maxRows) {
        getData(conn, sq, maxRows, DEFAULT_MAX_TEXT_COL_WIDTH);
    }
    public static String[][] getData(Connection conn, String sq, int maxRows, int maxStringColWidth) {
        String[][] data=null;
        if (conn == null) {
            System.err.println("Error: No connection to database (Connection is null)!");
            return null;
        }
        if (sq == null) {
            System.err.println("Error: No table name (tableName is null)!");
            return null;
        }
        if (sq.length() == 0) {
            System.err.println("Error: Empty SQ name!");
            return null;
        }
        if (maxRows < 1) {
            System.err.println("Info: Invalid max. rows number. Using default!");

        }
        Statement stmt = null;
        ResultSet rs = null;
        try {
            if (conn.isClosed()) {
                System.err.println(" Error: Connection is closed!");
                return null;
            }
            PreparedStatement s= conn.prepareStatement(sq);

            rs= s.executeQuery();

            data=resultSet(rs, maxStringColWidth,conn,sq);

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

            }
        }
        return data;
    }
    public static void resultSet(ResultSet rs,Connection conn,String sq) {
        resultSet(rs, DEFAULT_MAX_TEXT_COL_WIDTH,conn,sq);
    }
    public static String[][] resultSet(ResultSet rs, int maxStringColWidth,Connection conn,String sq) {

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

            }
            ResultSetMetaData rsmd;
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount(); //nr de coloane

            int rc=0; //nr de linii din tabela
            while(rs.next())
            {
                rc++;
            }
            PreparedStatement s=conn.prepareStatement(sq);
            rs= s.executeQuery();
            String[][] matrix=new String[rc][columnCount];
            int rowCount=0;
            while (rs.next()) {

                for (int i = 0; i < columnCount; i++) {
                    String value=rs.getString(i+1) == null ? "" : rs.getString(i+1);
                    matrix[rowCount][i]=value;
                }
                rowCount++;
            }
            int j=0;
            return matrix;

        } catch (SQLException e)
        {
            System.err.println("SQL exception. Message:");
            System.err.println(e.getMessage());
        }
        return null;
    }

}


