package com.pune.earnwealth.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pune.earnwealth.R;
import com.pune.earnwealth.adapter.TaskAdapter;
import com.pune.earnwealth.models.TaskDeatils;
import com.pune.earnwealth.web.ApiConstants;
import com.pune.earnwealth.web.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.pune.earnwealth.helper.Utils.isNetworkAvailable;

public class DashboardPage extends AppCompatActivity {


    RecyclerView rec ;
    ArrayList<TaskDeatils> list ;
    Gson gson ;
    TaskAdapter taskAdapter ;
     View pageLoader  ;
     TextView retry ;
     EditText search ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_page);

        findId();
        define();
        getData();
    }

    private void findId()
    {
        pageLoader = findViewById(R.id.pageLoader);
        rec = findViewById(R.id.rec);
        search = findViewById(R.id.search);
        retry = findViewById(R.id.retry);
        rec.setLayoutManager(new LinearLayoutManager(this));

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry.setVisibility(View.GONE);
                getData();
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!list.isEmpty())
                {
//                    taskAdapter.getFilter().filter(search.getText().toString().trim().toLowerCase());
                    taskAdapter.onSearchTaskName(search.getText().toString().trim().toLowerCase());
                }
            }
        });
    }

    private void define()
    {
        list = new ArrayList<>();
        gson = new Gson();
        taskAdapter = new TaskAdapter(this);
         rec.setAdapter(taskAdapter);

    }

    private void getData()
    {
        // Calls API Using with HttpHandler

        /** i can use Retrofit or Volley but for now im using HttpHandler cause its pretty convenient **/

        if(isNetworkAvailable(this,true))
        {
            pageLoader.setVisibility(View.VISIBLE);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {

                try {

                    HttpHandler handlerApi = new HttpHandler();
                    String jsonResponse ;

                    JSONObject object = new JSONObject();
                    object.put("UserID" , "36");
                    jsonResponse = handlerApi.postServiceCall(ApiConstants.taskview , object.toString() , "application/json"  );

                    if(jsonResponse!=null){

                        JSONObject jsonObject=new JSONObject(jsonResponse);

                        if (jsonObject.getInt("statusCode") == 200)
                        {
                            Type type = new TypeToken<ArrayList<TaskDeatils>>(){}.getType();
                            list.addAll(gson.fromJson(jsonObject.getJSONArray("TaskDeatils").toString() , type));
                        }

                    }else{
                        Toast.makeText(this,"No Response",Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    setRetry(e.getMessage() + "\nClick to Retry");
                }

                handler.post(() -> {
                    pageLoader.setVisibility(View.GONE);
                    taskAdapter.onLoad(list);

                    if(list.isEmpty())
                    {
                        setRetry("No Data Found\nClick to Retry");
                    }
                });
            });
        }
        else
        {
            setRetry("Connect internet connection and click to retry.");
        }



    }


    private void setRetry(String msg)
    {
        pageLoader.setVisibility(View.GONE);
        retry.setVisibility(View.VISIBLE);
        retry.setText(msg);
    }

}