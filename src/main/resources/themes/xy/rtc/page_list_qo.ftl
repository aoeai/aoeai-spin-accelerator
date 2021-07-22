package ${packageName};

import lombok.Data;
import com.starbuds.server.common.annotation.query.QueryTag;

<#list po.importList as classFullName>
import ${classFullName};
</#list>

import java.io.Serializable;

/**
* ${classComment}
*
*/
@Data
public class ${className} extends PageQO implements Serializable {

<#list po.fieldList as field>
    /**
    * ${field.comment}
    */
    @QueryTag
    private ${field.classShortName} ${field.name};

</#list>
    /**
    * 开始时间
    */
    @QueryTag(column = "createTime", condition = ">=")
    private Long startTime;

    /**
    * 结束时间
    */
    @QueryTag(column = "createTime", condition = "<=")
    private Long endTime;
}