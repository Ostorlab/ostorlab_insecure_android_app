import 'package:webview_flutter/webview_flutter.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] implementation that triggers the use of hardcoded password in URL.
class HardcodedCredsInUrl extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = "HardcodedCredsInUrl";

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => "Use of hardcoded password in URL";

  /// Trigger the bug rule
  @override
  Future<void> run() async {
    print(_tag + " Message: " + get_url());
    WebViewController webView = WebViewController();
    await webView.loadRequest(Uri.parse(get_url()));
  }

  /// Returns the hardcoded URL containing the credentials.
  String get_url() {
    return "https://ostora:07b8a0abfx53p98f7ae5@ostora.ostorlab.com/faq/?country=mars";
  }
}