package ${packageName};

import ${po.packageName}.${po.className};
import ${pageListQO.packageName}.${pageListQO.className};

import com.starbuds.server.common.pojo.api.PageList;
import lombok.extern.slf4j.Slf4j;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ${interfaceClass.packageName}.${interfaceClass.className};
import com.starbuds.server.common.pojo.daoplus.UpdateWrapper;

import org.springframework.stereotype.Service;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.transaction.annotation.Transactional;

/**
* ${classComment}
*/
@Service
@Slf4j
public class ${className} implements ${interfaceClass.className} {

    @Reference(version = "1.0.0")
    private ${serviceClass.className} ${serviceClassVariable};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${po.className} create(${po.className} po){
        return ${serviceClassVariable}.create(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createBatch(List<${po.className}> list){
        return ${serviceClassVariable}.createBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrUpdate(${po.className} po){
        return ${serviceClassVariable}.createOrUpdate(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(${po.className} po) {
        return ${serviceClassVariable}.updateById(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateWrapper uw){
        return ${serviceClassVariable}.update(uw);
    }

    @Override
    public ${po.className} getById(Long id){
        return ${serviceClassVariable}.getById(id);
    }

    @Override
    public boolean exist(Long id){
        return ${serviceClassVariable}.exist(id);
    }

    @Override
    public PageList<${po.className}> getPageList(${pageListQO.className} qo){
        return ${serviceClassVariable}.getPageList(qo);
    }

	@Override
	public Map<Long, ${po.className}> getMapByIds(Collection ids){
        return ${serviceClassVariable}.getMapByIds(ids);
    }

    // 手动编码开始

}