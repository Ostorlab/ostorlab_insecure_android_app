import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// A [BugRule] implementation that triggers the use of insecure shared preferences.
class InsecureSharedPreferences extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = "RULE";

  /// Returns a description of this [BugRule] implementation.
  @override
  String getDescription() {
    return "Call to shared preference method using insecure permission";
  }

  /// Trigger the bug rule.
  @override
  Future<void> run() async {
    String myPreference = "myPreference";
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString("PrivateOnly", myPreference);
    SharedPreferences prefs1 = await SharedPreferences.getInstance();
    await prefs1.setString("WorldReadableOnly", myPreference);
    SharedPreferences prefs2 = await SharedPreferences.getInstance();
    await prefs2.setString("WorldReadableAndWritable", myPreference);
    SharedPreferences prefs3 = await SharedPreferences.getInstance();
    await prefs3.setString("WorldReadableAndAppend", myPreference);
  }
}
