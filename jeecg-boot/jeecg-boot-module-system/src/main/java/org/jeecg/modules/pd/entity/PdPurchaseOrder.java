package org.jeecg.modules.pd.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 申购订单主表
 * @Author: mcb
 * @Date:   2020-02-04
 * @Version: V1.0
 */
@Data
@TableName("pd_purchase_order")
public class PdPurchaseOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
    private String createBy;
	/**创建日期*/
    @Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
	/**更新人*/
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
	/**所属部门标识*/
    @Excel(name = "所属部门", width = 15)
    private String sysOrgCode;
	/**申购编号*/
    @Excel(name = "申购编号", width = 15)
    private String orderNo;
	/**申购人编号*/
    @Excel(name = "申购人编号", width = 15)
    private String purchaseBy;
	/**申购日期*/
    @Excel(name = "申购日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderDate;
	/**申购科室编号*/
    @Excel(name = "申购科室编号", width = 15)
    private String deptId;
	/**申购科室名称*/
    @Excel(name = "申购科室名称", width = 15)
    private String deptName;
	/**审核状态*/
    @Excel(name = "审核状态", width = 15)
    private String orderStatus;
	/**审核人编号*/
    @Excel(name = "审核人编号", width = 15)
    private String auditBy;
	/**审核时间*/
    @Excel(name = "审核时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date auditDate;
	/**审核意见*/
    @Excel(name = "审核意见", width = 15)
    private String refuseReason;
	/**申购总数量*/
    @Excel(name = "申购总数量", width = 15)
    private Double amountCount;
	/**申购总金额*/
    @Excel(name = "申购总金额", width = 15)
    private BigDecimal amountMoney;
	/**备注信息*/
    @Excel(name = "备注信息", width = 15)
    private String remarks;
	/**提交状态*/
    @Excel(name = "提交状态", width = 15)
    private String submitStart;
}
