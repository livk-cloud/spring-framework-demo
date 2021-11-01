package com.livk.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * LivkPage
 *
 * @author livk
 * @date 2021/8/19
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class LivkPage<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private int pageNum;

	private int pageSize;

	private final long total;

	private final List<T> list;

	private LivkPage(List<T> list) {
		this.list = list;
		if (list instanceof Page) {
			Page<List<T>> page = (Page<List<T>>) list;
			this.pageNum = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.total = page.getTotal();
		}
		else {
			this.total = list.size();
		}
	}

	public static <T> LivkPage<T> of(List<T> list) {
		return new LivkPage<>(list);
	}

	public static void start(Integer pageNum, Integer pageSize) {
		PageMethod.startPage(pageNum, pageSize);
	}

}
