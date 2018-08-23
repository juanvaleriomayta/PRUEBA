package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BusM;

public class BusDAO extends DAO {

    public void registrar(BusM bus) throws Exception {
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("exec SP_BUS ?");
            st.setString(1, bus.getPlacaBus());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<BusM> listar() throws Exception {
        List<BusM> lista;
        ResultSet rs;

        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareCall("SELECT * FROM VW_LISTA_BUS");
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                BusM bus = new BusM();
                bus.setCodigoBus(rs.getInt("cod_Bus"));
                bus.setPlacaBus(rs.getString("placa_Bus"));
                lista.add(bus);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public BusM leerID(BusM bus) throws Exception {
        BusM buse = null;
        ResultSet rs;

        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("SELECT cod_Bus, UPPER(placa_Bus) AS placa_Bus FROM Bus WHERE cod_Bus=?");
            st.setInt(1, bus.getCodigoBus());
            rs = st.executeQuery();
            while (rs.next()) {
                buse = new BusM();
                buse.setCodigoBus(rs.getInt("cod_Bus"));
                buse.setPlacaBus(rs.getString("placa_Bus"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return buse;
    }

    public void modificar(BusM bus) throws Exception {
        try {
            this.Conexion();
//            PreparedStatement st = this.getCnt().prepareStatement("UPDATE Bus SET placa_Bus = ? WHERE cod_Bus LIKE ?");
            PreparedStatement st = this.getCnt().prepareStatement("EXEC SP_BUS_UPDATE ?,?");
            st.setString(1, bus.getPlacaBus());
            st.setInt(2, bus.getCodigoBus());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void eliminar(BusM bus) throws Exception {
        try {
            this.Conexion();
            PreparedStatement st = this.getCnt().prepareStatement("DELETE FROM Bus WHERE cod_Bus = ?");
            st.setInt(1, bus.getCodigoBus());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}
