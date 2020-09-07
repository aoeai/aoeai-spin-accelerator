package ${packageName};

import ${mapperService.po.packageName}.${mapperService.po.className};
import ${mapperService.packageName}.${mapperService.className};
import ${mapperClass.packageName}.${mapperClass.className};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* ${classComment}
*/
@Service
public class ${className} extends ServiceImpl<${mapperClass.className}, ${mapperService.po.className}> implements ${mapperService.className} {

}
