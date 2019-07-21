package avishkaar.com.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    public Repository repository;
    public LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allUsers = repository.getAllUsers();

    }

    public void insert (User user)
    {
        repository.insert(user);
    }

    public  void update(User user)
    {
        repository.update(user);
    }
    public  void delete(User user)
    {
        repository.delete(user);
    }
    public void deleteAllUsers()
    {
        repository.deleteAllUser();
    }
    public LiveData<List<User>> getAllUsers()
    {
        return allUsers;
    }

}
