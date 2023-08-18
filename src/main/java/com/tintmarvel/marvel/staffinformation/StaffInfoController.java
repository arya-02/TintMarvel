package com.tintmarvel.marvel.staffinformation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class StaffInfoController {

	@GetMapping("/department-select")
	public String departmentSelect(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "depselect";
	}

}
