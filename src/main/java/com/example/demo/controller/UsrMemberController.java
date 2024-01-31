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
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요");
		}

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return joinRd;
		}

		Member member = memberService.getMember((int) joinRd.getData1());

		return ResultData.newData(joinRd, member);
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(String loginId, String loginPw) {
		if (isLogined == 1) {
			return ResultData.from("F-1", "이미 로그인 중입니다.");
		}
		if (memberService.checkLoginId(loginId) == 0) {
			return ResultData.from("F-2", Ut.f("%s(아이디)가 올바르지 않습니다..", loginId));
		}
		if (!memberService.checkLoginPw(loginId).equals(loginPw)) {
			return ResultData.from("F-3", Ut.f("%s(비밀번호)가 올바르지 않습니다..", loginPw));
		}
		Member member = memberService.getMemberRow(loginId, loginPw);

		isLogined = 1;
		return ResultData.from("S-1", Ut.f("%s님 환영합니다!", member.getNickname()), member.getName());
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout() {
		if (isLogined == 0) {
			return ResultData.from("F-1", "로그인이 되어있지 않습니다.");
		}
		Member member = null;
		isLogined = 0;
		return ResultData.from("S-1", "로그아웃 되었습니다.");
	}
}
