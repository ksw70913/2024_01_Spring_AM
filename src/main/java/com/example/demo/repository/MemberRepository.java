package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {
	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);

	@Select("""
			SELECT *
			FROM `member`
			WHERE name = #{name}
			AND email = #{email}
			""")
	public Member getMemberByNameAndEmail(String name, String email);

//	@Insert("""
//			INSERT INTO
//			`member` SET
//			regDate = NOW(),
//			updateDate = NOW(),
//			loginId = #{loginId},
//			loginPw = #{loginPw},
//			`name` = #{name},
//			nickname = #{nickname},
//			cellphoneNum = #{cellphoneNum},
//			email = #{email}
//			""")
	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("SELECT * FROM `member` WHERE id = #{id}")
	public Member getMember(int id);

	@Select("""
			SELECT count(*)
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public int checkLoginId(String loginId);

	@Select("""
			SELECT loginPw
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public String checkLoginPw(String loginId);

	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId}
			AND loginPw = #{loginPw}
			""")
	public Member getMemberRow(String loginId, String loginPw);

}