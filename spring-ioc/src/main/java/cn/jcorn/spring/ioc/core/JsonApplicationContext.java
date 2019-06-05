package cn.jcorn.spring.ioc.core;

import cn.jcorn.spring.ioc.bean.BeanDefinition;
import cn.jcorn.spring.ioc.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

public class JsonApplicationContext extends BeanFactoryImpl {
    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 初始化，加载配置文件
     */
    public void init() {
        loadFile();
    }

    /**
     * 加载配置文件的真正方法，遍历注册所有 BeanDefinition
     */
    private void loadFile() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        //此时beanDefinitions的属性已经注入
        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {
        });
        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }
    }
}
