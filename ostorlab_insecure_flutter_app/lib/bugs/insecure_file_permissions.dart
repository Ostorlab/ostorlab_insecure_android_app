import 'dart:io';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] implementation that triggers the use of insecure file permissions.
class InsecureFilePermissions extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = "InsecureFilePermissions";

  /// Returns a description of this [BugRule] implementation.
  @override
  String getDescription() {
    return "Use of insecure file permissions";
  }

  /// Trigger the bug rule.
  @override
  Future<void> run() async {
    String filename = "test_filename";
    await openFileOutputWorldReadable(filename);
    await openFileOutputWorldWritable(filename);
    await setReadableAll(filename);
    await setWritableAll(filename);
  }

  /// Writes to a file with world-readable permission.
  Future<void> openFileOutputWorldReadable(String filename) async {
    final documentsDir = '/sdcard/Documents';
    final file = await File('${documentsDir}/$filename').create();
    await file.writeAsString('world readable');
    await _setFilePermissions(file.path, '444');
  }

  /// Writes to a file with world-writable permission.
  Future<void> openFileOutputWorldWritable(String filename) async {
    final documentsDir = '/sdcard/Documents';
    final file = await File('${documentsDir}/$filename').create();
    await file.writeAsString('world writable');
    await _setFilePermissions(file.path, '666');
  }

  /// Sets a file readable to all users.
  Future<void> setReadableAll(String filename) async {
    final documentsDir = '/sdcard/Documents';
    final file = await File('${documentsDir}/$filename').create();
    await file.writeAsString('readable all');
    await _setFilePermissions(file.path, '444');
  }

  /// Sets a file writable to all users.
  Future<void> setWritableAll(String filename) async {
    final documentsDir = '/sdcard/Documents';
    final file = await File('${documentsDir}/$filename').create();
    await file.writeAsString('writable all');
    await _setFilePermissions(file.path, '666');
  }

  /// Sets the permissions of a file using the chmod command.
  Future<void> _setFilePermissions(String filePath, String permission) async {
    await Process.run('chmod', [permission, filePath]);
  }
}
