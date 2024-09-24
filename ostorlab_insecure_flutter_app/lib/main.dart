import 'dart:async';

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
import 'package:ostorlab_insecure_flutter_app/bugs/bind.dart';

import 'package:receive_intent/receive_intent.dart' as receive_intent;

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Insecure Module',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Insecure Module'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title, String? data}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  late TextEditingController _controller;
  late StreamSubscription _intentData;
  String _output = '';

  Future<void> _initReceiveIntent() async {
    final receivedIntent =
        await receive_intent.ReceiveIntent.getInitialIntent();
    final extraList = receivedIntent!.extra;
    _runAll(input: extraList!["fuzz"] ?? "");
  }

  @override
  void initState() {
    super.initState();
    _controller = TextEditingController(text: _output);

    _initReceiveIntent();
  }

  @override
  void dispose() {
    _intentData.cancel();
    super.dispose();
  }

  void _runAll({String input = ""}) async {
    setState(() {
      _output = 'Running ... \n';
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
    caller.addRule(BindRule());

    try {
      await caller.callRules(input);
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
