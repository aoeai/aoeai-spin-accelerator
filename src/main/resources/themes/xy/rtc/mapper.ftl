package ${packageName};

import ${po.packageName}.${po.className};
import com.starbuds.server.common.pojo.daoplus.QueryWrapper;
import com.starbuds.server.common.pojo.daoplus.UpdateWrapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *  ${classComment}
 *
 */
@Mapper
@Repository
public interface ${className} {

    /**
	 * 插入数据
	 */
	int insert(${po.className} po);

    /**
	 * 插入批量数据
	 */
	int insertBatch(List<${po.className}> list);

    /**
	 * 插入或更新数据
	 */
	int insertOrUpdate(${po.className} po);

    /**
	 * 更新数据
	 */
	int update(UpdateWrapper uw);

    /**
	 * 查询一条记录
	 */
	${po.className} selectOne(QueryWrapper qw);

    /**
	 * 查询所有记录
	 */
	${po.className} selectList(QueryWrapper qw);

    /**
	 * 查询总数
	 */
	int selectCount(QueryWrapper qw);

	/**
	 * 查询列表(分页)
	 */
	List<${po.className}> selectPageList(QueryWrapper qw);

	/**
	 * 根据ids获得 DO Map
	 * @param ids id集合
	 * @return Map<主键, DO对象>
	 */
	@MapKey("${po.firstPrimaryKey}")
	Map<Long, ${po.className}> selectMapByIds(@Param("ids") Collection ids);

}