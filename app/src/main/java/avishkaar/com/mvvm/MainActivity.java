package avishkaar.com.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserDetailsFragment.OnFragmentInteractionListener{
    UserDetailsFragment fragment;
    UserViewModel userViewModel;
    int flag=0;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment= new UserDetailsFragment();
        FloatingActionButton fab = findViewById(R.id.addUserFab);
        RecyclerView userRecyclerView = findViewById(R.id.notesRecyclerView);
        final LiveRecyclerView liveRecyclerView = new LiveRecyclerView();
        userRecyclerView.setAdapter(liveRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        userViewModel=  ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //Updating recyclerView here
                liveRecyclerView.setUserList(users);

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    getSupportFragmentManager().beginTransaction().add(R.id.frameForFragment, fragment).commit();
                    flag=1;
                }else
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameForFragment,fragment).commit();
                }
            }
        });

    }

    @Override
    public void onFragmentInteraction(String title,String body, String userId) {
        userViewModel.insert(new User(body,title,userId));
    }
}
