package com.example.wengzipeng.mycontacts;

/**
 * Created by wengzipeng on 2016/11/20.
 */

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.Vector;

/**
 * Created by hp on 2016/11/6.
 */
//数据表类
public class ContactsTable {
    private final static String TABLENAME = "contactsTable";
    private MyDB db;

    public ContactsTable(Context context) {
        db = new MyDB(context);
        if(!db.isTableExits(TABLENAME)){
            String createTableSql = "CREATE TABLE IF NOT EXISTS" + TABLENAME +"(id_DB integer" +
                    "primary key AUTOINCREMENT,"+
                    User.NAME + "VARCHAR," +
                    User.MOBLIE + "VARCHAR" +
                    User.QQ + "VARCHAR" +
                    User.DANWEI + "VARCHAR" +
                    User.ADDRESS + "VARCHAR";
            db.createTable(createTableSql);
        }
    }
    //增加数据
    public boolean addData(User user){
        ContentValues values = new ContentValues();
        values.put(User.NAME,User.getNAME());
        values.put(User.MOBLIE,User.getMOBLIE());
        values.put(User.DANWEI,User.getDANWEI());
        values.put(User.QQ,User.getQQ());
        values.put(User.ADDRESS,User.getADDRESS());
        return db.save(TABLENAME,values);
    }

    public User[] getAllUser(){
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
            cursor = db.find("select * from " + TABLENAME, null);
            while (cursor.moveToNext()) {
                User temp = new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBLIE)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                v.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null){
                cursor.close();
            }
            db.closeConnection();
        }
        if(v.size()>0){
            return v.toArray(new User[]{});
        }else {
            User [] users = new User[1];
            User user = new User();
            user.setName("没有结果");
            users[0] = user;
            return users;
        }
    }

    public User getUserByID(int id){
        Cursor cursor = null;
        User temp = new User();
        try {
            cursor = db.find("select * from "+TABLENAME+"where"+
                    "id_DB = ?",new String[]{id+""});
            cursor.moveToNext();
            temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
            temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
            temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
            temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBLIE)));
            temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
            temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
            Log.d("aa",temp.getNAME());
            return temp;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null){
                cursor.close();
            }
            db.closeConnection();
        }
        return null;
    }

    public boolean updateUser(User user){
        ContentValues values = new ContentValues();

        values.put(User.NAME,user.getNAME());
        values.put(User.MOBLIE,user.getMOBLIE());
        values.put(User.DANWEI,user.getDANWEI());
        values.put(User.QQ,user.getQQ());
        values.put(User.ADDRESS,user.getADDRESS());

        return db.update(TABLENAME,values,"id_DB = ?",new String[]{user.getId_DB()+""});
    }

    public User[] findUserByKey(String key){
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;

        try {
            cursor = db.find("select * from "+TABLENAME+"where" +
                    User.NAME + "like '%" + key + "%' "+
                    "or" + User.MOBLIE + "like '%" + key + "%' "+
                    "or" + User.QQ + "like '%" + key + "%' ",null);
            while (cursor.moveToNext()){
                User temp = new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBLIE)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                v.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null){
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size()>0){
            return v.toArray(new User[]{});
        }else {
            User[] users = new User[1];
            User user = new User();
            user.setName("无结果");
            users[0] = user;
            return users;
        }
    }

    public boolean deleteByUser(User user){
        return db.delete(TABLENAME,"id_DB = ?",new String[]{user.getId_DB()+""});
    }
}
