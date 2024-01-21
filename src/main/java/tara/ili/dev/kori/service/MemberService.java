package tara.ili.dev.kori.service;

import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpSession;

public interface MemberService {

	int signup(JSONObject data);

	JSONObject login(JSONObject data, HttpSession httpSession);

}
