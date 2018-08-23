package bean;

import dao.PasajeroDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.PasajeroM;

@ManagedBean
@SessionScoped
public class PasajeroBean implements Serializable {

    private PasajeroM pasajero = new PasajeroM();
    private List<PasajeroM> lstPasajero;
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
        pasajero = new PasajeroM();
    }

    private void registrar() throws Exception {
        PasajeroDAO dao;

        try {
            dao = new PasajeroDAO();
            dao.registrar(pasajero);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeText(String query) throws SQLException {
        PasajeroDAO dao = new PasajeroDAO();
        return dao.autocompletePasajero(query);
    }

    public void listar() throws Exception {
        PasajeroDAO dao;

        try {
            dao = new PasajeroDAO();
            lstPasajero = dao.listar();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Listado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(PasajeroM pas) throws Exception {
        PasajeroDAO dao;
        PasajeroM temp;

        try {
            dao = new PasajeroDAO();
            temp = dao.leerID(pas);

            if (temp != null) {
                this.pasajero = temp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        PasajeroDAO dao;

        try {
            dao = new PasajeroDAO();
            dao.modificar(pasajero);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(PasajeroM pas) throws Exception {
        PasajeroDAO dao;

        try {
            dao = new PasajeroDAO();
            dao.eliminar(pas);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    //GETTER AND SETTER
    public PasajeroM getPasajero() {
        return pasajero;
    }

    public void setPasajero(PasajeroM pasajero) {
        this.pasajero = pasajero;
    }

    public List<PasajeroM> getLstPasajero() {
        return lstPasajero;
    }

    public void setLstPasajero(List<PasajeroM> lstPasajero) {
        this.lstPasajero = lstPasajero;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

}
