package ${packageName};

import com.alibaba.fastjson.JSON;
import com.wazhima.pojo.response.Result;
import ${po.packageName}.${po.className};
import ${serviceClass.packageName}.${serviceClass.className};
import ${form.packageName}.${form.className};
import ${pageListQO.packageName}.${pageListQO.className};
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
*
* ${classComment}
*/
@RestController
@RequestMapping("/${urlPrefix}")
@Slf4j
public class ${className} {

    @Resource
    private ${serviceClass.className} ${serviceClassVariable};

    @PostMapping("/create")
    public Result create(@Validated ${form.className} form, BindingResult bindingResult) {
        long traceId = System.currentTimeMillis();
            if (log.isDebugEnabled()) {
            log.debug("创建对象-接收数据 traceId={} form={}", traceId, JSON.toJSONString(form));
        }

        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);
        log.info("创建对象-转换PO数据 traceId={} po={}", traceId, JSON.toJSONString(po));

        boolean result = ${serviceClassVariable}.create(po);
        log.info("创建对象-执行结果 traceId={} result={}", traceId, result);

        return new Result(result);
    }

    @PostMapping("/createOrUpdate")
    public Result createOrUpdate(@Validated ${form.className} form, BindingResult bindingResult) {
        long traceId = System.currentTimeMillis();
        if (log.isDebugEnabled()) {
            log.debug("创建或更新对象-接收数据 traceId={} form={}", traceId, JSON.toJSONString(form));
        }

        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);
        log.info("创建或更新对象-转换PO数据 traceId={} po={}", traceId, JSON.toJSONString(po));

        boolean result = ${serviceClassVariable}.createOrUpdate(po);
        log.info("创建或更新对象-执行结果 traceId={} result={}", traceId, result);

        return new Result(result);
    }

    @PostMapping("/update")
    public Result update(@Validated ${form.className} form, BindingResult bindingResult) {
        long traceId = System.currentTimeMillis();
        if (log.isDebugEnabled()) {
            log.debug("更新对象-接收数据 traceId={} form={}", traceId, JSON.toJSONString(form));
        }

        ${po.className} po = new ${po.className}();
        BeanUtils.copyProperties(form, po);
        log.info("更新对象-转换PO数据 traceId={} po={}", traceId, JSON.toJSONString(po));

        boolean result = ${serviceClassVariable}.updateById(po);
        log.info("更新对象-执行结果 traceId={} result={}", traceId, result);

        return new Result(result);
    }

    @PostMapping("/delete")
    public Result delete(BigInteger id) {
        long traceId = System.currentTimeMillis();
        log.info("删除对象-接收数据 traceId={} id={}", traceId, id);

        boolean result = ${serviceClassVariable}.delete(id);
        log.info("删除对象-执行结果 traceId={} result={}", traceId, result);

        return new Result(result);
    }

    @GetMapping("getById")
    public Result getById(BigInteger id) {
        if (log.isDebugEnabled()) {
            log.debug("根据Id查询对象 id={}", id);
        }

        return new Result(${serviceClassVariable}.getById(id));
    }

    @GetMapping("exist")
    public Result exist(BigInteger id) {
        if (log.isDebugEnabled()) {
            log.debug("根据Id判断对象是否存在 id={}", id);
        }

        return new Result(${serviceClassVariable}.exist(id));
    }

    @PostMapping("getPageList")
    public Result getPageList(@Validated ${pageListQO.className} qo, BindingResult bindingResult) {
        if (log.isDebugEnabled()) {
            log.debug("分页查询 qo={}", JSON.toJSONString(qo));
        }

        return new Result(${serviceClassVariable}.getPageList(qo));
    }
}
