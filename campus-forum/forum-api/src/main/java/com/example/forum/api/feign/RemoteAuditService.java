package com.example.forum.api.feign;

import com.example.admin.api.dto.CreateAuditDTO;
import com.example.admin.api.entity.AuditRecord;
import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteAuditService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteAuditService {
        /**
         * 创建审核记录
         */
        @PostMapping("/api/audit/create")
        Result<AuditRecord> createAuditRecord(@RequestBody CreateAuditDTO dto);
}
