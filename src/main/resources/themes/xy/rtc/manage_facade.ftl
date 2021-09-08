package ${packageName};

import ${form.packageName}.${form.className};
import ${pageListQO.packageName}.${pageListQO.className};

import com.starbuds.server.common.pojo.api.Result;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
*
* ${classComment}
*/
public interface ${className} {

   /**
	 * 插入数据
	 */
    Result create(@Valid ${form.className} form);

    /**
	 * 更新数据
	 */
    Result update(@Valid ${form.className} form);

    /**
	 * 查询详细信息
     * @param id 主键
	 */
    Result getInfo(@Valid @NotNull(message = "id不能为空") Long id);

    /**
	 * 分页查询
	 */
    Result getPageList(${pageListQO.className} qo);

    // 手动编码开始
}
