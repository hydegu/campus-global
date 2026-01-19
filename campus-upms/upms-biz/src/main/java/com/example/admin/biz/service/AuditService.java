package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.entity.AuditRecord;
import com.example.admin.api.dto.AuditDTO;
import com.example.admin.api.dto.MerchantSettleInQueryDTO;
import com.example.admin.api.dto.PartnerAuditQueryDTO;
import com.example.admin.api.dto.RiderApplyQueryDTO;
import com.example.admin.api.dto.ServiceStaffAuditQueryDTO;
import com.example.admin.api.vo.MerchantSettleInVO;
import com.example.admin.api.vo.PartnerAuditVO;
import com.example.admin.api.vo.RiderApplyVO;
import com.example.admin.api.vo.ServiceStaffAuditVO;

public interface AuditService {

	void auditPartner(Long id, AuditDTO auditDTO);

	void auditMerchant(Long id, AuditDTO auditDTO);

	void auditStaff(Long id, AuditDTO auditDTO);

	void auditRider(Long id, AuditDTO auditDTO);

	AuditRecord getAuditRecordByBizTypeAndId(String bizType, Long bizId);

	Page<RiderApplyVO> listRiderApply(RiderApplyQueryDTO queryDTO);

	Page<MerchantSettleInVO> listMerchantSettleIn(MerchantSettleInQueryDTO queryDTO);

	Page<ServiceStaffAuditVO> listServiceStaffAudit(ServiceStaffAuditQueryDTO queryDTO);

	Page<PartnerAuditVO> listPartnerAudit(PartnerAuditQueryDTO queryDTO);
}
