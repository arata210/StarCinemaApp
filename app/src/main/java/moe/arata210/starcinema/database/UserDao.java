package moe.arata210.starcinema.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDao {
    private SQLiteOpenHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addUser(String phone, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String username = "用户" + phone.substring(phone.length() - 3);
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PHONE, phone);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        long id = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
        return id;
    }

    public boolean checkUser(String phone, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = { DatabaseHelper.COLUMN_ID };
        String selection = DatabaseHelper.COLUMN_PHONE + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { phone, password };
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public String getUsername(String phone) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = { DatabaseHelper.COLUMN_USERNAME };
        String selection = DatabaseHelper.COLUMN_PHONE + " = ?";
        String[] selectionArgs = { phone };
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        String username = null;
        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME));
        }
        cursor.close();
        db.close();
        return username;
    }
}
