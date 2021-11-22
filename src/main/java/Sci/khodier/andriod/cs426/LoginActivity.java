package Sci.khodier.andriod.cs426;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {
    EditText userrname, password, Email;
    Button login;
    //account 1
    String Email1 = "", password1 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Email = findViewById(R.id.Emaillabel);
        password = findViewById(R.id.passwordlabel);
        userrname = findViewById(R.id.usernamelabel);

        login = findViewById(R.id.loginbtn);
        login.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("in onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("in onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("in onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("in onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("in onPause");
    }

    @Override
    public void onClick(View view) {
        String inputUsername = userrname.getText().toString();
        String inputPassword = password.getText().toString();
        String inputEmail = Email.getText().toString();
        if (inputEmail.equalsIgnoreCase(Email1) && inputPassword.equals(password1)) {
            Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(this, HomeActivity.class);
// sending
            myIntent.putExtra("message", inputUsername);

            startActivity(myIntent);

        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();

        }
    }
}
