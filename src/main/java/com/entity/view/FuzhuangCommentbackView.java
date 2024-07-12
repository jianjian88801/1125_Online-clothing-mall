package com.entity.view;

import com.entity.FuzhuangCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 服装评价
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("fuzhuang_commentback")
public class FuzhuangCommentbackView extends FuzhuangCommentbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 fuzhuang
			/**
			* 服装名称
			*/
			private String fuzhuangName;
			/**
			* 服装照片
			*/
			private String fuzhuangPhoto;
			/**
			* 服装类型
			*/
			private Integer fuzhuangTypes;
				/**
				* 服装类型的值
				*/
				private String fuzhuangValue;
			/**
			* 服装库存
			*/
			private Integer fuzhuangKucunNumber;
			/**
			* 购买获得积分
			*/
			private Integer fuzhuangPrice;
			/**
			* 服装原价
			*/
			private Double fuzhuangOldMoney;
			/**
			* 现价/积分
			*/
			private Double fuzhuangNewMoney;
			/**
			* 点击次数
			*/
			private Integer fuzhuangClicknum;
			/**
			* 服装简介
			*/
			private String fuzhuangContent;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer fuzhuangDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 总积分
			*/
			private Double yonghuSumJifen;
			/**
			* 现积分
			*/
			private Double yonghuNewJifen;
			/**
			* 会员等级
			*/
			private Integer huiyuandengjiTypes;
				/**
				* 会员等级的值
				*/
				private String huiyuandengjiValue;

	public FuzhuangCommentbackView() {

	}

	public FuzhuangCommentbackView(FuzhuangCommentbackEntity fuzhuangCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, fuzhuangCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
















				//级联表的get和set fuzhuang
					/**
					* 获取： 服装名称
					*/
					public String getFuzhuangName() {
						return fuzhuangName;
					}
					/**
					* 设置： 服装名称
					*/
					public void setFuzhuangName(String fuzhuangName) {
						this.fuzhuangName = fuzhuangName;
					}
					/**
					* 获取： 服装照片
					*/
					public String getFuzhuangPhoto() {
						return fuzhuangPhoto;
					}
					/**
					* 设置： 服装照片
					*/
					public void setFuzhuangPhoto(String fuzhuangPhoto) {
						this.fuzhuangPhoto = fuzhuangPhoto;
					}
					/**
					* 获取： 服装类型
					*/
					public Integer getFuzhuangTypes() {
						return fuzhuangTypes;
					}
					/**
					* 设置： 服装类型
					*/
					public void setFuzhuangTypes(Integer fuzhuangTypes) {
						this.fuzhuangTypes = fuzhuangTypes;
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
					* 获取： 服装库存
					*/
					public Integer getFuzhuangKucunNumber() {
						return fuzhuangKucunNumber;
					}
					/**
					* 设置： 服装库存
					*/
					public void setFuzhuangKucunNumber(Integer fuzhuangKucunNumber) {
						this.fuzhuangKucunNumber = fuzhuangKucunNumber;
					}
					/**
					* 获取： 购买获得积分
					*/
					public Integer getFuzhuangPrice() {
						return fuzhuangPrice;
					}
					/**
					* 设置： 购买获得积分
					*/
					public void setFuzhuangPrice(Integer fuzhuangPrice) {
						this.fuzhuangPrice = fuzhuangPrice;
					}
					/**
					* 获取： 服装原价
					*/
					public Double getFuzhuangOldMoney() {
						return fuzhuangOldMoney;
					}
					/**
					* 设置： 服装原价
					*/
					public void setFuzhuangOldMoney(Double fuzhuangOldMoney) {
						this.fuzhuangOldMoney = fuzhuangOldMoney;
					}
					/**
					* 获取： 现价/积分
					*/
					public Double getFuzhuangNewMoney() {
						return fuzhuangNewMoney;
					}
					/**
					* 设置： 现价/积分
					*/
					public void setFuzhuangNewMoney(Double fuzhuangNewMoney) {
						this.fuzhuangNewMoney = fuzhuangNewMoney;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getFuzhuangClicknum() {
						return fuzhuangClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setFuzhuangClicknum(Integer fuzhuangClicknum) {
						this.fuzhuangClicknum = fuzhuangClicknum;
					}
					/**
					* 获取： 服装简介
					*/
					public String getFuzhuangContent() {
						return fuzhuangContent;
					}
					/**
					* 设置： 服装简介
					*/
					public void setFuzhuangContent(String fuzhuangContent) {
						this.fuzhuangContent = fuzhuangContent;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
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
					/**
					* 获取： 逻辑删除
					*/
					public Integer getFuzhuangDelete() {
						return fuzhuangDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setFuzhuangDelete(Integer fuzhuangDelete) {
						this.fuzhuangDelete = fuzhuangDelete;
					}
















				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}
					/**
					* 获取： 总积分
					*/
					public Double getYonghuSumJifen() {
						return yonghuSumJifen;
					}
					/**
					* 设置： 总积分
					*/
					public void setYonghuSumJifen(Double yonghuSumJifen) {
						this.yonghuSumJifen = yonghuSumJifen;
					}
					/**
					* 获取： 现积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 现积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}
					/**
					* 获取： 会员等级
					*/
					public Integer getHuiyuandengjiTypes() {
						return huiyuandengjiTypes;
					}
					/**
					* 设置： 会员等级
					*/
					public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
						this.huiyuandengjiTypes = huiyuandengjiTypes;
					}


						/**
						* 获取： 会员等级的值
						*/
						public String getHuiyuandengjiValue() {
							return huiyuandengjiValue;
						}
						/**
						* 设置： 会员等级的值
						*/
						public void setHuiyuandengjiValue(String huiyuandengjiValue) {
							this.huiyuandengjiValue = huiyuandengjiValue;
						}



}
