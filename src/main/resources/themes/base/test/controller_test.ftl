package ${packageName};


import com.wazhima.utils.OkHttpTools;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.wazhima.utils.CheckResultTools.*;

/**
* ${classComment}
*/
@Slf4j
public class ${className} {

    private String HOST = "${hostTest}${urlPrefix}/";

    @Test(priority = 10)
    public void createTest() throws IOException {
        String action = "create";
        Map<String, String> params = new HashMap<>();
        <#list createParamList as param>
        ${param}
        </#list>

        String res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkSuccess(res);
    }

    @Test(priority = 11)
    public void existTest() throws IOException {
        String action = "exist";
        String res = OkHttpTools.get(HOST + action);
        log.debug(res);
        checkFail(res);

        res = OkHttpTools.get(HOST + action + "?id=1");
        log.debug(res);
        checkSuccess(res);
        checkDataTrue(res);

        res = OkHttpTools.get(HOST + action + "?id=" + Long.MAX_VALUE);
        log.debug(res);
        checkSuccess(res);
        checkDataFalse(res);
    }

    @Test(priority = 12)
    public void createOrUpdateTest() throws IOException {
        String action = "createOrUpdate";
        Map<String, String> params = new HashMap<>();
        <#list createParamList as param>
        ${param}
        </#list>

        String res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkSuccess(res);

        params.put("tid", "1");
        <#list createOrUpdateParamList as param>
        ${param}
        </#list>
        res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkSuccess(res);
    }

    @Test(priority = 13)
    public void updateTest() throws IOException {
        String action = "update";
        Map<String, String> params = new HashMap<>();
        <#list createParamList as param>
        ${param}
        </#list>

        String res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkDataFalse(res);

        params.put("tid", "1");
        <#list updateParamList as param>
        ${param}
        </#list>
        res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkSuccess(res);
    }

    @Test(priority = 14)
    public void deleteTest() throws IOException {
        String action = "delete";
        Map<String, String> params = new HashMap<>();

        String res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkFail(res);

        params.put("id", "1");
        res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkDataTrue(res);
    }

    @Test(priority = 15)
    public void getByIdTest() throws IOException {
        String action = "getById";
        String res = OkHttpTools.get(HOST + action);
        log.debug(res);
        checkFail(res);

        res = OkHttpTools.get(HOST + action + "?id=1");
        log.debug(res);
        checkSuccess(res);
        checkDataIsNotNull(res);
    }

    @Test
    public void getPageListTest() throws IOException {
        String action = "getPageList";
        Map<String, String> params = new HashMap<>();
        params.put("pageIndex", "1");

        String res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkFail(res);

        params.put("pageSize", "10");
        res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkSuccess(res);

        params.put("tid", "1");
        res = OkHttpTools.post(HOST + action, params);
        log.debug(res);
        checkSuccess(res);
    }
}