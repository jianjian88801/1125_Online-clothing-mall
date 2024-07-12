package com.dao;

import com.entity.FuzhuangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FuzhuangView;

/**
 * 服装 Dao 接口
 *
 * @author 
 */
public interface FuzhuangDao extends BaseMapper<FuzhuangEntity> {

   List<FuzhuangView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
