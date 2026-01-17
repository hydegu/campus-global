package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.entity.AuditRecord;
import com.example.admin.biz.dto.AuditDTO;
import com.example.admin.biz.dto.MerchantSettleInQueryDTO;
import com.example.admin.biz.dto.PartnerAuditQueryDTO;
import com.example.admin.biz.dto.RiderApplyQueryDTO;
import com.example.admin.biz.dto.ServiceStaffAuditQueryDTO;
import com.example.admin.biz.vo.MerchantSettleInVO;
import com.example.admin.biz.vo.PartnerAuditVO;
import com.example.admin.biz.vo.RiderApplyVO;
import com.example.admin.biz.vo.ServiceStaffAuditVO;

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
