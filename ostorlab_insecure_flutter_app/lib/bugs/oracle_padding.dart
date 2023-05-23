import 'dart:convert';
import 'package:encrypt/encrypt.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';


abstract OraclePadding class BugRule {
    String get _tag => 'OraclePadding';

    String get description => 'This bug rule uses CBC insecure encryption mode.';

    Future<void> run() async {

        String data = "Jan van Eyck was here 1434";
        String password = "ThisIs128bitSize";

        final plainText = utf8.encode(data);
        // specify an ecryption key and a random initialization vector
        final key = Key.fromUtf8(password);
        final iv = IV.fromLength(16);
        // Encrypter instantiation with the CBC mode.
        final encrypter = Encrypter(AES(key, mode: AESMode.cbc, padding: 'PKCS7'));
    }
}
