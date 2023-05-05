import 'package:flutter_android/android_content.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] that triggers the use of insecure shared preference in a Flutter app.
class InsecureSharedPreferences extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = 'InsecureSharedPreferences';

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => 'Call to shared preference method using insecure permission';

  /// Android Context modes
  int MODE_PRIVATE = 0;
  int MODE_WORLD_READABLE = 1; // This constant was deprecated in API level 17.
  int MODE_WORLD_WRITEABLE = 2;
  int MODE_MULTI_PROCESS = 4; // This constant was deprecated in API level 23.

  /// Create multiple shared preferences instances using different flags.
  Future<void> run() async {
    String token = 'SuperSecretToken';
    Context.getSharedPreferences(token, MODE_PRIVATE);
    Context.getSharedPreferences(token, MODE_WORLD_READABLE);
    Context.getSharedPreferences(token, MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
    Context.getSharedPreferences(token, MODE_WORLD_READABLE | MODE_MULTI_PROCESS);
  }
}
