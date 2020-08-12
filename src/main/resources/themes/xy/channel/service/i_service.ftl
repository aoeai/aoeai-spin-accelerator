package ${packageName};

import ${mapperService.po.packageName}.${mapperService.po.className};
import ${pageListQO.packageName}.${pageListQO.className};
import ${vo.packageName}.${vo.className};

import java.util.List;

import com.starbuds.server.common.pojo.daoplus.UpdateWrapper;

/**
* ${classComment}
*/
public interface ${className} {

    /**
	 * 插入数据
	 */
    boolean create(${mapperService.po.className} po);

    /**
	 * 插入批量数据
	 */
	boolean createBatch(List<${mapperService.po.className}> list);

    /**
	 * 插入或更新数据
	 */
    boolean createOrUpdate(${mapperService.po.className} po);

    /**
	 * 更新数据
	 */
    boolean update(UpdateWrapper uw);

    /**
	 * 根据id查询
	 */
    ${po.className} getById(Long id);

    /**
	 * 根据id查询
	 */
    ${vo.className} getVOById(Long id);

    /**
	 * 判断是否存在
     * @param id 主键
     * @return true:存在
	 */
    boolean exist(Long id);

    /**
	 * 分页查询
	 */
    PageList<${po.className}> getPageList(${pageListQO.className} qo);

    /**
	 * 分页查询
	 */
    PageList<${vo.className}> getVOPageList(${pageListQO.className} qo);

// 手动编码开始

}