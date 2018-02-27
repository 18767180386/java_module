package com.aiiju.store.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aboutus")
public class AboutUsController {

	@RequestMapping("/ui")
	public String UI() {
		return "us";
	}
}
