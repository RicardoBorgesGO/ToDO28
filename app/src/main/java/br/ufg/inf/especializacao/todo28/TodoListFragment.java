package br.ufg.inf.especializacao.todo28;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment {


    public TodoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View theView = inflater.inflate(R.layout.fragment_todo_list, container, false);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String photo = sharedPref.getString("photo", "");

        ImageView imageView = (ImageView) theView.findViewById(R.id.user_image);
        Glide.with(this).load(photo).into(imageView);


        return theView;
    }

    /*
    public void lerUsuarioNaSessao() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String photo = sharedPref.getString("photo", "");

        ImageView imageView = (ImageView) getActivity().findViewById(R.id.user_image);
        Glide.with(this).load(photo).into(imageView);
    }*/

}
