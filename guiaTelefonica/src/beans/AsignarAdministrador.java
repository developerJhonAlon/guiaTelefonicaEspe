package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Busqueda;
import modelo.Personal;
import controlador.AsignarAdministradorServicio;

@ManagedBean
@ViewScoped
public class AsignarAdministrador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AsignarAdministradorServicio asignarServicio = new AsignarAdministradorServicio();
	private Personal selectPersonal;
	private List<Busqueda> listaSedes;
	private Busqueda[] selectSedes;
	
	
	public AsignarAdministrador() {
	}

	
	
	public Busqueda[] getSelectSedes() {
		return selectSedes;
	}



	public void setSelectSedes(Busqueda[] selectSedes) {
		this.selectSedes = selectSedes;
	}



	public List<Busqueda> getListaSedes() {
		return listaSedes;
	}

	public void setListaSedes(List<Busqueda> listaSedes) {
		this.listaSedes = listaSedes;
	}



	public AsignarAdministradorServicio getAsignarServicio() {
		return asignarServicio;
	}

	public void setAsignarServicio(AsignarAdministradorServicio asignarServicio) {
		this.asignarServicio = asignarServicio;
	}

	public Personal getSelectPersonal() {
		return selectPersonal;
	}

	public void setSelectPersonal(Personal selectPersonal) {
		this.selectPersonal = selectPersonal;
	}

	@PostConstruct
	public void inicializar() {
		this.listaSedes = this.asignarServicio.obtenerSedes();
		System.out.println("LLENADO DE COMBO DE SEDES");

	}

	public void botonBuscar() {
		// addMessage("Buscando Información !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");

		// this.personal =
		// this.asignarServicio.buscarPersonal(this.datoBuscado);

	}

	public void botonAsignar() {
		// addMessage("Buscando Información !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");

		// this.personal =
		// this.admiSedeServicio.buscarPersonal(this.textoBuscado);

	}

}
