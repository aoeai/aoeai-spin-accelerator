package ${packageName};

import lombok.Data;

<#list importList as classFullName>
import ${classFullName};
</#list>

/**
 * ${classComment}
 *
 */
@Data
public class ${className} {

<#list fieldList as field>
	/**
	 * ${field.comment}
	 */
	<#list field.checkTagList as checkTag>
	${checkTag}
	</#list>
	private ${field.classShortName} ${field.name};

</#list>
}