package com.example.aop;

import com.example.redis.ActionHistoryRepo;
import com.example.redis.RankDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private Action act;

	@Autowired
	private ActionHistoryRepo<String, String> saver;

	@RequestMapping(value = { "/" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(1, 1, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = { "/act1" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String test_Aop(Locale locale, Model model) {
		this.act.doAct();
		try {
			model.addAttribute("MethodName",
					this.act.getClass().getMethod("doAct", null).getName());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return "Act";
	}

	@RequestMapping(value = { "/act2" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String test_Aop2(Locale locale, Model model) {
		this.act.doActString("Hello World");
		try {
			model.addAttribute(
					"MethodName",
					this.act.getClass()
							.getMethod("doActString",
									new Class[] { String.class }).getName());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return "Act";
	}

	@RequestMapping(value = { "/act3" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String test_Aop3(Locale locale, Model model) {
		this.act.doActWithParam("Hello World with param", 124);
		Class[] classes = { String.class, Integer.TYPE };
		try {
			model.addAttribute("MethodName",
					this.act.getClass().getMethod("doActWithParam", classes)
							.getName());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return "Act";
	}

	@RequestMapping(value = { "/rank" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String testHistory(Locale locale, Model model) {
		model.addAttribute("RankList", this.saver.getRank());

		return "rank";
	}

	@RequestMapping(value = { "/json" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String testJson(Locale locale, Model model) {
		GsonBuilder gb = new GsonBuilder();
		Gson sonson = gb.create();
		String json = sonson.toJson(this.saver.getRank());
		sonson.fromJson(json, RankDto.class);
		return sonson.toJson(this.saver.getRank());
	}
}

