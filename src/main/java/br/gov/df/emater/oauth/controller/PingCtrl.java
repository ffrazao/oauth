package br.gov.df.emater.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ping")
public class PingCtrl {

	@RequestMapping(method = RequestMethod.GET)
	private String getLogin() {
		return "pong.html";
	}

}
