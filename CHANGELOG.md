>相关 SDK 更新内容
> * [Android](https://github.com/GuanceCloud/datakit-android/blob/dev/ft-sdk/CHANGELOG.md) 
> * [iOS ](https://github.com/GuanceCloud/datakit-ios/blob/develop/CHANGELOG.md) 

## 0.5.1-pre.2
* 适配 Android ft-sdk:1.5.2，ft-native 1.1.1 ft-plugin 1.3.1
* 适配 iOS 1.5.1

## 0.5.1-pre.1
* 优化休眠唤醒页面监听的设置方式，无需设置 FTLifeRecycleHandler
* 修正 Android 特殊场景长时间使用后，会偶现频繁刷新 session_id 的问题
* Android 适配 ft-sdk 1.5.1

---

## 0.5.1-dev.4
* 优化休眠唤醒页面监听的设置方式，无需设置 FTLifeRecycleHandler
* 修正 Android 特殊场景长时间使用后，会偶现频繁刷新 session_id 的问题
* Android 适配 ft-sdk 1.5.1.-alpha03

---
## 0.5.1-dev.3
* 扩充 FTDialogRouteFilterObserver，针对 PopupRoute 类型页面进行过滤
* Android 适配 ft-sdk 1.5.0，iOS 适配 1.5.0

---

## 0.5.0-pre.1
* 同 0.5.0-dev.2，0.5.0-dev.3

---

## 0.5.0-dev.3
* 添加 FTDialogRouteFilterObserver，针对 DialogRoute 类型页面进行针对过滤

---
## 0.5.0-dev.2
* FTRouteObserver 添加 routeFilter, 可以过滤不进行 View 追踪的页面

---
## 0.4.6-pre.1
* 同 0.4.6-dev.1

---
## 0.4.6-dev.1
* Android 适配 ft-sdk:1.4.3, ft-native:1.1.0, ft-plugin:1.3.0
* iOS 适配 1.4.11

---
## 0.4.5-pre.4
* iOS 适配 1.4.9-beta.5，iOS webview 数据 time 精度问题

---
## 0.4.5-pre.3
* iOS 适配 1.4.9-beta.4，处理 macOS 编译环境兼容问题

---
## 0.4.5-pre.2
* iOS 适配 1.4.9-beta.3

---
## 0.4.5-pre.1
* Android 适配 1.4.1-beta01，iOS 适配 1.4.9-beta.1
* 同 0.4.5-dev.1

---
## 0.4.5-dev.1
* 新增 dataway 上传方式
* Error 数据新增 errorType 数据
* 修复 iOS enableUserResource 关闭，获取 trace header 崩溃的问题
* iOS 适配 1.4.9-alpha.5，Android 适配 1.4.1-alpha01

---
## 0.4.4-dev.1
* Android 添加最大重试次数配置，添加内部日志接管对象, 处理 addResource 某些情况出现异常的的问题
* Android 适配 ft-sdk:1.3.17-alpha05
* iOS 1.4.8-alpha.3

---
## 0.4.3-pre.1
* Android ft-sdk:1.3.17-beta01,ft-plugin:1.2.2-beta01
* iOS 1.4.7-beta.1

---
## 0.4.3-dev.5
* Android ft-sdk:1.3.16-alpha05

---
## 0.4.3-dev.4
* Android ft-sdk:1.3.16-beta01
* iOS 适配 1.4.6-alpha.4

---
## 0.4.3-dev.3
* Android ft-sdk:1.3.16-alpha02
* iOS 适配 1.4.6-alpha.1

---
## 0.4.3-dev.2
* webview 功能更新适配
* Android ft-sdk:1.3.16-alpha01,ft-plugin:1.2.2-alpha01
* iOS 适配 1.4.5-alpha.1

---
## 0.4.2-pre.3
* 调整对 Java 版本的兼容性

---
## 0.4.2-pre.2
* 移除对 http 库的依赖

---
## 0.4.2-pre.1
* 新增将自定义日志打印至控制台的功能
* 同 0.4.2-dev.1

---
## 0.4.2-dev.1
* iOS 适配 1.4.4-beta.1
* Android 适配 ft-sdk:1.3.13-beta01

---
## 0.4.1-dev.1
* iOS 适配 1.4.3-alpha.1
* Android 适配 ft-sdk:1.3.12-beta01，ft-native:1.0.0-beta01

---
## 0.4.0-dev.2
* SDK version 显示问题修复

---
## 0.4.0-dev.1
* 修正 ios
* android ft-sdk:1.3.12-alpha01
* ios sdk 1.4.1-alpha.3

---
## 0.3.0-dev.1
* 新增 View Action Resource Log 添加扩展 property
* 适配 Android agent_1.3.10-beta02 
* 适配 iOS 1.4.1-alpha.2

---
## 0.2.8-dev.9
* 适配 Android 1.3.10-beta01

---
## 0.2.8-dev.8
* 适配 Android 1.3.10-alpha01

---
## 0.2.8-dev.7
* 优化 ErrorMonitorType DeviceMetricsMonitorType 配置方式

---
## 0.2.8-dev.6
* iOS 适配 1.3.9-alpha.14

---
## 0.2.8-dev.5
* iOS 适配 1.3.9-alpha.13
* 优化 route name 显示

---
## 0.2.8-dev.3
* trackEventFromExtension 方法调整

---
## 0.2.8-dev.2
* 适配 iOS 1.3.9-alpha.11

---
## 0.2.8-dev.1
* 适配 iOS 1.3.9-alpha.10
* 适配 Android ft-sdk:1.3.8-beta03

---
## 0.2.7-dev.4
* 适配 Android ft-sdk:1.3.8-beta02

---
## 0.2.7-dev.3
* 修改 FTLifeRecycleHandler removeObserver 错误

---
## 0.2.7-dev.2
* 添加 RUM 页面自动监测唤醒和休眠的方式

---
## 0.2.7-dev.1
* FTRouteObserver 从示例添加至 SDK

---
## 0.2.6-dev.1
* 添加 http autoTrack
* RUM view 补充不设置 route name，获取到 view_name 的方式

---
## 0.2.5-dev.4
* iOS action 传参调整

---
## 0.2.5-dev.3
* 优化 Android ID 获取规则，可以动态控制

---
## 0.2.5-dev.2
* 修正 iOS 监控类型参数不起作用的问题

---
## 0.2.5-dev.1
* 添加用户信息设置
* 添加页面监控指标上报

---
## 0.2.4-dev.1
* 添加 longtask 支持

---
## 0.2.3-dev.10
* 升级 Android ft-sdk:1.3.6-beta06

---
## 0.2.3-dev.8
* 升级 Android ft-sdk:1.3.6-beta05

---
## 0.2.3-dev.7
* 升级 Android ft-sdk:1.3.6-beta04

---
## 0.2.3-dev.6
* 升级 Android ft-sdk:1.3.6-beta03
* 升级 iOS 1.3.5-beta.4

---
## 0.2.3-dev.5
* 调整  Android Native lib 版本 ft-sdk:1.3.6-beta02

---
## 0.2.3-dev.4
* 调整  Android Native lib 版本 ft-sdk:1.3.6-beta02 

---
## 0.2.3-dev.3
* 调整  Android Native lib 版本 ft-native:1.0.0-alpha05
* 设置 iOS Native SDK 1.3.5-beta.3
---
## 0.2.3-dev.2
* 调整  Android miniSDK 为 21
* 升级 iOS Native SDK 1.3.5-beta.2

---
## 0.2.3-dev.1
* iOS SDK 版本升级 1.3.5-beta.1
* Android SDK 版本升级 1.3.6-beta01

---
## 0.2.2-dev.2
* 调整 Android SDK

---
## 0.2.2-dev.1
* Android Native Bug 修复升级

---
## 0.2.1-dev.5
* 调整 Android SDK

---
## 0.2.1-dev.4
* 调整 Android SDK

---
## 0.2.1-dev.3
* 调整 iOS SDK

---
## 0.2.1-dev.1
* 适配新版本 Native SDK

---
## 0.2.0-dev.5
* 修复 Android 单 trace 数据不触发同步数据的问题

---
## 0.2.0-dev.4
* 升级 iOS Android Native 支持库

---
## 0.2.0-dev.3
* 用户绑定方法方法名修改
* resource  遗漏参数添加

---
## 0.2.0-dev.2
* 修改 README LICENSE

---
## 0.2.0-dev.1
* 添加 Trace RUM Log 调用接口

---
## 0.1.0-dev.1
* 适配新 rum SDK
* null safety

---
## 0.0.1-dev.5
* 更新 Android iOS Dataflux SDK

---
## 0.0.1-dev.4
* 优化地理位置获取的方法

---
## 0.0.1-dev.3
* 文档格式修改

---
## 0.0.1-dev.2
* 修改文档

---
## 0.0.1-dev.1
* 用户自定义埋点
* 流程图上报方法
* 监控项目关联

