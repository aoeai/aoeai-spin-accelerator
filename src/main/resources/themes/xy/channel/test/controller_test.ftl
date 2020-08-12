package ${packageName};


import com.starbuds.login.ChannelLoginTest;
import com.starbuds.test.utils.XyHttpTools;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.starbuds.test.utils.XyHttpTools.*;
import static com.starbuds.test.utils.XyResultTools.*;

/**
* ${classComment}
*/
@Slf4j
public class ${className} {

    private static String HOST = HOST_CHANNEL_DEV;
    static {
//        HOST = HOST_CHANNEL_TEST_ALY;

        XyHttpTools.pkgType = PKG_TYPE_ADMIN;
        try {
            ChannelLoginTest.saveLoginToken(HOST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 10)
    public void createTest() throws IOException {
        String action = "${urlCommonPath}create";
        Map<String, String> params = new HashMap<>();
        <#list createParamList as param>
        ${param}
        </#list>

        String res = XyHttpTools.post(HOST, action, params);
        checkSuccess(res);
    }

}