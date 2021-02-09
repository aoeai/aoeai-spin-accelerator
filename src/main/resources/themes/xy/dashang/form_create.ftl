package ${packageName};

import lombok.Data;
import com.dashang.vod.annotation.check.*;

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
	<#if field.name !="version"
      && field.name !="isDelete"
      && field.name !="createTime"
      && field.name !="modifyTime">
	@ApiModelProperty(value = "${field.comment}", required = true)
	<#if field.checkTagList??>
	<#list field.checkTagList as checkTag>
	${checkTag}
	</#list>
	</#if>
	private ${field.classShortName} ${field.name};

	</#if>
</#list>
}