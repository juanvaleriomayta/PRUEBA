package bean;

import dao.BoletoDAO;
import dao.PasajeroDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.BoletoM;

@ManagedBean
@SessionScoped
public class BoletoBean implements Serializable {

    private BoletoM boleto = new BoletoM();
    private List<BoletoM> lstBoleto;
    private String accion;

    public void operar() throws Exception {
        switch (accion) {
            case "Registrar":
                this.registrar();
                break;
            case "Modificar":
                this.modificar();
                break;
        }
    }

    public void limpiar() {
        boleto = new BoletoM();
    }

    private void registrar() throws Exception {
        BoletoDAO dao;
        PasajeroDAO dao2;

        try {
            dao = new BoletoDAO();
            dao2 = new PasajeroDAO();
            boleto.setCodigoP(dao2.obtenerCodigoPasajero(boleto.getPasajero()));
            dao.registrar(boleto);
            this.listar();
            this.limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

        public List<String> completText(String query) throws SQLException {
        BoletoDAO dao = new BoletoDAO();
        return dao.autocompletePasajero(query);
        }
    
    public void listar() throws Exception {
        BoletoDAO dao;

        try {
            dao = new BoletoDAO();
            lstBoleto = dao.listar();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Listado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(String Codigo) throws Exception {
        BoletoDAO dao;

        try {
            dao = new BoletoDAO();
            this.boleto = dao.leerID(Codigo);
            this.accion = "Modificar";

        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        BoletoDAO dao;
        PasajeroDAO dao2;
        try {
            dao = new BoletoDAO();
            dao2 = new PasajeroDAO();
            boleto.setCodigoP(dao2.obtenerCodigoPasajero(boleto.getPasajero()));
            dao.modificar(boleto);
            this.listar();
            this.limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(BoletoM bol) throws Exception {
        BoletoDAO dao;

        try {
            dao = new BoletoDAO();
            dao.eliminar(bol);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

//GETTER AND SETTER
    public BoletoM getBoleto() {
        return boleto;
    }

    public void setBoleto(BoletoM boleto) {
        this.boleto = boleto;
    }

    public List<BoletoM> getLstBoleto() {
        return lstBoleto;
    }

    public void setLstBoleto(List<BoletoM> lstBoleto) {
        this.lstBoleto = lstBoleto;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

}
