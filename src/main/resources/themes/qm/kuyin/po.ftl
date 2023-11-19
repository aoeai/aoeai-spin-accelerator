import com.baomidou.mybatisplus.annotation.TableName;
import com.xc.ml.mybatis.dataobject.DeletableDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * ${classComment}
 *
 */
@TableName("${table.name}")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ${className} extends DeletableDO {

<#list fieldList as field>
	/**
	 * ${field.comment}
	 */
	<#if field.isPrimaryKey>
	@TableId(type = IdType.AUTO)
	<#else>
		<#lt><#rt>
	</#if>
	private ${field.classShortName} ${field.name};

</#list>
}