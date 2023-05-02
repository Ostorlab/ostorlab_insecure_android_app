import 'package:flutter_inappwebview/flutter_inappwebview.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

class WebviewInsecureSettings extends BugRule {
  @override
  String get description => 'The application uses sqflite';

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
      },
    );
  }
}
