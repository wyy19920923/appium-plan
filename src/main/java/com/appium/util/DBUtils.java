package com.appium.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyuying on 2017/9/18.
 */


public class DBUtils {
    public static List driverJDBC(String URL, String user, String password) {
        List list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select username,password from t_user where id<=?";
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection(URL, user, password);
            ps = con.prepareStatement(sql);
            ps.setInt(1, 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] tmp = new String[2];
                tmp[0] = rs.getString("username");
                tmp[1] = rs.getString("password"); list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close(); ps.close(); con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
