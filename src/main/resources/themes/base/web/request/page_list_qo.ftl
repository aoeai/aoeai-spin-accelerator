package ${packageName};

import lombok.Data;
import com.wazhima.common.annotation.QueryTag;
import com.wazhima.common.response.page.PageQO;

<#list po.importList as classFullName>
import ${classFullName};
</#list>

/**
* ${classComment}
*
*/
@Data
public class ${className} extends PageQO {

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
    @QueryTag
    private Long startTime;

    /**
    * 结束时间
    */
    @QueryTag
    private Long endTime;
}