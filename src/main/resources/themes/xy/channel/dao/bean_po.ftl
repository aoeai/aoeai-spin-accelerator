package ${packageName};

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

<#list importList as classFullName>
import ${classFullName};
</#list>
import java.io.Serializable;

/**
 * ${classComment}
 *
 */
@Data
public class ${className} implements Serializable {

<#list fieldList as field>
	/**
	 * ${field.comment}
	 */
	private ${field.classShortName} ${field.name};

</#list>
}