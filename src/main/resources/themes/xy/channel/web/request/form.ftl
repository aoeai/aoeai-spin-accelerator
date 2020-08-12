package ${packageName};

import lombok.Data;

<#list importList as classFullName>
import ${classFullName};
</#list>

import javax.ws.rs.FormParam;
import java.io.Serializable;

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
	<#list field.checkTagList as checkTag>
	${checkTag}
	</#list>
	@FormParam("${field.name}")
	private ${field.classShortName} ${field.name};

</#list>
}