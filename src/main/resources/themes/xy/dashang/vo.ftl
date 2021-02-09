package ${packageName};

import lombok.Data;

<#list po.importList as classFullName>
<#if classFullName !="java.time.LocalDateTime">
import ${classFullName};
</#if>
</#list>

import io.swagger.annotations.ApiModelProperty;

/**
* ${classComment}
* @author aoe
*/
@Data
public class ${className} {
<#list po.fieldList as field>
    <#if field.name !="version"
      && field.name !="isDelete"
      && field.name !="createTime"
      && field.name !="modifyTime">
    @ApiModelProperty(value = "${field.comment}", required = true)
    private ${field.classShortName} ${field.name};

    </#if>
</#list>

    @ApiModelProperty(value = "创建时间", required = true)
    private Long createTime;
}