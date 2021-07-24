package ${packageName};

import com.alibaba.fastjson.JSON;
import ${po.packageName}.${po.className};
import ${providerClass.packageName}.${providerClass.className};
import ${pageListQO.packageName}.${pageListQO.className};

import com.starbuds.server.common.pojo.api.PageList;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    private ${providerClass.className} ${providerClassVariable};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${po.className} create(${po.className} po){
        return ${providerClassVariable}.create(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createBatch(List<${po.className}> list){
        return ${providerClassVariable}.createBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrUpdate(${po.className} po){
        return ${providerClassVariable}.createOrUpdate(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(${po.className} po) {
        return ${providerClassVariable}.updateById(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateWrapper uw){
        return ${providerClassVariable}.update(uw);
    }

    @Override
    public ${po.className} getById(Long id){
        return ${providerClassVariable}.getById(id);
    }

    @Override
    public boolean exist(Long id){
        return ${providerClassVariable}.exist(id);
    }

    @Override
    public PageList<${po.className}> getPageList(${pageListQO.className} qo){
        return ${providerClassVariable}.getPageList(qo);
    }

	@Override
	public Map<Long, ${po.className}> getMapByIds(Collection ids){
        return ${providerClassVariable}.getMapByIds(ids);
    }

    // 手动编码开始

}