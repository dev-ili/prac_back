package tara.ili.dev.kori.dao;

import tara.ili.dev.kori.dto.MemberDto;

public interface MemberDao {

	int insertMember(MemberDto dto);

	MemberDto loginByDto(MemberDto dto);

}
