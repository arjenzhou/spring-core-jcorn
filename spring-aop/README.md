## Spring AOP

### 概念

- advice 通知
- pointCut 切点
- advisor 切面：advice + pointCut
- advisedSupport 切面：advisor + targetResource（目标对象）
- invocation：方法调用，描述方法的一个执行过程
- adapter：将切面(advisor)适配为拦截器(interceptor)，以此来拦截方法调用(invocation)

### 流程
- 加载配置文件：将 application.json 转化为 aopBeanDefinition（运行时数据结构）
- 获取代理对象：代理不存在时通过 aopBeanDefinition 创建
    - 在 map 中获得 aopBeanDefinition
    - 通过 aopBeanDefinition 获得切面
    - 通过 cglib 获得切面的代理对象
    - 返回代理对象
- cglib 通过 callback（subclass MethodInvocation）增强代理方法（Enhance 继承被代理对象，拦截方法调用并增强）