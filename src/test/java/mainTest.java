import zj.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import zj.tool.Cmd;



/**
 * Created by zj on 2017-2-4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= Application.class, loader=SpringApplicationContextLoader.class)
@WebAppConfiguration
public class mainTest {

    private final String OK = "OK";
    private final String FAIL = "FAIL";
    private MockMvc mvc;
    @Autowired private Cmd cmd;
    @Autowired
    WebApplicationContext webApplicationConnect;
    @Before
    public void setUp() throws JsonProcessingException {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();
    }

    private void check(String uri , String s , String error) throws Exception{
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        String result = Jsoup.parse(content).select("h4[name]").
                first().text();
        System.out.println(result);
        Assert.assertTrue("错误，返回应为200", status == 200);
        Assert.assertTrue(error,result.equals(s));

    }


    @Test
    public void testUnlock() throws Exception{
        check("/unlock/0/1/300" , OK, "解锁失败");
        check("/unlock/0/2/300" , FAIL , "解锁失败,密码用户不匹配");
    }

    @Test
    public void testGetAccounts() throws Exception{
       check("/accounts",OK,"");
    }

    @Test
    public void testGetTransactions() throws Exception{
        check("/getTransaction/11111111",OK,"");
        check("/getTransaction/11",FAIL,"长度应为8");
    }

    @Test
    public void testSendTransactions() throws Exception{
        check("/transaction/as/ew/1/32/111111111",FAIL,"索引应为数字");
        check("/transaction/0/1/111111111111111111111/32/11111111",FAIL,"amount溢出");
        check("/transaction/0/1/11111/32/1111",FAIL,"识别码长度应为8");
        check("/transaction/0/1/11111/32/11111111",FAIL,"未解锁账户");
        cmd.UnlockAccount(0,"1",30);
        check("/transaction/0/1/11111/32/11111111",FAIL,"余额不足");
        check("/transaction/0/1/1/32/11111111",OK,"交易失败，未知原因");
    }

    @Test
    public void testSCreate() throws Exception{
        check("/createAccount/2",OK,"");
    }
}
