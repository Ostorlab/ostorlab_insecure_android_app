import 'dart:io';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] that triggers the execution of insecure commands from an external storage in a Flutter app.
class InsecureCommands extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = 'InsecureCommands';

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description =>
      'The application executes commands from an external storage';

  /// Trigger the [BugRule]
  @override
  Future<void> run(String user_input) async {
    String command = 'chmod 777 ostorlab.bin';
    if (user_input.isNotEmpty) {
      command = user_input;
    }
    await executeCommand(command, '/sdcard/');
  }

  /// Execute a command from the given directory path.
  Future<void> executeCommand(String command, String pathName) async {
    final file = Directory(pathName);
    await Process.run(command, [], workingDirectory: file.path);
  }
}
