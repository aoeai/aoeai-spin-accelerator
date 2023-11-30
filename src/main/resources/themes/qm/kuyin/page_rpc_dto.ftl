import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ${classComment}
 *
 */
@Data
@Accessors(chain = true)
public class ${className} implements Serializable {

	private Long id;

<#list fieldList as field>
	/**
	 * ${field.comment}
	 */
	private ${field.classShortName} ${field.name};

</#list>
	private Long createTime;
	private Long updateTime;
}