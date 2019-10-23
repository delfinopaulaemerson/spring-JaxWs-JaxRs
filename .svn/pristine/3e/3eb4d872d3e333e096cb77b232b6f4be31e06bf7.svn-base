package br.com.appservice.ws;

import javax.xml.ws.WebFault;

@WebFault(name="usuarioException")
public class UsuarioException extends Exception{
	
	
	private UsuarioDeniedFault faultInfo;
	
	public UsuarioException(String message, UsuarioDeniedFault faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	/**
	 * 
	 * @return faultInfo
	 */
	public UsuarioDeniedFault getFaultInfo() {
		return faultInfo;
	}	
}
