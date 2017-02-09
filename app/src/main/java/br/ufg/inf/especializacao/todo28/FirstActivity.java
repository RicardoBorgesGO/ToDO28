package br.ufg.inf.especializacao.todo28;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import br.ufg.inf.especializacao.todo28.model.User;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.activity_first, new TodoListFragment());

        transaction.commit();

        /*
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.mensagem);
        textView.setText(message);*/

        //Log.i("USER", getUserStickyEvent().toString());

        //ViewGroup layout = (ViewGroup) findViewById(R.id.activity_first);
        //layout.addView(textView);
    }

    /*
    public void lerUsuarioNaSessao() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String photo = sharedPref.getString("photo", "");

        ImageView imageView = (ImageView) findViewById(R.id.user_image);
        Glide.with(this).load(photo).into(imageView);
    }*/

    /*
    public User getUserStickyEvent() {
        return EventBus.getDefault().getStickyEvent(User.class);
    }*/
}
