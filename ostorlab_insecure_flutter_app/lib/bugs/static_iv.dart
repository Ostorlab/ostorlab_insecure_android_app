import 'dart:convert';
import 'package:encrypt/encrypt.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] implementation that triggers the use of static IV for encryption.
class StaticIV extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = "StaticIV";

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => 'Use of hardcoded static IV';

  /// Trigger the bug rule
  @override
  Future<void> run(String input) async {
    String data = "Jan van Eyck was here 1434";
    String password = "ThisIs128bitSize";

    final plainText = utf8.encode(data);
    final key = Key.fromUtf8(password);
    final iv = IV.fromUtf8("0123456789abcdef");
    final encrypter = Encrypter(AES(key, mode: AESMode.cbc));
    final encrypted = encrypter.encryptBytes(plainText, iv: iv);
    final encryptedString = base64.encode(encrypted.bytes);
    print(_tag + ' Message: ' + 'Encrypted data: $encryptedString');
  }
}
