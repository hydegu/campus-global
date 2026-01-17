package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.entity.SysDict;
import com.example.admin.api.dto.SysDictAddDTO;
import com.example.admin.api.dto.SysDictPartialUpdateDTO;
import com.example.admin.biz.mapper.SysDictMapper;
import com.example.admin.biz.service.SysDictService;
import com.example.admin.api.vo.SysDictVO;
import com.example.common.core.exception.CheckedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	@Override
	public void addDict(SysDictAddDTO dto) {
		log.info("新增字典，dictKey: {}, dictValue: {}", dto.getDictKey(), dto.getDictValue());

		validateDictKeyAndParentId(dto.getDictKey(), dto.getParentId());

		if (dto.getParentId() != null) {
			SysDict parentDict = getById(dto.getParentId());
			if (parentDict == null) {
				throw new CheckedException("父字典不存在");
			}
		}

		if (dto.getDictKey() != null) {
			LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
			wrapper.eq(SysDict::getDictKey, dto.getDictKey());
			long count = count(wrapper);
			if (count > 0) {
				throw new CheckedException("字典键已存在");
			}
		}

		SysDict dict = new SysDict();
		dict.setParentId(dto.getParentId());
		dict.setDictKey(dto.getDictKey());
		dict.setDictValue(dto.getDictValue());
		dict.setRemarks(dto.getRemarks());
		dict.setSortOrder(dto.getSortOrder());
		dict.setStatus(dto.getStatus());
		save(dict);

		log.info("新增字典成功，id: {}", dict.getId());
	}

	@Override
	public void updateDict(Long id, SysDictPartialUpdateDTO dto) {
		log.info("更新字典，id: {}, dictKey: {}, dictValue: {}", id, dto.getDictKey(), dto.getDictValue());

		SysDict dict = getById(id);
		if (dict == null) {
			throw new CheckedException("字典不存在");
		}

		validateDictKeyAndParentId(dto.getDictKey(), dto.getParentId());

		if (dto.getParentId() != null) {
			SysDict parentDict = getById(dto.getParentId());
			if (parentDict == null) {
				throw new CheckedException("父字典不存在");
			}
		}

		if (dto.getDictKey() != null && !dto.getDictKey().equals(dict.getDictKey())) {
			LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
			wrapper.eq(SysDict::getDictKey, dto.getDictKey());
			wrapper.ne(SysDict::getId, id);
			long count = count(wrapper);
			if (count > 0) {
				throw new CheckedException("字典键已存在");
			}
		}

		if (dto.getParentId() != null) {
			dict.setParentId(dto.getParentId());
		}
		if (dto.getDictKey() != null) {
			dict.setDictKey(dto.getDictKey());
		}
		if (dto.getDictValue() != null) {
			dict.setDictValue(dto.getDictValue());
		}
		if (dto.getRemarks() != null) {
			dict.setRemarks(dto.getRemarks());
		}
		if (dto.getSortOrder() != null) {
			dict.setSortOrder(dto.getSortOrder());
		}
		if (dto.getStatus() != null) {
			dict.setStatus(dto.getStatus());
		}
		updateById(dict);

		log.info("更新字典成功，id: {}", id);
	}

	@Override
	public void deleteDict(Long id) {
		log.info("删除字典，id: {}", id);

		SysDict dict = getById(id);
		if (dict == null) {
			throw new CheckedException("字典不存在");
		}

		LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(SysDict::getParentId, id);
		long childCount = count(wrapper);
		if (childCount > 0) {
			throw new CheckedException("存在子字典，无法删除");
		}

		removeById(id);

		log.info("删除字典成功，id: {}", id);
	}

	@Override
	public List<SysDictVO> getDictTree(Long parentId) {
		log.info("查询字典树形结构，parentId: {}", parentId);

		SysDict parentDict = getById(parentId);
		if (parentDict == null) {
			throw new CheckedException("字典不存在");
		}

		LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(SysDict::getParentId, parentId);
		wrapper.orderByAsc(SysDict::getSortOrder);
		List<SysDict> childDicts = list(wrapper);

		List<SysDictVO> children = new ArrayList<>();
		for (SysDict childDict : childDicts) {
			SysDictVO childVO = convertToVO(childDict);
			childVO.setParentDictValue(parentDict.getDictValue());
			children.add(childVO);
		}

		SysDictVO parentVO = convertToVO(parentDict);
		parentVO.setChildren(children);

		log.info("查询字典树形结构成功，parentId: {}, 子节点数量: {}", parentId, children.size());
		return List.of(parentVO);
	}

	private void validateDictKeyAndParentId(String dictKey, Long parentId) {
		boolean isDictKeyNull = dictKey == null;
		boolean isParentIdNull = parentId == null;

		if (isDictKeyNull && !isParentIdNull) {
			throw new CheckedException("dictKey为null时，parentId也必须为null");
		}
		if (!isDictKeyNull && isParentIdNull) {
			throw new CheckedException("dictKey不为null时，parentId也不能为null");
		}
	}

	private SysDictVO convertToVO(SysDict dict) {
		SysDictVO vo = new SysDictVO();
		vo.setId(dict.getId());
		vo.setParentId(dict.getParentId());
		vo.setRemarks(dict.getRemarks());
		vo.setDictKey(dict.getDictKey());
		vo.setDictValue(dict.getDictValue());
		vo.setSortOrder(dict.getSortOrder());
		vo.setStatus(dict.getStatus());
		vo.setCreatedAt(dict.getCreateAt());
		return vo;
	}
}
