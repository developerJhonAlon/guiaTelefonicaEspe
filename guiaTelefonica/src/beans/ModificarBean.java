package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.VistaBusqueda;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controlador.ModificarServicio;

@ManagedBean
@ViewScoped
public class ModificarBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ModificarServicio modificarServicio = new ModificarServicio();
	private List<VistaBusqueda> vistaBusqueda;
	private VistaBusqueda busquedaSelect;
	private String valorEnviado;
	private String nuevoFono;
	private String nuevaExten;
	
	public ModificarBean(){}
	
	public ModificarServicio getModificarServicio() {
		return modificarServicio;
	}
	
	
	
	public String getNuevoFono() {
		return nuevoFono;
	}

	public void setNuevoFono(String nuevoFono) {
		this.nuevoFono = nuevoFono;
	}

	public String getNuevaExten() {
		return nuevaExten;
	}

	public void setNuevaExten(String nuevaExten) {
		this.nuevaExten = nuevaExten;
	}

	public void setModificarServicio(ModificarServicio modificarServicio) {
		this.modificarServicio = modificarServicio;
	}
	public List<VistaBusqueda> getVistaBusqueda() {
		return vistaBusqueda;
	}
	public void setVistaBusqueda(List<VistaBusqueda> vistaBusqueda) {
		this.vistaBusqueda = vistaBusqueda;
	}
	public VistaBusqueda getBusquedaSelect() {
		return busquedaSelect;
	}
	public void setBusquedaSelect(VistaBusqueda busquedaSelect) {
		this.busquedaSelect = busquedaSelect;
	}
	
	
	public String getValorEnviado() {
		return valorEnviado;
	}

	public void setValorEnviado(String valorEnviado) {
		this.valorEnviado = valorEnviado;
	}

	/* *
	 * Metodo para realizar la consultas de las extensiones existentes.
	 */
	public void botonConsulta() {
		
		System.out.println("OBTENER EXTENSIONES --->>");
		this.vistaBusqueda = modificarServicio.obtenerExtension(this.valorEnviado);

	}
	
	/* *
	 * Metodo para realizar la modificaci�n de Telefono y Extension.
	 */
	public void botonModificar() {
		addMessage("Informaci�n Modificada !!");
		System.out.println("REALIZAR MODIFICACION --->>");
		this.vistaBusqueda =   this.modificarServicio.modificaExtOrFono(this.busquedaSelect.getIdAsignacion(),this.busquedaSelect.getIdPersonal(),this.nuevoFono,this.nuevaExten);
		
	}
	
	/* *
	 * Metodo para realizar la eliminacion de un registro Telefonico.
	 */
	public void botonEliminar() {
		addMessage("Informaci�n Eliminada !!");
		System.out.println("REALIZAR ELIMINACION --->>");
		this.vistaBusqueda =   this.modificarServicio.eliminaRegistro(this.busquedaSelect);
		
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void onRowSelect(SelectEvent event) {
//		this.nuevoFono = this.busquedaSelect.getTelefonoNomb();
//		this.nuevaExten = this.busquedaSelect.getExtensionNomb();
        FacesMessage msg = new FacesMessage("Car Selected", ((VistaBusqueda) event.getObject()).getPersonalNomb());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
	
    public void onRowUnselect(UnselectEvent event) {
//    	this.nuevoFono = "";
//		this.nuevaExten = "";
        FacesMessage msg = new FacesMessage("Car Unselected", ((VistaBusqueda) event.getObject()).getPersonalNomb());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
} 
