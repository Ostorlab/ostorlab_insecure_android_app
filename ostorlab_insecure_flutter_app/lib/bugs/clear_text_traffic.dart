import 'package:http/http.dart' as http;
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] that triggers the use of cleartext HTTP traffic in a Flutter app.
class ClearTextTraffic extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = 'ClearTextTraffic';

  /// Trigger the [BugRule]
  @override
  Future<void> run() async {
    var client = http.Client();
    var request = http.Request('GET', Uri.parse('http://ostorlab.co/'));
    var response = await client.send(request);
  }

  /// Returns a description of this [BugRule] implementation.
  @override
  String getDescription() {
    return 'Use of cleartext HTTP traffic';
  }
}