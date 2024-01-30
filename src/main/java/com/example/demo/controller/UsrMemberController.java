package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	int isLogined = 0;
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		if (Ut.isNullOrEmpty(loginId)) {
			return ResultData.from("F-1", Ut.f("%s(아이디)가 제대로 입력되지 않았습니다.", loginId));
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return ResultData.from("F-1", Ut.f("%s(비밀번호)가 제대로 입력되지 않았습니다.", loginPw));
		}
		if (Ut.isNullOrEmpty(name)) {
			return ResultData.from("F-1", Ut.f("%s(이름)이 제대로 입력되지 않았습니다.", name));
		}
		if (Ut.isNullOrEmpty(nickname)) {
			return ResultData.from("F-1", Ut.f("%s(닉네임)이 제대로 입력되지 않았습니다.", nickname));
		}
		if (Ut.isNullOrEmpty(cellphoneNum)) {
			return ResultData.from("F-1", Ut.f("%s(전화번호)가 제대로 입력되지 않았습니다.", cellphoneNum));
		}
		if (Ut.isNullOrEmpty(email)) {
			return ResultData.from("F-1", Ut.f("%s(이메일)이 제대로 입력되지 않았습니다.", email));
		}

		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (id == -1) {
			return ResultData.from("F-1", Ut.f("%s(아이디)가 이미 사용중입니다.", loginId));
		}

		if (id == -2) {
			return ResultData.from("F-1", Ut.f("이미 사용중인 이름(%s)과 이메일(%s)입니다", name, email));
		}

		Member member = memberService.getMember(id);

		return ResultData.from("S-1", Ut.f("%d번째 회원이 생성되었습니다..", id), member);
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public Object doLogin(String loginId, String loginPw) {
		if (memberService.checkLoginId(loginId) == 0) {
			return "아이디가 올바르지 않습니다.";
		}
		if (!memberService.checkLoginPw(loginId).equals(loginPw)) {
			return "비밀번호가 올바르지 않습니다.";
		}
		Member member = memberService.getMemberRow(loginId, loginPw);

		isLogined = 1;

		return member.getNickname() + "님 환영합니다!";
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		Member member = null;
		isLogined = 0;
		return "로그아웃 되었습니다.";
	}
}
