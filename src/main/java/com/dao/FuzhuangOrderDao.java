package com.dao;

import com.entity.FuzhuangOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FuzhuangOrderView;

/**
 * 服装订单 Dao 接口
 *
 * @author 
 */
public interface FuzhuangOrderDao extends BaseMapper<FuzhuangOrderEntity> {

   List<FuzhuangOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
