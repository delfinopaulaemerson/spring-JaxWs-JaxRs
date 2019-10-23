package br.com.appservice.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.appservice.facade.UsuarioServiceFacade;
import br.com.appservice.model.User;

@Component("usuarioWs")
@WebService(name="UsuarioWebService")
public class UsuarioWs {
	
	@Autowired
	private UsuarioServiceFacade facade; 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioWs.class);
	
	@WebMethod(operationName="inserirUsuario")
	@WebResult(name="cadastrado")
	public String inserirUsuario(@WebParam(name="username")String username,
			@WebParam(name="password")String password,@WebParam(name="token")String token) {
		boolean ok = true;
		User user = new User();
		UsuarioDeniedFault usuarioDeniedFault = new UsuarioDeniedFault();
		try {
			
			//verifica se o token foi inserido
			if(!"dWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR".equals(token)) {
				ok = false;
				usuarioDeniedFault.setMessage(" token é obrigatório! ");
				
			}
			
			//verifica se o  username foi inserido
			if("".equals(username)) {
				ok = false;
				usuarioDeniedFault.setMessage(" userName é obrigatório! ");
			}
			
			//verifica se o password foi inserido 
			if("".equals(password)) {
				ok=false;
				usuarioDeniedFault.setMessage(" password é obrigatório! ");
				
			}
			
			if(ok) {
				//encapsula os valores no objeto
				user.setUsername(username);
				user.setPassword(password);
				//persiste o objeto.
				this.facade.inserirUsuario(user);
				usuarioDeniedFault.setMessage("Usuários cadastrados com sucesso!");
			}
			
		} catch (Exception e) {
			LOGGER.error(" Erro ao cadastrar o usuario "+ e.getMessage());
			usuarioDeniedFault.setMessage(" Erro ao cadastrar o usuario ");
			try {
				throw new UsuarioException(" Usuario Exception ", usuarioDeniedFault);
			} catch (UsuarioException e1) {
				LOGGER.error(" Erro ao cadastrar o usuario "+ e1.getMessage());
			}
		}
		
		return usuarioDeniedFault.getMessage() ;
	}
	
	
	/**
	 * 
	 * @param token
	 * @param idUser
	 * @return User
	 */
	public User findByIdUsuario(@WebParam(name="token")String token,@WebParam(name="idUser")Long idUser) {
		boolean ok = true;
		User user = new User();
		UsuarioDeniedFault usuarioDeniedFault = new UsuarioDeniedFault();
		try {
			//verifica se o token foi inserido
			if(!"dWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR".equals(token)) {
				ok = false;
				usuarioDeniedFault.setMessage(" token é obrigatório! ");
			}
			//verifica se o token foi inserido
			if(idUser == null ) {
				ok = false;
				usuarioDeniedFault.setMessage(" IdUser é obrigatório! ");
			}
			
			user = this.facade.findById(idUser);
		}  catch (Exception e) {
			LOGGER.error(" Erro ao recuperar o usuario "+ e.getMessage());
			usuarioDeniedFault.setMessage(" Erro ao recuperar o usuario ");
			try {
				throw new UsuarioException(" Usuario Exception ", usuarioDeniedFault);
			} catch (UsuarioException e1) {
				LOGGER.error(" Erro ao recuperar o usuario "+ e1.getMessage());
			}
		}
		return user;
	}
	
	@WebMethod(operationName="updateUsuario")
	@WebResult(name="atualizado")
	public String updateUsuario(@WebParam(name="idUsuario")Long idUsuario,@WebParam(name="username")String username,
			@WebParam(name="password")String password,@WebParam(name="token")String token) {
		boolean ok = true;
		User user = new User();
		UsuarioDeniedFault usuarioDeniedFault = new UsuarioDeniedFault();
		try {
			//verifica se o token foi inserido
			if(!"dWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR".equals(token)) {
				ok = false;
				usuarioDeniedFault.setMessage(" token é obrigatório! ");
				
			}
			
			//verifica se o  username foi inserido
			if("".equals(username)) {
				ok = false;
				usuarioDeniedFault.setMessage(" userName é obrigatório! ");
			}
			
			//verifica se o password foi inserido 
			if("".equals(password)) {
				ok=false;
				usuarioDeniedFault.setMessage(" password é obrigatório! ");
				
			}
			
			if(ok) {
				//encapsula os valores no objeto
				user.setId(idUsuario);
				user.setUsername(username);
				user.setPassword(password);
				
				//atualiza o objeto.
				this.facade.upadateUsuario(user);
				usuarioDeniedFault.setMessage(" Usuários atualizado com sucesso!");
			}
			
		} catch (Exception e) {
			LOGGER.error(" Erro ao atualizar o usuario "+ e.getMessage());
			usuarioDeniedFault.setMessage(" Erro ao atualizar o usuario ");
			try {
				throw new UsuarioException(" Usuario Exception ", usuarioDeniedFault);
			} catch (UsuarioException e1) {
				LOGGER.error(" Erro ao atualizar o usuario "+ e1.getMessage());
			}
		}
		
		return usuarioDeniedFault.getMessage();
		
	}
	
	@WebMethod(operationName="deleteUsuario")
	@WebResult(name="deletado")
	public String deleteUsuario(@WebParam(name="token")String token,@WebParam(name="idUsuario")Long idUsuario) {
		boolean ok = true;
		User user = new User();
		UsuarioDeniedFault usuarioDeniedFault = new UsuarioDeniedFault();
		try {
			
			//verifica se o token foi inserido
			if(!"dWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR".equals(token)) {
				ok = false;
				usuarioDeniedFault.setMessage(" token é obrigatório! ");
			}
			//verifica se o token foi inserido
			if(idUsuario == null ) {
				ok = false;
				usuarioDeniedFault.setMessage(" idUsuario é obrigatório! ");
			}
			
			if(ok) {
				this.facade.deleteUsuario(idUsuario);
				usuarioDeniedFault.setMessage(" Usuário deletado com sucesso! ");
			}
				
		}catch (Exception e) {
				LOGGER.error(" Erro ao deletar o usuario "+ e.getMessage());
				usuarioDeniedFault.setMessage(" Erro ao deletar o usuario ");
				try {
					throw new UsuarioException(" Usuario Exception ", usuarioDeniedFault);
				} catch (UsuarioException e1) {
					LOGGER.error(" Erro ao deletar o usuario "+ e1.getMessage());
				}
			}
			
			return usuarioDeniedFault.getMessage();
	}

	@WebMethod(exclude=true)
	public void setFacade(UsuarioServiceFacade facade) {
		this.facade = facade;
	}
}
