import 'dart:convert';
import 'package:encrypt/encrypt.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

class AESCipher extends BugRule {
  static const String _tag = "AESCipher";

  @override
  String getDescription() {
    return "Use of insecure ECB Mode";
  }

  @override
  Future<void> run() async {
    String data = "Jan van Eyck was here 1434";
    String password = "ThisIs128bitSize";

    final plainText = utf8.encode(data);
    final key = Key.fromUtf8(password);
    final iv = IV.fromLength(16);
    final encrypter = Encrypter(AES(key, mode: AESMode.ecb));
    final encrypted = encrypter.encryptBytes(plainText, iv: iv);
    final encryptedString = base64.encode(encrypted.bytes);
    print('Encrypted data: $encryptedString');
  }
}
