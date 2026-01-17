package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.api.entity.Address;
import com.example.admin.api.entity.AuditRecord;
import com.example.admin.api.entity.BaseUser;
import com.example.admin.api.entity.SysSchool;
import com.example.admin.api.entity.UserApp;
import com.example.admin.api.entity.UserMch;
import com.example.admin.api.entity.UserPartner;
import com.example.admin.api.entity.UserRider;
import com.example.admin.api.dto.AuditDTO;
import com.example.admin.api.dto.MerchantSettleInQueryDTO;
import com.example.admin.api.dto.PartnerAuditQueryDTO;
import com.example.admin.api.dto.RiderApplyQueryDTO;
import com.example.admin.api.dto.ServiceStaffAuditQueryDTO;
import com.example.admin.biz.mapper.AuditRecordMapper;
import com.example.admin.biz.mapper.AddressMapper;
import com.example.admin.biz.mapper.BaseUserMapper;
import com.example.admin.biz.mapper.SysSchoolMapper;
import com.example.admin.biz.mapper.UserAppMapper;
import com.example.admin.biz.mapper.UserMchMapper;
import com.example.admin.biz.mapper.UserPartnerMapper;
import com.example.admin.biz.mapper.UserRiderMapper;
import com.example.admin.biz.service.AuditService;
import com.example.admin.api.vo.MerchantSettleInVO;
import com.example.admin.api.vo.PartnerAuditVO;
import com.example.admin.api.vo.RiderApplyVO;
import com.example.admin.api.vo.ServiceStaffAuditVO;
import com.example.common.core.enums.AuditStatus;
import com.example.common.core.enums.UserStatus;
import com.example.common.core.exception.BusinessException;
import com.example.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

	private final AuditRecordMapper auditRecordMapper;
	private final BaseUserMapper baseUserMapper;
	private final UserPartnerMapper userPartnerMapper;
	private final UserMchMapper userMchMapper;
	private final UserAppMapper userAppMapper;
	private final UserRiderMapper userRiderMapper;
	private final AddressMapper addressMapper;
	private final SysSchoolMapper sysSchoolMapper;

	private static final String BIZ_TYPE_PARTNER = "PARTNER_APPLY";
	private static final String BIZ_TYPE_MERCHANT = "MERCHANT_SETTLE";
	private static final String BIZ_TYPE_STAFF = "STAFF_APPLY";
	private static final String BIZ_TYPE_RIDER = "RIDER_APPLY";

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void auditPartner(Long id, AuditDTO auditDTO) {
		validateAuditParams(id, auditDTO);

		UserPartner partner = userPartnerMapper.selectById(id);
		if (partner == null) {
			throw new BusinessException("PARTNER_NOT_FOUND", "合伙人不存在");
		}

		AuditRecord auditRecord = auditRecordMapper.selectById(partner.getAuditId());
		if (auditRecord == null) {
			throw new BusinessException("AUDIT_RECORD_NOT_FOUND", "审核记录不存在");
		}

		validateAuditStatus(auditRecord.getStatus());

		Long auditorId = SecurityUtils.getCurrentUserId();
		String auditorName = SecurityUtils.getCurrentUsername();

		auditRecord.setStatus(auditDTO.getAuditStatus());
		auditRecord.setRemark(auditDTO.getAuditOpinion());
		auditRecord.setAuditorId(auditorId);
		auditRecordMapper.updateById(auditRecord);

		BaseUser baseUser = baseUserMapper.selectById(partner.getBaseUserId());
		if (baseUser != null) {
			baseUser.setStatus(auditDTO.getAuditStatus().equals(AuditStatus.APPROVED.getCode()) ? 
				UserStatus.ENABLED.getCode() : UserStatus.DISABLED.getCode());
			baseUserMapper.updateById(baseUser);
		}

		log.info("合伙人审核完成，合伙人ID：{}，审核状态：{}", id, auditDTO.getAuditStatus());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void auditMerchant(Long id, AuditDTO auditDTO) {
		validateAuditParams(id, auditDTO);

		UserMch merchant = userMchMapper.selectById(id);
		if (merchant == null) {
			throw new BusinessException("MERCHANT_NOT_FOUND", "商家不存在");
		}

		AuditRecord auditRecord = auditRecordMapper.selectById(merchant.getAuditId());
		if (auditRecord == null) {
			throw new BusinessException("AUDIT_RECORD_NOT_FOUND", "审核记录不存在");
		}

		validateAuditStatus(auditRecord.getStatus());

		Long auditorId = SecurityUtils.getCurrentUserId();
		String auditorName = SecurityUtils.getCurrentUsername();

		auditRecord.setStatus(auditDTO.getAuditStatus());
		auditRecord.setRemark(auditDTO.getAuditOpinion());
		auditRecord.setAuditorId(auditorId);
		auditRecordMapper.updateById(auditRecord);

		BaseUser baseUser = baseUserMapper.selectById(merchant.getBaseUserId());
		if (baseUser != null) {
			baseUser.setStatus(auditDTO.getAuditStatus().equals(AuditStatus.APPROVED.getCode()) ? 
				UserStatus.ENABLED.getCode() : UserStatus.DISABLED.getCode());
			baseUserMapper.updateById(baseUser);
		}

		log.info("商家审核完成，商家ID：{}，审核状态：{}", id, auditDTO.getAuditStatus());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void auditStaff(Long id, AuditDTO auditDTO) {
		validateAuditParams(id, auditDTO);

		UserApp staff = userAppMapper.selectById(id);
		if (staff == null) {
			throw new BusinessException("STAFF_NOT_FOUND", "服务人员不存在");
		}

		AuditRecord auditRecord = auditRecordMapper.selectById(staff.getAuditId());
		if (auditRecord == null) {
			throw new BusinessException("AUDIT_RECORD_NOT_FOUND", "审核记录不存在");
		}

		validateAuditStatus(auditRecord.getStatus());

		Long auditorId = SecurityUtils.getCurrentUserId();
		String auditorName = SecurityUtils.getCurrentUsername();

		auditRecord.setStatus(auditDTO.getAuditStatus());
		auditRecord.setRemark(auditDTO.getAuditOpinion());
		auditRecord.setAuditorId(auditorId);
		auditRecordMapper.updateById(auditRecord);

		BaseUser baseUser = baseUserMapper.selectById(staff.getBaseUserId());
		if (baseUser != null) {
			baseUser.setStatus(auditDTO.getAuditStatus().equals(AuditStatus.APPROVED.getCode()) ? 
				UserStatus.ENABLED.getCode() : UserStatus.DISABLED.getCode());
			baseUserMapper.updateById(baseUser);
		}

		log.info("服务人员审核完成，服务人员ID：{}，审核状态：{}", id, auditDTO.getAuditStatus());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void auditRider(Long id, AuditDTO auditDTO) {
		validateAuditParams(id, auditDTO);

		UserRider rider = userRiderMapper.selectById(id);
		if (rider == null) {
			throw new BusinessException("RIDER_NOT_FOUND", "骑手不存在");
		}

		AuditRecord auditRecord = auditRecordMapper.selectById(rider.getAuditId());
		if (auditRecord == null) {
			throw new BusinessException("AUDIT_RECORD_NOT_FOUND", "审核记录不存在");
		}

		validateAuditStatus(auditRecord.getStatus());

		Long auditorId = SecurityUtils.getCurrentUserId();
		String auditorName = SecurityUtils.getCurrentUsername();

		auditRecord.setStatus(auditDTO.getAuditStatus());
		auditRecord.setRemark(auditDTO.getAuditOpinion());
		auditRecord.setAuditorId(auditorId);
		auditRecordMapper.updateById(auditRecord);

		BaseUser baseUser = baseUserMapper.selectById(rider.getBaseUserId());
		if (baseUser != null) {
			baseUser.setStatus(auditDTO.getAuditStatus().equals(AuditStatus.APPROVED.getCode()) ? 
				UserStatus.ENABLED.getCode() : UserStatus.DISABLED.getCode());
			baseUserMapper.updateById(baseUser);
		}

		log.info("骑手审核完成，骑手ID：{}，审核状态：{}", id, auditDTO.getAuditStatus());
	}

	@Override
	public AuditRecord getAuditRecordByBizTypeAndId(String bizType, Long bizId) {
		if (!StringUtils.hasText(bizType) || bizId == null) {
			throw new BusinessException("INVALID_PARAM", "业务类型和业务ID不能为空");
		}

		LambdaQueryWrapper<AuditRecord> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(AuditRecord::getBizType, bizType);
		wrapper.eq(AuditRecord::getApplicantId, bizId);
		wrapper.orderByDesc(AuditRecord::getCreateAt);
		wrapper.last("LIMIT 1");
		return auditRecordMapper.selectOne(wrapper);
	}

	@Override
	public Page<RiderApplyVO> listRiderApply(RiderApplyQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserRider> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<UserRider> wrapper = Wrappers.lambdaQuery();
		if (StringUtils.hasText(queryDTO.getRiderName())) {
			wrapper.like(UserRider::getRealName, queryDTO.getRiderName());
		}
		if (StringUtils.hasText(queryDTO.getRiderPhone())) {
			wrapper.like(UserRider::getCardNumber, queryDTO.getRiderPhone());
		}
		if (queryDTO.getAuditStatus() != null) {
			wrapper.eq(UserRider::getAuditId, queryDTO.getAuditStatus());
		}
		wrapper.orderByDesc(UserRider::getCreateTime);

		Page<UserRider> riderPage = userRiderMapper.selectPage(page, wrapper);

		if (riderPage.getRecords().isEmpty()) {
			return new Page<>(riderPage.getCurrent(), riderPage.getSize(), riderPage.getTotal());
		}

		List<Long> riderIds = riderPage.getRecords().stream()
				.map(UserRider::getId)
				.collect(Collectors.toList());

		List<Long> auditIds = riderPage.getRecords().stream()
				.map(UserRider::getAuditId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		Map<Long, AuditRecord> auditRecordMap = batchQueryAuditRecords(auditIds);
		Map<Long, Address> addressMap = batchQueryAddresses(riderPage.getRecords());

		List<RiderApplyVO> voList = riderPage.getRecords().stream().map(rider -> {
			RiderApplyVO vo = new RiderApplyVO();
			vo.setId(rider.getId());
			vo.setRiderId(rider.getId());
			vo.setRiderName(rider.getRealName());
			vo.setRiderPhone(maskPhone(rider.getCardNumber()));
			vo.setIdCardFrontUrl(rider.getIdCardFront());
			vo.setIdCardBackUrl(rider.getIdCardBack());

			if (rider.getAuditId() != null) {
				AuditRecord auditRecord = auditRecordMap.get(rider.getAuditId());
				if (auditRecord != null) {
					vo.setAuditNo(auditRecord.getAuditNo());
					vo.setAuditStatus(auditRecord.getStatus());
					vo.setAuditStatusText(AuditStatus.getText(auditRecord.getStatus()));
					vo.setAuditOpinion(auditRecord.getRemark());
					vo.setApplyTime(auditRecord.getCreateAt());
					vo.setAuditTime(auditRecord.getUpdateAt());
				}
			}

			if (rider.getAddressId() != null) {
				Address address = addressMap.get(rider.getAddressId());
				if (address != null) {
					vo.setFullAddress(address.getProvince() + address.getCity() + address.getDistrict());
				}
			}

			return vo;
		}).collect(Collectors.toList());

		Page<RiderApplyVO> resultPage = new Page<>(riderPage.getCurrent(), riderPage.getSize(), riderPage.getTotal());
		resultPage.setRecords(voList);
		return resultPage;
	}

	@Override
	public Page<MerchantSettleInVO> listMerchantSettleIn(MerchantSettleInQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserMch> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<UserMch> wrapper = Wrappers.lambdaQuery();
		if (StringUtils.hasText(queryDTO.getOrgName())) {
			wrapper.like(UserMch::getMchName, queryDTO.getOrgName());
		}
		if (queryDTO.getPartnerId() != null) {
			wrapper.eq(UserMch::getPartnerId, queryDTO.getPartnerId());
		}
		if (queryDTO.getAuditStatus() != null) {
			wrapper.eq(UserMch::getAuditId, queryDTO.getAuditStatus());
		}
		wrapper.orderByDesc(UserMch::getCreateTime);

		Page<UserMch> mchPage = userMchMapper.selectPage(page, wrapper);

		if (mchPage.getRecords().isEmpty()) {
			return new Page<>(mchPage.getCurrent(), mchPage.getSize(), mchPage.getTotal());
		}

		List<Long> mchIds = mchPage.getRecords().stream()
				.map(UserMch::getId)
				.collect(Collectors.toList());

		List<Long> partnerIds = mchPage.getRecords().stream()
				.map(UserMch::getPartnerId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		List<Long> auditIds = mchPage.getRecords().stream()
				.map(UserMch::getAuditId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		List<Long> addressIds = mchPage.getRecords().stream()
				.map(UserMch::getAddressId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		List<Long> userIds = mchPage.getRecords().stream()
				.map(UserMch::getBaseUserId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		Map<Long, UserPartner> partnerMap = batchQueryPartners(partnerIds);
		Map<Long, AuditRecord> auditRecordMap = batchQueryAuditRecords(auditIds);
		Map<Long, Address> addressMap = batchQueryAddressesByIds(addressIds);
		Map<Long, BaseUser> userMap = batchQueryBaseUsers(userIds);

		List<MerchantSettleInVO> voList = mchPage.getRecords().stream().map(mch -> {
			MerchantSettleInVO vo = new MerchantSettleInVO();
			vo.setId(mch.getId());
			vo.setOrgName(mch.getMchName());
			vo.setContactPerson(mch.getContactName());
			vo.setContactPhone(maskPhone(mch.getCardNumber()));
			vo.setPartnerId(mch.getPartnerId());
			vo.setAuditId(mch.getAuditId());
			vo.setSettlementAccount(mch.getCardNumber());
			vo.setCreatedAt(mch.getCreateTime());
			vo.setUpdatedAt(mch.getUpdateTime());

			if (mch.getPartnerId() != null) {
				UserPartner partner = partnerMap.get(mch.getPartnerId());
				if (partner != null) {
					vo.setPartnerName(partner.getPartnerName());
				}
			}

			if (mch.getAuditId() != null) {
				AuditRecord auditRecord = auditRecordMap.get(mch.getAuditId());
				if (auditRecord != null) {
					vo.setAuditStatus(auditRecord.getStatus());
					vo.setAuditOpinion(auditRecord.getRemark());
					vo.setApplyTime(auditRecord.getCreateAt());
					vo.setAuditTime(auditRecord.getUpdateAt());
				}
			}

			if (mch.getAddressId() != null) {
				Address address = addressMap.get(mch.getAddressId());
				if (address != null) {
					vo.setProvince(address.getProvince());
					vo.setCity(address.getCity());
					vo.setDistrict(address.getDistrict());
					vo.setAddress(address.getDetailAddress());
				}
			}

			BaseUser baseUser = userMap.get(mch.getBaseUserId());
			if (baseUser != null) {
				vo.setStatus(baseUser.getStatus());
			}

			return vo;
		}).collect(Collectors.toList());

		Page<MerchantSettleInVO> resultPage = new Page<>(mchPage.getCurrent(), mchPage.getSize(), mchPage.getTotal());
		resultPage.setRecords(voList);
		return resultPage;
	}

	@Override
	public Page<ServiceStaffAuditVO> listServiceStaffAudit(ServiceStaffAuditQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserApp> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<UserApp> wrapper = Wrappers.lambdaQuery();
		if (queryDTO.getAuditStatus() != null) {
			wrapper.eq(UserApp::getAuditId, queryDTO.getAuditStatus());
		}
		wrapper.orderByDesc(UserApp::getCreateTime);

		Page<UserApp> staffPage = userAppMapper.selectPage(page, wrapper);

		if (staffPage.getRecords().isEmpty()) {
			return new Page<>(staffPage.getCurrent(), staffPage.getSize(), staffPage.getTotal());
		}

		List<Long> schoolIds = staffPage.getRecords().stream()
				.map(UserApp::getSchoolId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		List<Long> auditIds = staffPage.getRecords().stream()
				.map(UserApp::getAuditId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		Map<Long, SysSchool> schoolMap = batchQuerySchools(schoolIds);
		Map<Long, AuditRecord> auditRecordMap = batchQueryAuditRecords(auditIds);

		List<ServiceStaffAuditVO> voList = staffPage.getRecords().stream().map(staff -> {
			ServiceStaffAuditVO vo = new ServiceStaffAuditVO();
			vo.setId(staff.getId());
			vo.setUsername(staff.getRealName());
			vo.setPhone(maskPhone(staff.getStuCode()));
			vo.setAuditId(staff.getAuditId());
			vo.setCreatedAt(staff.getCreateTime());

			if (staff.getSchoolId() != null) {
				SysSchool school = schoolMap.get(staff.getSchoolId());
				if (school != null) {
					vo.setSchoolName(school.getSchoolName());
				}
			}

			if (staff.getAuditId() != null) {
				AuditRecord auditRecord = auditRecordMap.get(staff.getAuditId());
				if (auditRecord != null) {
					vo.setAuditStatus(auditRecord.getStatus());
					vo.setAuditOpinion(auditRecord.getRemark());
					vo.setAuditTime(auditRecord.getUpdateAt());
				}
			}

			return vo;
		}).collect(Collectors.toList());

		Page<ServiceStaffAuditVO> resultPage = new Page<>(staffPage.getCurrent(), staffPage.getSize(), staffPage.getTotal());
		resultPage.setRecords(voList);
		return resultPage;
	}

	@Override
	public Page<PartnerAuditVO> listPartnerAudit(PartnerAuditQueryDTO queryDTO) {
		validatePageParams(queryDTO.getPage(), queryDTO.getSize());

		Page<UserPartner> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

		LambdaQueryWrapper<UserPartner> wrapper = Wrappers.lambdaQuery();
		if (StringUtils.hasText(queryDTO.getPartnerName())) {
			wrapper.like(UserPartner::getPartnerName, queryDTO.getPartnerName());
		}
		if (queryDTO.getAuditStatus() != null) {
			wrapper.eq(UserPartner::getAuditId, queryDTO.getAuditStatus());
		}
		wrapper.orderByDesc(UserPartner::getCreateAt);

		Page<UserPartner> partnerPage = userPartnerMapper.selectPage(page, wrapper);

		if (partnerPage.getRecords().isEmpty()) {
			return new Page<>(partnerPage.getCurrent(), partnerPage.getSize(), partnerPage.getTotal());
		}

		List<Long> userIds = partnerPage.getRecords().stream()
				.map(UserPartner::getBaseUserId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		List<Long> auditIds = partnerPage.getRecords().stream()
				.map(UserPartner::getAuditId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());

		Map<Long, BaseUser> userMap = batchQueryBaseUsers(userIds);
		Map<Long, AuditRecord> auditRecordMap = batchQueryAuditRecords(auditIds);

		List<PartnerAuditVO> voList = partnerPage.getRecords().stream().map(partner -> {
			PartnerAuditVO vo = new PartnerAuditVO();
			vo.setId(partner.getId());
			vo.setPartnerName(partner.getPartnerName());
			vo.setAuditId(partner.getAuditId());
			vo.setSettlementAccount(partner.getCardNumber());

			BaseUser baseUser = userMap.get(partner.getBaseUserId());
			if (baseUser != null) {
				vo.setPhone(maskPhone(baseUser.getPhone()));
				vo.setEmail(baseUser.getEmail());
				vo.setAvatar(baseUser.getAvatar());
			}

			if (partner.getAuditId() != null) {
				AuditRecord auditRecord = auditRecordMap.get(partner.getAuditId());
				if (auditRecord != null) {
					vo.setAuditStatus(auditRecord.getStatus());
					vo.setAuditOpinion(auditRecord.getRemark());
					vo.setApplyTime(auditRecord.getCreateAt());
					vo.setAuditTime(auditRecord.getUpdateAt());
				}
			}

			return vo;
		}).collect(Collectors.toList());

		Page<PartnerAuditVO> resultPage = new Page<>(partnerPage.getCurrent(), partnerPage.getSize(), partnerPage.getTotal());
		resultPage.setRecords(voList);
		return resultPage;
	}

	private Map<Long, AuditRecord> batchQueryAuditRecords(List<Long> auditIds) {
		if (auditIds == null || auditIds.isEmpty()) {
			return Collections.emptyMap();
		}
		return auditRecordMapper.selectBatchIds(auditIds).stream()
				.collect(Collectors.toMap(AuditRecord::getId, Function.identity()));
	}

	private Map<Long, Address> batchQueryAddresses(List<UserRider> riders) {
		Set<Long> addressIds = riders.stream()
				.map(UserRider::getAddressId)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());

		if (addressIds.isEmpty()) {
			return Collections.emptyMap();
		}

		return addressMapper.selectBatchIds(addressIds).stream()
				.collect(Collectors.toMap(Address::getId, Function.identity()));
	}

	private Map<Long, Address> batchQueryAddressesByIds(List<Long> addressIds) {
		if (addressIds == null || addressIds.isEmpty()) {
			return Collections.emptyMap();
		}
		return addressMapper.selectBatchIds(addressIds).stream()
				.collect(Collectors.toMap(Address::getId, Function.identity()));
	}

	private Map<Long, UserPartner> batchQueryPartners(List<Long> partnerIds) {
		if (partnerIds == null || partnerIds.isEmpty()) {
			return Collections.emptyMap();
		}
		return userPartnerMapper.selectBatchIds(partnerIds).stream()
				.collect(Collectors.toMap(UserPartner::getId, Function.identity()));
	}

	private Map<Long, SysSchool> batchQuerySchools(List<Long> schoolIds) {
		if (schoolIds == null || schoolIds.isEmpty()) {
			return Collections.emptyMap();
		}
		return sysSchoolMapper.selectBatchIds(schoolIds).stream()
				.collect(Collectors.toMap(SysSchool::getId, Function.identity()));
	}

	private Map<Long, BaseUser> batchQueryBaseUsers(List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return Collections.emptyMap();
		}
		return baseUserMapper.selectBatchIds(userIds).stream()
				.collect(Collectors.toMap(BaseUser::getId, Function.identity()));
	}

	private String maskPhone(String phone) {
		if (phone == null || phone.length() < 7) {
			return phone;
		}
		return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
	}

	private void validateAuditParams(Long id, AuditDTO auditDTO) {
		if (id == null) {
			throw new BusinessException("INVALID_PARAM", "ID不能为空");
		}
		if (auditDTO == null || auditDTO.getAuditStatus() == null) {
			throw new BusinessException("INVALID_PARAM", "审核状态不能为空");
		}
		if (!Arrays.asList(AuditStatus.APPROVED.getCode(), AuditStatus.REJECTED.getCode())
				.contains(auditDTO.getAuditStatus())) {
			throw new BusinessException("INVALID_AUDIT_STATUS", "审核状态不合法");
		}
	}

	private void validateAuditStatus(Integer currentStatus) {
		if (!AuditStatus.PENDING.getCode().equals(currentStatus)) {
			throw new BusinessException("AUDIT_ALREADY_PROCESSED", 
				"当前状态不允许审核，当前状态：" + AuditStatus.getText(currentStatus));
		}
	}

	private void validatePageParams(Integer page, Integer size) {
		if (page == null || page < 1) {
			throw new BusinessException("INVALID_PAGE", "页码必须大于0");
		}
		if (size == null || size < 1 || size > 1000) {
			throw new BusinessException("INVALID_SIZE", "每页数量必须在1-1000之间");
		}
	}
}
