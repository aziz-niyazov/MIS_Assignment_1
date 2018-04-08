package com.example.zizik.mis_assignment_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText etURL;
    public static WebView wvContent;
    public static TextView tvContent;

    private String serverURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = (TextView)findViewById(R.id.tvContent);
        wvContent = (WebView)findViewById(R.id.vwContent);
        etURL = (EditText)findViewById(R.id.etURL);


    }

    public void connect(View view) {
        serverURL = etURL.getText().toString();
        Toast.makeText(this, "Loading... "+serverURL, Toast.LENGTH_SHORT).show();
        //Web View
        wvContent.loadUrl(serverURL);
        //enable JS
        wvContent.getSettings().setJavaScriptEnabled(true);
        //to load all sub pages in the app
        wvContent.setWebViewClient(new WebViewClient());

        //HTML view
        new GetContent(tvContent, this).execute(serverURL);

        etURL.setText("");
    }


    public void clear(View view) {
        tvContent.setText("");
        wvContent.loadUrl("about:blank");
        etURL.setText("");
    }
}
