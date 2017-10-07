package com.palominocia.JooqProject.services;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palominocia.JooqProject.modelo.Keys;
import com.palominocia.JooqProject.modelo.tables.daos.UsuarioTransaccionDao;
import com.palominocia.JooqProject.modelo.tables.daos.UsuariosDao;
import com.palominocia.JooqProject.modelo.tables.pojos.UsuarioTransaccion;

@Service
public class UsuariosService {
	@Autowired
	DSLContext dsl;
	
	public void test(){
		String usuario = crearUsuario();
		asignarTransaccion(usuario);
	}

	private void asignarTransaccion(String usuario) {
		com.palominocia.JooqProject.modelo.tables.pojos.UsuarioTransaccion ut = new UsuarioTransaccion();
		ut.setDireccionIp("123456");
		ut.setIdUsuario(usuario);
		
		UsuarioTransaccionDao usertx = new UsuarioTransaccionDao(dsl.configuration());
		usertx.insert(ut);
	}

	private String crearUsuario() {
		com.palominocia.JooqProject.modelo.tables.pojos.Usuarios user = new com.palominocia.JooqProject.modelo.tables.pojos.Usuarios();
		user.setNombre("Pruebas");
		user.setIdUsuario("usuario"+Math.random());
		
		UsuariosDao hhh = new UsuariosDao(dsl.configuration());
		hhh.insert(user);
		
		return user.getIdUsuario();
	}
}
