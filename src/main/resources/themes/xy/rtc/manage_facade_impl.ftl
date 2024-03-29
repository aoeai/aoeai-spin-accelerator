package ${packageName};

import ${po.packageName}.${po.className};
import ${serviceClass.packageName}.${serviceClass.className};
import ${form.packageName}.${form.className};
import ${pageListQO.packageName}.${pageListQO.className};
import ${facade.packageName}.${facade.className};

import javax.annotation.Resource;
import javax.ws.rs.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.starbuds.server.common.pojo.api.Result;
import com.starbuds.server.common.constant.common.GlobalApiError;

import org.apache.dubbo.rpc.protocol.rest.support.ContentType;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
*
* ${classComment}
*/
@Path("/v1/${actionSuffix}/")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
@Component
@Slf4j
@Validated
public class ${className} extends BaseFacade implements ${facade.className} {

    @Resource
    private ${serviceClass.className} ${serviceClassVariable};

   /**
	 * 插入数据
	 */
    @POST
    @Path("create${po.classNameWithoutSuffix}")
    @Override
    public Result create(@BeanParam @Valid ${form.className} form){
        String checkForm = checkForm(form);
        if (checkForm != null) {
            return Result.ofBadRequest(checkForm);
        }

        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);

        if (${serviceClassVariable}.create(po) == null) {
            return new Result(GlobalApiError.OperationFailed, "创建失败");
        }
        return Result.ofSuccess("创建成功");
    }

    /**
	 * 更新数据
	 */
    @POST
    @Path("update${po.classNameWithoutSuffix}")
    @Override 
    public Result update(@BeanParam @Valid ${form.className} form){
        String checkForm = checkForm(form);
        if (checkForm != null) {
            return Result.ofBadRequest(checkForm);
        }

        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);
        boolean flag = ${serviceClassVariable}.updateById(po);

        if (!flag) {
            return new Result(GlobalApiError.OperationFailed, "更新失败");
        }
        return Result.ofSuccess("更新成功");
    }

    /**
	 * 查询详细信息
     * @param id 主键
	 */
    @GET
    @Path("get${po.classNameWithoutSuffix}ById")
    @Override 
    public Result getInfo(@QueryParam("id") @Valid @NotNull(message = "id不能为空") Long id){
        return Result.ofSuccess(${serviceClassVariable}.getById(id));
    }

    /**
	 * 分页查询
	 */
    @GET
    @Path("get${po.classNameWithoutSuffix}PageList")
    @Override 
    public Result getPageList(@BeanParam ${pageListQO.className} qo){
        return Result.ofSuccess(${serviceClassVariable}.getPageList(qo));
    }

    /**
     * 检查表单数据
     * @param form
     * @return null:数据正确；错误原因
     */
    private String checkForm(${form.className} form) {
        return null;
    }

    // 手动编码开始
}
