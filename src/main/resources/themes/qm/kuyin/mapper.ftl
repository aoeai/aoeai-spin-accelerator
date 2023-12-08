import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  ${classComment}
 *
 */
public interface ${className} extends BaseMapper<${po.className}> {

    default IPage<${po.className}> selectPageList(${po.classNameWithoutSuffix}ManagePageParam param) {
        QueryWrapper<${po.className}> query = new QueryWrapper<>();
        query.orderByDesc("id");

        return selectPage(param.page(), query);
    }
}