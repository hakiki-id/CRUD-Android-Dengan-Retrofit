package com.hakiki95.appbiodata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hakiki95.appbiodata.api.ApiRequestBiodata;
import com.hakiki95.appbiodata.api.Retroserver;
import com.hakiki95.appbiodata.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText nama, usia, domisili;
    Button btnsave, btnTampildata, btnupdate,btndelete;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nama = (EditText) findViewById(R.id.edt_nama);
        usia = (EditText) findViewById(R.id.edt_usia);
        domisili = (EditText) findViewById(R.id.edtdomisili);
        btnTampildata = (Button) findViewById(R.id.btntampildata);
        btnupdate =(Button) findViewById(R.id.btnUpdate);
        btnsave = (Button) findViewById(R.id.btn_insertdata);
        btndelete=(Button) findViewById(R.id.btnhapus);

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if(iddata != null) {
            btnsave.setVisibility(View.GONE);
            btnTampildata.setVisibility(View.GONE);
            btnupdate.setVisibility(View.VISIBLE);
            btndelete.setVisibility(View.VISIBLE);
            nama.setText(data.getStringExtra("nama"));
            usia.setText(data.getStringExtra("usia"));
            domisili.setText(data.getStringExtra("domisili"));
        }

        pd = new ProgressDialog(this);



        btnTampildata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent godata = new Intent(MainActivity.this, TampilData.class);
                startActivity(godata);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Loading Hapus ...");
                pd.setCancelable(false);
                pd.show();

                ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);
                Call<ResponsModel> del  = api.deleteData(iddata);
                del.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        Log.d("Retro", "onResponse");
                        Toast.makeText(MainActivity.this, response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        Intent gotampil = new Intent(MainActivity.this,TampilData.class);
                        startActivity(gotampil);

                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "onFailure");
                    }
                });
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("update ....");
                pd.setCancelable(false);
                pd.show();

                ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);
                Call<ResponsModel> update = api.updateData(iddata,nama.getText().toString(),usia.getText().toString(),domisili.getText().toString());
                update.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        Log.d("Retro", "Response");
                        Toast.makeText(MainActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        pd.hide();
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "OnFailure");

                    }
                });
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("send data ... ");
                pd.setCancelable(false);
                pd.show();

                String snama = nama.getText().toString();
                String susia = usia.getText().toString();
                String sdomisili = domisili.getText().toString();
                ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);

                Call<ResponsModel> sendbio = api.sendBiodata(snama,susia,sdomisili);
                sendbio.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("1"))
                        {
                            Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(MainActivity.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });
    }
}
