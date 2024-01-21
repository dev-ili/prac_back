package tara.ili.dev.kori.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tara.ili.dev.kori.service.MemberService;


@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class MemberController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	@GetMapping("/kori")
	public String Home(Model model) {
		String res = "test";
		
		log.info(res);
		System.out.println(res);
		return res;
	}
	
	@PostMapping("/member/signup")
	public String signup(@RequestBody JSONObject data) {
		JSONObject result = new JSONObject();
		log.info(data.toJSONString());
		
		String res = "succ";
		int errCode = 0;
		String errMsg = "succ";
		try {
			res = service.signup(data) == 1 ? res : "fail";
		} catch (Exception e) {
			errCode = -1;
			errMsg = e.getMessage();
		}
		result.put("res", res);
		result.put("errCode", errCode);
		result.put("errMsg", errMsg);
		
		return result.toJSONString();
	}
	
	@PostMapping("/member/login")
	public String login(@RequestBody JSONObject data, HttpSession httpSession, HttpServletResponse response) {
		ResponseCookie cookie = ResponseCookie.from("sameSiteCookie", "sameSiteCookieValue")
				.sameSite("Strict")
				.build();
		response.setHeader("Set-Cookie", cookie.toString());
		
		JSONObject result = new JSONObject();
		log.info(data.toJSONString());
		
		JSONObject memberJson = null;
		String res = "succ";
		int errCode = 0;
		String errMsg = "succ";
		try {
			memberJson = service.login(data, httpSession);
		} catch (Exception e) {
			errCode = -1;
			errMsg = e.getMessage();
		}
		res = memberJson==null ? "fail" : "succ";
		
		result.put("memberDto", memberJson);
		result.put("res", res);
		result.put("errCode", errCode);
		result.put("errMsg", errMsg);
		
		return result.toJSONString();
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession httpSession, HttpServletResponse response) {
		ResponseCookie cookie = ResponseCookie.from("sameSiteCookie", "sameSiteCookieValue")
				.sameSite("Strict")
				.build();
		response.setHeader("Set-Cookie", cookie.toString());
		httpSession.invalidate();
		
		JSONObject result = new JSONObject();
		result.put("res", "logout");
		
		return result.toJSONString();
	}
}
