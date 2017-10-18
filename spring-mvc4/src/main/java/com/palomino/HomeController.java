package com.palomino;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String hello1(Model model) {

		model.addAttribute("name", "Jonathan Palomino");

		return "welcome";
	}
}
