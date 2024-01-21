package tara.ili.dev.kori.repository;

import java.util.List;

import tara.ili.dev.kori.dto.MemberDto;


public interface MemberRepository {

	int insertMember(MemberDto dto);
	MemberDto findMemberByEmail(String email);
	MemberDto findMemberById(String id);
	MemberDto findMemberByMno(long mno);
	List<MemberDto> findMemberByName(String name);
	List<MemberDto> findMemberAll();
	MemberDto loginByDto(MemberDto dto);
}
