package bk.s3browser.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * The only purpose of this root controller is to redirect the user on root context 
 * access to the right home page
 * 
 * @author Biju Kunjummen
 *
 */
@Controller
public class RootController {
	@RequestMapping("/")
	public String onRootAccess() {
		return "redirect:/home";
	}

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
}
