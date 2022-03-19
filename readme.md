# 图书管理系统设计文档

## 一、简介

图书借阅是学生获取知识的一个很重要的途径，如何既能方便学生借书，又能减轻图书馆管理人员的工作负担，高效地完成图书借阅管理工作，是一件非常重要的事情。web 图书管理系统可以使图书借阅更加方便和快捷。
本项目为图书管理项目,由原 [book_mng_sys_2019](https://github.com/Newway1997/book_mng_sys_2019) 升级重构而成。

## 二、演示
用户测试账号：user,密码:123456
管理员测试账号:admin,密码:123456
数据库建表sql文件：book_store_2022.sql
也可本地启动
需要安装mysql然后通过sql文件建表
在根目录启动springboot后端项目
进入front-end前端文件夹，运行npm run dev启动前端项目

## 三、技术栈

- 后端：java+springboot+data jpa
- 前端：vue3+typescript+element-plus
## 四、功能设计

图书管理系统主要功能分为两端，一位用户访问的客户端，另一个为管理员访问的管理端。 ### 用户端主要功能有：

1. **用户基础功能**
   - [x] 登录注册注销
   - [x] 修改密码
   - [x] 用户信息查看
   - [x] 用户信息修改
1. **用户图书操作功能**
   - [x] 访问图书列表
   - [x] 查看图书信息
   - [x] 借阅图书
   - [x] 归还图书
   - [x] 查看已借图书


### 管理端主要功能有：

1. **管理员基础功能**
   - [x] 登录注册注销
1. **管理员图书操作功能**
   - [x] 上传图书
   - [x] 查询图书
   - [x] 图书状态管理
   - [x] 修改图书
1. **管理员操作用户功能**
   - [x] 查询用户
   - [x] 封禁解封用户


## 五、 数据库设计

1. 用户

   **user**

   | 字段名   | 类型    | 长度 | 是否为空 | 描述                        |
   | -------- | ------- | ---- | -------- | --------------------------- |
   | id       | int     | 11   | 否       | 主键，用户 id               |
   | username | varchar | 32   | 否       | 用户名                      |
   | password | varchar | 32   | 否       | 密码                        |
   | type     | int     | 11   | 否       | 0 为管理员，1 为用户        |
   | status   | int     | 11   | 否       | 用户状态：0 为禁用 1 为启用 |

   **reader**

   | 字段名 | 类型 | 长度 | 是否为空 | 描述 |
   | -------- | ------- | ---- | -------- | -------------------- |
   | id | int | 11 | 否 | 主键，读者 id |
   | name | varchar | 32 | | 姓名 |
   | age | int | 11 | | 年龄 |
   | sex | int | 11 | | 性别 1：男 or 0：女 |
   | phone | varchar | 11 | | 电话 |
   | regdate | datetime | | | 注册时间 |
   | level|int|11|否|读者等级，默认 0 级
   | status | int | 11 | 否 | 读者状态：0 为禁用 1 为启用 |

   **reader_level**
      
   | 字段名 | 类型 | 长度 | 是否为空 | 描述 |
   | -------- | ------- | ---- | -------- | -------------------- |
   | id | int | 11 | 否 | 主键，读者等级 id |
   | max_br_num | int | 11 | 否 | 最大借阅书本数 |
   | max_br_time | int | 11 | 否 | 最大借阅时间 |

1. 图书

   **book**

   | 字段名 | 类型 | 长度 | 是否为空 | 描述 |
   | -------- | ------- | ---- | -------- | -------------------- |
   | isbn | varchar | 32 | 否 | 主键，书籍 ISBN |
   | category_id | int | 11 | | 书籍类别 |
   | bookname | varchar | 255 | | 书名 |
   | author | varchar | 32 | | 作者 |
   | publisher | varchar | 32 | | 出版社 |
   | publish_date | datetime | | | 出版时间 |
   | unit_price|decimal|10| |单价
   |img_url|varchar|255| |图片地址|
   |summary|text|| |简介|
   |all_num|int|11| |总数量|
   |tags|varchar|255| |标签|
   |last_num|int|11| |剩余数量|
   |status|int|11|否|图书状态，0 为下架，1 为上架|

   **category**

   | 字段名 | 类型 | 长度 | 是否为空 | 描述 |
   | -------- | ------- | ---- | -------- | -------------------- |
   | id | int | | 否 | 主键，书籍类别 id |
   | category_name | varchar | 32 | 否| 书籍类别 |

1. 借阅表

   **borrow_book**

   | 字段名 | 类型 | 长度 | 是否为空 | 描述 |
   | -------- | ------- | ---- | -------- | -------------------- |
   | id | int | 11| 否 | 主键，借阅 id |
   | isbn | varchar | 32 | 否| 书籍类别 |
   | reader_id | int | 11 | 否 | 书名 |
   | borrow_date | datetime | |否 | 作者 |
   | return_date | datetime | | 否 | 出版社 |
   | fine | decimal | 10| | 出版时间 |
   |status|int|11|否|借阅状态 0 为未完成，1 为已完成|

## 六、后端设计

1. entity 数据实体层
   
2. dao 数据访问层
  
3. service 服务层
 
4. controller 控制层

5. domain 实体层
   

## 七、 API

1. 用户操作

   1. 用户登录

      | 请求路径 | /api/user/login   |
      | -------- | ----------------- |
      | 请求方法 | post              |
      | 参数     | username,password |
      | 返回值   | user              |

   1. 用户注册

      | 请求路径 | /api/user/register |
      | -------- | ------------------ |
      | 请求方法 | post               |
      | 参数     | username，password |
      | 返回值   |

   1. 用户注销

      | 请求路径 | /api/user/logout |
      | -------- | ---------------- |
      | 请求方法 | get              |
      | 参数     |
      | 返回值   |

   1. 修改密码

      | 请求路径 | /api/user/updatePass |
      | -------- | -------------------- |
      | 请求方法 | post                 |
      | 参数     | newPass,oldPass      |
      | 返回值   |

   1. 获取用户信息

      | 请求路径 | /api/reader/getReaderInfo |
      | -------- | --------------------- |
      | 请求方法 | get                   |
      | 参数     |       |
      | 返回值   | reader                 |

   1. 用户信息修改

      | 请求路径 | /api/reader/updateUserInfo |
      | -------- | ------------------------ |
      | 请求方法 | post                     |
      | 参数     | reader                     |
      | 返回值   |reader                     |

1. 书籍操作

   1. 访问图书列表

      请求路径 |/api/book/getBooks
      --------|--------
      请求方法 | get
      参数 | pageSize，curPage
      返回值 | books
      
   1. 查看图书信息

      请求路径 | /api/book/getBookDetail
      --------|--------
      请求方法 | get
      参数 | isbn
      返回值 | book

   1. 借阅图书

      请求路径 | /api/book/borrowBook
      --------|--------
      请求方法 | get
      参数 | isbn
      返回值 | book

   1. 归还图书

      请求路径 | /api/book/returnBook
      --------|--------
      请求方法 | get
      参数 | isbn
      返回值 | book

   1. 查看已借图书

      请求路径 | /api/borrowBook/getBorrowBooks
      --------|--------
      请求方法 | get
      参数 |
      返回值 | borrowBooks

   1. 上传图书

      请求路径 | /api/book/uploadBook
      --------|--------
      请求方法 | post
      参数 | book
      返回值 |

   1. 上架图书

      请求路径 | /api/book/enableBook
      --------|--------
      请求方法 | get
      参数 | isbn
      返回值 |

   1. 下架图书

      请求路径 | /api/book/disableBook
      --------|--------
      请求方法 | get
      参数 | isbn
      返回值 |

   1. 修改图书

      请求路径 | /api/book/updateBook
      --------|--------
      请求方法 | get
      参数 | newBook
      返回值 |

   1. 查询用户

      请求路径 | /api/user/getAllUsers
      --------|--------
      请求方法 | get
      参数 | curPage,pageSize
      返回值 | users

   1. 封禁用户

      请求路径 | /api/user/disableUser
      --------|--------
      请求方法 | get
      参数 | id
      返回值 |

   1. 启用用户

      请求路径 | /api/user/enableUser
      --------|--------
      请求方法 | get
      参数 | id
      返回值 |
