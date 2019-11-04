package com.example.contentprovident;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context,"BookDatabase.sqlite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Authors("+
                " id_author integer primary key,"+
                " name text,"+
                "address text,"+
                "email text)");
        sqLiteDatabase.execSQL("CREATE TABLE Books("+
                "id_book integer primary key, "+
                "title text , "+
                "id_author integer " +
                "constraint id_author references Authors(id_author) "+
                "On Delete Cascade ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Books");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Author");
    }

    public int inserAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_author",author.getId_author());
        contentValues.put("name",author.getName());
        contentValues.put("address",author.getAddress());
        contentValues.put("email",author.getEmail());
        int result = (int)db.insert("Authors",null,contentValues);
        db.close();
        return result;
    }

    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            list.add(new Author(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    //Ham insert book
    public int insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_book",book.getId_book());
        contentValues.put("title",book.getTitle());
        contentValues.put("id_author",book.getId_author());
        int result = (int)db.insert("Books",null,contentValues);
        db.close();
        return result;
    }
    //Ham getAllBook
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Books",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            list.add(new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    //Tim sach
    public ArrayList<String> getBookAuthor(int id){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlstr = "select Books.id_author, title " +
                "from Authors inner join Books on "+
                "where Authors.id_author="+id;
        Cursor cursor = db.rawQuery(sqlstr,null);
        if(cursor != null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() ==false){
            list.add(cursor.getInt(0)+"");
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public int getBookCount() {

        String countQuery = "SELECT  * FROM " + "Book";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int getBookCount(int id) {

        String countQuery = "SELECT  * FROM " + "Books where id_book = " +id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public boolean deleteBook(int id) {
        if(getBookCount(id)>0){
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM Books WHERE id_book = " + id);
            db.close();
            return  true;
        }
        return false;
    }

    public boolean updateBook(Book book) {

        if(getBookCount(book.getId_book())>0){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id_book", book.getId_book());
            values.put("title", book.getTitle());
            values.put("id_author", book.getId_author());
            db.update("Books",  values,"id_book = " + book.getId_book(), null);
            db.close();
            return true;
        }
        else {
            return false;
        }

    }
    //
}
