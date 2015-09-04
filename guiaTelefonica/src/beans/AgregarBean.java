package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Administrador;
import modelo.Personal;
import controlador.AgregarServicio;
import controlador.LoginServicio;

@ManagedBean
@ViewScoped
public class AgregarBean implements Serializable {

	/**
	 * esta clase sirve para agregar nuevas extensiones.
	 */
	private static final long serialVersionUID = 1L;

	private AgregarServicio admiSedeServicio = new AgregarServicio();
	private LoginServicio loginServicio = new LoginServicio();
	private List<Administrador> administrado;
	private String nombreAdministrador ="";
	
	private List<Personal> personal;
	private Personal selectPersonal;
	private String textoBuscado;
	private String telefono;
	private String extension;

	public AgregarBean() {
	}
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}


	public LoginServicio getLoginServicio() {
		return loginServicio;
	}

	public void setLoginServicio(LoginServicio loginServicio) {
		this.loginServicio = loginServicio;
	}
	
	

	public String getNombreAdministrador() {
		return nombreAdministrador;
	}

	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}

	public List<Administrador> getAdministrado() {
		return administrado;
	}

	public void setAdministrado(List<Administrador> administrado) {
		this.administrado = administrado;
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
	

	/* *
	 * Obtener el ID del login y obtener todos los datos del admin, para la interfaz y las consultas.
	 */
	@PostConstruct
	public void inicializar() {
		System.out.println("ID ADMINISTRADOR: "+loginBean.getIdentificador());
		if(this.loginBean.getIdentificador().equals("")){
			System.out.println("ERROR DE LOGIN");
		}else{
			this.administrado = loginServicio.obtenerAdminConSedes(this.loginBean.getIdentificador());
			this.nombreAdministrador = this.administrado.get(0).getNombAdmin();
		}
		
		
	}
	

	public void botonAction() {
		addMessage("Buscando Información !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");
		List<String> sedesCodigos = new ArrayList<String>();
		for (Administrador sedecodigo : administrado) {
			sedesCodigos.add(sedecodigo.getCodigoSede());
		}
		
		this.personal = this.admiSedeServicio.buscarPersonal(this.textoBuscado,sedesCodigos);

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
