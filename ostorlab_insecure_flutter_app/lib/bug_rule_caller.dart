import 'package:flutter/material.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A class that manages a list of [BugRule] instances and calls their implementation of the [run] method.
class BugRuleCaller {
  static const String _tag = "BugRuleCaller";

  late List<BugRule> rules;
  late BuildContext context;

  /// Creates a new instance of [BugRuleCaller] with the provided [BuildContext].
  BugRuleCaller(this.context) {
    rules = [];
  }

  /// Returns the list of [BugRule] instances managed by this [BugRuleCaller] instance.
  List<BugRule> getRules() => rules;

  /// Adds the provided [BugRule] instance to the list of managed rules and sets its [BuildContext].
  void addRule<T extends BugRule>(T rule) {
    rule.context = context;
    rules.add(rule);
  }

  /// Returns a string representation of the [BugRule] instances managed by this [BugRuleCaller] instance.
  Future<String> listBugRules() async {
    final buffer = StringBuffer();
    for (final rule in rules) {
      buffer.write('${rule.toString()}\n');
    }
    return buffer.toString();
  }

  /// Calls the [run] method of each [BugRule] instance managed by this [BugRuleCaller] instance.
  Future<void> callRules(String input) async {
    for (final rule in rules) {
      try {
        await rule.run(input);
      } catch (e) {
        print(
            'Exception caught while running rule ${rule.toString()}: ${e.toString()}');
      }
    }
  }
}
