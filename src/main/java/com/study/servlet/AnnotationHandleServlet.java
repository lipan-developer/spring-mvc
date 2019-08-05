package com.study.servlet;


import com.study.annoation.Controller;
import com.study.annoation.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 实现自己的dispatchservlet，用来实现相应的操作
 */

/**
 * 学习阶段：自己的思路，init启动的时候先将所有的加了注解的扫描出来，然后将类和全限定命放到map中，在get和post时候跟据相应的路径请求时实列话类创建对象，得到结果返回
 * 求学阶段：
 *      AnnotationHandleServlet初始化(init)时扫描指定的包下面使用了Controller注解的类
 *          1 遍历类中的方法，找到类中使用了RequestMapping注解标注的那些方法，获取RequestMapping注解的value属性值，value属性值指明了该方法的访问路径，
 *            以RequestMapping注解的value属性值作为key，Class类作为value将存储到一个静态Map集合中
 *            login/handle-----> Hello.class
 *          2 当用户请求时(无论是get还是post请求)，会调用封装好的execute方法 ，execute会先获取请求的url，然后解析该URL，根据解析好的URL从Map集合中取出要调用的目标类 ，
 *            再遍历目标类中定义的所有方法，找到类中使用了RequestMapping注解的那些方法，
 *            判断方法上面的RequestMapping注解的value属性值是否和解析出来的URL路径一致,如果一致，说明了这个就是要调用的目标方法，
 *            此时就可以利用java反射机制先实例化目标类对象，然后再通过实例化对象调用要执行的方法处理用户请求。服务器将以下图的方式与客户端进行交互
 *
 */

public class AnnotationHandleServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        //必须对父类中的config进行赋值，super.init(config);正是做了这样的操作
        super.init(config);
        System.out.println("---初始化开始---");
//        //获取web.xml中配置的要扫描的包
//        String basePackage = config.getInitParameter("basePackage");
//        //如果配置了多个包，例如：<param-value>me.gacl.web.controller,me.gacl.web.UI</param-value>
//        if (basePackage.indexOf(",")>0) {
//            //按逗号进行分隔
//            String[] packageNameArr = basePackage.split(",");
//            for (String packageName : packageNameArr) {
//                initRequestMapingMap(packageName);
//            }
//        }else {
//            initRequestMapingMap(basePackage);
//        }
        System.out.println("----初始化结束---");
    }

    private void initRequestMapingMap(String packageName){
        //得到扫描包下的class
//        Set<Class<?>> setClasses =  ScanClassUtil.getClasses(packageName);
//        for (Class<?> clazz :setClasses) {
//
//            if (clazz.isAnnotationPresent(Controller.class)) {
//                Method[] methods = BeanUtils.findDeclaredMethods(clazz);
//                for(Method m:methods){//循环方法，找匹配的方法进行执行
//                    if(m.isAnnotationPresent(RequestMapping.class)){
//                        String anoPath = m.getAnnotation(RequestMapping.class).value();
//                        if(anoPath!=null && !"".equals(anoPath.trim())){
//                            if (RequestMapingMap.getRequesetMap().containsKey(anoPath)) {
//                                throw new RuntimeException("RequestMapping映射的地址不允许重复！");
//                            }
//                            //把所有的映射地址存储起来  映射路径--类
//                            RequestMapingMap.put(anoPath, clazz);
//                        }
//                    }
//                }
//            }
//        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        super.doGet(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("doPost");
    }
}
