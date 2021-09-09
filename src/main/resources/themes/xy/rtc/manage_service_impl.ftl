package ${packageName};

import ${po.packageName}.${po.className};
import ${mapperClass.packageName}.${mapperClass.className};
import ${pageListQO.packageName}.${pageListQO.className};

import com.starbuds.server.common.pojo.api.PageList;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ${interfaceClass.packageName}.${interfaceClass.className};
import ${manageProviderClass.packageName}.${manageProviderClass.className};
import com.starbuds.server.common.pojo.daoplus.UpdateWrapper;
import com.starbuds.server.common.service.core.IdentityService;

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

    @Resource
    private ${mapperClass.className} ${mapperClassVariable};

    @Reference(version = "1.0.0")
    private IdentityService identityService;

    @Resource
    private ${manageProviderClass.className} ${manageProviderVariable};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${po.className} create(${po.className} po){
        return ${manageProviderVariable}.create(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createBatch(List<${po.className}> list){
        return ${manageProviderVariable}.createBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrUpdate(${po.className} po){
        return ${manageProviderVariable}.createOrUpdate(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(${po.className} po) {
        return ${manageProviderVariable}.updateById(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateWrapper uw){
        return ${manageProviderVariable}.update(uw);
    }

    @Override
    public ${po.className} getById(Long id){
        return ${manageProviderVariable}.getById(id);
    }

    @Override
    public boolean exist(Long id){
        return ${manageProviderVariable}.exist(id);
    }

    @Override
    public PageList<${po.className}> getPageList(${pageListQO.className} qo){
        return ${manageProviderVariable}.getPageList(qo);
    }

	@Override
	public Map<Long, ${po.className}> getMapByIds(Collection ids){
        return ${manageProviderVariable}.getMapByIds(ids);
    }

    // 手动编码开始

}