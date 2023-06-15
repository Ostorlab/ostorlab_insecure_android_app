import 'package:flutter/material.dart';

import 'package:ostorlab_insecure_flutter_app/bug_rule_caller.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/command_exec.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/ecb_cipher_mode.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/clear_text_traffic.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/hardcoded_creds_in_url.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/hash_call.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/insecure_commands.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/insecure_random.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/insecure_shared_preferences.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/intent_call.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/path_traversal.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/sqlite_database_call.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/static_iv.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/tls_traffic.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/webview_insecure_settings.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/oracle_padding.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/biometric_none_cryptobject.dart';
import 'package:ostorlab_insecure_flutter_app/bugs/reflection_api.dart';

import 'package:receive_intent/receive_intent.dart' as intent; 

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  MyApp({super.key});

  String? _initReceiveIntent()  {
    // Platform messages may fail, so we use a try/catch PlatformException.
    intent.Intent? receivedIntent;
    try {
      final receivedIntent =  intent.ReceiveIntent.getInitialIntent();
      // Validate receivedIntent and warn the user, if it is not correct,
      // but keep in mind it could be `null` or "empty"(`receivedIntent.isNull`).
    } catch (e) {
      print('error caught: $e');
      // return Null;
    }
    if (receivedIntent == Null){
      return Null;
    } 
    return receivedIntent.data;
    
  }

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    final data = _initReceiveIntent();
    final home;
    if (data != Null){
      home = RunTainted(title: 'Flutter Insecure Module', user_input : data);
    }
    else {
      home = MyHomePage(title: 'Flutter Insecure Module');
    }
    return MaterialApp(
      title: 'Flutter Insecure Module',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: home,
    );
  }
}


class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  late TextEditingController _controller;
  String _output = '';

  @override
  void initState() {
    super.initState();
    _controller = TextEditingController(text: _output);
    _runAll();
  }

  void _runAll() async {
    setState(() {
      _output = 'Running ...\n';
      _controller.text = _output;
    });

    BugRuleCaller caller = BugRuleCaller(context);
    _output += 'Adding rules ...\n';
    caller.addRule(ECBCipher());
    caller.addRule(ClearTextTraffic());
    caller.addRule(TLSTraffic());
    caller.addRule(StaticIV());
    caller.addRule(HardcodedCredsInUrl());
    caller.addRule(InsecureCommands());
    caller.addRule(CommandExec());
    caller.addRule(IntentCall());
    caller.addRule(HashCall());
    caller.addRule(InsecureRandom());
    caller.addRule(SQLiteDatabaseCall());
    caller.addRule(PathTraversal());
    caller.addRule(WebviewInsecureSettings());
    caller.addRule(InsecureSharedPreferences());
    caller.addRule(OraclePadding());
    caller.addRule(BiometricNoneCryptObject());
    caller.addRule(ReflectionApi());

    try {
      await caller.callRules();
      _output += await caller.listBugRules();
    } catch (e) {
      _output += e.toString();
    }

    setState(() {
      _controller.text = _output;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Output:',
            ),
            Expanded(
              child: SingleChildScrollView(
                child: TextField(
                  controller: _controller,
                  decoration: InputDecoration(border: OutlineInputBorder()),
                  minLines: 15,
                  maxLines: null,
                  readOnly: true,
                ),
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                ElevatedButton(
                  onPressed: _runAll,
                  child: Text('Run All'),
                ),_MyHomePageState
                SizedBox(width: 10),
              ],
            )
          ],
        ),
      ),
    );
  }
}

class RunTainted extends StatefulWidget {
  RunTainted({Key? key, required this.title, required this.user_input}) : super(key: key);

  final String title;
  final String user_input;

  @override
  _MyTaintedPageState createState() => _MyTaintedPageState();
}

class _MyTaintedPageState extends State<RunTainted> {
  late TextEditingController _controller;
  String _output = '';

  @override
  void initState() {
    super.initState();
    _controller = TextEditingController(text: _output);
    _runAll();
  }

  void _runAll() async {
    setState(() {
      _output = 'Running ...\n';
      _controller.text = _output;
    });

    BugRuleCaller caller = BugRuleCaller(context);
    _output += 'Adding rules ...\n';
    // caller.addRule(ECBCipher());
    // caller.addRule(ClearTextTraffic());
    // caller.addRule(TLSTraffic());
    // caller.addRule(StaticIV());
    // caller.addRule(HardcodedCredsInUrl());
    caller.addRule(InsecureCommands());
    caller.addRule(CommandExec());
    caller.addRule(IntentCall());
    // caller.addRule(HashCall());
    // caller.addRule(InsecureRandom());
    caller.addRule(SQLiteDatabaseCall());
    caller.addRule(PathTraversal());
    // caller.addRule(WebviewInsecureSettings());
    // caller.addRule(InsecureSharedPreferences());
    // caller.addRule(OraclePadding());
    // caller.addRule(BiometricNoneCryptObject());
    // caller.addRule(ReflectionApi());

    try {
      await caller.callTaintedRules(widget.user_input);
      _output += await caller.listBugRules();
    } catch (e) {
      _output += e.toString();
    }

    setState(() {
      _controller.text = _output;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Output:',
            ),
            Expanded(
              child: SingleChildScrollView(
                child: TextField(
                  controller: _controller,
                  decoration: InputDecoration(border: OutlineInputBorder()),
                  minLines: 15,
                  maxLines: null,
                  readOnly: true,
                ),
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                ElevatedButton(
                  onPressed: _runAll,
                  child: Text('Run All'),
                ),
                SizedBox(width: 10),
              ],
            )
          ],
        ),
      ),
    );
  }
}
