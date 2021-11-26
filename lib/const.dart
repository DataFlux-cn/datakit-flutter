import 'package:flutter/services.dart';

const MethodChannel channel = const MethodChannel('ft_mobile_agent_flutter');

const methodConfig = "ftConfig";

const methodBindUser = "ftBindUser";
const methodUnbindUser = "ftUnBindUser";

const methodLogConfig = "ftLogConfig";
const methodLog = "ftLogging";

const methodRumConfig = "ftRumConfig";
const methodRumAddAction = "ftRumAddAction";
const methodRumStartView = "ftRumStartView";
const methodRumStopView = "ftRumStopView";
const methodRumAddError = "ftRumAddError";
const methodRumStartResource = "ftRumStartResource";
const methodRumStopResource = "ftRumStopResource";
const methodRumAddResource = "ftRumAddResource";

const methodTraceConfig = "ftTraceConfig";
const methodTrace = "ftTrace";
const methodGetTraceGetHeader = "ftTraceGetHeader";
