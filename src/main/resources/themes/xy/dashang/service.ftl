package ${packageName};

import com.alibaba.fastjson.JSON;
import ${po.packageName}.${po.className};
import ${mapperClass.packageName}.${mapperClass.className};
import ${pageListQO.packageName}.${pageListQO.className};

import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.dashang.vod.constant.sql.DbColumn.PRIMARY_KEY;
import static com.dashang.vod.util.PageListUtil.buildPageListQueryWrapper;
import com.dashang.vod.common.daoplus.UpdateWrapper;
import com.dashang.vod.common.daoplus.QueryWrapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* ${classComment}
* @author aoe
*/
@Component
@Slf4j
public class ${className} {

    @Resource
    private ${mapperClass.className} ${mapperClassVariable};

    /**
	 * 插入数据
	 * @param po
	 * @return null:插入失败
	 */
    @Transactional(rollbackFor = Exception.class)
    public ${mapperClass.po.className} create(${mapperClass.po.className} po){
        po.setCreateTime(LocalDateTime.now());
<#list po.fieldList as field>
    <#if field.name =="modifyTime">
        po.setModifyTime(null);
    </#if>
</#list>
        boolean flag = ${mapperClassVariable}.insert(po) == 1;

        String json = JSON.toJSONString(po);
        if (flag) {
            log.info("创建成功 {}", json);
            return po;
        }
        log.warn("创建失败 {}", json);
        return null;
    }

    /**
	 * 插入批量数据
	 */
    @Transactional(rollbackFor = Exception.class)
    public boolean createBatch(List<${mapperClass.po.className}> list){
        boolean flag = ${mapperClassVariable}.insertBatch(list) > 0;
        return flag;
    }

    /**
	 * 插入或更新数据
	 */
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrUpdate(${mapperClass.po.className} po){
        boolean flag = ${mapperClassVariable}.insertOrUpdate(po) == 1;
        return flag;
    }

    /**
	 * 更新数据
	 */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(${mapperClass.po.className} po) {
        po.setCreateTime(null);
<#list po.fieldList as field>
    <#if field.name =="modifyTime">
        po.setModifyTime(null);
    </#if>
</#list>

        UpdateWrapper uw = new UpdateWrapper();
        uw.setPo(po);
        QueryWrapper qw = new QueryWrapper();
        qw.eq(PRIMARY_KEY, po.${getPkMethod}());
        uw.setQw(qw);
        return update(uw);
    }

    /**
	 * 更新数据
	 */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateWrapper uw){
        boolean flag = ${mapperClassVariable}.update(uw) > 0;
        return flag;
    }

    /**
	 * 根据id查询
	 */
    public ${po.className} getById(Long id){
        QueryWrapper qw = new QueryWrapper();
        qw.eq(PRIMARY_KEY, id);
        return ${mapperClassVariable}.selectOne(qw);
    }

    /**
	 * 判断是否存在
     * @param id 主键
     * @return true:存在
	 */
    public boolean exist(Long id){
        if (id == null) {
            return false;
        }
        QueryWrapper qw = new QueryWrapper();
        qw.eq(PRIMARY_KEY, id);
        return ${mapperClassVariable}.selectCount(qw) > 0;
    }

   /**
	 * 分页查询
	 */
    public PageInfo<${po.className}> getPageList(${pageListQO.className} qo){
        QueryWrapper qw = buildPageListQueryWrapper(qo);
        PageHelper.startPage(qo.getPage(), qo.getSize());
        PageInfo<${po.className}> pageInfo = new PageInfo<>(${mapperClassVariable}.selectPageList(qw));
        return pageInfo;
    }

    // 手动编码开始

}