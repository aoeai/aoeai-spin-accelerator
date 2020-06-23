package ${packageName};

import ${mapperService.po.packageName}.${mapperService.po.className};
import ${mapperService.packageName}.${mapperService.className};
import ${pageListQO.packageName}.${pageListQO.className};
import ${vo.packageName}.${vo.className};
import com.wazhima.common.constant.DB;
import com.wazhima.common.response.page.PageList;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import static com.wazhima.common.utils.DaoUtil.setDefaultValue;
import static com.wazhima.common.utils.PageUtil.buildPageListQueryWrapper;

/**
* ${classComment}
*/
@Service
@Slf4j
public class ${className} {

    @Resource
    private ${mapperService.className} ${mapperServiceVariable};

    public boolean create(${mapperService.po.className} po) {
        po.setTid(null);
        po.setCreateTime(System.currentTimeMillis());
        po.setUpdateTime(po.getCreateTime());
        po.setIsDelete(DB.IS_DELETE_NO);
        po = setDefaultValue(po);
        return ${mapperServiceVariable}.save(po);
    }

    public boolean createOrUpdate(${mapperService.po.className} po){
        if (po == null) {
            return false;
        }
        if (exist(po.getTid())) {
            return updateById(po);
        }

        return create(po);
    }

    public boolean updateById(${mapperService.po.className} po) {
        UpdateWrapper wapper = new UpdateWrapper();
        wapper.eq("tid", po.getTid());

        po.setUpdateTime(System.currentTimeMillis());

        return ${mapperServiceVariable}.update(po, wapper);
     }

    public boolean delete(BigInteger id) {
        ${mapperService.po.className} po = new ${mapperService.po.className}();
        po.setTid(id);
        po.setIsDelete(DB.IS_DELETE_YES);
        po.setUpdateTime(System.currentTimeMillis());

        return updateById(po);
    }

    public ${vo.className} getById(BigInteger id) {
        ${mapperService.po.className} po = ${mapperServiceVariable}.getById(id);
        if (po == null) {
            return null;
        }

        ${vo.className} vo = new ${vo.className}();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public boolean exist(BigInteger id) {
        if (id == null) {
            return false;
        }
        QueryWrapper qw = new QueryWrapper();
        qw.eq("tid", id);
        qw.eq("isDelete", DB.IS_DELETE_NO);

        return ${mapperServiceVariable}.count(qw) > 0;
    }

    public PageList getPageList(${pageListQO.className} qo){
        QueryWrapper qw = buildPageListQueryWrapper(qo);
        Page<${mapperService.po.className}> page = new Page<>(qo.getPageIndex(),qo.getPageSize());;
        List<${mapperService.po.className}> records = ${mapperServiceVariable}.page(page, qw).getRecords();

        List<${vo.className}> voList = new ArrayList<>(records.size());
        for (${mapperService.po.className} po : records) {
            ${vo.className} vo = new ${vo.className}();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        }

        PageList pageList = new PageList(qo);
        pageList.setList(voList);
        pageList.setTotal(page.getTotal());
        return pageList;
    }
}