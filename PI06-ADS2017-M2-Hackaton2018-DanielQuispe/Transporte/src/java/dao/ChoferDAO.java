package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChoferM;


public class ChoferDAO extends DAO{
    public void registrar(ChoferM cho) throws Exception{
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("EXEC SP_CHOFER ?,?,?,?");
            st.setString(1, cho.getNombreC());
            st.setString(2, cho.getApellidoC());
            st.setString(3, cho.getDniC());
            st.setString(4, cho.getCelularC());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }

    public List<ChoferM> listar() throws Exception{
        List<ChoferM> lista;
        ResultSet rs;
        
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareCall("SELECT * FROM VW_CHOFER_LISTA");
            rs = st.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                ChoferM cho = new ChoferM();
                cho.setCodigoC(rs.getInt("cod_Chofer"));
                cho.setNombreC(rs.getString("nom_Chofer"));
                cho.setApellidoC(rs.getString("ape_Chofer"));
                cho.setDniC(rs.getString("dni_Chofer"));
                cho.setCelularC(rs.getString("cel_Chofer"));
                lista.add(cho);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
        return lista;
       } 
    
    public ChoferM leerID(ChoferM cho) throws Exception{
        ChoferM chof = null;
        ResultSet rs;
        
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("SELECT cod_Chofer, nom_Chofer, ape_Chofer, dni_Chofer, cel_Chofer FROM Chofer WHERE cod_Chofer=?");
            st.setInt(1, cho.getCodigoC());
            rs = st.executeQuery();
            while(rs.next()){
                chof = new ChoferM();
                chof.setCodigoC(rs.getInt("cod_Chofer"));
                chof.setNombreC(rs.getString("nom_Chofer"));
                chof.setApellidoC(rs.getString("ape_Chofer"));
                chof.setDniC(rs.getString("dni_Chofer"));
                chof.setCelularC(rs.getString("cel_Chofer"));
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
        return chof;
       } 
    
    public void modificar(ChoferM cho) throws Exception{
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("UPDATE Chofer SET nom_Chofer=?, ape_Chofer=?, dni_Chofer=?, cel_Chofer=? WHERE cod_Chofer=?");
            st.setString(1, cho.getNombreC());
            st.setString(2, cho.getApellidoC());
            st.setString(3, cho.getDniC());
            st.setString(4, cho.getCelularC());
            st.setInt(5, cho.getCodigoC());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }
    public void eliminar(ChoferM cho) throws Exception{
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("DELETE FROM Chofer WHERE cod_Chofer = ?");
            st.setInt(1, cho.getCodigoC());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            this.Cerrar();
        }
    }

}
