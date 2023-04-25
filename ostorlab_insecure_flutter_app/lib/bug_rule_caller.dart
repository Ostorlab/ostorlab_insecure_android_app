import 'package:flutter/material.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

class BugRuleCaller {
  static const String TAG = "BugRuleCaller";

  late List<BugRule> rules;
  late BuildContext context;

  BugRuleCaller(this.context) {
    rules = [];
  }

  List<BugRule> getRules() {
    return rules;
  }

  void addRule<T extends BugRule>(T rule) {
    rule.setContext(context);
    rules.add(rule);
  }

  Future<String> listBugRules() async {
    final buffer = StringBuffer();
    for (final rule in rules) {
      buffer.write('${rule.toString()}\n');
    }
    return buffer.toString();
  }

  Future<void> callRules() async {
    for (final rule in rules) {
      try {
        await rule.run();
      } catch (e) {
        print(e.toString());
      }
    }
  }
}
