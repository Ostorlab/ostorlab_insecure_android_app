import 'dart:convert';
import 'package:encrypt/encrypt.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

/// A [BugRule] implementation that triggers the use of insecure ECB mode for encryption.
class ECBCipher extends BugRule {
  /// The tag used to identify instances of this rule.
  static const String _tag = "ECBCipher";

  /// Returns a description of this [BugRule] implementation.
  @override
  String get description => "Use of insecure ECB Mode";

  /// Trigger the bug rule
  @override
  Future<void> run(String input) async {
    String password = "ThisIs128bitSize";

    final plainText = utf8.encode(input);
    final key = Key.fromUtf8(password);
    final iv = IV.fromLength(16);
    final encrypter = Encrypter(AES(key, mode: AESMode.ecb));
    final encrypted = encrypter.encryptBytes(plainText, iv: iv);
    final encryptedString = base64.encode(encrypted.bytes);
    print('Encrypted data: $encryptedString');
  }
}
