API总体设计要求

基本规范
1. 请求和相应 HTTP 头部字段
   Version 标识API的版本信息
   Songmsso-Sessionid 会话ID
   
2. Cookie 中保存 songmsso_sessionid 会话ID

3. URI 路径节点使用英文字符、数值组成，全部小写

4. 请求参数小写，单词间使用下划线分割

5. Path 路径规范
path = /{资源}/{子资源}/{行为}
{资源}  用一个名词来代表系统中资源
{子资源}没有可以不用不用书写
{行为}  对资源进行的相关操作

API具体设计

1. 注册
POST /register.json

参数
account String 


