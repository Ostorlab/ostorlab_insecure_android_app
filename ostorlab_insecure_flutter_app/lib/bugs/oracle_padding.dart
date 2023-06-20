import 'dart:convert';
import 'package:encrypt/encrypt.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

class OraclePadding extends BugRule {
  String get _tag => 'OraclePadding';

  String get description => 'This bug rule uses CBC insecure encryption mode.';

  @override
  Future<void> run(String input) async {
    String password = "ThisIs128bitSize";

    // Specify an ecryption key and a random initialization vector.
    final key = Key.fromUtf8(password);
    final iv = IV.fromLength(16);
    // Encrypter instantiation with the CBC mode.
    final encrypter = Encrypter(AES(key, mode: AESMode.cbc, padding: 'PKCS7'));
    final ciphertext = encrypter.encrypt("test", iv: iv);
    print(ciphertext.base64);
    final plaintext = encrypter.decrypt64(input, iv: iv);
    print(plaintext);
  }
}
