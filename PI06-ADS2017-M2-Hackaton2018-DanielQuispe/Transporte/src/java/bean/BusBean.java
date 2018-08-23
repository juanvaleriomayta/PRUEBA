package bean;

import dao.BusDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.BusM;

@ManagedBean
@ViewScoped
public class BusBean {

    private BusM bus = new BusM();
    private List<BusM> lstBus;
    private String accion;

    public void operar() throws Exception {
        switch (accion) {
            case "INGRESAR":
                this.registrar();
                break;
            case "Modificar":
                this.modificar();
                break;
        }
    }

    public void limpiar() {
        bus = new BusM();
    }

    private void registrar() throws Exception {
        BusDAO dao;

        try {
            dao = new BusDAO();
            dao.registrar(bus);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        BusDAO dao;

        try {
            dao = new BusDAO();
            lstBus = dao.listar();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Listado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(BusM buse) throws Exception {
        BusDAO dao;
        BusM temp;

        try {
            dao = new BusDAO();
            temp = dao.leerID(buse);

            if (temp != null) {
                this.bus = temp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        BusDAO dao;

        try {
            dao = new BusDAO();
            dao.modificar(bus);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(BusM buse) throws Exception {
        BusDAO dao;

        try {
            dao = new BusDAO();
            dao.eliminar(buse);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    //getter and setter
    public BusM getBus() {
        return bus;
    }

    public void setBus(BusM bus) {
        this.bus = bus;
    }

    public List<BusM> getLstBus() {
        return lstBus;
    }

    public void setLstBus(List<BusM> lstBus) {
        this.lstBus = lstBus;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
}
