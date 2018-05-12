package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import co.ostorlab.insecure_app.BugRule;

public class SQLiteDatabaseCall extends BugRule {

    @Override
    public void run() throws Exception {

        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this.getContext());
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        mySQLiteOpenHelper.createTable();
        String insert_query = "INSERT INTO accounts(name, amount) VALUES(?, ?)";
        db.execSQL(insert_query, new Object[]{"Jack", 3000});
        mySQLiteOpenHelper.dropTable();
        db.close();

    }

    @Override
    public String getDescription() {
        return "The application uses SQLiteOpenHelper";
    }
}


class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // database name
    private static final String DATABASE_NAME = "bank";

    // table name
    private static final String TABLE_NAME = "accounts";

    // database version
    private static final int DATABASE_VERSION = 1;

    // names of the table columns
    private static final String UID = "_id";
    private static final String NAME = "name";
    private static final String AMOUNT = "amount";

    // sql to create table
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ("
            + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " VARCHAR(255), "
            + AMOUNT + " REAL"
            + ");";

    // sql to drop table
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    // custom constructor
    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public void createTable(){
        this.getWritableDatabase().execSQL(CREATE_TABLE);
    }

    public void dropTable(){
        this.getWritableDatabase().execSQL(DROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}