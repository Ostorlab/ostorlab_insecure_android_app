import 'dart:io';
import 'package:http/http.dart' as http;
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] that triggers insecure use of TLS/SSL in HTTP traffic.
class TLSTraffic extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = 'TLSTraffic';

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => 'TLS/SSL HTTP traffic';

  /// Trigger insecure use of TLS/SSL in HTTP traffic.
  ///
  /// Uses the [http.Client] class to send a GET request to 'https://ostorlab.co/',
  /// with a custom [badCertificateCallback] that accepts all certificates.
  Future<void> run() async {
    final client = http.Client();
    final request = http.Request('GET', Uri.parse('https://ostorlab.co/'));
    final response = await client.send(request);
    HttpClient(context: SecurityContext()).badCertificateCallback =
        (X509Certificate cert, String host, int port) {
      return true;
    };
  }
}