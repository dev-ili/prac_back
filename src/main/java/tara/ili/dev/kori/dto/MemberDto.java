package tara.ili.dev.kori.dto;

public class MemberDto {
	private long mno;
	private String id;
	private String name;
	private String password;
	private String email;
	
	public MemberDto() {}
	public MemberDto(long mno, String id, String name, String password, String email) {
		this.mno = mno;
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	public long getMno() {
		return mno;
	}
	public void setMno(long mno) {
		this.mno = mno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
