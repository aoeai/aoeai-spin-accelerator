package ${packageName};

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

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
	private ${field.classShortName} ${field.name};

</#list>
}