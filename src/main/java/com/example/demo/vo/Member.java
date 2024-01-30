package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	int id;
	String loginId;
	String loginPw;
	String name;
	String nickname;
	int cellphoneNum;
	String email;

}
