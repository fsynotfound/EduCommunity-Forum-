package com.nowcoder.community;

import com.nowcoder.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//以XX配置类运行
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTest {

    @Autowired
    private SensitiveFilter sensitiveFilter;


    @Test
    public void testSensitiveFilter(){
        String text = "这里面可以赌博，可以嫖娼，可以吸毒，可以开票，哈哈哈！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
        text = "这里面可以☆赌博☆，可以☆嫖☆娼☆，可以☆吸☆毒☆，可以☆开☆票☆，哈哈哈！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }
}
