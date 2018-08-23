package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PasajeroM;


public class PasajeroDAO extends DAO{
    public void registrar(PasajeroM pas) throws Exception{
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("EXEC SP_PASAJERO ?,?,?,?,?");
            st.setString(1, pas.getNombreP());
            st.setString(2, pas.getApellidoP());
            st.setString(3, pas.getDniP());
            st.setString(4, pas.getCelularP());
            st.setString(5, pas.getFecnacP());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }

        public String obtenerCodigoPasajero(String Pasajero) throws SQLException {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select cod_Pasajero from Pasajero where CONCAT(Pasajero.nom_Pasajero,' ',Pasajero.ape_Pasajero) like ?";
            PreparedStatement ps = this.getCnt().prepareCall(sql);
            ps.setString(1, Pasajero);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("cod_Pasajero");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }
        
        public List<String> autocompletePasajero(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select CONCAT(Pasajero.nom_Pasajero,' ',Pasajero.ape_Pasajero) AS Pasajero FROM Pasajero WHERE UPPER(Pasajero.nom_Pasajero) like UPPER(?) or UPPER(Pasajero.ape_Pasajero) like UPPER(?)";
            PreparedStatement ps = this.getCnt().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            ps.setString(2, "%" + Consulta + "%");
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
    
    public List<PasajeroM> listar() throws Exception{
        List<PasajeroM> lista;
        ResultSet rs;
        
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareCall("SELECT * FROM VW_PASAJERO_LISTA");
            rs = st.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                PasajeroM pas = new PasajeroM();
                pas.setCodigoP(rs.getInt("cod_Pasajero"));
                pas.setNombreP(rs.getString("nom_Pasajero"));
                pas.setApellidoP(rs.getString("ape_Pasajero"));
                pas.setDniP(rs.getString("dni_Pasajero"));
                pas.setCelularP(rs.getString("cel_Pasajero"));
                pas.setFecnacP(rs.getString("fecnac_Pasajero"));
                lista.add(pas);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
        return lista;
       } 
    
    public PasajeroM leerID(PasajeroM pas) throws Exception{
        PasajeroM pasa = null;
        ResultSet rs;
        
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("SELECT cod_Pasajero, nom_Pasajero, ape_Pasajero,dni_Pasajero,cel_Pasajero, CONVERT(nvarchar(10),fecnac_Pasajero,103) AS fecnac_Pasajero FROM Pasajero WHERE cod_Pasajero=?");
            st.setInt(1, pas.getCodigoP());
            rs = st.executeQuery();
            while(rs.next()){
                pasa = new PasajeroM();
                pasa.setCodigoP(rs.getInt("cod_Pasajero"));
                pasa.setNombreP(rs.getString("nom_Pasajero"));
                pasa.setApellidoP(rs.getString("ape_Pasajero"));
                pasa.setDniP(rs.getString("dni_Pasajero"));
                pasa.setCelularP(rs.getString("cel_Pasajero"));
                pasa.setFecnacP(rs.getString("fecnac_Pasajero"));
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
        return pasa;
       } 
    
    public void modificar(PasajeroM pas) throws Exception{
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("UPDATE Pasajero SET nom_Pasajero=?, ape_Pasajero=?,dni_Pasajero=?,cel_Pasajero=?, fecnac_Pasajero =? FROM Pasajero WHERE cod_Pasajero=?");
//            PreparedStatement st = this.getCnt().prepareStatement("EXEC SP_PASAJERO_UPDATE ?,?,?,?,?,?");
            st.setString(1, pas.getNombreP());
            st.setString(2, pas.getApellidoP());
            st.setString(3, pas.getDniP());
            st.setString(4, pas.getCelularP());
            st.setString(5, pas.getFecnacP());
            st.setInt(6, pas.getCodigoP());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }
    public void eliminar(PasajeroM pas) throws Exception{
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("DELETE FROM Pasajero WHERE cod_Pasajero = ?");
            st.setInt(1, pas.getCodigoP());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }
}

