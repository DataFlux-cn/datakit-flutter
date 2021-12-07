import 'dart:async';

import 'const.dart';

class FTTracer {
  static final FTTracer _singleton = FTTracer._internal();

  factory FTTracer() {
    return _singleton;
  }

  FTTracer._internal();

  ///配置 trace
  ///[sampleRate]采样率
  ///[serviceName]服务名
  ///[traceType] 链路类型
  ///[enableLinkRUMData] 是否与 RUM 数据关联
  ///[enableNativeAutoTrace] 是否开启原生网络网络自动追踪 iOS NSURLSession ,Android OKhttp
  Future<void> setConfig(
      {double? sampleRate,
        String? serviceName,
        TraceType? traceType,
        bool? enableLinkRUMData,
        bool? enableNativeAutoTrace,
      }) async {
    var map = Map<String, dynamic>();
    map["sampleRate"] = sampleRate;
    map["serviceName"] = serviceName;
    map["traceType"] = traceType?.index;
    map["enableLinkRUMData"] = enableLinkRUMData;
    map["enableNativeAutoTrace"] = enableNativeAutoTrace;
    await channel.invokeMethod(methodTraceConfig, map);
  }

  /// 上传 Trace 数据
  /// [key] 唯一 id
  /// [httpMethod] 请求方法
  /// [requestHeader] 请求头参数
  /// [statusCode] 返回状态码
  /// [responseHeader] 返回头参数
  /// [errorMessage] 错误消息
  Future<void> addTrace({
    required String key,
    required String httpMethod,
    required Map<String, dynamic> requestHeader,
    int? statusCode,
    Map<String, dynamic>? responseHeader,
    String? errorMessage,
  }) async {
    var map = Map<String, dynamic>();
    map["key"] = key;
    map["httpMethod"] = httpMethod;
    map["requestHeader"] = requestHeader;
    map["responseHeader"] = responseHeader;
    map["statusCode"] = statusCode;
    map["errorMessage"] = errorMessage;
    await channel.invokeMethod(methodTrace, map);
  }

  /// 获取 trace http 请求头数据
  /// [key] 唯一 id
  /// [url] 请求地址
  ///
  Future<Map<String, String>> getTraceHeader(String key, String url) async {
    var map = Map<String, dynamic>();
    map["key"] = key;
    map["url"] = url;
    Map? header = await channel.invokeMethod(methodGetTraceGetHeader, map);
    if (header != null) {
      return new Map<String, String>.from(header);
    }else{
      return <String,String>{};
    }
  }
}

/// 使用 trace trace 类型
enum TraceType {
  ddTrace,
  zipkin,
  jaeger
}
