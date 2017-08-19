/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.*;

/**
 *
 * @author Michel
 */
public class conexao {

    static Connection connect;
    private static Statement st;

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            connect = DriverManager.getConnection("jdbc:mysql://localhost/radioufv","root", "");
            System.out.println("Conectando ao banco de dados");
            return connect;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void executa(String sql) throws Exception {

        st = connect.createStatement();
        st.executeUpdate(sql);
    }

    public static ResultSet listar(String sql) throws Exception {
        System.out.println(sql);
        st = connect.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
        }
        return rs;
    }
    
    public static void desconect() throws Exception{
        connect.close();
    }
}