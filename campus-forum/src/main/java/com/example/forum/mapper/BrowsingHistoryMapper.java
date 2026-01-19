package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.entity.BrowsingHistory;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 22417
* @description 针对表【browsing_history(用户浏览记录表)】的数据库操作Mapper
* @createDate 2025-12-09 17:57:55
* @Entity com.example.campusapp.entity.BrowsingHistory
*/
@Mapper
public interface BrowsingHistoryMapper extends BaseMapper<BrowsingHistory> {

}




