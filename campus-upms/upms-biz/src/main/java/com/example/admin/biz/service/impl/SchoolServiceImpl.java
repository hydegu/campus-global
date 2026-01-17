package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.entity.Address;
import com.example.admin.api.entity.SysSchool;
import com.example.admin.biz.dto.SchoolAddDTO;
import com.example.admin.biz.dto.SchoolQueryDTO;
import com.example.admin.biz.dto.SchoolUpdateDTO;
import com.example.admin.biz.mapper.AddressMapper;
import com.example.admin.biz.mapper.SysSchoolMapper;
import com.example.admin.biz.service.SchoolService;
import com.example.admin.biz.vo.SysSchoolVO;
import com.example.common.core.exception.CheckedException;
import com.example.common.mybatis.utils.PageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolServiceImpl extends ServiceImpl<SysSchoolMapper, SysSchool> implements SchoolService {

    private final AddressMapper addressMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSchool(SchoolAddDTO dto) {
        log.info("新增学校，参数：{}", dto);

        Address address = new Address();
        address.setProvince(dto.getProvince());
        address.setCity(dto.getCity());
        address.setDistrict(dto.getDistrict());
        address.setDetailAddress(dto.getAddress());
        address.setContactName(dto.getContactPerson());
        address.setContactPhone(dto.getContactPhone());
        addressMapper.insert(address);

        SysSchool sysSchool = new SysSchool();
        sysSchool.setSchoolName(dto.getOrgName());
        sysSchool.setStatus(dto.getStatus());
        sysSchool.setAddressId(address.getId());
        save(sysSchool);

        log.info("新增学校成功，学校ID：{}，地址ID：{}", sysSchool.getId(), address.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSchool(Long id, SchoolUpdateDTO dto) {
        log.info("更新学校，ID：{}，参数：{}", id, dto);

        SysSchool sysSchool = getById(id);
        if (sysSchool == null) {
            log.error("学校不存在，ID：{}", id);
            throw new CheckedException("学校不存在");
        }

        if (StringUtils.hasText(dto.getOrgName())) {
            sysSchool.setSchoolName(dto.getOrgName());
        }
        if (dto.getStatus() != null) {
            sysSchool.setStatus(dto.getStatus());
        }

        if (sysSchool.getAddressId() != null) {
            Address address = addressMapper.selectById(sysSchool.getAddressId());
            if (address != null) {
                boolean needUpdate = false;
                if (StringUtils.hasText(dto.getProvince())) {
                    address.setProvince(dto.getProvince());
                    needUpdate = true;
                }
                if (StringUtils.hasText(dto.getCity())) {
                    address.setCity(dto.getCity());
                    needUpdate = true;
                }
                if (StringUtils.hasText(dto.getDistrict())) {
                    address.setDistrict(dto.getDistrict());
                    needUpdate = true;
                }
                if (StringUtils.hasText(dto.getAddress())) {
                    address.setDetailAddress(dto.getAddress());
                    needUpdate = true;
                }
                if (StringUtils.hasText(dto.getContactPerson())) {
                    address.setContactName(dto.getContactPerson());
                    needUpdate = true;
                }
                if (StringUtils.hasText(dto.getContactPhone())) {
                    address.setContactPhone(dto.getContactPhone());
                    needUpdate = true;
                }
                if (needUpdate) {
                    addressMapper.updateById(address);
                }
            }
        }
        updateById(sysSchool);

        log.info("更新学校成功，ID：{}", id);
    }

    @Override
    public void deleteSchool(Long id) {
        log.info("删除学校，ID：{}", id);

        SysSchool sysSchool = getById(id);
        if (sysSchool == null) {
            log.error("学校不存在，ID：{}", id);
            throw new CheckedException("学校不存在");
        }

        removeById(id);

        log.info("删除学校成功，ID：{}", id);
    }

    @Override
    public SysSchoolVO getSchoolById(Long id) {
        log.info("查询学校详情，ID：{}", id);

        SysSchool sysSchool = getById(id);
        if (sysSchool == null) {
            log.error("学校不存在，ID：{}", id);
            throw new CheckedException("学校不存在");
        }
        SysSchoolVO vo = new SysSchoolVO();
        vo.setId(sysSchool.getId());
        vo.setOrgName(sysSchool.getSchoolName());
        vo.setStatus(sysSchool.getStatus());
        vo.setCreateTime(sysSchool.getCreateAt());
        vo.setUpdateTime(sysSchool.getUpdateAt());

        if (sysSchool.getAddressId() != null) {
            Address address = addressMapper.selectById(sysSchool.getAddressId());
            if (address != null) {
                vo.setProvince(address.getProvince());
                vo.setCity(address.getCity());
                vo.setDistrict(address.getDistrict());
                vo.setAddress(address.getDetailAddress());
                vo.setContactPerson(address.getContactName());
                vo.setContactPhone(address.getContactPhone());
            }
        }

        log.info("查询学校详情成功，ID：{}", id);
        return vo;
    }

    @Override
    public PageResult<SysSchoolVO> listSchools(SchoolQueryDTO dto) {
        log.info("分页查询学校列表，参数：{}", dto);

        LambdaQueryWrapper<SysSchool> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(dto.getOrgName())) {
            wrapper.like(SysSchool::getSchoolName, dto.getOrgName());
        }
        if (dto.getStatus() != null) {
            wrapper.eq(SysSchool::getStatus, dto.getStatus());
        }

        Page<SysSchool> page = new Page<>(dto.getPage(), dto.getSize());
        Page<SysSchool> schoolPage = page(page, wrapper);

        List<SysSchoolVO> voList = schoolPage.getRecords().stream().map(school -> {
            SysSchoolVO vo = new SysSchoolVO();
            vo.setId(school.getId());
            vo.setOrgName(school.getSchoolName());
            vo.setStatus(school.getStatus());
            vo.setCreateTime(school.getCreateAt());
            vo.setUpdateTime(school.getUpdateAt());

            if (school.getAddressId() != null) {
                Address address = addressMapper.selectById(school.getAddressId());
                if (address != null) {
                    vo.setProvince(address.getProvince());
                    vo.setCity(address.getCity());
                    vo.setDistrict(address.getDistrict());
                    vo.setAddress(address.getDetailAddress());
                    vo.setContactPerson(address.getContactName());
                    vo.setContactPhone(address.getContactPhone());
                }
            }

            return vo;
        }).collect(Collectors.toList());

        log.info("分页查询学校列表成功，总数：{}", schoolPage.getTotal());
        return new PageResult<>(schoolPage.getCurrent(), schoolPage.getSize(), schoolPage.getTotal(), voList);
    }
}