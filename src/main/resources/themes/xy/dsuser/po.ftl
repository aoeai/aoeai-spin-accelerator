package ${packageName};

import com.dashang.user.common.base.BaseEntityPlus;
import lombok.Data;

<#list importList as classFullName>
import ${classFullName};
</#list>

/**
 * ${classComment}
 * @author aoe
 */
@Data
public class ${className} implements BaseEntityPlus {

<#list fieldList as field>
	/**
	 * ${field.comment}
	 */
	private ${field.classShortName} ${field.name};

</#list>
}