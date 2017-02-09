package br.ufg.inf.especializacao.todo28;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import br.ufg.inf.especializacao.todo28.model.User;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "br.ufg.inf.especializacao.MENSSAGEM";
    public static boolean loginRealized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        lerUsuarioNaSessao();
    }

    public void login(View v) {
        EditText editUserName = (EditText) findViewById(R.id.edit_username);
        EditText editPassword = (EditText) findViewById(R.id.edit_password);

        String username = editUserName.getText().toString();
        String password = editPassword.getText().toString();

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);

        //http://private-8a56d-todo87.apiary-mock.com/login

        httpLogin();

        if (username.equals("viniciussebba@inf.ufg.br") && password.equals("123456")) {
            Intent firstActivityIntent = new Intent(this, FirstActivity.class);
            //firstActivityIntent.putExtra(EXTRA_MESSAGE, "DEU CERTO");

            User user = new User("Vinicius Sebba Pato Sofredor", "viniciussebba@inf.ufg.br", "5646486asdsaDSADSADsad@_ae", "http://www.inf.ufg.br/~viniciussebba/images/profil_homepage.jpg");
            salvarUsuarioNaSessao(user);
            //userPostSticky(user);

            startActivity(firstActivityIntent);
            finish();
        } else {
            Snackbar snackbar = Snackbar.make(relativeLayout, "DEU RUIM", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void httpLogin() {
        OkHttpClient client = new OkHttpClient();

        try {

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", "marcelo");
            jsonObject.put("password", "123456");

            Request request = new Request.Builder()
                .url("http://private-54aacf-todo87.apiary-mock.com/login")
                .post(RequestBody.create(MEDIA_TYPE_JSON,jsonObject.toString()))
                .cacheControl(new CacheControl.Builder().maxStale(1, TimeUnit.MINUTES).build()) // 2 minutes cache
                .build();

            Call call = client.newCall(request);

            Log.i("EXECUTOU", String.valueOf(call.isExecuted()));

            loginRealized = false;

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    loginRealized = true;
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        String text = response.body().string();
                        Log.d("RESPONSE", text); // How to parse it to User?
                        loginRealized = true;
                    }
                }
            });


            while (!loginRealized) {
                Log.i("LOGIN", "SUCCSES");
            }
            /*
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println(response.body().string());*/
        } catch (JSONException je) {

        }
    }

    public void salvarUsuarioNaSessao(User user) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("email", user.getEmail());
        editor.putString("nome", user.getNome());
        editor.putString("token", user.getToken());
        editor.putString("photo", user.getPhotoUrl());

        boolean ok = editor.commit();
        Log.i("",ok+"");
    }

    public void lerUsuarioNaSessao() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String email = sharedPref.getString("email", "");

        EditText editText = (EditText) findViewById(R.id.edit_username);
        editText.setText(email);
    }

    public void userPostSticky(User user) {
        EventBus.getDefault().postSticky(user);
    }
}
