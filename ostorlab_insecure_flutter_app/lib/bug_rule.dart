import 'package:flutter/material.dart';

/// An abstract class that defines the basic structure of a bug rule.
abstract class BugRule {
  late BuildContext context;

  /// Runs the bug rule implementation asynchronously.
  Future<void> run() async {
    // Implementation of run method
  }

  /// Returns the description of the bug rule.
  String get description;

  /// Returns a string representation of the bug rule's runtime type.
  @override
  String toString() => runtimeType.toString();
}
