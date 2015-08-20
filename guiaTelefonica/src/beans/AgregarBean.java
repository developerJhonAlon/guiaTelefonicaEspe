package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Personal;
import controlador.AgregarServicio;

@ManagedBean
@ViewScoped
public class AgregarBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AgregarServicio admiSedeServicio = new AgregarServicio();
	private List<Personal> personal;
	private Personal selectPersonal;
	private String textoBuscado;
	private String telefono;
	private String extension;

	public AgregarBean() {
	}

	public AgregarServicio getAdmiSedeServicio() {
		return admiSedeServicio;
	}

	public void setAdmiSedeServicio(AgregarServicio admiSedeServicio) {
		this.admiSedeServicio = admiSedeServicio;
	}

	public List<Personal> getPersonal() {
		return personal;
	}

	public void setPersonal(List<Personal> personal) {
		this.personal = personal;
	}

	public Personal getSelectPersonal() {
		return selectPersonal;
	}

	public void setSelectPersonal(Personal selectPersonal) {
		this.selectPersonal = selectPersonal;
	}

	public String getTextoBuscado() {
		return textoBuscado;
	}

	public void setTextoBuscado(String textoBuscado) {
		this.textoBuscado = textoBuscado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void botonAction() {
		addMessage("Buscando Información !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");

		this.personal = this.admiSedeServicio.buscarPersonal(this.textoBuscado);

	}

	public void botonGuardar() {
		System.out.println("GUADAR EXTENSION --->>");
		this.admiSedeServicio.guardarInformacion(this.selectPersonal,
				this.telefono, this.extension);
		addMessage("Guardando Información !!");

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
