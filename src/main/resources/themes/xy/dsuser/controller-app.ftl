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
import com.dashang.user.common.result.ActionResult;

import com.dashang.user.common.result.ReturnCode;
import com.dashang.user.common.base.BaseController;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
*
* ${classComment}
*/
@RestController
@RequestMapping("app/vod/${pathName}")
@Api(value = "${className}", tags = "${classComment}")
@Slf4j
@Validated
public class ${className} extends BaseController {

    @Resource
    private ${serviceClass.className} ${serviceClassVariable};

    // 手动编码开始
}
