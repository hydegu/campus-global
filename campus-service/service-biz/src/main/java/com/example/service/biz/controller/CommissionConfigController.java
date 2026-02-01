package com.example.service.biz.controller;

import com.example.common.core.util.Result;
import com.example.service.api.dto.CommissionConfigAddDTO;
import com.example.service.api.dto.CommissionConfigUpdateDTO;
import com.example.service.biz.service.CommissionConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 分佣配置管理Controller
 */
@RestController
@RequestMapping("/api/commission-config")
@RequiredArgsConstructor
@Validated
@Tag(name = "分佣配置管理", description = "分佣配置的新增和更新功能")
public class CommissionConfigController {

    private final CommissionConfigService commissionConfigService;

    @PostMapping
    @Operation(summary = "新增分佣配置", description = "创建新的分佣配置。必填字段：配置类型、分佣比例。configType=1（全局默认）时categoryId可为null，其他类型必须提供categoryId。")
    public Result<Void> addCommissionConfig(@Valid @RequestBody CommissionConfigAddDTO dto) {
        commissionConfigService.addCommissionConfig(dto);
        return Result.ok();
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新分佣配置", description = "更新指定的分佣配置信息。可更新字段：服务分类ID、配置类型、分佣比例、状态。采用软删除旧记录并创建新记录的方式，保留历史记录。")
    public Result<Void> updateCommissionConfig(@PathVariable Long id, @Valid @RequestBody CommissionConfigUpdateDTO dto) {
        commissionConfigService.updateCommissionConfig(id, dto);
        return Result.ok();
    }
}