package com.entity.vo;

import com.entity.FuzhuangOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 服装订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fuzhuang_order")
public class FuzhuangOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "fuzhuang_order_uuid_number")
    private String fuzhuangOrderUuidNumber;


    /**
     * 收获地址
     */

    @TableField(value = "address_id")
    private Integer addressId;


    /**
     * 服装
     */

    @TableField(value = "fuzhuang_id")
    private Integer fuzhuangId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "fuzhuang_order_true_price")
    private Double fuzhuangOrderTruePrice;


    /**
     * 快递公司
     */

    @TableField(value = "fuzhuang_order_courier_name")
    private String fuzhuangOrderCourierName;


    /**
     * 订单快递单号
     */

    @TableField(value = "fuzhuang_order_courier_number")
    private String fuzhuangOrderCourierNumber;


    /**
     * 订单类型
     */

    @TableField(value = "fuzhuang_order_types")
    private Integer fuzhuangOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "fuzhuang_order_payment_types")
    private Integer fuzhuangOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getFuzhuangOrderUuidNumber() {
        return fuzhuangOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setFuzhuangOrderUuidNumber(String fuzhuangOrderUuidNumber) {
        this.fuzhuangOrderUuidNumber = fuzhuangOrderUuidNumber;
    }
    /**
	 * 设置：收获地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 获取：收获地址
	 */

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 设置：服装
	 */
    public Integer getFuzhuangId() {
        return fuzhuangId;
    }


    /**
	 * 获取：服装
	 */

    public void setFuzhuangId(Integer fuzhuangId) {
        this.fuzhuangId = fuzhuangId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getFuzhuangOrderTruePrice() {
        return fuzhuangOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setFuzhuangOrderTruePrice(Double fuzhuangOrderTruePrice) {
        this.fuzhuangOrderTruePrice = fuzhuangOrderTruePrice;
    }
    /**
	 * 设置：快递公司
	 */
    public String getFuzhuangOrderCourierName() {
        return fuzhuangOrderCourierName;
    }


    /**
	 * 获取：快递公司
	 */

    public void setFuzhuangOrderCourierName(String fuzhuangOrderCourierName) {
        this.fuzhuangOrderCourierName = fuzhuangOrderCourierName;
    }
    /**
	 * 设置：订单快递单号
	 */
    public String getFuzhuangOrderCourierNumber() {
        return fuzhuangOrderCourierNumber;
    }


    /**
	 * 获取：订单快递单号
	 */

    public void setFuzhuangOrderCourierNumber(String fuzhuangOrderCourierNumber) {
        this.fuzhuangOrderCourierNumber = fuzhuangOrderCourierNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getFuzhuangOrderTypes() {
        return fuzhuangOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setFuzhuangOrderTypes(Integer fuzhuangOrderTypes) {
        this.fuzhuangOrderTypes = fuzhuangOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getFuzhuangOrderPaymentTypes() {
        return fuzhuangOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setFuzhuangOrderPaymentTypes(Integer fuzhuangOrderPaymentTypes) {
        this.fuzhuangOrderPaymentTypes = fuzhuangOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
