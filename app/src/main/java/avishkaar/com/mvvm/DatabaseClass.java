package avishkaar.com.mvvm;

import android.content.Context;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(exportSchema = false,version = 1 ,entities = User.class)

public abstract class DatabaseClass extends RoomDatabase{
    private static final String TAG = "DatabaseClass";
    private static DatabaseClass instance;

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabase().execute();

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new DeleteAllUser().execute();

        }
    };


     static DatabaseClass getInstance(Context context) {
        if(instance==null)
        {
            instance = Room.databaseBuilder(context,DatabaseClass.class,"LocalDatabase").addCallback(roomDatabaseCallback).build();
        }
        Log.e(TAG, "newInstance:Database instance  " + "Returned already created database" );
        return instance;
    }

    public abstract UserDao databaseDAO();


     public static class PopulateDatabase extends AsyncTask<Void,Void,Void>{
         @Override
         protected Void doInBackground(Void... voids) {
             instance.databaseDAO().insert(new User("Body1","Title 1","UserID 1"));
             instance.databaseDAO().insert(new User("Body2","Title 2","UserID 2"));
             instance.databaseDAO().insert(new User("Body3","Title 3","UserID 3"));
             instance.databaseDAO().insert(new User("Body4","Title 4","UserID 4"));
             return null;
         }
     }


     static class DeleteAllUser extends AsyncTask<Void,Void,Void>{
         @Override
         protected Void doInBackground(Void... voids) {
             instance.databaseDAO().deleteAllUsers();
             return null;
         }
     }





}
