package com.entity.view;

import com.entity.FuzhuangEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 服装
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("fuzhuang")
public class FuzhuangView extends FuzhuangEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 服装类型的值
		*/
		private String fuzhuangValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public FuzhuangView() {

	}

	public FuzhuangView(FuzhuangEntity fuzhuangEntity) {
		try {
			BeanUtils.copyProperties(this, fuzhuangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 服装类型的值
			*/
			public String getFuzhuangValue() {
				return fuzhuangValue;
			}
			/**
			* 设置： 服装类型的值
			*/
			public void setFuzhuangValue(String fuzhuangValue) {
				this.fuzhuangValue = fuzhuangValue;
			}
			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}












}
