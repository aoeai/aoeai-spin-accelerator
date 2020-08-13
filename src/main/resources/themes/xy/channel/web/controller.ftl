package ${packageName};

import ${po.packageName}.${po.className};
import ${serviceClass.packageName}.${serviceClass.className};
import ${form.packageName}.${form.className};
import ${pageListQO.packageName}.${pageListQO.className};
import ${interfaceClass.packageName}.${interfaceClass.className};

import javax.annotation.Resource;
import javax.ws.rs.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.starbuds.server.common.pojo.api.Result;
import com.starbuds.server.common.constant.common.GlobalApiError;

import org.apache.dubbo.rpc.protocol.rest.support.ContentType;
import static com.starbuds.server.common.utils.CheckFormUtil.*;

/**
*
* ${classComment}
*/
@Path("/v1/channel/补全路径/")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
@Component
@Slf4j
public class ${className} extends BaseFacade implements ${interfaceClass.className} {

    @Resource
    private ${serviceClass.className} ${serviceClassVariable};

   /**
	 * 插入数据
	 */
    @POST
    @Path("create") 
    @Override
    public Result create(@BeanParam ${form.className} form){
        String check = checkCreate(form);
        if (check != null) {
            return new Result(GlobalApiError.BadRequest, check);
        }

        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);

        if (${serviceClassVariable}.create(po) == null) {
            return new Result(GlobalApiError.OperationFailed, "创建失败");
        }
        return new Result("创建成功");
    }

    /**
	 * 更新数据
	 */
    @POST
    @Path("update")
    @Override 
    public Result update(@BeanParam ${form.className} form){
        String check = checkUpdate(form);
        if (check != null) {
            return new Result(GlobalApiError.BadRequest, check);
        }

        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);
        boolean flag = ${serviceClassVariable}.updateById(po);

        if (!flag) {
            return new Result(GlobalApiError.OperationFailed, "更新失败");
        }
        return new Result("更新成功");
    }

    /**
	 * 查询详细信息
     * @param id 主键
	 */
    @GET
    @Path("getInfo") 
    @Override 
    public Result getInfo(@QueryParam("id") Long id){
        if (id == null) {
            return new Result(GlobalApiError.BadRequest, "id不能为空");
        }
        return new Result(${serviceClassVariable}.getVOById(id));
    }

    /**
	 * 判断是否存在
     * @param id 主键
     * @return true:存在
	 */
    @GET
    @Path("exist")  
    @Override 
    public Result exist(@QueryParam("id") Long id){
        if (id == null) {
            return new Result(GlobalApiError.BadRequest, "id不能为空");
        }
        return new Result(${serviceClassVariable}.exist(id));
    }

    /**
	 * 分页查询
	 */
    @GET
    @Path("getPageList") 
    @Override 
    public Result getPageList(@BeanParam ${pageListQO.className} qo){
        return new Result(${serviceClassVariable}.getVOPageList(qo));
    }

    // 手动编码开始
}
