package ${packageName};

import ${po.packageName}.${po.className};
import com.starbuds.server.common.pojo.daoplus.QueryWrapper;
import com.starbuds.server.common.pojo.daoplus.UpdateWrapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *  ${classComment}仅限单元测试使用
 *
 */
@Mapper
@Repository
public interface ${className} {

    /**
	 * 插入数据
	 */
	@Insert("INSERT INTO ${po.table.name} (<#list po.table.columns as column>${column.name}<#if column_has_next>,</#if></#list>) VALUES (<#list po.table.columns as column>#${r'{'}${column.name}}<#if column_has_next>,</#if></#list>)")
	int insertMinify(${po.className} po);

    /**
     * 插入数据
     */
    @Insert("INSERT INTO ${po.table.name} " +
            "(<#list po.table.columns as column>${column.name}<#if column_has_next>,</#if></#list>) " +
            "VALUES " +
            "(<#list po.table.columns as column>#${r'{'}${column.name}}<#if column_has_next>,</#if></#list>)")
    int insert(${po.className} po);


}