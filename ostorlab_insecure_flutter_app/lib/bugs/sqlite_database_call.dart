import 'dart:async';
import 'package:ostorlab_insecure_flutter_app/bug_rule.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class SQLiteDatabaseCall extends BugRule {
  @override
  Future<void> run() async {
    SqfliteHelper sqfliteHelper = SqfliteHelper();
    Database db = await sqfliteHelper.getWritableDatabase();
    sqfliteHelper.createTable();
    String insertQuery = "INSERT INTO accounts(name, amount) VALUES(?, ?)";
    await db.rawInsert(insertQuery, ['ostorlab_user', 1000]);
    sqfliteHelper.dropTable();
    db.close();
  }

  @override
  String get description => 'The application uses sqflite';
}

class SqfliteHelper {
  // database name
  static const String DATABASE_NAME = 'bank';

  // table name
  static const String TABLE_NAME = 'accounts';

  // database version
  static const int DATABASE_VERSION = 1;

  // names of the table columns
  static const String UID = '_id';
  static const String NAME = 'name';
  static const String AMOUNT = 'amount';

  // sql to create table
  static const String CREATE_TABLE = 'CREATE TABLE $TABLE_NAME '
      '($UID INTEGER PRIMARY KEY AUTOINCREMENT, '
      '$NAME VARCHAR(255), '
      '$AMOUNT REAL'
      ');';

  // sql to drop table
  static const String DROP_TABLE = 'DROP TABLE IF EXISTS $TABLE_NAME';

  // custom constructor
  SqfliteHelper();

  FutureOr<Database> getWritableDatabase() async {
    var databasesPath = await getDatabasesPath();
    var path = join(databasesPath, DATABASE_NAME);

    return await openDatabase(path, version: DATABASE_VERSION,
        onCreate: (Database db, int version) async {
      await db.execute(CREATE_TABLE);
    });
  }

  void createTable() async {
    Database db = await getWritableDatabase();
    await db.execute(CREATE_TABLE);
    db.close();
  }

  void dropTable() async {
    Database db = await getWritableDatabase();
    await db.execute(DROP_TABLE);
    db.close();
  }
}
