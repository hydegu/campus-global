package com.example.admin.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "合伙人树形结构结果")
public class PartnerTreeResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "合伙人树形列表（根节点及其所有子节点）")
	private List<UserPartnerListVO> tree;

	@Schema(description = "合伙人总数")
	private Long total;
}