package com.example.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping({ "/session", "/stats" })
	public String showSession(HttpSession session, Model model) {
		if (session.getAttribute("id") == null)
			return "redirect:/Login";

		model.addAttribute("sessionId", session.getId());
		model.addAttribute("sessionNew", session.isNew());
		model.addAttribute("name", session.getAttribute("name"));
		return "session";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		if (session.getAttribute("id") == null)
			return "redirect:/Login";

		return "home";
	}
}
