package org.jeecg.modules.pd.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.pd.entity.PdStatisticalReport;
import org.jeecg.modules.pd.entity.PdStockRecordDetail;
import org.jeecg.modules.pd.mapper.PdStatisticalReportMapper;
import org.jeecg.modules.pd.service.IPdStatisticalReportService;
import org.jeecg.modules.pd.vo.RpDepartUseReportPage;
import org.jeecg.modules.pd.vo.RpReDetailReportPage;
import org.jeecg.modules.pd.vo.RpSupplierUseReportPage;
import org.jeecg.modules.pd.vo.RpUseDetailReportPage;
import org.jeecg.modules.pd.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 空实现
 * @Author: zxh
 * @Date:   2020-01-14
 * @Version: V1.0
 */
@Service
public class PdPdStatisticalReportImpl extends ServiceImpl<PdStatisticalReportMapper, PdStatisticalReport> implements IPdStatisticalReportService {

    /**
     *供应商用量使用统计
     * @param page
     * @param rpSupplierUseReportPage
     * @return
     */
    @Override
    public IPage<RpSupplierUseReportPage> supplierUseReport(Page<RpSupplierUseReportPage> page, RpSupplierUseReportPage rpSupplierUseReportPage) {
        return baseMapper.supplierUseReport(page,rpSupplierUseReportPage);
    }
    /**
     *供应商用量使用统计
     * @param rpSupplierUseReportPage
     * @return
     */
    @Override
    public List<RpSupplierUseReportPage> supplierUseReport(RpSupplierUseReportPage rpSupplierUseReportPage) {
        return baseMapper.supplierUseReport(rpSupplierUseReportPage);
    }

    /**
     * zxh供应商用量统计查询入库明细
     * @param inPageDetail
     * @param inDetail
     * @return
     */
    @Override
    public IPage<PdStockRecordDetail> supplierInDetailReport(Page<PdStockRecordDetail> inPageDetail, PdStockRecordDetail inDetail) {
        return baseMapper.supplierInDetailReport(inPageDetail,inDetail);
    }

    /**
     * zxh用量明细统计报表
     * @param usePageDetail
     * @param rpUseDetailReportPage
     * @return
     */
    @Override
    public IPage<RpUseDetailReportPage> rpUseDetailReport(Page<RpUseDetailReportPage> usePageDetail, RpUseDetailReportPage rpUseDetailReportPage) {
        return baseMapper.rpUseDetailReport(usePageDetail,rpUseDetailReportPage);
    }
    /**
     * zxh退货明细统计报表
     * @param rePageDetail
     * @param rpReDetailReportPage
     * @return
     */
    @Override
    public IPage<RpReDetailReportPage> rpReDetailReport(Page<RpReDetailReportPage> rePageDetail, RpReDetailReportPage rpReDetailReportPage) {
        return baseMapper.rpReDetailReport(rePageDetail,rpReDetailReportPage);
    }
    /**
     * zxh部门用量使用统计
     * @param page
     * @param rpDepartUseReportPage
     * @return
     */
    @Override
    public IPage<RpDepartUseReportPage> departUseReport(Page<RpDepartUseReportPage> page, RpDepartUseReportPage rpDepartUseReportPage) {
        return baseMapper.departUseReport(page,rpDepartUseReportPage);
    }
    /**
     * zxh部门用量使用统计
     * @param rpDepartUseReportPage
     * @return
     */
    @Override
    public List<RpDepartUseReportPage> departUseReport(RpDepartUseReportPage rpDepartUseReportPage) {
        return baseMapper.departUseReport(rpDepartUseReportPage);
    }
    /**
     * zxh部门用量使用明细
     * @param usePageDetail
     * @param rpDepartUseDetailReportPage
     * @return
     */
    @Override
    public IPage<RpDepartUseDetailReportPage> rpDepartUseDetailReport(Page<RpDepartUseDetailReportPage> usePageDetail, RpDepartUseDetailReportPage rpDepartUseDetailReportPage) {
        return baseMapper.rpDepartUseDetailReport(usePageDetail,rpDepartUseDetailReportPage);
    }

    /**
     * 出入库统计报表查询——分页
     * add by jiangxz 2020年8月14日 09:59:53
     * @param page
     * @param entity
     * @return
     */
    @Override
    public IPage<RpInAndOutReportPage> rpInAndOutReport(Page<RpInAndOutReportPage> page, RpInAndOutReportPage entity) {
        return baseMapper.rpInAndOutReport(page,entity);
    }

    /**
     * 出入库统计报表查询
     * add by jiangxz 2020年8月14日 09:59:53
     * @param entity
     * @return
     */
    @Override
    public List<RpInAndOutReportPage> rpInAndOutReport(RpInAndOutReportPage entity) {
        return baseMapper.rpInAndOutReport(entity);
    }

    /**
     * 出入库统计报表明细查询——分页
     * add by jiangxz 2020年8月14日 09:59:53
     * @param page
     * @param entity
     * @return
     */
    @Override
    public IPage<RpInAndOutDetailReportPage> rpInAndOutDetailReport(Page<RpInAndOutDetailReportPage> page, RpInAndOutDetailReportPage entity) {
        return baseMapper.rpInAndOutDetailReport(page,entity);
    }

    /**
     * 出入库统计报表明细查询
     * add by jiangxz 2020年8月14日 09:59:53
     * @param entity
     * @return
     */
    @Override
    public List<RpInAndOutDetailReportPage> rpInAndOutDetailReport(RpInAndOutDetailReportPage entity) {
        return baseMapper.rpInAndOutDetailReport(entity);
    }


    /**
     * zxh库存统计报表
     * @param page
     * @param rpDepartStockReportPage
     * @return
     */
    @Override
    public IPage<RpDepartStockReportPage> departStockReport(Page<RpDepartStockReportPage> page, RpDepartStockReportPage rpDepartStockReportPage) {
        return baseMapper.departStockReport(page,rpDepartStockReportPage);
    }
    /**
     * zxh库存统计报表
     * @param rpDepartStockReportPage
     * @return
     */
    @Override
    public List<RpDepartStockReportPage> departStockReport(RpDepartStockReportPage rpDepartStockReportPage) {
        return baseMapper.departStockReport(rpDepartStockReportPage);
    }
    /**
     * zxh库存统计报表
     * @param rpDepartStockDetailReportPage
     * @return
     */
    @Override
    public IPage<RpDepartStockDetailReportPage> rpDepartStockDetailReport(Page<RpDepartStockDetailReportPage> stockPageDetail, RpDepartStockDetailReportPage rpDepartStockDetailReportPage) {
        return baseMapper.rpDepartStockDetailReport(stockPageDetail,rpDepartStockDetailReportPage);
    }
    /**
     * zxh供应商试剂使用报表
     * @param page
     * @param rpSupplierUseReportPage
     * @return
     */
    @Override
    public IPage<RpSupplierUseReportPage> supplierReagentUseReport(Page<RpSupplierUseReportPage> page, RpSupplierUseReportPage rpSupplierUseReportPage) {
        return baseMapper.supplierReagentUseReport(page,rpSupplierUseReportPage);
    }
    /**
     * zxh供应商试剂使用报表详情
     * @param usePageDetail
     * @param rpReagentUseDetailReportPage
     * @return
     */
    @Override
    public IPage<RpReagentUseDetailReportPage> rpReagentUseDetailReport(Page<RpReagentUseDetailReportPage> usePageDetail, RpReagentUseDetailReportPage rpReagentUseDetailReportPage) {
        return baseMapper.rpReagentUseDetailReport(usePageDetail,rpReagentUseDetailReportPage);
    }

    /**
     *  综合统计报表  采购收费对照表数据获取
     * @param purchaseUseReportPage
     * @return
     */
    @Override
    public List<RpPurchaseUseReportPage> queryPurchaseCountView(RpPurchaseUseReportPage purchaseUseReportPage) {
        return baseMapper.queryPurchaseCountView(purchaseUseReportPage);
    }

    /**
     *  综合统计报表    全院耗材占比数据查
     * @param purchaseUseReportPage
     * @return
     */
    @Override
    public List<RpPurchaseUseReportPage> queryConsumptionView(RpPurchaseUseReportPage purchaseUseReportPage) {
        return baseMapper.queryConsumptionView(purchaseUseReportPage);
    }

    /**
     *  综合统计报表    采购科室金额占比
     * @param purchaseUseReportPage
     * @return
     */
    @Override
    public List<RpPurchaseUseReportPage> queryDepartContionView(RpPurchaseUseReportPage purchaseUseReportPage) {
        return baseMapper.queryDepartContionView(purchaseUseReportPage);
    }

    /**
     *  综合统计报表    科室收费金额占比
     * @param purchaseUseReportPage
     * @return
     */
    @Override
    public List<RpPurchaseUseReportPage> queryDepartChargeView(RpPurchaseUseReportPage purchaseUseReportPage) {
        return baseMapper.queryDepartChargeView(purchaseUseReportPage);
    }
}
