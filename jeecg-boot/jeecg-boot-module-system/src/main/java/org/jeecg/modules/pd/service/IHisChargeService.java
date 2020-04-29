package org.jeecg.modules.pd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.pd.entity.HisChargeInf;

import java.util.List;

/**
 * @Description: HIS系统接口相关服务
 * @Author: jeecg-boot
 * @Date:  2020-4-29
 * @Version: V1.0
 */
public interface IHisChargeService extends IService<HisChargeInf> {

    /**
     * 分页查询
     * @param pageList
     * @param hisChargeInf
     * @return
     */
    Page<HisChargeInf> selectList(Page<HisChargeInf> pageList, HisChargeInf hisChargeInf);

   /*查询his系统收费项目基础信息*/
	List<HisChargeInf> selectByHisCharge();

    /*删除后保存所有收费项目基础信息*/
    void saveMain(List<HisChargeInf> list);

}
