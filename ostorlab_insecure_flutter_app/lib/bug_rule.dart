import 'package:flutter/material.dart';

/// An abstract class that defines the basic structure of a bug rule.
abstract class BugRule {
  late BuildContext context;

  /// Sets the [BuildContext] object associated with this [BugRule] instance.
  void setContext(BuildContext context) {
    this.context = context;
  }

  /// Returns the [BuildContext] object associated with this [BugRule] instance.
  BuildContext getContext() {
    return context;
  }

  /// Runs the bug rule implementation asynchronously.
  Future<void> run() async {
    // Implementation of run method
  }

  /// Returns the description of the bug rule.
  String getDescription();

  /// Returns a string representation of the bug rule's runtime type.
  String toString() {
    return runtimeType.toString();
  }
}
