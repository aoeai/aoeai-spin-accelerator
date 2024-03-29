package ${packageName};

import lombok.Data;

<#list importList as classFullName>
import ${classFullName};
</#list>

/**
 * ${classComment}
 * @author aoe
 */
@Data
public class ${className} {

<#list fieldList as field>
	/**
	 * ${field.comment}
	 */
	private ${field.classShortName} ${field.name};

</#list>
}