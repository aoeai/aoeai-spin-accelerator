# 使用说明

## PO 类生成配置举例

- src/main/java/com/aoeai/spin/accelerator/themes/customize/xy/dashang
  - XyDashangThemeRegister.java
  - factory
    - XyDsPoFactory.java
    
- src/main/java/com/aoeai/spin/accelerator/themes/customize/xy/dashang
  - config
    - base-config.yaml
    - po.yaml
  - po.ftl

## 1. 注册自定义模板

- 目录 : src/main/java/com/aoeai/spin/accelerator/themes/customize/xy/dashang

- 1.1. XyDashangThemeRegister.java 自定义方法入口
- 1.2. XyDsMapperFactory.java 

## 2. 各工厂配置信息

- 目录 : src/main/resources/themes/xy/dashang/config

- 2.0. base-config.yaml : 自定义变量（$开头）
- 2.1. po.yaml : PO（数据库对应的）持久对象配置信息

## 3. 各工厂实现模板
- 目录 : src/main/resources/themes/xy/dashang
- 3.1. po.ftl : PO（数据库对应的）持久对象模板

## 4. 各模块实现工厂
- 目录 : src/main/java/com/aoeai/spin/accelerator/themes/customize/xy/dashang/factory

### 4.1. XyDsPoFactory.java
- 创建PO（数据库对应的）持久对象