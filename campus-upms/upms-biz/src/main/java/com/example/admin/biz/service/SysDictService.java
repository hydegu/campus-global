package com.example.admin.biz.service;

import com.example.admin.api.dto.SysDictAddDTO;
import com.example.admin.api.dto.SysDictPartialUpdateDTO;
import com.example.admin.api.vo.SysDictVO;

import java.util.List;

public interface SysDictService {

	void addDict(SysDictAddDTO dto);

	void updateDict(Long id, SysDictPartialUpdateDTO dto);

	void deleteDict(Long id);

	List<SysDictVO> getDictTree(Long parentId);
}
