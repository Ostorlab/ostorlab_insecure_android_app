import 'package:flutter/material.dart';
import 'package:reflectable/reflectable.dart';
import 'package:ostorlab_insecure_flutter_app/main.reflectable.dart';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';


class MyReflectable extends Reflectable {
  const MyReflectable() : super(invokingCapability);
}

const myReflectable = const MyReflectable();

class ReflectionApi extends BugRule {
  @override
  String get description => 'call to reflectable to invoke method';

  @override
  Future<void> run() async {
    initializeReflectable();
    var instance = Reflectee();
    var instanceMirror = myReflectable.reflect(instance);
    instanceMirror.invoke('reflecteeMethod', [10]);
  }
}

@myReflectable
class Reflectee {
  void reflecteeMethod(int number) {
    print("Invoked with param: ${number}");
  }
}
