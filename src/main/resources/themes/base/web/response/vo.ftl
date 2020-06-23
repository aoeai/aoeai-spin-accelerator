package ${packageName};

import lombok.Data;

<#list po.importList as classFullName>
    import ${classFullName};
</#list>

/**
* ${classComment}
*
*/
@Data
public class ${className} {

<#list po.fieldList as field>
    /**
    * ${field.comment}
    */
    private ${field.classShortName} ${field.name};

</#list>
}