package com.tintmarvel.marvel.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "index";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "register";

	}

	@PostMapping("/register/save")
	public String registration(@ModelAttribute("user") User user, Model m) {

		User temp = userRepo.findByEmail(user.getEmail());
		if (temp != null) {
			return "redirect:/register?error";
		}

		User u = userService.saveUser(user);
		if (u != null) {
			return "redirect:/register?success";
		}
		return "redirect:/register?error";

	}

	@PostMapping("/userlogin")
	public String userLogin(@ModelAttribute("user") User user, Model m, HttpSession session) {

		User temp = userRepo.findByEmail(user.getEmail());
		if (temp == null) {
			return "redirect:/login?error";
		}
		String pass = userService.doHashing(user.getPassword());
		if (pass.equals(temp.getPassword())) {
			String uname = temp.getEmail();
			session.setAttribute("user", uname);
			return "redirect:/";
		}

		return "redirect:/login?error";
	}

	@GetMapping("/userlogout")
	public String userLogout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/login";
	}

}
