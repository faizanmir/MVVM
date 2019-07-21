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
    static DatabaseClass db;
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabase().execute();

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

        }
    };


     public static DatabaseClass getInstance(Context context) {
        if(db==null)
        {
            db = Room.databaseBuilder(context,DatabaseClass.class,"LocalDatabase").addCallback(roomDatabaseCallback).build();
        }
        Log.e(TAG, "newInstance:Database instance  " + "Returned Previous Instance" );
        return db;
    }

    public abstract UserDao databaseDAO();


     public static class PopulateDatabase extends AsyncTask<Void,Void,Void>{
         @Override
         protected Void doInBackground(Void... voids) {
             db.databaseDAO().insert(new User("Body1","Title 1","UserID 1"));
             db.databaseDAO().insert(new User("Body2","Title 2","UserID 2"));
             db.databaseDAO().insert(new User("Body3","Title 3","UserID 3"));
             db.databaseDAO().insert(new User("Body4","Title 4","UserID 4"));
             return null;
         }
     }





}
