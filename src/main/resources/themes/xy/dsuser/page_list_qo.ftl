package ${packageName};

import lombok.Data;
import com.dashang.user.model.query.PageQuery;
import com.dashang.user.annotation.query.QueryTag;

<#list po.importList as classFullName>
<#if classFullName !="java.time.LocalDateTime">
import ${classFullName};
</#if>
</#list>

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* ${classComment}分页查询参数
* @author aoe
*/
@Data
@ApiModel("${classComment}分页查询参数")
public class ${className} extends PageQuery {

<#list po.fieldList as field>
    <#if field.name !="version"
      && field.name !="isDelete"
      && field.name !="createTime"
      && field.name !="modifyTime">
    @ApiModelProperty(value = "${field.comment}")
    @QueryTag(column = "${field.column}")
    private ${field.classShortName} ${field.name};

    </#if>
</#list>

    @ApiModelProperty(value = "开始时间")
    @QueryTag(column = "create_time", condition = ">=")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    @QueryTag(column = "create_time", condition = "<=")
    private String endTime;
}