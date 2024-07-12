package com.entity.model;

import com.entity.FuzhuangOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 服装订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class FuzhuangOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String fuzhuangOrderUuidNumber;


    /**
     * 收获地址
     */
    private Integer addressId;


    /**
     * 服装
     */
    private Integer fuzhuangId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double fuzhuangOrderTruePrice;


    /**
     * 快递公司
     */
    private String fuzhuangOrderCourierName;


    /**
     * 订单快递单号
     */
    private String fuzhuangOrderCourierNumber;


    /**
     * 订单类型
     */
    private Integer fuzhuangOrderTypes;


    /**
     * 支付类型
     */
    private Integer fuzhuangOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getFuzhuangOrderUuidNumber() {
        return fuzhuangOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setFuzhuangOrderUuidNumber(String fuzhuangOrderUuidNumber) {
        this.fuzhuangOrderUuidNumber = fuzhuangOrderUuidNumber;
    }
    /**
	 * 获取：收获地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 设置：收获地址
	 */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 获取：服装
	 */
    public Integer getFuzhuangId() {
        return fuzhuangId;
    }


    /**
	 * 设置：服装
	 */
    public void setFuzhuangId(Integer fuzhuangId) {
        this.fuzhuangId = fuzhuangId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getFuzhuangOrderTruePrice() {
        return fuzhuangOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setFuzhuangOrderTruePrice(Double fuzhuangOrderTruePrice) {
        this.fuzhuangOrderTruePrice = fuzhuangOrderTruePrice;
    }
    /**
	 * 获取：快递公司
	 */
    public String getFuzhuangOrderCourierName() {
        return fuzhuangOrderCourierName;
    }


    /**
	 * 设置：快递公司
	 */
    public void setFuzhuangOrderCourierName(String fuzhuangOrderCourierName) {
        this.fuzhuangOrderCourierName = fuzhuangOrderCourierName;
    }
    /**
	 * 获取：订单快递单号
	 */
    public String getFuzhuangOrderCourierNumber() {
        return fuzhuangOrderCourierNumber;
    }


    /**
	 * 设置：订单快递单号
	 */
    public void setFuzhuangOrderCourierNumber(String fuzhuangOrderCourierNumber) {
        this.fuzhuangOrderCourierNumber = fuzhuangOrderCourierNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getFuzhuangOrderTypes() {
        return fuzhuangOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setFuzhuangOrderTypes(Integer fuzhuangOrderTypes) {
        this.fuzhuangOrderTypes = fuzhuangOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getFuzhuangOrderPaymentTypes() {
        return fuzhuangOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setFuzhuangOrderPaymentTypes(Integer fuzhuangOrderPaymentTypes) {
        this.fuzhuangOrderPaymentTypes = fuzhuangOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
