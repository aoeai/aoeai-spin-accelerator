package ${packageName};

import ${po.packageName}.${po.className};
import ${mapperClass.packageName}.${mapperClass.className};
import com.dashang.user.common.base.BaseServicePlus;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
* ${classComment}
* @author aoe
*/
@Service
@Slf4j
public class ${className} extends BaseServicePlus<${mapperClass.po.className}> {

    @Resource
    private ${mapperClass.className} ${mapperClassVariable};

    @Override
    protected BaseDAOPlus<${mapperClass.po.className}> dao() {
        return ${mapperClassVariable};
    }

}