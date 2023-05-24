import 'package:flutter_inappwebview/flutter_inappwebview.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

class WebviewInsecureSettings extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = 'WebviewInsecureSettings';

  @override
  String get description => 'The application has insecure webview';

  @override
  Future<void> run() async {
    await AndroidInAppWebViewController.setWebContentsDebuggingEnabled(true);
    InAppWebViewController webViewController;
    InAppWebView(
      initialUrlRequest: URLRequest(url: Uri.parse("http://www.ostorlab.co")),
      initialOptions: InAppWebViewGroupOptions(
        crossPlatform: InAppWebViewOptions(
          allowFileAccessFromFileURLs: true,
          allowUniversalAccessFromFileURLs: true,
        ),
      ),
      onWebViewCreated: (controller) {
        webViewController = controller;
        webViewController.setOptions(
          options: InAppWebViewGroupOptions(
            crossPlatform: InAppWebViewOptions(
              allowFileAccessFromFileURLs: true,
              allowUniversalAccessFromFileURLs: true,
            ),
          ),
        );
        webViewController.evaluateJavascript(source: "new XMLSerializer().serializeToString(document);");
      },
    );
  }
}
