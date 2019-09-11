package com.zkhy.officialWebsite.common.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRes <T>{

	private long total;
	private List<T> list;
}
