package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.AsistenteM;

public class AsistenteDAO extends DAO {

    public void Ingresar(AsistenteM asis) throws Exception {
        try {
            this.Conexion();
            String sql = " EXEC SP_ASISTENTE ?,?,?,?";
            PreparedStatement pst = this.getCnt().prepareStatement(sql);
            pst.setString(1, asis.getNombreA());
            pst.setString(2, asis.getApellidoA());
            pst.setString(3, asis.getDniA());
            pst.setString(4, asis.getCelularA());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<AsistenteM> listar() throws Exception {
        List<AsistenteM> lista;
        ResultSet rs;

        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LISTA_ASISTENTE";
            PreparedStatement pst = this.getCnt().prepareCall(sql);
            rs = pst.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                AsistenteM asis = new AsistenteM();
                asis.setCodigoA(rs.getInt("cod_Asistente"));
                asis.setNombreA(rs.getString("nom_Asistente"));
                asis.setApellidoA(rs.getString("ape_Asistente"));
                asis.setDniA(rs.getString("dni_Asistente"));
                asis.setCelularA(rs.getString("cel_Asistente"));
                lista.add(asis);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public AsistenteM leerID(AsistenteM asis) throws Exception {
        AsistenteM asist = null;
        ResultSet rs;

        try {

            this.Conexion();
//            String sql = "SELECT cod_Asistente,UPPER(nom_Asistente),UPPER(ape_Asistente),dni_Asistente,cel_Asistente FROM Asistente WHERE cod_Asistente LIKE ?";
            PreparedStatement pst = this.getCnt().prepareStatement("SELECT cod_Asistente,nom_Asistente,ape_Asistente,dni_Asistente,cel_Asistente FROM Asistente WHERE cod_Asistente LIKE ?");
            pst.setInt(1, asis.getCodigoA());
            rs = pst.executeQuery();
            while(rs.next()) {
                asist = new AsistenteM();
                asist.setCodigoA(rs.getInt("cod_Asistente"));
                asist.setNombreA(rs.getString("nom_Asistente"));
                asist.setApellidoA(rs.getString("ape_Asistente"));
                asist.setDniA(rs.getString("dni_Asistente"));
                asist.setCelularA(rs.getString("cel_Asistente"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return asist;
    }

    public void actualizar(AsistenteM asis) throws Exception {
        try {
            this.Conexion();
//            String sql = "EXEC SP_ASISTENTE_UPDATE ?,?,?,?,?";
            PreparedStatement pst = this.getCnt().prepareStatement("UPDATE Asistente SET nom_Asistente = ?, ape_Asistente = ?, dni_Asistente=?, cel_Asistente=? WHERE cod_Asistente LIKE ?");
            pst.setString(1, asis.getNombreA());
            pst.setString(2, asis.getApellidoA());
            pst.setString(3, asis.getDniA());
            pst.setString(4, asis.getCelularA());
            pst.setInt(5, asis.getCodigoA());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Eliminar(AsistenteM asis) throws Exception {
        try {
            this.Conexion();
            String sql = "DELETE FROM Asistente WHERE cod_Asistente =?";
            PreparedStatement pst = this.getCnt().prepareStatement(sql);
            pst.setInt(1, asis.getCodigoA());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}
