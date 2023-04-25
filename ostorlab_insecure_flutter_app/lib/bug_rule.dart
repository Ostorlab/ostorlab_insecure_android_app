import 'package:flutter/material.dart';

abstract class BugRule {
  late BuildContext context;

  void setContext(BuildContext context) {
    this.context = context;
  }

  BuildContext getContext() {
    return context;
  }

  Future<void> run() async {
    // Implementation of run method
  }

  String getDescription();

  String toString() {
    return runtimeType.toString();
  }
}
