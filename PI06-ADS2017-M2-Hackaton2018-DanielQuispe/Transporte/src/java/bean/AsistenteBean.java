package bean;

import dao.AsistenteDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.AsistenteM;

@ManagedBean
@SessionScoped
public class AsistenteBean implements Serializable {

    private AsistenteM asistente = new AsistenteM();
    private List<AsistenteM> lstAsistente;
    private String accion;

    public void operar() throws Exception {
        switch (accion) {
            case "INGRESAR":
                this.ingresar();
                break;
            case "Actualizar":
                this.actualizar();
                break;
        }
    }

    public void limpiar() throws Exception {
        asistente = new AsistenteM();
    }

    private void ingresar() throws Exception {
        AsistenteDAO dao;

        try {
            dao = new AsistenteDAO();
            dao.Ingresar(asistente);
            this.limpiar();
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        AsistenteDAO dao;
        try {
            dao = new AsistenteDAO();
            lstAsistente = dao.listar();
            
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Listado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(AsistenteM asis) throws Exception {
        AsistenteDAO dao;
        AsistenteM temp;

        try {
            dao = new AsistenteDAO();
            temp = dao.leerID(asis);

            if (temp != null) {
                this.asistente = temp;
                this.accion = "Actualizar";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void actualizar() throws Exception {
        AsistenteDAO dao;
        try {
            dao = new AsistenteDAO();
            dao.actualizar(asistente);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(AsistenteM asis) throws Exception {
        AsistenteDAO dao;

        try {
            dao = new AsistenteDAO();
            dao.Eliminar(asis);
            this.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado con Exito"));
        } catch (Exception e) {
            throw e;
        }
    }

    //GETTER AND SETTER   
    public AsistenteM getAsistente() {
        return asistente;
    }

    public void setAsistente(AsistenteM asistente) {
        this.asistente = asistente;
    }

    public List<AsistenteM> getLstAsistente() {
        return lstAsistente;
    }

    public void setLstAsistente(List<AsistenteM> lstAsistente) {
        this.lstAsistente = lstAsistente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) throws Exception {
        this.limpiar();
        this.accion = accion;
    }
}
