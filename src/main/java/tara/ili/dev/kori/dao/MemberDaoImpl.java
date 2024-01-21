package tara.ili.dev.kori.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tara.ili.dev.kori.dto.MemberDto;
import tara.ili.dev.kori.repository.MemberRepository;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	MemberRepository repository;
	
	@Override
	public int insertMember(MemberDto dto) {
		return repository.insertMember(dto);
	}

	@Override
	public MemberDto loginByDto(MemberDto dto) {
		return repository.loginByDto(dto);
	}

}
