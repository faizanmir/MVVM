package avishkaar.com.mvvm;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static avishkaar.com.mvvm.RetrofitApi.BASE_URL;

class Repository {
    private LiveData<List<User>> allUsers;
    private static final String TAG = "Repository";
    private UserDao userDao;

    Repository(Application application) {
        DatabaseClass database = DatabaseClass.getInstance(application);
        userDao = database.databaseDAO();
        allUsers = userDao.getAllUsers();
        fetchFromRetrofit(this);
    }

    public void insert(User user)
    {
        new InsertIntoDatabase(userDao).execute(user);
    }
    public  void update(User user)
    {
        new UpdateUser(userDao).execute(user);
    }
    public void delete(User user)
    {
        new DeleteFromDatabase(userDao).execute(user);
    }
    public void deleteAllUser()
    {
        new DeleteAllUsers(userDao).execute();
    }
    public LiveData<List<User>> getAllUsers()
    {
        return allUsers;
    };
    public void fetchFromRetrofit(Repository repository){
        new FetchFromRetrofit(repository).execute();
    }


    static class FetchFromRetrofit extends AsyncTask<Void,Void,Void>{
        Repository repository;
        FetchFromRetrofit(Repository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

            Call<List<User>> userCall = retrofitApi.get();

            userCall.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> userList = response.body();
                    for (User user: userList
                    ) {
                        user = new User(user.title,user.title,user.userId);
                        repository.insert(user);

                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
            return null;
        }
    }



    static class InsertIntoDatabase extends AsyncTask<User,Void,Void>{
        public UserDao userDao;

        public InsertIntoDatabase(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    static class DeleteFromDatabase extends AsyncTask<User,Void,Void>{
        public UserDao userDao;

        public DeleteFromDatabase(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }


    static class DeleteAllUsers extends AsyncTask<Void,Void,Void>{
       UserDao userDao;

        public DeleteAllUsers(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }


    static class UpdateUser extends AsyncTask<User,Void,Void>{
        UserDao userDao;

        public UpdateUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }
    }


}
