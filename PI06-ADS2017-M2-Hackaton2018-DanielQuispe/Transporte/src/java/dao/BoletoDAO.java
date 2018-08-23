package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BoletoM;


public class BoletoDAO extends DAO{
    public void registrar(BoletoM bol) throws Exception{
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("EXEC SP_BOLETO ?,?,?,?,?,?");
            st.setString(1, bol.getOrigenB());
            st.setString(2, bol.getDestinoB());
            st.setString(3, bol.getFechaviajeB());
            st.setString(4, bol.getCostoB());
            st.setString(5, bol.getHoraviajeB());
            st.setString(6, bol.getCodigoP());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }

    public List<String> autocompletePasajero(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT CONCAT(Pasajero.nom_Pasajero,' ',Pasajero.ape_Pasajero) AS Pasajero FROM Pasajero WHERE UPPER(Pasajero.nom_Pasajero) like UPPER(?) or UPPER(Pasajero.ape_Pasajero) LIKE UPPER(?)";
            PreparedStatement ps = this.getCnt().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("Pasajero"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public List<BoletoM> listar() throws Exception{
        List<BoletoM> lista;
        ResultSet rs;
        
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareCall("SELECT * FROM VW_LISTA_BOLETO");
            rs = st.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                BoletoM bol = new BoletoM();
                bol.setCodigob(rs.getString("cod_Boleto"));
                bol.setOrigenB(rs.getString("orig_Boleto"));
                bol.setDestinoB(rs.getString("dest_Boleto"));
                bol.setFechaviajeB(rs.getString("FecViaj_Boleto"));
                bol.setCostoB(rs.getString("cost_Boleto"));
                bol.setHoraviajeB(rs.getString("horViaj_Boleto"));
                bol.setPasajero(rs.getString("Pasajero"));
                lista.add(bol);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
        return lista;
       } 
    
    public BoletoM leerID(String Codigo) throws Exception{
        BoletoM bole = null;
        ResultSet rs;
        
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("SELECT cod_Boleto, orig_Boleto, dest_Boleto, CONVERT(nvarchar(10),FecViaj_Boleto,103) AS FecViaj_Boleto, cost_Boleto, horViaj_Boleto, CONCAT(Pasajero.nom_Pasajero,' ',Pasajero.ape_Pasajero) AS Pasajero FROM Boleto LEFT OUTER JOIN Pasajero ON Boleto.Pasajero_cod_Pasajero = Pasajero.cod_Pasajero WHERE cod_Boleto=?");
            st.setString(1, Codigo);
            rs = st.executeQuery();
            if(rs.next()){
                bole = new BoletoM();
                bole.setCodigob(rs.getString("cod_Boleto"));
                bole.setOrigenB(rs.getString("orig_Boleto"));
                bole.setDestinoB(rs.getString("dest_Boleto"));
                bole.setFechaviajeB(rs.getString("FecViaj_Boleto"));
                bole.setCostoB(rs.getString("cost_Boleto"));
                bole.setHoraviajeB(rs.getString("horViaj_Boleto"));
                bole.setPasajero(rs.getString("Pasajero"));
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
        return bole;
       } 
    
    public void modificar(BoletoM bol) throws Exception{
        try {
            this.Conexion();
//            PreparedStatement st = this.getCnt().prepareStatement("UPDATE Boleto SET orig_Boleto=?, dest_Boleto=?, CONVERT(nvarchar(10),FecViaj_Boleto,103) AS FecViaj_Boleto=?, cost_Boleto=?, horViaj_Boleto=? CONCAT(Pasajero.nom_Pasajero,' ',Pasajero.ape_Pasajero) AS Pasajero =? WHERE cod_Boleto=?");
            PreparedStatement st = this.getCnt().prepareStatement("EXEC SP_BOLETO_UPDATE ?,?,?,?,?,?,?");
            st.setString(1, bol.getOrigenB());
            st.setString(2, bol.getDestinoB());
            st.setString(3, bol.getFechaviajeB());
            st.setString(4, bol.getCostoB());
            st.setString(5, bol.getHoraviajeB());
            st.setString(6, bol.getCodigoP());
            st.setString(7, bol.getCodigob());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }
    public void eliminar(BoletoM bol) throws Exception{
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("DELETE FROM Boleto WHERE cod_Boleto = ?");
            st.setString(1, bol.getCodigob());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }
}
