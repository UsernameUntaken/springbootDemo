package com.zkhy.officialWebsite.common.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageReq <T>{

	private T enity; 
	private Integer pageNum;
	private Integer pageSize;
}
