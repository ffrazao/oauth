package br.gov.df.emater.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginCtrl {

	@RequestMapping(method = RequestMethod.GET)
	private String getLogin() {
		return "login.html";
	}

}
