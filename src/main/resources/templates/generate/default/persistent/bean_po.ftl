package ${table.entityPackageName};

import lombok.Data;

<#list importList as classFullName>
import ${classFullName};
</#list>

/**
 * aoeai mysql tools generate.
 * ${table.comment}
 *
 */
@Data
public class ${table.className} {

<#list table.columns as column>
	/**
	 * ${column.comment}
	 */
	private ${column.javaFieldType} ${column.javaFieldName};

</#list>

}