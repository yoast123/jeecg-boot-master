package org.jeecg.modules.pd.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.PdConstant;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.pd.entity.PdCategory;
import org.jeecg.modules.pd.entity.PdDepartConfig;
import org.jeecg.modules.pd.service.IPdDepartConfigService;
import org.jeecg.modules.pd.service.IPdDepartService;
import org.jeecg.modules.system.entity.*;
import org.jeecg.modules.system.model.SysDepartTreeModel;
import org.jeecg.modules.system.model.TreeModel;
import org.jeecg.modules.system.service.*;
import org.jeecg.modules.system.util.FindsDepartsChildrenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
* @Description: 部门管理
* @Author: zxh
* @Date:   2020-01-19
* @Version: V1.0
*/
@RestController
@RequestMapping("/pd/pdDepart")
@Slf4j
public class PdDepartController extends JeecgController<PdDepartConfig, IPdDepartConfigService> {

    @Autowired
    private IPdDepartService pdDepartService;

    @Autowired
    private ISysPermissionService sysPermissionService;

    @Autowired
    private ISysDepartRoleService sysDepartRoleService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysDepartRolePermissionService sysDepartRolePermissionService;

    /**
     * 查询数据 查出所有部门,并以树结构数据格式响应给前端
     *
     * @return
     */
    @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
    public Result<List<SysDepartTreeModel>> queryTreeList() {
        Result<List<SysDepartTreeModel>> result = new Result<>();
        try {
            SysDepart sysDepart = new SysDepart();
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            sysDepart.setDepartParentId(sysUser.getDepartParentId());
            List<SysDepartTreeModel> list = pdDepartService.queryTreeList(sysDepart);
            result.setResult(list);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }


    /**
     * 查询数据 查出所有部门,并以树结构数据格式响应给前端
     *
     * @return
     */
    @RequestMapping(value = "/queryPdDepartTreeList", method = RequestMethod.GET)
    public Result<Map<String, Object>> queryPdDepartTreeList() {
        Result<Map<String, Object>> result = new Result<>();
        try {
            SysDepart sysDepart = new SysDepart();
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            sysDepart.setDepartParentId(sysUser.getDepartParentId());
            List<SysDepartTreeModel> treeList = pdDepartService.queryTreeList(sysDepart);
            List<String> ids =  pdDepartService.findSysDepart(sysDepart);
            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("treeList", treeList); // 全部树节点数据
            resMap.put("ids", ids);// 全部树ids
            result.setResult(resMap);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }

    /**
     * 用户角色授权功能，查询菜单权限树
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryPermissionTreeList", method = RequestMethod.GET)
    public Result<Map<String,Object>> queryTreeList(HttpServletRequest request) {
        Result<Map<String,Object>> result = new Result<>();
        //全部权限ids
        List<String> ids = new ArrayList<>();
        try {
            LambdaQueryWrapper<SysPermission> query = new LambdaQueryWrapper<>();
            query.eq(SysPermission::getDelFlag, CommonConstant.DEL_FLAG_0);
            query.eq(SysPermission::getBusinessType, PdConstant.PERMISSION_BUSINESS_TYPE_0);
            query.orderByAsc(SysPermission::getSortNo);
            List<SysPermission> list = sysPermissionService.list(query);
            for(SysPermission sysPer : list) {
                ids.add(sysPer.getId());
            }
            List<TreeModel> treeList = new ArrayList<>();
            getPermissionTreeModelList(treeList, list, null);
            Map<String,Object> resMap = new HashMap<String,Object>();
            resMap.put("treeList", treeList); //全部树节点数据
            resMap.put("ids", ids);//全部树ids
            result.setResult(resMap);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    private void getPermissionTreeModelList(List<TreeModel> treeList,List<SysPermission> metaList,TreeModel temp) {
        for (SysPermission permission : metaList) {
            String tempPid = permission.getParentId();
            TreeModel tree = new TreeModel(permission.getId(), tempPid, permission.getName(),permission.getRuleFlag(), permission.isLeaf());
            if(temp==null && oConvertUtils.isEmpty(tempPid)) {
                treeList.add(tree);
                if(!tree.getIsLeaf()) {
                    getPermissionTreeModelList(treeList, metaList, tree);
                }
            }else if(temp!=null && tempPid!=null && tempPid.equals(temp.getKey())){
                temp.getChildren().add(tree);
                if(!tree.getIsLeaf()) {
                    getPermissionTreeModelList(treeList, metaList, tree);
                }
            }

        }
    }

    /**
     * 分页列表查询
     *
     * @param sysDepartRole
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "部门角色-分页列表查询")
    @ApiOperation(value="部门角色-分页列表查询", notes="部门角色-分页列表查询")
    @GetMapping(value = "/queryDepartRole")
    public Result<?> queryDepartRole(SysDepartRole sysDepartRole,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   @RequestParam(name="deptId",required=false) String deptId,
                                   HttpServletRequest req) {
        Page<SysDepartRole> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<SysDepartRole> query = new LambdaQueryWrapper<>();
        query.eq(SysDepartRole::getDepartId,deptId);
        if(!oConvertUtils.isEmpty(sysDepartRole.getRoleName())){
            query.like(SysDepartRole::getRoleName,sysDepartRole.getRoleName());
        }
        IPage<SysDepartRole> pageList = sysDepartRoleService.page(page, query);
        return Result.ok(pageList);
    }

    /**
     * 部门用户列表
     */
    @RequestMapping(value = "/departUserList", method = RequestMethod.GET)
    public Result<IPage<SysUser>> departUserList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                 @RequestParam(name="deptId",required=false) String deptId,
                                                 @RequestParam(name="username",required=false) String username,
                                                 @RequestParam(name="name",required=false) String name,
                                                 HttpServletRequest req) {
        Result<IPage<SysUser>> result = new Result<>();
        Page<SysUser> page = new Page<>(pageNo, pageSize);
        Map<String,Object> parMap = new HashMap<>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        parMap.put("DEL_FLAG_NORMAL",PdConstant.DEL_FLAG_0);
        parMap.put("name",name==null?"":name);
        parMap.put("username",username==null?"":username);
        parMap.put("deptId",deptId==null?"":deptId);
        parMap.put("deptParentId",sysUser.getDepartParentId());
        parMap.put("admin",PdConstant.ADMIN_DEPART_CODE);
        IPage<SysUser> pageList = pdDepartService.departUserList(page,parMap);
        result.setResult(pageList);
        return result;
    }

    /**
     * 保存角色授权
     *
     * @return
     */
    @RequestMapping(value = "/saveDeptRolePermission", method = RequestMethod.POST)
    public Result<String> saveDeptRolePermission(@RequestBody JSONObject json) {
        long start = System.currentTimeMillis();
        Result<String> result = new Result<>();
        try {
            String roleId = json.getString("roleId");
            String permissionIds = json.getString("permissionIds");
            String lastPermissionIds = json.getString("lastpermissionIds");
            String departId = json.getString("departId");
            pdDepartService.saveDeptRolePermission(roleId, permissionIds, lastPermissionIds,departId);
            result.success("保存成功！");
            log.info("======部门角色授权成功=====耗时:" + (System.currentTimeMillis() - start) + "毫秒");
        } catch (Exception e) {
            result.error500("授权失败！");
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 查询所有用户
     * @param user
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public Result<IPage<SysUser>> queryPageList(SysUser user,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,HttpServletRequest req) {
        Result<IPage<SysUser>> result = new Result<>();
        //查询所有用户 但不包括admin
        Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        user.setDepartParentId(sysUser.getDepartParentId());
        Map<String,Object> parMap = new HashMap<>();
        parMap.put("DEL_FLAG_NORMAL",PdConstant.DEL_FLAG_0);
        parMap.put("name",user.getRealname()==null?"":user.getRealname());
        parMap.put("username",user.getUsername()==null?"":user.getUsername());
        parMap.put("deptParentId",sysUser.getDepartParentId());
        parMap.put("admin",PdConstant.ADMIN_DEPART_CODE);
        IPage<SysUser> pageList = pdDepartService.findUserList(page,parMap);

        //批量查询用户的所属部门
        //step.1 先拿到全部的 useids
        //step.2 通过 useids，一次性查询用户的所属部门名字
        List<String> userIds = pageList.getRecords().stream().map(SysUser::getId).collect(Collectors.toList());
        if(userIds!=null && userIds.size()>0){
            Map<String,String>  useDepNames = sysUserService.getDepNamesByUserIds(userIds);
            pageList.getRecords().forEach(item->{
                //TODO 临时借用这个字段用于页面展示
                item.setOrgCode(useDepNames.get(item.getId()));
            });
        }
        result.setSuccess(true);
        result.setResult(pageList);
        log.info(pageList.toString());
        return result;
    }
}
