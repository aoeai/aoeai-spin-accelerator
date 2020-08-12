package ${packageName};

import lombok.Data;

<#list po.importList as classFullName>
    import ${classFullName};
</#list>

import java.io.Serializable;

/**
* ${classComment}
*
*/
@Data
public class ${className} implements Serializable {

<#list po.fieldList as field>
    /**
    * ${field.comment}
    */
    private ${field.classShortName} ${field.name};

</#list>
}