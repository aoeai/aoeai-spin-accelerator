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

/**
*
* ${classComment}
*/
@RestController
@RequestMapping("app/vod/${pathName}")
@Api(value = "${className}", description = "${classComment}")
@Slf4j
public class ${className} {

    @Resource
    private ${serviceClass.className} ${serviceClassVariable};

    // 手动编码开始
}
