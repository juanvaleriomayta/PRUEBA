package bean;

import dao.ChoferDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.ChoferM;

@ManagedBean
@SessionScoped
public class ChoferBean implements Serializable{
    private ChoferM chofer = new ChoferM();
    private List<ChoferM> lstChofer;
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
       chofer = new ChoferM();
    }

    private void registrar() throws Exception {
        ChoferDAO dao;

        try {
            dao = new ChoferDAO();
            dao.registrar(chofer);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        ChoferDAO dao;

        try {
            dao = new ChoferDAO();
            lstChofer = dao.listar();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Listado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(ChoferM cho) throws Exception {
        ChoferDAO dao;
        ChoferM temp;

        try {
            dao = new ChoferDAO();
            temp = dao.leerID(cho);

            if (temp != null) {
                this.chofer = temp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        ChoferDAO dao;

        try {
            dao = new ChoferDAO();
            dao.modificar(chofer);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modificado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(ChoferM cho) throws Exception {
        ChoferDAO dao;

        try {
            dao = new ChoferDAO();
            dao.eliminar(cho);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }
    
    //GETTER AND SETTER

    public ChoferM getChofer() {
        return chofer;
    }

    public void setChofer(ChoferM chofer) {
        this.chofer = chofer;
    }

    public List<ChoferM> getLstChofer() {
        return lstChofer;
    }

    public void setLstChofer(List<ChoferM> lstChofer) {
        this.lstChofer = lstChofer;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    
}
