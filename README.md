# YOA系统

系统使用springboot来搭建项目，使用maven来管理项目以及实现多模块开发。系统为五个模块组成，分别为
- common：公用模块，用于存放base基类和一些公用工具类
- web：程序入口，存放所有的配置文件
- doc：公文管理模块，用于存放公文管理的model、service、controller以及前端js和html文件
- inform：公告管理模块，用于存放公告管理的model、service、controller以及前端js和html文件
- sys：系统管理模块，用于存放系统管理的model、service、Controller以及前端js和html文件

系统约定各模块包名具有相同结构，在后续进行功能扩展时也要遵循这些约定
- com.yoa.model:存放实体类，字段应与数据库相关联
- com.yoa.dao:存放数据层接口类
- com.yoa.service:存放服务层接口类
- com.yoa.service.impl:存放服务层具体的实现类
- /mappers/:存放持久层映射文件
- /static/：存放各类静态资源，如js、css、amage等
- /templates/:存放前端模板文件，即html文件

