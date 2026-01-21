package com.example.admin.biz.controller;

import com.example.admin.api.dto.SchoolAddDTO;
import com.example.admin.api.dto.SchoolQueryDTO;
import com.example.admin.api.dto.SchoolUpdateDTO;
import com.example.admin.biz.service.SchoolService;
import com.example.admin.api.vo.SysSchoolVO;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.common.mybatis.utils.PageResult;
import com.example.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/school")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "学校管理", description = "学校信息的增删改查功能")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    @Operation(summary = "新增学校", description = "创建新的学校。需要提供学校名称、地址等基本信息。需要权限：system:school:add")
    public Result<Void> addSchool(@Valid @RequestBody SchoolAddDTO dto) {
        schoolService.addSchool(dto);
        return Result.ok();
    }

    @PutMapping("/{id}")
    @Operation(summary = "部分更新学校信息", description = "更新学校信息，只更新传入的字段。可以更新学校名称、状态等信息。需要权限：system:school:edit")
    public Result<Void> updateSchool(@PathVariable Long id, @Valid @RequestBody SchoolUpdateDTO dto) {
        schoolService.updateSchool(id, dto);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除学校", description = "删除指定的学校。需要权限：system:school:delete")
    public Result<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return Result.ok();
    }

    @GetMapping("/{id}")
    @Inner
    @Operation(summary = "查询学校详情", description = "根据ID查询学校的详细信息")
    public Result<SysSchoolVO> getSchoolById(@PathVariable Long id) {
        SysSchoolVO vo = schoolService.getSchoolById(id);
        return Result.ok(vo);
    }

    @GetMapping("/list")
    @Operation(summary = "分页查询学校列表", description = "根据查询条件分页查询学校列表，支持按学校名称、状态等条件筛选。需要权限：system:school:list")
    public Result<PageResult<SysSchoolVO>> listSchools(@Valid @ParameterObject SchoolQueryDTO dto) {
        PageResult<SysSchoolVO> pageResult = schoolService.listSchools(dto);
        return Result.ok(pageResult);
    }
}