#SSH框架下单元测试的实现

##  实现的功能

- 实现了部门的增删改查
- 对Action进行了单元测试
- 对Service 进行了单元测试，通过mock的方式实现。
##  实现的步骤
**一、对Action层的单元测试实现**
  1、首先在pom文件中需要引入的依赖
  
   ```
       
        <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.12</version>
           <scope>test</scope>
         </dependency>
         <dependency>
           <groupId>org.mockito</groupId>
           <artifactId>mockito-core</artifactId>
           <version>1.10.19</version>
           <scope>test</scope>
         </dependency>
         <dependency>
           <groupId>org.apache.struts</groupId>
           <artifactId>struts2-junit-plugin</artifactId>
           <version>2.1.8</version>
           <scope>test</scope>
         </dependency>
         <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-test</artifactId>
           <version>3.2.8.RELEASE</version>
         </dependency> 
               
   ```
   说明：
      在此处我们引入struts2-junit-plugin.2.1.8.jar,因为其里面有测试Struts2中action的类
      StrutsSpringTestCase,用来测试ssh中的action。
   2、新建一个测试类
   如果你使用的是idea，那么你可以直接在需要建立测试类的Action类中
   按ctrl+shift+t,新建一个测试类。如DepartmentActionTest
   3、如果是普通的action的话,
   待测试代码：
   
   ```
          public String findById() {
                 String did = ServletActionContext.getRequest().getParameter("did");
                 department=departmentService.findByDid(Integer.valueOf(did));
                 return "goEditDepartment";
             }  
       
   ```
   测试代码：
   
   ```
          @Test
             public void testFindById() throws Exception {
                  /*这个函数相当@Before注解的函数，是调用单元测试后的时候，
                首先会执行的方法。可以在这里面做一些必要的准备工作*/
                 request.setParameter("did", "1");
                 ActionProxy proxy = getActionProxy("/department_findById.action");
                 DepartmentAction action = (DepartmentAction) proxy.getAction();
                 String result = action.findById();
                 Assert.assertEquals("goEditDepartment", result);
             }

   ```
   
   4、如果是返回json的action的话，待测试代码：
   
   
   ```
    public void  findAll() {
            List<Department> departmentList= departmentService.findAll();
            JSONObject jsonObject = new JSONObject();
            if (CollectionUtils.isEmpty(departmentList)) {
                jsonObject.put("key", "success");
                jsonObject.put("data", JSON.toJSONString(departmentList));
                writeJson(jsonObject);
                return;
            }
            jsonObject.put("key", "success");
            jsonObject.put("data", JSON.toJSONString(departmentList));
            writeJson(jsonObject);
        }
   ```
   测试代码：
   
   ```
   String result = executeAction("/json_findAll.action");
           Assert.assertNotNull(result);
           JSONObject jsonObject = JSON.parseObject(result);
           if ("success".equals(jsonObject.getString("key"))) {
               List<Department> departmentList = JSON.parseArray(jsonObject.getString("data"), Department.class);
               if (CollectionUtils.isEmpty(departmentList)) {
                   return;
               }
               for (Department department : departmentList) {
                   System.out.println(department.toString());
               }
           }
   
   ```
   5、关于lazy问题的解决：首先在setUp()函数中加入下述代码：
   
   ```
        @Override
            protected void setUp() throws Exception {
                super.setUp();
                SessionFactory sessionFactory = lookupSessionFactory(request);
                Session hibernateSession= getSession(sessionFactory);
                TransactionSynchronizationManager.bindResource(sessionFactory,
                        new SessionHolder(hibernateSession));
                //在只读模式下(FlushMode.NEVER/MANUAL)写操作不被允许
                hibernateSession.setFlushMode(FlushMode.AUTO);
            }
            
   ```
   然后在添加两个私有函数
   
   ```
    private Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
           Session session = sessionFactory.openSession();
           FlushMode flushMode = FlushMode.NEVER;
           if (flushMode != null) {
               session.setFlushMode(flushMode);
           }
           return session;
       }
       private SessionFactory lookupSessionFactory(HttpServletRequest request) {
           //“sessionFactory”是你spring配置文件（通常是application.xml）中的SessionFactory。
           //如：org.springframework.orm.hibernate4.annotation.AnnotationSessionFactoryBean
           return (SessionFactory)this.applicationContext.getBean("sessionFactory");
       }
    
   ```
   
   说明：通过lookupSessionFactory()方法获取这个测试环境中的org.hibernate.SessionFactory实体对象，其中
   applicationContext是org.springframework.context.ApplicationContext的实现org.springframework.context.support.GenericApplicationContext
   我们可以通过它的getBean方法来获取你配置文件中配置的SessionFactory。使用注解是org.springframework.orm.hibernate4.annotation.AnnotationSessionFactoryBean
   不需要使用注解的时候可以使用org.springframework.orm.hibernate.LocalSessionFactoryBean来实现。
   
   该单元测试需要加载spring配置文件信息，默认加载路径是你项目的src目录下，文件名默认为applicationContext.xml，如果路径不对或者
   文件名不同，则需要重写getContextLocations()方法,如
   
   ```
    protected String getContextLocations() {
           return "classpath*:applicationContext.xml";
       }

   ```
   关于实现web session的问题，很简单，该类提供了一个MockHttpServletRequest成员变量，我们只要mock一个session出来，然后加入到这个request中，就可以实现session的模拟了。示例代码如下：
   
   ```  
     HttpSession  session = new MockHttpSession();
    String sessionId = UUID.randomUUID().toString();
    session.setAttribute(ConstParameter.USER_SESSION, sessionId);
    //user是一个用户信息的类，你可以根据你的需要自己定义
    UserInfor user = new UserInfo();
    user.setUserId(1);
    user.setName("xxx");
    session.setAttribute(ConstParameter.USER_INFO, user);
    request.setSession(session);
    
   ```
   关于action的单元测试我们就说完了。
   **二、Service的单元测试**
   接下来我们在说说关于service的测试，
   同样，我们先从依赖说起，需要添加的依赖：
  ```
      <powermock.version>1.7.1</powermock.version>

     <!--********************powermock使用*********************-->
          <dependency>
            <groupId>org.powermock</groupId>
            <artifactId>powermock-module-junit4</artifactId>
            <version>${powermock.version}</version>
            <scope>test</scope>
          </dependency>
          <dependency>
            <groupId>org.powermock</groupId>
            <artifactId>powermock-api-mockito</artifactId>
            <version>${powermock.version}</version>
            <scope>test</scope>
          </dependency>
    
  ```
   此处我们采用的是powermock+junit 进行mock测试。为啥使用powermock呢，毋庸置疑，由于他的功能比较强大
   1、首先，我们我们看看待测试的代码：
 ```
  @Override
     public List<MyInvoice> getMyInvoice(String buyerId) {
         if (StringUtils.isBlank(buyerId)) {
             return null;
         }
         List<MyInvoice> myInvoices = new ArrayList<MyInvoice>();
         String url = baseDataUrl + UrlConfig.GET_MYINVOICE_URL + "?t=" + VerifyBaseUtil.getT()
                 + "&token=" + VerifyBaseUtil.getToken() + "&buyerId=" + buyerId;
         System.out.println("MyInvoiceServiceImpl getMyInvoice接口请求参数为：" + url);
         try {
             String responseInfo = HttpUtil.getHttp(url);
             System.out.println("MyInvoiceServiceImpl getMyInvoice接口返回结果为：" + responseInfo);
             Map<String, Object> result = JSON.parseObject(responseInfo, Map.class);
             if (DistrictReturnNum.SUCCESS.getValue().equals(result.get("code"))) {
                 myInvoices = JSON.parseArray(JSON.toJSONString(result.get("result")), MyInvoice.class);
                 return myInvoices;
             }
         } catch (Exception e) {
             System.out.println("MyInvoiceServiceImpl getMyInvoice 程序出错,查询发票失败"+e.getMessage());
             return null;
         }
         return null;
     }
 ```
 getMyInvoice方法是一个调用外部接口的方法，通过http协议进行通信。这儿有两个问题
 1.HttpUtil.getHttp(url) 是一个静态方法，我们如何mock？
 2.我们如何mock这个方法所在的实现类？因为该实现类是通过spring ioc 容器生成并注入的。
 
 要回答这两个问题，我们首先需要看看，我们的测试代码：
  ```
  @RunWith(PowerMockRunner.class)
  @PrepareForTest(HttpUtil.class)
  public class MyInvoiceServiceImplTest {
      @InjectMocks
      private MyInvoiceService myInvoiceService = new MyInvoiceServiceImpl();
      @Before
      public void setUp(){
          PowerMockito.mockStatic(HttpUtil.class);
      }
      @Test
      public void testGetMyInvoice() throws Exception {
          String result_http="{\"result\":[{\"addDate\":1509010776000,\"buyerId\":" +
                  "\"9E59A2D27B7748848FB65041B854240E\",\"headName\":\"项伟测试\"," +
                  "\"headType\":\"0\",\"invoiceId\":\"9747A51B57FF4EA781F1CFDF73A0D9DF\"," +
                  "\"invoiceType\":\"0\",\"isDefault\":0},{\"addDate\":1509092635000,\"" +
                  "buyerId\":\"9E59A2D27B7748848FB65041B854240E\",\"editDate\":1509094177000,\"headName\":\"项伟测试二\",\"headType\":\"0\",\"invoiceId\":\"720CF6C50E594283B01C79D03D6D52B2\"" +
                  ",\"invoiceType\":\"0\",\"isDefault\":1}],\"msg\":\"成功\",\"code\":104}";
  //      1、  buyerId为空
          String buyerId = null;
          Assert.assertEquals(null, myInvoiceService.getMyInvoice(buyerId));
  //        2、buyerId不为空
          buyerId = "FF8080810F5E601526";
          PowerMockito.when(HttpUtil.getHttp(anyString())).thenReturn(result_http);
          List<MyInvoice> result = myInvoiceService.getMyInvoice(buyerId);
          Assert.assertEquals(2,result.size());
      }
    }
    
  ```
  第一个问题：我们通过PowerMockito.mockStatic(HttpUtil.class); 一个静态方法的实现类。然后就可以调用该静态方法。
  第二个问题：@InjectMocks注解来mock我们需要测试的业务类。
  至此，我们就可以通过powermock对service层的方法进行单元测试了。

## 运行环境
- 环境描述：Struts2+Spring4.2.4+hibernate4
  JAVA 1.7
- 额外依赖第三方jar包,请参考pom.xml
