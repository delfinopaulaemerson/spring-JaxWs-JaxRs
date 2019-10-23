package br.com.appservice.dao;

import org.springframework.stereotype.Component;

import br.com.appservice.model.User;

@Component("usuarioDao")
public interface UsuarioDao {

	void inserirUsuario(User user) throws Exception;

	User findById(Long idUser)throws Exception;

	void upadateUsuario(User user)throws Exception;

	void deleteUsuario(Long idUsuario)throws Exception;

}
