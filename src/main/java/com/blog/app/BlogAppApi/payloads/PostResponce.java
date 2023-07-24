package com.blog.app.BlogAppApi.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponce {
	
	private List<PostDto>content;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private long totalElements;
	
	private Integer totalPages;
	
	private boolean isLastPage;

}
