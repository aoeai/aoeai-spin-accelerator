package ${packageName};

import com.alibaba.fastjson.JSON;
import ${po.packageName}.${po.className};
import ${mapperClass.packageName}.${mapperClass.className};
import ${pageListQO.packageName}.${pageListQO.className};
import ${vo.packageName}.${vo.className};

import com.starbuds.server.common.pojo.api.PageList;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

import ${interfaceClass.packageName}.${interfaceClass.className};
import com.starbuds.server.common.pojo.daoplus.UpdateWrapper;
import com.starbuds.server.common.pojo.daoplus.QueryWrapper;
import com.starbuds.server.common.service.core.IdentityService;
import static com.starbuds.server.common.pojo.util.PageListUtil.buildPageListQueryWrapper;

import org.springframework.stereotype.Service;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.transaction.annotation.Transactional;

/**
* ${classComment}
*/
@Service
@Slf4j
public class ${className} implements ${interfaceClass.className} {

    /**
     * 主键
     */
    private String PRIMARY_KEY = "${pkColumn}";

    @Reference(version = "1.0.0")
    private IdentityService identityService;

    @Resource
    private ${mapperClass.className} ${mapperClassVariable};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${mapperClass.po.className} create(${mapperClass.po.className} po){
        po.${setPkMethod}(identityService.getId());
        po.setCreateTime(System.currentTimeMillis());
        po.setUpdateTime(po.getCreateTime());
        boolean flag = ${mapperClassVariable}.insert(po) == 1;

        String json = JSON.toJSONString(po);
        if (flag) {
            log.info("创建成功 {}", json);
            return po;
        }
        log.warn("创建失败 {}", json);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createBatch(List<${mapperClass.po.className}> list){
        boolean flag = ${mapperClassVariable}.insertBatch(list) > 0;
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrUpdate(${mapperClass.po.className} po){
        boolean flag = ${mapperClassVariable}.insertOrUpdate(po) == 1;
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(${mapperClass.po.className} po) {
        po.setCreateTime(null);
        po.setUpdateTime(System.currentTimeMillis());
        
        UpdateWrapper uw = new UpdateWrapper();
        uw.setPo(po);
        QueryWrapper qw = new QueryWrapper();
        qw.eq(PRIMARY_KEY, po.${getPkMethod}());
        uw.setQw(qw);
        return update(uw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateWrapper uw){
        boolean flag = ${mapperClassVariable}.update(uw) > 0;
        return flag;
    }

    @Override
    public ${po.className} getById(Long id){
        QueryWrapper qw = new QueryWrapper();
        qw.eq(PRIMARY_KEY, id);
        return ${mapperClassVariable}.selectOne(qw);
    }

    @Override
    public ${vo.className} getVOById(Long id){
        ${po.className} po = getById(id);
        if (po == null) {
            return null;
        }
        ${vo.className} vo = new ${vo.className}();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    @Override
    public boolean exist(Long id){
        if (id == null) {
            return false;
        }
        QueryWrapper qw = new QueryWrapper();
        qw.eq(PRIMARY_KEY, id);
        return ${mapperClassVariable}.selectCount(qw) > 0;
    }

    @Override
    public PageList<${po.className}> getPageList(${pageListQO.className} qo){
        QueryWrapper qw = buildPageListQueryWrapper(qo);
        PageList<${po.className}> page = new PageList<>(qo.getPageIndex(),qo.getPageSize());
        qw.setPageCursor(page.getPageCursor());
        qw.setPageSize(page.getPageSize());
        page.setList(${mapperClassVariable}.selectPageList(qw));
        page.setTotal(${mapperClassVariable}.selectCount(qw));
        return page;
    }

    @Override
    public PageList<${vo.className}> getVOPageList(${pageListQO.className} qo){
        PageList<${vo.className}> page = new PageList<>(qo.getPageIndex(),qo.getPageSize());
        PageList<${po.className}> poPage = getPageList(qo);
        page.setTotal(poPage.getTotal());

        List<${vo.className}> voList = new ArrayList<>(poPage.getList().size());
        for (${po.className} po : poPage.getList()) {
            ${vo.className} vo = new ${vo.className}();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        }
        page.setList(voList);
        return page;
    }

    // 手动编码开始

}