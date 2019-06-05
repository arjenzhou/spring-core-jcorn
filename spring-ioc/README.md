## Spring IOC

### 流程
- 初始化：加载配置文件，将其转化为运行时数据结构（BeanDefinition）
- 获取 bean：
    - 注入构造器：通过 BeanDefinition 得到 bean 的 Class 对象，获得构造方法的参数，反射得到 bean 实例
    - 属性注入：反射获取 bean 的属性。根据 bean name 判断 bean 是否已经加载过，未加载则加载后再注入
- 使用 bean