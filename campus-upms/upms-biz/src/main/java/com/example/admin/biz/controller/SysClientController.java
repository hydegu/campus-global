package com.example.admin.biz.controller;

import com.example.admin.api.entity.SysOauthClientDetails;
import com.example.admin.biz.service.SysOauthClientDetailsService;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@StandardApiResponses
@Tag(name = "客户端管理", description = "OAuth2客户端信息管理")
public class SysClientController {

	private final SysOauthClientDetailsService sysOauthClientDetailsService;

	@GetMapping("/getClientDetailsById/{clientId}")
	@Operation(summary = "根据客户端ID查询客户端详情", description = "通过clientId查询客户端信息，用于OAuth2认证")
	public Result<SysOauthClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId) {
		SysOauthClientDetails clientDetails = sysOauthClientDetailsService.getClientDetailsById(clientId);
		return Result.ok(clientDetails);
	}
}
