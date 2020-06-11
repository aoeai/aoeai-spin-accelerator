package ${packageName};

import lombok.Data;

import java.io.Serializable;

<#list importList as classFullName>
import ${classFullName};
</#list>

/**
 * ${classComment}
 *
 */
@Data
public class ${className} implements Serializable {

<#list fieldList as field>
	/**
	 * ${field.comment}
	 */
	private ${field.classShortName} ${field.name};

</#list>
}