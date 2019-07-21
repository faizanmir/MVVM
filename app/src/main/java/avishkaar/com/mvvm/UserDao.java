package avishkaar.com.mvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void updateUser(User user);

    @Query("Delete from user")
    void deleteAllUsers();

    @Query("Select * from user where userId = :userId")
    User selectUser(String userId);

    @Query("Select * from user")
    LiveData<List<User>> getAllUsers();



}
