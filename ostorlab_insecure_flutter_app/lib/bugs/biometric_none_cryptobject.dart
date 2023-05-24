import 'package:flutter/material.dart';
import 'package:local_auth/local_auth.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';

class BiometricNoneCryptObject extends BugRule {

  static const String _tag = 'BiometricNoneCryptObject';

  String get description => 'Use Biometric authentication without a crypto Object.';

  Future<void> run() async {
    // Initialising an instance of the localAuthentication.
    final LocalAuthentication localAuthentication = LocalAuthentication();

    // Attempting authentication using biometrics
    final isAuth = localAuthentication.authenticate(
    localizedReason: 'Please complete the biometrics to proceed.');
  }

}
