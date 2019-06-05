package cn.jcorn.spring.aop.core;

import cn.jcorn.spring.aop.bean.AopBeanDefinition;
import cn.jcorn.spring.ioc.bean.BeanDefinition;
import cn.jcorn.spring.ioc.utils.ClassUtils;
import cn.jcorn.spring.ioc.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

public class AopApplicationContext extends AopBeanFactoryImpl {

    private String fileName;

    public AopApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        loadFile();
    }

    /**
     * 加载配置文件，获得 aopBeanDefinition
     */
    private void loadFile() {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<AopBeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<AopBeanDefinition>>() {
        });

        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {

            for (AopBeanDefinition beanDefinition : beanDefinitions) {
                Class<?> clz = ClassUtils.loadClass(beanDefinition.getClassName());
                if (clz == ProxyFactoryBean.class) {
                    registerBean(beanDefinition.getName(), beanDefinition);
                } else {
                    registerBean(beanDefinition.getName(), (BeanDefinition) beanDefinition);
                }
            }
        }

    }
}
