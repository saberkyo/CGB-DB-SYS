package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/doIndexUI")
	public String doIndexUI() {
		return "starter";
	}

//	@RequestMapping("/log/log_list")
//	public String doLogUI() {
//		return "sys/log_list";
//	}

	@RequestMapping("/doPageUI")
	public String doPageUI() {
		return "common/page";
	}

//	@RequestMapping("/menu/menu_list")
//	public String doMenuUI() {
//		return "sys/menu_list";
//	}

	@RequestMapping("{module}/{page}")
	public String doLoadUI(@PathVariable String page) {
		return "sys/" + page;
	}
}
