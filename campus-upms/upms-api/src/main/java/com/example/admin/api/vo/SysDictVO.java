package com.example.admin.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "字典VO")
public class SysDictVO {

	@Schema(description = "字典ID", example = "1")
	private Long id;

	@Schema(description = "父字典ID", example = "0")
	private Long parentId;

	@Schema(description = "备注", example = "订单状态字典")
	private String remarks;

	@Schema(description = "字典键", example = "pending")
	private String dictKey;

	@Schema(description = "字典值", example = "待处理")
	private String dictValue;

	@Schema(description = "排序", example = "1")
	private Integer sortOrder;

	@Schema(description = "状态:0-禁用,1-启用", example = "1")
	private Integer status;

	@Schema(description = "创建时间")
	private LocalDateTime createdAt;

	@Schema(description = "父字典的值（用于显示分组名称）", example = "订单状态")
	private String parentDictValue;

	@Schema(description = "子字典列表")
	private List<SysDictVO> children;
}
