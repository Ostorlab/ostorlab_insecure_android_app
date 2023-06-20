import 'package:flutter/material.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';
import 'dart:io';

/// A [BugRule] that triggers the execution of arbitrary commands in a Flutter app.
class CommandExec extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = 'CommandExec';

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => 'The application executes commands';

  /// Trigger the [BugRule]
  @override
  Future<void> run(String input) async {
    // command contains chmod
    final command = "chmod 777 /sdcard/document.pdf";
    await executeCommand(command, "/sdcard");
  }

  /// Execute a command with optional working directory.
  ///
  /// * [command] the command to execute.
  /// * [pathName] the optional path to set as the working directory for the command.
  Future<void> executeCommand(String command, String pathName) async {
    try {
      var file = pathName != null ? Directory(pathName) : null;
      var process = Process.runSync(command, [], workingDirectory: file?.path);
      print(process.stdout);
    } catch (e) {
      print(e);
    }
  }
}
