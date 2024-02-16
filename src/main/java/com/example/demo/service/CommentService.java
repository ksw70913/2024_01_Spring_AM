package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.CommentRepository;

public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

}
