package tara.ili.dev.kori.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tara.ili.dev.kori.dao.MemberDao;
import tara.ili.dev.kori.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;
	
	@Override
	public int signup(JSONObject data) {
		MemberDto dto = new MemberDto();
		dto.setId((String) data.get("id"));
		dto.setPassword((String) data.get("password"));
		dto.setName((String) data.get("name"));
		dto.setEmail((String) data.get("email"));
		
		int res = 0;
		if(!(dto.getId().isEmpty() || dto.getEmail().isEmpty() || dto.getPassword().isEmpty())) {
			res =  dao.insertMember(dto);
		}
		
		return res;
	}

	@Override
	public JSONObject login(JSONObject data, HttpSession httpSession) {
		JSONObject memberJson = new JSONObject();
		
		MemberDto dto = new MemberDto();
		String id = (String) data.get("id");
		String password = (String) data.get("password");
		dto.setId(id);
		dto.setPassword(password);
		
		if(!(id.isEmpty() || password.isEmpty())) {
			MemberDto memberDto = dao.loginByDto(dto);
			
			if(memberDto != null) {
				httpSession.setAttribute("memberDto", memberDto);
				
				id = memberDto.getId();
				String name = memberDto.getName();
				String email = memberDto.getEmail();
				memberJson.put("name", name);
			} else {
				memberJson = null;
			}
		}
		
		return memberJson;
	}

}
