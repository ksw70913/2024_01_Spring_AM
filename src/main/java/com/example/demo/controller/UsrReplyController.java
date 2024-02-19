package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrReplyController {

	@Autowired
	private Rq rq;

	@Autowired
	private ReplyService replyService;

	@RequestMapping("/usr/reply/doWriteReply")
	@ResponseBody
	public String doWriteReply(HttpServletRequest req, String relTypeCode, int relId, int memberId, String body) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (Ut.isNullOrEmpty(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력해주세요");
		}

		ResultData writeReplyRd = replyService.writeReply(relTypeCode, relId, memberId, body);

		int id = (int) writeReplyRd.getData1();

		return Ut.jsReplace(writeReplyRd.getResultCode(), writeReplyRd.getMsg(), "../article/detail?id=" + relId);

	}
}
