package avishkaar.com.mvvm;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class User  {

    public User(String body, String title, String userId) {
        this.body = body;
        this.title = title;
        this.userId = userId;
    }

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    public int id;

    @SerializedName("body")
    @ColumnInfo
    public String body;


    @SerializedName("title")
    @ColumnInfo
    String title;

    @SerializedName("userId")
    @ColumnInfo
    String userId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
