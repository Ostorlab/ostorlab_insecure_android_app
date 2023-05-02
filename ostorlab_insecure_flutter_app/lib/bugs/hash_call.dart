import 'dart:convert';
import 'package:crypto/crypto.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] implementation that triggers the use of hash functions without salt or iteration.
class HashCall extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String TAG = 'HashCall';

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => "The application uses hash functions without salt or iteration";

  /// Computes the SHA-1 hash of a fixed message without salt or iteration.
  ///
  /// Computes the SHA-1 hash of the message "Ostorlab hidden message" without salt or iteration.
  /// The hash is computed using the `crypto` package and printed to the console.
  @override
  Future<void> run() async {
    final String message = 'Ostorlab hidden message';

    final sha = sha1.convert(utf8.encode(message));
    final digest = sha.bytes;
    print('SHA-1 digest: ${digest.map((byte) => byte.toRadixString(16).padLeft(2, '0')).join()}');
  }
}
