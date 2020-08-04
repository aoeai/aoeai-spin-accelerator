package ${packageName};

import ${po.packageName}.${po.className};
import com.starbuds.server.common.pojo.qw.QueryWrapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *  ${classComment}
 *
 */
@Mapper
@Repository
public interface ${className} {

    /**
	 * 新增
	 */
	int insert(${po.className} po);

    /**
	 * 更新数据
	 */
	int update(${po.className} po);

    /**
	 * 根据相等条件查询数据
	 */
	${po.className} selectByEQ(Long id);

    /**
    * 查询列表总数
    */
    int selectCountPageList(QueryWrapper qw);

	/**
	 * 查询列表(分页)
	 */
	List<${po.className}> selectPageList(QueryWrapper qw);

}