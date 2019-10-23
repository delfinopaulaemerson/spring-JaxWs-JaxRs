package br.com.appservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.appservice.dao.UsuarioDao;
import br.com.appservice.model.User;

@Component("usuarioServiceFacadeImpl")
public class UsuarioServiceFacadeImpl implements UsuarioServiceFacade{
	
	@Autowired
	private UsuarioDao dao;
	
	@Override
	public void inserirUsuario(User user) throws Exception {
		this.dao.inserirUsuario(user);
		
	}
	

	@Override
	public User findById(Long idUser)throws Exception {
		return this.dao.findById(idUser);
	}
	
	@Override
	public void upadateUsuario(User user) throws Exception {
			this.dao.upadateUsuario(user);
		
	}
	@Override
	public void deleteUsuario(Long idUsuario) throws Exception {
		this.dao.deleteUsuario(idUsuario);
		
	}
	
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}




}
