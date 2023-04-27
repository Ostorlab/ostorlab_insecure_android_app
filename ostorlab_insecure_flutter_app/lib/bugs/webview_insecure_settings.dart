import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';
import 'package:webview_flutter/webview_flutter.dart';

/// A [BugRule] implementation that triggers the use of insecure settings in a WebView.
class WebviewInsecureSettings extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = 'WebviewInsecureSettings';

  /// Returns a description of this [BugRule] implementation.
  @override
  String getDescription() {
    return 'Insecure WebView settings';
  }

  /// Creates a WebView with insecure settings and loads a URL.
  ///
  /// Creates a new [WebView] with insecure settings, such as enabling JavaScript,
  /// allowing file access, and allowing access from file URLs.
  /// Also enables debugging in the WebView and loads the URL "http://www.ostorlab.co".
  @override
  Future<void> run() async {
    WebViewController()
      ..loadRequest(
        Uri.parse('http://www.ostorlab.co'),
      )..setJavaScriptMode(JavaScriptMode.unrestricted);
  }
}
