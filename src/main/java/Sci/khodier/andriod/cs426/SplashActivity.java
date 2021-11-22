package Sci.khodier.andriod.cs426;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends Activity implements View.OnClickListener {
    Button introbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);
        introbtn = findViewById(R.id.introbtn);
        introbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }
}
