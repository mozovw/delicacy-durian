package com.delicacy.durian.easypoi.excel.entity;


import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountsEntity implements Serializable {

    private static final long          serialVersionUID = 1L;

    @Excel(name = "序号")
    private String                     index;

    @Excel(name = "资金性质")
    private String                     accountType;

    @ExcelCollection(name = "预算科目")
    private List<BudgetAccountsEntity> budgetAccounts;

    @Excel(name = "项目名称")
    private String                     projectName;

    @ExcelEntity(name = "收款人")
    private PayeeEntity                payee;

    @Excel(name = "申请金额")
    private String                     amountApplied;

    @Excel(name = "核定金额")
    private String                     approvedAmount;


}
