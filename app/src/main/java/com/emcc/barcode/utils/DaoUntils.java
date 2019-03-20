package com.emcc.barcode.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;



public class DaoUntils {

//    private static DaoSession mDaoSession;
//
//    public static void initGreenDao(Context context) {
//        //创建数据库mydb.db
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"mydb.db");
//        //获取可写数据库
//        SQLiteDatabase database = helper.getWritableDatabase();
//        //获取数据库对象
//        DaoMaster daoMaster = new DaoMaster(database);
//        //获取Dao对象管理者
//        mDaoSession = daoMaster.newSession();
//    }
//
//    public static long insertUser(User user){
//        return mDaoSession.getUserDao().insertOrReplace(user);
//    }
//
//
//    public static User getUser(String phone,String password){
//        List<User> users =  mDaoSession.getUserDao().queryBuilder().where(UserDao.Properties.Phone.eq(phone)).where(UserDao.Properties.Password.eq(password)).list();
//        if(users.size()>0){
//            return users.get(0);
//        }
//        return null;
//    }
//
//    public static boolean hasCheckData(){
//        List<CheckData> checkDatas = mDaoSession.getCheckDataDao().queryBuilder().list();
//        return checkDatas.size()!=0;
//    };
//
//    public static List<CheckData> queryCheckData(int ageGroup,int type){
//        return  mDaoSession.getCheckDataDao().queryBuilder().where(CheckDataDao.Properties.AngeGrop.eq(ageGroup)).where(CheckDataDao.Properties.Type.eq(type)).list();
//    };
//
//    public static void insetCheckDatas(List<CheckData> checkDatas){
//        mDaoSession.getCheckDataDao().insertInTx(checkDatas);
//    }
//
//    public static void insertOrUpdateUserMessage(UserMessage userMessage){
//      String userName = userMessage.getUserName();
//      List<UserMessage> userMessages = mDaoSession.getUserMessageDao().queryBuilder().where(UserMessageDao.Properties.UserName.eq(userName)).list();
//      if(userMessages.size()>0){
//          Long id = userMessages.get(0).getId();
//          Log.i("ccccccccccccccc","updateupdateupdate===");
//          userMessage.setId(id);
//          mDaoSession.getUserMessageDao().update(userMessage);
//      }else{
//          Log.i("ccccccccccccccc","insertinsertinsert===");
//          mDaoSession.getUserMessageDao().insert(userMessage);
//      }
//    }
//
//    public static UserMessage getUserMessageByUserName(String userName){
//        List<UserMessage> userMessages = mDaoSession.getUserMessageDao().queryBuilder().where(UserMessageDao.Properties.UserName.eq(userName)).list();
//        Log.i("ccccccccccccccc","size==="+userMessages.size());
//        if(userMessages.size()>0){
//            return userMessages.get(0);
//        }
//        return null;
//    }
}
