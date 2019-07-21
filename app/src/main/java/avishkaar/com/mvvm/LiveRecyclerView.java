package avishkaar.com.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LiveRecyclerView extends RecyclerView.Adapter<LiveRecyclerView.DatabaseViewholder> {
    List<User> userList = new ArrayList<>();


    @NonNull
    @Override
    public DatabaseViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DatabaseViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewholder holder, int position) {
        holder.body.setText(userList.get(position).body);
        holder.title.setText(userList.get(position).title);
//        holder.databaseId.setText(userList.get(position).id);
        holder.userId.setText(userList.get(position).getUserId());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public void setUserList(List<User> userList)
    {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public class DatabaseViewholder extends RecyclerView.ViewHolder{
        TextView userId,databaseId,title,body;
        public DatabaseViewholder(@NonNull View itemView) {
            super(itemView);
            userId  = itemView.findViewById(R.id.userId);
            databaseId = itemView.findViewById(R.id.userDatabaseId);
            title = itemView.findViewById(R.id.userTitle);
            body = itemView.findViewById(R.id.userBody);
        }
    }
}
