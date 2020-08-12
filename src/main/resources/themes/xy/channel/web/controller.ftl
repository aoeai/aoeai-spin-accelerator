package ${packageName};

import ${po.packageName}.${po.className};
import ${serviceClass.packageName}.${serviceClass.className};
import ${form.packageName}.${form.className};
import ${pageListQO.packageName}.${pageListQO.className};
import ${interfaceClass.packageName}.${interfaceClass.className};

import javax.ws.rs.*;
import org.springframework.beans.BeanUtils;

import com.starbuds.server.common.pojo.api.Result;
import com.starbuds.server.common.constant.common.GlobalApiError;

import org.apache.dubbo.rpc.protocol.rest.support.ContentType;

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
        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);
        boolean flag = ${serviceClassVariable}.create(po);

        if (!flag) {
            return new Result(GlobalApiError.BadRequest, "创建失败");
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
        return new Result();
    }

    /**
	 * 查询详细信息
     * @param id 主键
	 */
    @GET
    @Path("getInfo") 
    @Override 
    public Result getInfo(Long id){
        return new Result();
    }

    /**
	 * 判断是否存在
     * @param id 主键
     * @return true:存在
	 */
    @GET
    @Path("exist")  
    @Override 
    public Result exist(Long id){
        return new Result();
    }

    /**
	 * 分页查询
	 */
    @GET
    @Path("getPageList") 
    @Override 
    public Result getPageList(@BeanParam ${pageListQO.className} qo){
        return new Result();
    }

    // 手动编码开始
}
