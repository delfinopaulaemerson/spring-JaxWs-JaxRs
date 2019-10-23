package br.com.appservice.rest;

import java.io.Writer;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

import br.com.appservice.facade.UsuarioServiceFacade;
import br.com.appservice.model.User;

@Component("usuarioRest")
@Service("usuarioRest")
public class UsuarioRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioRest.class);
	
	@Autowired
	private UsuarioServiceFacade facade;
	
	@POST
	@Path("/inserirUsuario")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response inserirUsuario(@QueryParam("username")String username,@QueryParam("password")String password,@QueryParam("token")String token) {
		XStream xStream = new XStream(new JsonHierarchicalStreamDriver(){
			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			}
		});
		xStream.setMode(XStream.NO_REFERENCES);
		String msg = null;
		String json = null;
		String excessao = null;
		User user =  new User();
		try {
			if(!"dWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR".equals(token)){
				msg = "token incorreto.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			if ("".equals(username)) {
				msg = "preencha o nome.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			if ("".equals(password)) {
				msg = "preencha o password.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			user.setPassword(password);
			user.setUsername(username);
			user.setSucesso(" Usuário inserido com sucesso! ");
			this.facade.inserirUsuario(user);
			
			
			
		} catch (Exception e) {
			LOGGER.error(" Erro ao cadastrar o usuario "
					+ e.getMessage());
			excessao = " Erro ao cadastrar o usuario ";
			user.setExcessao(excessao);
			xStream.alias("usuarioAndroid", User.class);
			json = xStream.toXML(user);
			return Response.status(Status.OK).entity(json).type(MediaType.APPLICATION_JSON).build();
		}
		
		return Response.status(Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/findByIdUsuario")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findById(@QueryParam("token")String token,@QueryParam("idUsuario")String idUsuario) {
		XStream xStream = new XStream(new JsonHierarchicalStreamDriver(){
			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			}
		});
		xStream.setMode(XStream.NO_REFERENCES);
		String msg = null;
		String json = null;
		String excessao = null;
		User user = new User();
		try {
			
			if(!"dWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR".equals(token)){
				msg = "token incorreto.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			if("".equals(idUsuario)){
				msg = " idUsuario obrigatório. ";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			user = this.facade.findById(Long.valueOf(idUsuario));
			
			
		}  catch (Exception e) {
			LOGGER.error(" Erro ao recuperar o usuario "
					+ e.getMessage());
			excessao = " Erro ao recuperar o usuario ";
			user.setExcessao(excessao);
			xStream.alias("usuario",User.class);
			json = xStream.toXML(user);
			return Response.status(Status.OK).entity(json)
					.type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build();
	}
	
	
	
	@PUT
	@Path("/UpdateUsuario")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response UpdateUsuario(@QueryParam("idUsuario")String idUsuario,@QueryParam("username")String username,@QueryParam("password")String password,@QueryParam("token")String token) {
		XStream xStream = new XStream(new JsonHierarchicalStreamDriver(){
			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			}
		});
		xStream.setMode(XStream.NO_REFERENCES);
		String msg = null;
		String json = null;
		String excessao = null;
		User user = new User();
		try {
			if ("".equals(idUsuario)) {
				msg = "preencha o idUsuario.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			if(!"dWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR".equals(token)){
				msg = "token incorreto.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			if ("".equals(username)) {
				msg = "preencha o nome.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			if ("".equals(password)) {
				msg = "preencha o password.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			
			user.setId(Long.valueOf(idUsuario));
			user.setPassword(password);
			user.setUsername(username);
			user.setSucesso(" Usuário atualizado com sucesso! ");
			
			
			this.facade.upadateUsuario(user);
			
		} catch (Exception e) {
			LOGGER.error(" Erro ao atualizar ou usuario inexistente! "
					+ e.getMessage());
			excessao = " Erro ao atualizar ou usuario inexistente! ";
			user.setExcessao(excessao);
			user.setError(" Erro ao atualizar ou usuario inexistente! ");
			xStream.alias("usuario",User.class);
			json = xStream.toXML(user);
			return Response.status(Status.OK).entity(json)
					.type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("/deleteUsuario")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteUsuario(@QueryParam("token")String token,@QueryParam("idUsuario") String idUsuario) {
		XStream xStream = new XStream(new JsonHierarchicalStreamDriver(){
			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			}
		});
		xStream.setMode(XStream.NO_REFERENCES);
		String msg = null;
		String json = null;
		String excessao = null;
		User user = new User();
		try {
			if(!"dWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR".equals(token)){
				msg = "token incorreto.";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			if("".equals(idUsuario)){
				msg = " idUsuario obrigatório. ";
				xStream.alias("usuario", User.class);
				user.setError(msg);
				json = xStream.toXML(user);
				return Response.status(Status.OK).entity(json)
						.type(MediaType.APPLICATION_JSON).build();
			}
			
			this.facade.deleteUsuario(Long.valueOf(idUsuario));
			
			user.setSucesso(" Usuário foi deletado com suceso! ");

		} catch (Exception e) {
			LOGGER.error(" Erro ao deletar o usuario "
					+ e.getMessage());
			excessao = " Erro ao deletar o usuario ";
			user.setExcessao(excessao);
			xStream.alias("usuario",User.class);
			json = xStream.toXML(user);
			return Response.status(Status.OK).entity(json)
					.type(MediaType.APPLICATION_JSON).build();

		}
		return Response.status(Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build();
		
	}
	

	@WebMethod(exclude=true)
	public void setFacade(UsuarioServiceFacade facade) {
		this.facade = facade;
	}
	
}
