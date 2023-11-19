import com.xc.ml.systemservice.bo.BaseManagePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * ${classComment}
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ${className} extends BaseManagePage<${po.className}> {

	private Date startTime;

	private Date endTime;
}