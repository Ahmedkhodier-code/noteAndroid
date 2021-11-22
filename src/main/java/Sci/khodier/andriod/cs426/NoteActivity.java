package Sci.khodier.andriod.cs426;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NoteActivity extends Activity implements View.OnClickListener{
    ImageView imageView;
    TextView title;
    EditText et, url;
    Button save, remove, load;
    String t;
    TextInputLayout body;
    int c;
    boolean flag = false;
    String message;
    Bitmap bitmap = null;
    CheckBox dwnldimgcheck;
    Bitmap bt=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_page);

        //------------------------------------
        url = findViewById(R.id.url);
        remove = findViewById(R.id.button_remove);
        load = findViewById(R.id.button_load);
        imageView = findViewById(R.id.image);
        dwnldimgcheck = findViewById(R.id.checkBox);
        //---------------------------------
        title = findViewById(R.id.title);
        save = findViewById(R.id.savebtn);
        et = findViewById(R.id.tiltechange);
        body=findViewById(R.id.body);
        //-----------------------------------------

        save.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("user");
        c = bundle.getInt("count");
        //---------------------------------



        //-------------------------------------
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url.setText("");
                imageView.setImageBitmap(null);
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urllink = url.getText().toString();

                if (urllink.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter URL>>>", Toast.LENGTH_LONG).show();

                } else {

                    LoadImage loadImage = new LoadImage(imageView);
                    System.out.println("loaded");
                    loadImage.execute(urllink);
                    System.out.println("img viwed");
                }

            }
        });

    }

    private void visToggle() {
        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(700);
        transition.addTarget(R.id.dwnimg);

        TransitionManager.beginDelayedTransition(findViewById(R.id.dwnimg), transition);
        findViewById(R.id.dwnimg).setVisibility(View.VISIBLE);
    }

    private void invisToggle() {
        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(300);
        transition.addTarget(R.id.dwnimg);

        TransitionManager.beginDelayedTransition(findViewById(R.id.dwnimg), transition);
        findViewById(R.id.dwnimg).setVisibility(View.INVISIBLE);
    }

    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        if (dwnldimgcheck.isChecked()) {
            Toast.makeText(this, "checked", Toast.LENGTH_LONG).show();
            visToggle();
//            findViewById(R.id.dwnimg).setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "unchecked", Toast.LENGTH_LONG).show();
            invisToggle();
//            findViewById(R.id.dwnimg).setVisibility(View.INVISIBLE);
        }
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;

        public LoadImage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            System.out.println("doInBackground");

            String urllink = strings[0];


            try {
                InputStream inputStream = new java.net.URL(urllink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                System.out.println("try");
            } catch (IOException e) {
//                System.out.println("Error eccure");
                e.printStackTrace();
//                 url.setError("please");
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bt =bitmap;
            imageView.setImageBitmap(bitmap);
        }
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
        if (et.getText().toString().equals("")) {
            Toast.makeText(this, "please Enter tilte", Toast.LENGTH_LONG).show();

        } else {
            if (view.getId() == R.id.savebtn) {
                t = et.getText().toString();
                title.setText("Note's Title :" + t);
                flag = true;
                //------------------------
             //   Notes note =new Notes(c,et.getText().toString() ,body.getEditText().getText().toString() ,url.getText().toString() ,bt);

                //------------------------
                Intent it = new Intent(this, HomeActivity.class);
                it.putExtra("flag", flag);
                //---------------------------------------
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                //-------------------------------------
                it.putExtra("noteTilte" , et.getText().toString());
                it.putExtra("noteBitmap" , byteArray);
                it.putExtra("noteBody" , body.getEditText().getText().toString());
                it.putExtra("noteUrl" , url.getText().toString());
                //------------------------------------
                it.putExtra("user", message);
                it.putExtra("count", c + 1);
                setResult(RESULT_OK, it);
                finish();
            }
        }
    }
}
