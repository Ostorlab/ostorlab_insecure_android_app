import 'dart:io';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] that triggers the use of bind.
class BindRule extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = 'BindRule';

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => 'Bind local host to a port';

  /// Trigger the [BugRule] by starting an HTTP server that listens for connections and binds to a port.
  @override
  Future<void> run(String input) async {
    var port = (input != "") ? int.parse(input) : 4450;

    try {
      var server = await HttpServer.bind('localhost', port);

      print('Server listening on port $port');
      await for (var request in server) {
        request.response
          ..write('Server running on port $port')
          ..close();
      }
    } catch (e) {
      print('Failed to bind server: $e');
    }
  }
}
