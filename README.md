# todolist-springboot

网址：http://www.zzfhasweb.xyz:9091/

# 借鉴别人的想法:
1.	邮箱验证码: 用MD5Utils 类来加密验证码，并在上传信息中与用户上传的验证码进行hash值的比较
2.	Sweetalert库的swal函数可以美化alert函数并且有回调函数，非常方便
3.	这个网站的UI非常好看 ：http://www.htmleaf.com/jQuery/Form/list_10_5.html
# 自己的功能完善：
1.0：注册，登录，修改密码，添加任务，完成任务，自动显示所有任务，邮箱自动提醒

2.0： 新增加：
1.不同的ergency对应不同的背景色 
2.可以修改发布后的任务内容 
3.必须通过邮箱验证码进行注册
4.GET传输变成POST传输，信息更安全
5.可以通过邮箱找回密码
6.邮箱提醒的信息更完善
7.美化的提醒框

将来会完善：服务端鉴权，限制设置简单密码，ajax跨域问题


# 一些难题或bug的解决
前期：
1.	判断是否登陆成功，注册成功: 定义result类返回前端json字符串
并在Controller中加注解 
  


2.	时间储存格式 后端加注解 @JsonFormat(pattern = "yyyy-MM-dd HH:mm") @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm") 前端用Html5自带的datetime控件 数据库中用datetime类型储存。并且要含datetime域的类继承BaseController 


3. 邮箱发送 
1)smtp申请：QQ邮箱的最有用（尝试了gmail和163），并且要SSL加密 
2）在后端做时间运算（mapper添加返回todolist中各项时间的时间戳，与当地时间进行比较） 
3)自动计时器：@enablescheduling 注解定义计时器，并重写方法已发送邮件 
4)cron的书写
 


4. 主页中存有登录时的界面的信息，并且返回todolist 
1)创建cookie并接收，要注意及时清理cookie
 2)从前端传username在数据库中查找


5. 除了表单，还想发一些其他的数据 （这个就应该不用跨域了）
1)在js中创建一个XMLHttpRequest对象，send URL，可以替代表单进行请求 
 
2)在前端给表单加一些正则字符串以实现整齐的数据库
 


6. 前端给前端，前端给后端，后端给前端等url中存在中文乱码 先在前后端分别声明utf-8的编码，再解码（cookie也需要）
 前端解码cookie
 给后端发的数据用utf-8编码


7. 如果运行spring时一些配置找不到，给maven托管
 
后期
1.	数据库时间比后端时间少了14个小时,在数据库配置加上：
 


2.	jq的层次选择器很有用…
 


3.	配置云服务器，在xshell中先在云服务器中配置环境（docker不好搞）:java,mysql(或者是他的分支MariaDB)，然后maven打jar包(要在execute maven goal中verify:install)，然后上传后写这两条命令（在云服务器里不能翻译.class文件，说找不到主类（暂未解决））：
 
 


# 不足:
1.	数据库一开始建的不好
 
 


2.	前端写的很混乱，应该把很多步骤抽象增加可读性.或者把函数加到控件上，或者独立出来一个script
 


3.	后端没有很好的调用各个类之间的关系，有的时候初始化一个对象就Ok了，我却又写了一个方法…
