import 'package:android_intent_plus/android_intent.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] that constructs an Intent with a string value.
class IntentCall extends BugRule {

  /// The tag used to identify instances of this rule.
  static const String _tag = 'IntentCall';

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => "The application uses a string value to construct an Intent";

  /// Constructs an Intent with a string value.
  @override
  Future<void> run() async {
    final intent1 = AndroidIntent(action: 'co.ostorlab');
    final intent2 = AndroidIntent(action: 'com.google.android.view');
    final intent3 = AndroidIntent(action: 'android.test.VIEW');
    intent1.launch();
    intent2.launch();
    intent3.launch();
  }
}
