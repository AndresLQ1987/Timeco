package com.android.timeco.View;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.android.timeco.R;



public class ViewWebFragment extends Fragment {

    private WebViewViewModel webViewViewModel;

    //private ProgressBar progressBar;


    public static ViewWebFragment newInstance() {
        return new ViewWebFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_web, container, false);

        //progressBar = ui_layout.findViewById(R.id.progressBarWeb);

        webViewViewModel =
                ViewModelProviders.of(this).get(WebViewViewModel.class);
        final WebView web = root.findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);

        MiHilo miHilo = new MiHilo();
        miHilo.execute("https://agora.xtec.cat/insjoandaustria/");


        webViewViewModel.getWeb().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String html) {
                web.loadData(html,"text/html", "utf-8");
            }
        });

        return root;
    }


    public class MiHilo extends AsyncTask<String,Void,String> {

        //@Override
        //protected void onPressExceute() {
        //    progressBar.setVisibility(View.VISIBLE);
        //}

        @Override
        protected String doInBackground(String... strings) {
            webViewViewModel.downloadURL(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }

    }


}

