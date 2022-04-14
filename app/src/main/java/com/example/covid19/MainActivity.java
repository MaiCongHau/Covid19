package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView txt_case_word,txt_recovered_word,txt_deaths_word,txt_case_vietnam,txt_recovered_vietnam,txt_deaths_vietnam;
    ListView lv;
    ArrayList<Listview_covid> arrayList_covid;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        Getdatacovid();
    }

    private void Getdatacovid() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://static.pipezero.com/covid/data.json";
        StringRequest request =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    NumberFormat nf = NumberFormat.getInstance();
                    Locale locale = new Locale("VIE", "VietNam");
                    NumberFormat en = NumberFormat.getInstance(locale);
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject total = jsonObject.getJSONObject("total");
                    JSONObject vietnam = total.getJSONObject("internal");
                    String case_value_vietnam = vietnam.getString("cases");
                    int case_value_vietnam_int = Integer.valueOf(case_value_vietnam);
                    String case_vietnam = en.format(case_value_vietnam_int);
                    txt_case_vietnam.setText(case_vietnam);
                    String recovered_value_vietnam = vietnam.getString("recovered");
                    int recovered_value_vietnam_int = Integer.valueOf(recovered_value_vietnam);
                    String recovered_vietnam = en.format(recovered_value_vietnam_int);
                    txt_recovered_vietnam.setText(recovered_vietnam);
                    String case_deaths_vietnam = vietnam.getString("death");
                    int deaths_value_vietnam_int = Integer.valueOf(case_deaths_vietnam);
                    String deaths_vietnam = en.format(deaths_value_vietnam_int);
                    txt_deaths_vietnam.setText(deaths_vietnam);
                    JSONObject world = total.getJSONObject("world");
                    String case_value_world = world.getString("cases");
                    int case_value_world_int = Integer.valueOf(case_value_world);
                    String case_world = en.format(case_value_world_int);
                    txt_case_word.setText(case_world);
                    String recovered_value_world = world.getString("recovered");
                    int recovered_value_world_int = Integer.valueOf(recovered_value_world);
                    String recovered_world = en.format(recovered_value_world_int);
                    txt_recovered_word.setText(recovered_world);
                    String case_deaths_world = world.getString("death");
                    int deaths_value_world_int = Integer.valueOf(case_deaths_world);
                    String deaths_world = en.format(deaths_value_world_int);
                    txt_deaths_word.setText(deaths_world);

                    JSONArray jsonArray = jsonObject.getJSONArray("locations");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject_value = jsonArray.getJSONObject(i);
                        String name = jsonObject_value.getString("name");
                        String cases= jsonObject_value.getString("cases");
                        int cases_int = Integer.valueOf(cases);
                        String case_lv = en.format(cases_int);

                        String casesToday =jsonObject_value.getString("casesToday");
                        int casesToday_int = Integer.valueOf(casesToday);
                        String casesToday_lv = en.format(casesToday_int);

                        String death=jsonObject_value.getString("death");
                        int casesdeath_int = Integer.valueOf(death);
                        String casesdeath_lv = en.format(casesdeath_int);

                        arrayList_covid.add(new Listview_covid(name,case_lv,"+ "+casesToday_lv,casesdeath_lv));

                    }

                    customAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }


    private void anhxa() {
        txt_case_word = (TextView) findViewById(R.id.cases_word);
        txt_recovered_word = (TextView) findViewById(R.id.recovered_word);
        txt_deaths_word = (TextView) findViewById(R.id.deaths_word);
        txt_case_vietnam = (TextView) findViewById(R.id.cases_vietnam);
        txt_recovered_vietnam = (TextView) findViewById(R.id.recovered_vietnam);
        txt_deaths_vietnam = (TextView) findViewById(R.id.deaths_vietnam);
        lv = (ListView) findViewById(R.id.list);
        arrayList_covid = new ArrayList<Listview_covid>();
        customAdapter = new CustomAdapter(MainActivity.this,arrayList_covid);
        lv.setAdapter(customAdapter);
    }

}