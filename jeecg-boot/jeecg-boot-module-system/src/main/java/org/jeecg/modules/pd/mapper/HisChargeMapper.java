package org.jeecg.modules.pd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.external.entity.ExInspectionItems;
import org.jeecg.modules.pd.entity.HisChargeInf;
import org.jeecg.modules.pd.entity.HisDepartInf;
import org.jeecg.modules.pd.entity.HisUserInf;
import org.jeecg.modules.pd.entity.PdDosage;
import java.util.*;
import java.util.List;

/**
 * @Description: HIs接口相关
 * @Author: jeecg-boot
 * @Date:   2020-02-25
 * @Version: V1.0
 */
public interface HisChargeMapper extends BaseMapper<HisChargeInf> {

	List<HisChargeInf> selectList(HisChargeInf hisChargeInf);

	List<HisChargeInf> selectByHisCharge();

	void deleteChargeInf();

	List<HisDepartInf> selectHisDepart();

	List<HisUserInf> selectHisUser();

	List<PdDosage> queryPatientInfoSS(PdDosage pdDosage);

	List<PdDosage> queryPatientInfoJC(PdDosage pdDosage);

	 Map<String,Object>   queryMztfList(String  id);

	List<PdDosage> queryPatientInfoMZ(PdDosage pdDosage);

	List<ExInspectionItems> selectExjianYan(ExInspectionItems exInspectionItems);
}
