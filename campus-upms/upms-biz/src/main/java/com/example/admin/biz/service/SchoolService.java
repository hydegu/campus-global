package com.example.admin.biz.service;

import com.example.admin.api.dto.SchoolAddDTO;
import com.example.admin.api.dto.SchoolQueryDTO;
import com.example.admin.api.dto.SchoolUpdateDTO;
import com.example.admin.api.vo.SysSchoolVO;
import com.example.common.mybatis.utils.PageResult;

public interface SchoolService {

    /**
     * 新增学校
     * @param dto 新增学校请求DTO
     */
    void addSchool(SchoolAddDTO dto);

    /**
     * 更新学校信息
     * @param id 学校ID
     * @param dto 学校信息更新DTO
     */
    void updateSchool(Long id, SchoolUpdateDTO dto);

    /**
     * 删除学校
     * @param id 学校ID
     */
    void deleteSchool(Long id);

    /**
     * 查询学校详情
     * @param id 学校ID
     * @return 学校信息响应VO
     */
    SysSchoolVO getSchoolById(Long id);

    /**
     * 分页查询学校列表
     * @param dto 学校列表查询DTO
     * @return 学校信息分页结果
     */
    PageResult<SysSchoolVO> listSchools(SchoolQueryDTO dto);
}