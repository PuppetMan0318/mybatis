# mybatis
1.  Mybatis的动态sql就是根据条件动态的处理sql语句，来达到一次编写可以在不同的场景或条件下使用。
    Mybatis 提供了 9 种动态 SQL 标签：\<if/\>、\<choose/\>、\<when/\>、\<otherwise/\>、\<trim/\>、\<when/\>、\<set/\>、\<foreach/\>、\<bind/\>。
    执行原理是从SQL参数对象中计算表达式的值,根据表达式的值动态拼接 SQL，以此来完成动态SQL的功能。
2.  Mybatis仅支持association关联对象和collection关联集合对象的延迟加载。
    在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。
    它的原理是，使用CGLIB创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，拦截器invoke()方法发现a.getB()是null值，
    那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b)，于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用。
3.  Mybatis有三种基本的Executor执行器:SimpleExecutor、ReuseExecutor、BatchExecutor。
    SimpleExecutor：每执行一次增删改查操作，就开启一个Statement对象，用完立刻关闭Statement对象。
    ReuseExecutor：执行增删改查操作，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map内，供下一次使用。
    BatchExecutor：执行update时将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，
    等待逐一执行executeBatch()批处理。
4.  Mybatis的一级缓存的作用域是SqlSession范围的，使用HashMap存储缓存数据在内存中。在同一个SqlSession中执行两次相同的sql语句时，第一次执行完毕时将数据库中的数据存到缓存中，第二次执行时就直接去缓存中
    取数据。如果SqlSession执行了增删改操作，并且提交到数据库，MyBatis则会清空SqlSession中的一级缓存。或者SqlSession结束后该SqlSession中的一级缓存也就不存在了。
    二级缓存是基于mapper文件的namespace的，二级缓存的存储介质比较多样，不一定只存在内存中，也可以存在硬盘中。在mapper的用一个namespace中，如果有其他增删改操作，并且提交到数据库，
    设置了statement配置中的flushCache="true"是，就会刷新缓存，是原来的二级缓存失效。
5.  Mybatis插件运行原理：在Executor、StatementHandler、ParameterHandler、ResultSetHandler对象创建时，每个创建出来的对象不是直接返回的，而是interceptorChain.pluginAll(parameterHandler);
    获取到所有的Interceptor（拦截器）（插件需要实现的接口）；调用interceptor.plugin(target);返回target包装后的对象。                        
    插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面）我们的插件可以为四大对象创建出代理对象；代理对象就可以拦截到四大对象的每一个执行。
    自定义插件： 1) 实现Mybatis的插件接口Interceptor，重写其中的intercept()、plugin()、setProperties()方法,通过注解@Intercepts配置一个或多个需要拦截的对象；
                2) @Signature中的type属性配置拦截拦截的接口、method属性配置拦截接口内的方法名，args属性配置需要拦截方法的入参；
                3)配置mybatis的配置文件，加入自定义的插件，配置需要传入的参数。