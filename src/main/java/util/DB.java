package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 访问数据库最基础的一层
 *
 * @author administrator
 */
public class DB {
    // 保持一个静态常量只让其产生一个实例
    private static Connection conn = null;
    private static int i = 0;

    /**
     * 输入password返回此password的MD5编码
     */
    public static String encodeMD5(String password) {
        String pass = null;
//		String sql="select MD5('" + password + "')";
//		String sql = "select HASHBYTES('MD5','"+password+"')";
        String sql = "select substring(sys.fn_sqlvarbasetostr(HashBytes('MD5','" + password + "')),3,32)";
        System.out.println(sql);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                pass = rs.getString(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
        }
        pass = pass.replaceAll(" ", "");
        return pass;
    }

    /**
     * 获取连接
     *
     * @return 连接conn
     */
    public static Connection getConn() {

        if (null == conn) {
            try {
                Class.forName(PropertiesUtil.DRIVER);
                conn = DriverManager.getConnection(PropertiesUtil.URL,
                        PropertiesUtil.USER, PropertiesUtil.PASSWORD);
                i++;
                System.out.println("调用的第" + i + "次");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return conn;
    }

    /**
     * 获取preparedStatement
     *
     * @param conn 传入的链接
     * @param sql  传入的sql语句
     * @return
     */
    public static PreparedStatement getPreparedStatement(Connection conn,
                                                         String sql) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ps;
    }

    /**
     * 用预编译的方式query
     *
     * @param ps     传入的preparedStatement
     * @param params 传入的参数
     * @return resultSet
     */
    public static ResultSet getResultSet(PreparedStatement ps, Object... params) {
        ResultSet rs = null;
        int j = params.length;
        try {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 关闭链接
     *
     * @param conn
     */
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭stmt
     */
    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
                ps = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭rs
     *
     * @param rs
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
