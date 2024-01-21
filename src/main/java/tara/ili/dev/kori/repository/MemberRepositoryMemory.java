package tara.ili.dev.kori.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import tara.ili.dev.kori.dto.MemberDto;

@Repository
public class MemberRepositoryMemory implements MemberRepository {
	private static final Logger log = LoggerFactory.getLogger(MemberRepositoryMemory.class);
	
    private static Map<Long, MemberDto> store = new HashMap<Long, MemberDto>();
    private static long sequence = 0L;
    
	@Override
	public int insertMember(MemberDto dto) {
		int res = 0;
		if(findMemberById(dto.getId())==null && findMemberByEmail(dto.getEmail())==null) {
			dto.setMno(++sequence);
			store.put(dto.getMno(), dto);
			res = 1;
		}
		return res;
	}
	@Override
	public MemberDto findMemberByEmail(String email) {
		MemberDto res = null;
		for(Map.Entry<Long, MemberDto> entry : store.entrySet()) {
			long key = entry.getKey();
			MemberDto value = entry.getValue();
			log.info("key={}, email={}", key, value.getEmail());
			if(!value.getEmail().isEmpty() && email.equals(value.getEmail())) {
				res = value;
				break;
			}
		}
		
		return res;
	}
	@Override
	public MemberDto findMemberById(String id) {
		MemberDto res = null;
		for(Map.Entry<Long, MemberDto> entry : store.entrySet()) {
			long key = entry.getKey();
			MemberDto value = entry.getValue();
			log.info("key={}, id={}", key, value.getId());
			if(!value.getId().isEmpty() && id.equals(value.getId())) {
				res = value;
				break;
			}
		}
		
		return res;
	}
	@Override
	public List<MemberDto> findMemberByName(String name) {
		List<MemberDto> res = new ArrayList<MemberDto>();
		for(Map.Entry<Long, MemberDto> entry : store.entrySet()) {
			long key = entry.getKey();
			MemberDto value = entry.getValue();
			if(!value.getName().isEmpty() && name.equals(value.getName())) {
				res.add(value);
			}
		}
		return res;
	}
	@Override
	public List<MemberDto> findMemberAll() {
		return new ArrayList<MemberDto>(store.values());
	}
	@Override
	public MemberDto findMemberByMno(long mno) {
//		Optional.ofNullable(store.get(mno));
		return store.get(mno);
	}
	@Override
	public MemberDto loginByDto(MemberDto dto) {
		MemberDto memberDto = null;
		for(Map.Entry<Long, MemberDto> entry : store.entrySet()) {
			long key = entry.getKey();
			MemberDto value = entry.getValue();
			if(dto.getId().equals(value.getId()) && dto.getPassword().equals(value.getPassword())) {
				memberDto = value;
				break;
			}
		}
		return memberDto;
	}
    
}
