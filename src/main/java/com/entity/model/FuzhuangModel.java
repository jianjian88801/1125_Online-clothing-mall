package com.entity.model;

import com.entity.FuzhuangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 服装
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class FuzhuangModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


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
     * 逻辑删除
     */
    private Integer fuzhuangDelete;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：服装名称
	 */
    public String getFuzhuangName() {
        return fuzhuangName;
    }


    /**
	 * 设置：服装名称
	 */
    public void setFuzhuangName(String fuzhuangName) {
        this.fuzhuangName = fuzhuangName;
    }
    /**
	 * 获取：服装照片
	 */
    public String getFuzhuangPhoto() {
        return fuzhuangPhoto;
    }


    /**
	 * 设置：服装照片
	 */
    public void setFuzhuangPhoto(String fuzhuangPhoto) {
        this.fuzhuangPhoto = fuzhuangPhoto;
    }
    /**
	 * 获取：服装类型
	 */
    public Integer getFuzhuangTypes() {
        return fuzhuangTypes;
    }


    /**
	 * 设置：服装类型
	 */
    public void setFuzhuangTypes(Integer fuzhuangTypes) {
        this.fuzhuangTypes = fuzhuangTypes;
    }
    /**
	 * 获取：服装库存
	 */
    public Integer getFuzhuangKucunNumber() {
        return fuzhuangKucunNumber;
    }


    /**
	 * 设置：服装库存
	 */
    public void setFuzhuangKucunNumber(Integer fuzhuangKucunNumber) {
        this.fuzhuangKucunNumber = fuzhuangKucunNumber;
    }
    /**
	 * 获取：购买获得积分
	 */
    public Integer getFuzhuangPrice() {
        return fuzhuangPrice;
    }


    /**
	 * 设置：购买获得积分
	 */
    public void setFuzhuangPrice(Integer fuzhuangPrice) {
        this.fuzhuangPrice = fuzhuangPrice;
    }
    /**
	 * 获取：服装原价
	 */
    public Double getFuzhuangOldMoney() {
        return fuzhuangOldMoney;
    }


    /**
	 * 设置：服装原价
	 */
    public void setFuzhuangOldMoney(Double fuzhuangOldMoney) {
        this.fuzhuangOldMoney = fuzhuangOldMoney;
    }
    /**
	 * 获取：现价/积分
	 */
    public Double getFuzhuangNewMoney() {
        return fuzhuangNewMoney;
    }


    /**
	 * 设置：现价/积分
	 */
    public void setFuzhuangNewMoney(Double fuzhuangNewMoney) {
        this.fuzhuangNewMoney = fuzhuangNewMoney;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getFuzhuangClicknum() {
        return fuzhuangClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setFuzhuangClicknum(Integer fuzhuangClicknum) {
        this.fuzhuangClicknum = fuzhuangClicknum;
    }
    /**
	 * 获取：服装简介
	 */
    public String getFuzhuangContent() {
        return fuzhuangContent;
    }


    /**
	 * 设置：服装简介
	 */
    public void setFuzhuangContent(String fuzhuangContent) {
        this.fuzhuangContent = fuzhuangContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getFuzhuangDelete() {
        return fuzhuangDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setFuzhuangDelete(Integer fuzhuangDelete) {
        this.fuzhuangDelete = fuzhuangDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
