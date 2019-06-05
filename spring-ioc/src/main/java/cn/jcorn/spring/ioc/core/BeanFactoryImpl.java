package cn.jcorn.spring.ioc.core;

import cn.jcorn.spring.ioc.bean.BeanDefinition;
import cn.jcorn.spring.ioc.bean.ConstructorArg;
import cn.jcorn.spring.ioc.utils.BeanUtils;
import cn.jcorn.spring.ioc.utils.ClassUtils;
import cn.jcorn.spring.ioc.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BeanFactoryImpl implements BeanFactory {
    private static final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap =
            new ConcurrentHashMap<>();
    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());


    /**
     * 获取bean
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        Object bean = beanMap.get(name);
        //若bean被加载过
        if (bean != null) {
            return bean;
        }
        //bean 未被加载过，创建 bean
        bean = createBean(beanDefinitionMap.get(name));
        if (bean != null) {
            //创建成功后
            populateBean(bean);
            //将依赖注入完成的 bean 放入 beanMap 中
            beanMap.put(name, bean);
        }

        return bean;

    }

    /**
     * 注入bean的依赖
     * @param bean
     * @throws Exception
     */
    private void populateBean(Object bean) throws Exception {
        //获得bean的参数
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String beanName = StringUtils.uncapitalize(field.getName());
                //判断参数bean是否已经初始化过
                if (beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    //注入
                    if (fieldBean != null) {
                        ReflectionUtils.injectField(field, bean, fieldBean);
                    }
                }
            }
        }
    }

    /**
     * 创建 bean
     *
     * @param beanDefinition beanDefinition
     * @return
     * @throws Exception
     */
    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        //从 beanDefinition 中获取 ClassName
        String beanName = beanDefinition.getClassName();
        //得到bean的Class
        Class clazz = ClassUtils.loadClass(beanName);
        if (clazz == null) {
            throw new Exception("can not find bean <" + beanName + "> by beanName");
        }
        //构造方法注入
        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        if (constructorArgs != null && !constructorArgs.isEmpty()) {
            //存放构造方法中参数的列表
            List<Object> objects = new ArrayList<>();
            for (ConstructorArg constructorArg : constructorArgs) {
                if (constructorArg.getValue() != null) {
                    objects.add(constructorArg.getValue());
                } else {
                    objects.add(getBean(constructorArg.getRef()));
                }
            }
            //获取每个参数的 Class 对象
            Class[] constructorArgTypes = objects.stream().map(it -> it.getClass()).collect(Collectors.toList()).toArray(new Class[]{});
            //通过反射获取bean的构造方法
            Constructor constructor = clazz.getConstructor(constructorArgTypes);
            return BeanUtils.instanceByCglib(clazz, constructor, objects.toArray());
            //构造方法没有参数
        } else {
            return BeanUtils.instanceByCglib(clazz, null, null);
        }
    }


    /**
     * 注册 Bean，将其加入到 beanDefinitionMap 和 beanNameSet 中
     *
     * @param name           BeanDefinition 的 name
     * @param beanDefinition BeanDefinition
     */
    protected void registerBean(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
        beanNameSet.add(name);
    }
}
