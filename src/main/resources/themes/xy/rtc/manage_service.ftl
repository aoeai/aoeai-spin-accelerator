package ${packageName};

import ${po.packageName}.${po.className};
import ${pageListQO.packageName}.${pageListQO.className};

import com.starbuds.server.common.pojo.api.PageList;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.starbuds.server.common.pojo.daoplus.UpdateWrapper;

/**
* ${classComment}
*/
public interface ${className} {

    /**
	 * 插入数据
	 * @param po
	 * @return null:插入失败
	 */
    ${po.className} create(${po.className} po);

    /**
	 * 插入批量数据
	 */
	boolean createBatch(List<${po.className}> list);

    /**
	 * 插入或更新数据
	 */
    boolean createOrUpdate(${po.className} po);

    /**
	 * 更新数据
	 */
    boolean updateById(${po.className} po);

    /**
	 * 更新数据
	 */
    boolean update(UpdateWrapper uw);

    /**
	 * 根据id查询
	 */
    ${po.className} getById(Long id);

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
	 * 根据ids获得 DO Map
	 * @param ids id集合
	 * @return Map<主键, DO对象>
	 */
	Map<Long, ${po.className}> getMapByIds(Collection ids);

    // 手动编码开始

}