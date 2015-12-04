package com.example.myapp;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		return "Login";
	}

	@RequestMapping(value = "/LoginCheck", method = RequestMethod.POST)
	public String loginCheck(@RequestParam String username,
			@RequestParam String password, HttpSession session) {
		
		if (password.equals("pass")) {
			session.setAttribute("id", username);
			return "home";
		}
		return "Login";
	}
}
