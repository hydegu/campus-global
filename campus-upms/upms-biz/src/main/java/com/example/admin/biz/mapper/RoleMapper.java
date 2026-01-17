package com.example.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.entity.Role;
import com.example.admin.biz.dto.RoleQueryDTO;
import com.example.admin.biz.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	Page<RoleVO> selectRolePageWithMenus(Page<RoleVO> page, @Param("query") RoleQueryDTO queryDTO);
}
