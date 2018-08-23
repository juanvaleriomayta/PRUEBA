package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    private Connection cnt;

    public void Conexion() {
        try {
            if (cnt == null) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                cnt = DriverManager.getConnection("jdbc:sqlserver://valerius.database.windows.net:1433;database=SistTransporte","jvalerio","pa$$word2018");
                cnt = DriverManager.getConnection("jdbc:sqlserver://192.168.91.152:1433;database=SistTransporte","jvaleriom","123");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+e);
        }
    }
    
    public void Cerrar() throws Exception{
        if(cnt !=null){
            if(cnt.isClosed() ==false){
                cnt.close();
            }
        }
    }
    
    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.Conexion();
        if(dao.getCnt() !=null){
            System.out.println("Conectado con Exito");
        }else{
            System.out.println("Error en la Conexion");
        }
    }

    public Connection getCnt() {
        return cnt;
    }

    public void setCnt(Connection cnt) {
        this.cnt = cnt;
    }
}
