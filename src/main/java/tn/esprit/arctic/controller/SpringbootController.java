package tn.esprit.arctic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class SpringbootController {

	@GetMapping("/docker")
	public String index(){
		return "Spring boot docker exemple";
	}
}
