package ${packageName};

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

<#list importList as classFullName>
import ${classFullName};
</#list>

/**
 * ${classComment}
 *
 */
@Data
@TableName("${table.name}")
public class ${className} {

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