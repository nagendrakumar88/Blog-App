package com.blog.app.BlogAppApi.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private String commId;
	
	private String content;
}
