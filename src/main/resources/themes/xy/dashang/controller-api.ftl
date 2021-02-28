package ${packageName};

import ${po.packageName}.${po.className};
import ${serviceClass.packageName}.${serviceClass.className};
import ${formCreate.packageName}.${formCreate.className};
import ${formUpdate.packageName}.${formUpdate.className};
import ${pageListQO.packageName}.${pageListQO.className};

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.dashang.vod.common.result.ActionResult;

import com.dashang.vod.common.result.ReturnCode;
import com.dashang.vod.common.base.BaseController;

/**
*
* ${classComment}
*/
@RestController
@RequestMapping("api/vod/${pathName}")
@Api(value = "${className}", tags = "${classComment}API")
@Slf4j
public class ${className} extends BaseController {

    @Resource
    private ${serviceClass.className} ${serviceClassVariable};

    @ApiOperation(value = "插入数据")
    @PostMapping(value = "create", produces = {"application/json;charset=UTF-8"})
    public ActionResult create(@RequestBody ${formCreate.className} form){
        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);

        if (${serviceClassVariable}.create(po) == null) {
            return new ActionResult<>(ReturnCode.FAIL);
        }
        return new ActionResult<>();
    }

    @ApiOperation(value = "更新数据")
    @PostMapping(value = "update", produces = {"application/json;charset=UTF-8"})
    public ActionResult update(@RequestBody ${formUpdate.className} form){
        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);

        if (!${serviceClassVariable}.updateById(po)) {
            return new ActionResult<>(ReturnCode.FAIL);
        }
        return new ActionResult<>();
    }

    /**
	 * 查询详细信息
     * @param id 主键
	 */
    @ApiOperation(value = "查询详细信息")
    @GetMapping(value = "getInfo/{id}", produces = {"application/json;charset=UTF-8"})
    public ActionResult getInfo(@PathVariable Long id){
        if (id == null) {
            return new ActionResult<>(ReturnCode.PARAM_INVALID);
        }
        return new ActionResult<>(${serviceClassVariable}.getById(id));
    }

    /**
	 * 判断是否存在
     * @param id 主键
     * @return true:存在
	 */
    @ApiOperation(value = "判断是否存在")
    @GetMapping(value = "exist/{id}", produces = {"application/json;charset=UTF-8"})
    public ActionResult exist(@PathVariable Long id){
        if (id == null) {
            return new ActionResult<>(ReturnCode.PARAM_INVALID);
        }
        return new ActionResult<>(${serviceClassVariable}.exist(id));
    }

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "getPageList", produces = {"application/json;charset=UTF-8"})
    public ActionResult getPageList(@RequestBody ${pageListQO.className} qo){
        return new ActionResult<>(${serviceClassVariable}.getPageList(qo));
    }

    // 手动编码开始
}
