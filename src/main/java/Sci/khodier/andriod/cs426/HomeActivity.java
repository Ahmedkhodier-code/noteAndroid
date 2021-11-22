package Sci.khodier.andriod.cs426;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class HomeActivity extends Activity implements View.OnClickListener {
    ImageButton add;
    TextView count;
    String message ,title ,body ,url;
    Bitmap bitmap;
    int c=0;
    ArrayList<Notes> myListData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("in onCreat");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=findViewById(R.id.count);
            add=findViewById(R.id.addbtn);
        //mssge 1
        Bundle bundle = getIntent().getExtras();
         message = bundle.getString("message");

        //massg 2

        TextView txtView = (TextView) findViewById(R.id.textView4);
        txtView.setText("Welcome "+message);
        count.setText("Notes("+c+")");
        add.setOnClickListener(this);

        //---------------------------------

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        MyListAdapter adapter = new MyListAdapter(myListData );
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        //------------------------------------
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("in onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("in onResume");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("in onDestroy");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println("in onRestart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("in onPause");
    }

    @Override
    public void onClick(View view) {

        count.setText("Notes("+c+")");
        Toast.makeText(this,c +"notes added" ,Toast.LENGTH_LONG).show();
        Intent myIntent= new Intent(this,NoteActivity.class);
        myIntent.putExtra("user", message);
        myIntent.putExtra("count", c);
        startActivityForResult(myIntent,5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       c=data.getIntExtra("count",0);
//        c++;
        count.setText("Notes("+c+")");

        //-------------------------------------

        byte[] byteArray = data.getByteArrayExtra("noteBitmap");
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        title=data.getStringExtra("noteTilte");
        body=data.getStringExtra("noteBody");
        url=data.getStringExtra("noteUrl");
        myListData.add(new Notes( c,title, body, url ,bitmap));

        //-------------------------------------
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}

