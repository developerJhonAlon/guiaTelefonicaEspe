package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controlador.LoginServicio;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String identificador = "";
	private LoginServicio loginServicio = new LoginServicio();

	public LoginBean() {
	}

	
	public LoginServicio getLoginServicio() {
		return loginServicio;
	}


	public void setLoginServicio(LoginServicio loginServicio) {
		this.loginServicio = loginServicio;
	}


	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String botonLogin() {
		boolean existencia = loginServicio.comprobarAdmin(this.identificador);
		if (existencia) {
			return "agregarExtension?faces-redirect=true";
		} else {
			return "error?faces-redirect=true";
		}

	}
	
	
}
