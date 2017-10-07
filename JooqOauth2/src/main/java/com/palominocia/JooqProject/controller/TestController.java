package com.palominocia.JooqProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palominocia.JooqProject.services.UsuariosService;

@RestController
public class TestController {
	@Autowired
	UsuariosService abc;
	
	@RequestMapping("/api")
	public String hola(){
		abc.test();
		return "DDD";
	}
}
