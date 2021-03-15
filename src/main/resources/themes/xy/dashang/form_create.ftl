package ${packageName};

import lombok.Data;
import javax.validation.constraints.*;

<#list importList as classFullName>
import ${classFullName};
</#list>

import io.swagger.annotations.ApiModelProperty;

/**
 * ${classComment}创建表单
 *
 */
@Data
public class ${className} {

<#list fieldList as field>
	<#if field.name !="id"
      && field.name !="version"
      && field.name !="isDelete"
      && field.name !="createTime"
      && field.name !="modifyTime">
	<#if field.checkTagList??>
	@ApiModelProperty(value = "${field.comment}", required = true)
	<#list field.checkTagList as checkTag>
	${checkTag}
	</#list>
	<#else>
	@ApiModelProperty(value = "${field.comment}", required = false)
	</#if>
	private ${field.classShortName} ${field.name};

	</#if>
</#list>
}