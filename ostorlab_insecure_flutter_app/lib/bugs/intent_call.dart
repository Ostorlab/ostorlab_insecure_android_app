import 'package:android_intent_plus/android_intent.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] that constructs an Intent with a string value.
class IntentCall extends BugRule {

  /// The tag used to identify instances of this rule.
  static const String _tag = 'IntentCall';

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => "The application broadcasts data through an intent";

  /// Constructs an Intent with a string value.
  @override
  Future<void> run() async {
    final intent = AndroidIntent(action: 'co.ostorlab', arguments:{"token":"SuperSecretToken"});
    intent.sendBroadcast();
  }
}
