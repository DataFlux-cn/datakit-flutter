package com.cloudcare.ft.mobile.sdk.tracker.flutter

import android.app.Application
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.ft.sdk.*
import com.ft.sdk.garble.bean.*
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** AgentPlugin */
public class FTMobileAgentFlutter : FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    private lateinit var application: Application
    private var viewGroup: ViewGroup? = null

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "ft_mobile_agent_flutter")
        channel.setMethodCallHandler(this)
        application = flutterPluginBinding.applicationContext as Application
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        try {
            val activity = binding.activity
            val temp = activity.window.decorView.rootView
            if (temp is ViewGroup) {
                viewGroup = temp
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDetachedFromActivity() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    companion object {
        const val LOG_TAG = "FTMobileAgentFlutter"
        const val METHOD_CONFIG = "ftConfig"

        const val METHOD_BIND_USER = "ftBindUser"
        const val METHOD_UNBIND_USER = "ftUnBindUser"

        const val METHOD_LOG_CONFIG = "ftLogConfig"
        const val METHOD_LOGGING = "ftLogging"

        const val METHOD_RUM_CONFIG = "ftRumConfig"
        const val METHOD_RUM_ADD_ACTION = "ftRumAddAction"
        const val METHOD_RUM_START_VIEW = "ftRumStartView"
        const val METHOD_RUM_STOP_VIEW = "ftRumStopView"
        const val METHOD_RUM_ADD_ERROR = "ftRumAddError"
        const val METHOD_RUM_START_RESOURCE = "ftRumStartResource"
        const val METHOD_RUM_STOP_RESOURCE = "ftRumStopResource"
        const val METHOD_RUM_ADD_RESOURCE = "ftRumAddResource"

        const val METHOD_TRACE_CONFIG = "ftTraceConfig"
        const val METHOD_TRACE = "ftTrace"
        const val METHOD_GET_TRACE_HEADER = "ftTraceGetHeader"


    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        Log.d(LOG_TAG, "${call.method} onMethodCall:${call.arguments}")
        when (call.method) {
            METHOD_CONFIG -> {
                val metricsUrl: String = call.argument<String>("metricsUrl")!!
                val useOAID: Boolean? = call.argument<Boolean>("useOAID")
                val debug: Boolean? = call.argument<Boolean>("debug")
                val datakitUUID: String? = call.argument<String>("datakitUUID")
                val env: Int? = call.argument<Int>("env")
                val envType: EnvType = when (env) {
                    EnvType.PROD.ordinal -> {
                        EnvType.PROD
                    }
                    EnvType.GRAY.ordinal -> {
                        EnvType.GRAY
                    }
                    EnvType.PRE.ordinal -> {
                        EnvType.PRE
                    }
                    EnvType.COMMON.ordinal -> {
                        EnvType.COMMON
                    }
                    EnvType.LOCAL.ordinal -> {
                        EnvType.LOCAL
                    }
                    else -> EnvType.PROD
                }
                val sdkConfig = FTSDKConfig.builder(metricsUrl).setEnv(envType)

                if (debug != null) {
                    sdkConfig.isDebug = debug
                }
                if (datakitUUID != null) {
                    sdkConfig.setXDataKitUUID(datakitUUID)
                }
                if (useOAID != null) {
                    sdkConfig.isUseOAID = useOAID
                }

                FTSdk.install(sdkConfig)
                result.success(null)
            }
            METHOD_RUM_CONFIG -> {
                val rumAppId: String = call.argument<String>("rumAppId")!!
                val sampleRate: Float? = call.argument<Float>("sampleRate")
                val enableUserAction: Boolean? = call.argument<Boolean>("enableUserAction")
                val enableUserView: Boolean? = call.argument<Boolean>("enableUserView")
                val enableUserResource: Boolean? = call.argument<Boolean>("enableUserResource")
                val monitorType: Int? = call.argument<Int>("monitorType")
                val globalContext: Map<String, String>? = call.argument("globalContext")
                val rumConfig = FTRUMConfig().setRumAppId(rumAppId)
                if (sampleRate != null) {
                    rumConfig.samplingRate = sampleRate
                }

                if (enableUserAction != null) {
                    rumConfig.isEnableTraceUserAction = enableUserAction
                }

                if (enableUserView != null) {
                    rumConfig.isEnableTraceUserView = enableUserView
                }

                if (enableUserResource != null) {
                    rumConfig.isEnableTraceUserResource = enableUserResource
                }

                if (monitorType != null) {
                    rumConfig.extraMonitorTypeWithError = monitorType
                }

                globalContext?.forEach {
                    rumConfig.addGlobalContext(it.key, it.value)
                }

                FTSdk.initRUMWithConfig(rumConfig)
                result.success(null)
            }
            METHOD_RUM_ADD_ACTION -> {
                val actionName: String? = call.argument<String>("actionName")
                val actionType: String? = call.argument<String>("actionType")
                FTRUMGlobalManager.get().startAction(actionName, actionType)
                result.success(null)
            }
            METHOD_RUM_START_VIEW -> {
                val viewName: String? = call.argument<String>("viewName")
                val viewReferrer: String? = call.argument<String>("viewReferrer")

                FTRUMGlobalManager.get().startView(viewName, viewReferrer)
                result.success(null)
            }
            METHOD_RUM_STOP_VIEW -> {
                FTRUMGlobalManager.get().stopView()
                result.success(null)
            }
            METHOD_RUM_ADD_ERROR -> {
                val stack: String? = call.argument<String>("stack")
                val message: String? = call.argument<String>("message")
                FTRUMGlobalManager.get().addError(
                    stack, message,
                    ErrorType.FLUTTER, AppState.RUN
                )
                result.success(null)

            }

            METHOD_RUM_START_RESOURCE -> {
                val key: String? = call.argument<String>("key")
                FTRUMGlobalManager.get().startResource(key)
                result.success(null)
            }

            METHOD_RUM_STOP_RESOURCE -> {
                val key: String? = call.argument<String>("key")
                FTRUMGlobalManager.get().stopResource(key)
                result.success(null)

            }

            METHOD_RUM_ADD_RESOURCE -> {
                val key: String? = call.argument<String>("key")
                val method: String? = call.argument<String>("resourceMethod")
                val requestHeader: Map<String, String>? =
                    call.argument<Map<String, String>>("requestHeader")
                val responseHeader: Map<String, String>? =
                    call.argument<Map<String, String>>("responseHeader")
                val responseBody: String? = call.argument<String>("responseBody")
                val responseConnection: String? = call.argument<String>("responseConnection")
                val responseContentType: String? = call.argument<String>("responseContentType")
                val responseContentEncoding: String? =
                    call.argument<String>("responseContentEncoding")
                val resourceStatus: Int? = call.argument<Int>("resourceStatus")
                val url: String? = call.argument<String>("url")
//                val fetchStartTime: Long? = call.argument<Long>("fetchStartTime")
//                val tcpStartTime: Long? = call.argument<Long>("tcpStartTime")
//                val tcpEndTime: Long? = call.argument<Long>("tcpEndTime")
//                val dnsStartTime: Long? = call.argument<Long>("dnsStartTime")
//                val dnsEndTime: Long? = call.argument<Long>("dnsEndTime")
//                val responseStartTime: Long? = call.argument<Long>("responseStartTime")
//                val responseEndTime: Long? = call.argument<Long>("responseEndTime")
//                val sslStartTime: Long? = call.argument<Long>("sslStartTime")
//                val sslEndTime: Long? = call.argument<Long>("sslEndTime")
                val params = ResourceParams()
                val netStatusBean = NetStatusBean()
                params.responseHeader = responseHeader.toString()
                params.resourceMethod = method
                params.requestHeader = requestHeader.toString()
                params.resourceStatus = resourceStatus ?: 0
                params.responseBody = responseBody ?: ""
                params.responseConnection = responseConnection ?: ""
                params.responseContentType = responseContentType ?: ""
                params.responseContentEncoding = responseContentEncoding ?: ""
                params.url = url ?: ""
//                netStatusBean.fetchStartTime = fetchStartTime!!
//                netStatusBean.tcpStartTime = tcpStartTime!!
//                netStatusBean.tcpEndTime = tcpEndTime!!
//                netStatusBean.dnsStartTime = dnsStartTime!!
//                netStatusBean.dnsEndTime = dnsEndTime!!
//                netStatusBean.responseStartTime = responseStartTime!!
//                netStatusBean.responseEndTime = responseEndTime!!
//                netStatusBean.sslStartTime = sslStartTime!!
//                netStatusBean.sslEndTime = sslEndTime!!
                FTRUMGlobalManager.get().addResource(key, params, netStatusBean)
                result.success(null)
            }

            METHOD_LOG_CONFIG -> {
                val sampleRate: Float? = call.argument<Float>("sampleRate")
                val serviceName: String? = call.argument<String>("serviceName")
                val logTypeArr: List<Int>? = call.argument<List<Int>>("logType")
                val enableLinkRumData: Boolean? = call.argument<Boolean>("enableLinkRumData")
                val enableCustomLog: Boolean? = call.argument<Boolean>("enableCustomLog")

                val logCacheDiscard: LogCacheDiscard =
                    when (call.argument<Int>("logCacheDiscard")) {
                        0 -> LogCacheDiscard.DISCARD
                        1 -> LogCacheDiscard.DISCARD_OLDEST
                        else -> LogCacheDiscard.DISCARD
                    }

                val logConfig = FTLoggerConfig()
                    .setEnableCustomLog(true)
                    .setLogCacheDiscardStrategy(logCacheDiscard)

                if (sampleRate != null) {
                    logConfig.samplingRate = sampleRate
                }
                if (serviceName != null) {
                    logConfig.serviceName = serviceName
                }

                if (logTypeArr != null) {
                    val arr: Array<Status?> = arrayOfNulls(logTypeArr.size)

                    logTypeArr.forEachIndexed { index, it ->
                        arr[index] = Status.values().find { status -> it == status.ordinal }!!
                    }
                    logConfig.setLogLevelFilters(arr)
                }

                if (enableLinkRumData != null) {
                    logConfig.isEnableLinkRumData = enableLinkRumData
                }
                if (enableCustomLog != null) {
                    logConfig.isEnableCustomLog = enableCustomLog
                }

                FTSdk.initLogWithConfig(logConfig)
                result.success(null)
            }


            METHOD_LOGGING -> {
                val content: String = call.argument<String>("content") ?: ""
                val status: Status = when (call.argument<Int>("status")) {
                    0 -> Status.INFO
                    1 -> Status.WARNING
                    2 -> Status.ERROR
                    3 -> Status.CRITICAL
                    4 -> Status.OK
                    else -> Status.INFO
                }

                FTLogger.getInstance().logBackground(content, status)
                result.success(null)
            }
            METHOD_TRACE_CONFIG -> {
                val sampleRate: Float? = call.argument<Float>("sampleRate")
                val traceType = call.argument<Int>("traceType")
                val enableLinkRUMData = call.argument<Boolean>("enableLinkRUMData")
                val enableAutoTrace = call.argument<Boolean>("enableNativeAutoTrace")

                val traceConfig = FTTraceConfig()
                if (sampleRate != null) {
                    traceConfig.samplingRate = sampleRate
                }

                if (traceType != null) {
                    traceConfig.traceType = when (traceType) {
                        0 -> TraceType.DDTRACE
                        1 -> TraceType.ZIPKIN
                        else -> TraceType.JAEGER
                    }
                }

                if (enableLinkRUMData != null) {
                    traceConfig.isEnableLinkRUMData = enableLinkRUMData
                }

                if (enableAutoTrace != null) {
                    traceConfig.isEnableAutoTrace = enableAutoTrace
                }

                FTSdk.initTraceWithConfig(traceConfig)
                result.success(null)
            }
            METHOD_TRACE -> {
                val key: String? = call.argument<String>("key")
                val httpMethod: String? = call.argument("httpMethod")
                val requestHeader: HashMap<String, String>? = call.argument("requestHeader")
                val responseHeader: HashMap<String, String>? = call.argument("responseHeader")
                val statusCode: Int? = call.argument("statusCode")
                val errorMsg: String? = call.argument("errorMsg")

                FTTraceManager.get().addTrace(
                    key, httpMethod, requestHeader,
                    responseHeader, statusCode ?: 0, errorMsg ?: ""
                )
                result.success(null)

            }
            METHOD_GET_TRACE_HEADER -> {
                val url: String? = call.argument<String>("url")
                val key: String? = call.argument<String>("key")
                result.success(FTTraceManager.get().getTraceHeader(key, url))
            }
            METHOD_BIND_USER -> {
                val userId: String? = call.argument<String>("userId")
                FTSdk.bindRumUserData(userId!!)
                result.success(null)
            }
            METHOD_UNBIND_USER -> {
                FTSdk.unbindRumUserData()
                result.success(null)
            }
            else -> {
                result.notImplemented()
            }
        }
    }


    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}