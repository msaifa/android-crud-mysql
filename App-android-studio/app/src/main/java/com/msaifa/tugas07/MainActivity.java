package com.msaifa.tugas07;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.msaifa.tugas07.api.ApiRequestBiodata;
import com.msaifa.tugas07.api.Retroserver;
import com.msaifa.tugas07.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText inNama, inEmail, inTelp, inAlamat;
    Button btnsave, btnupdate,btndelete;
    ProgressDialog pd;
    Spinner spinJK ;
    String valJK ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inNama = (EditText) findViewById(R.id.inNama);
        inEmail = (EditText) findViewById(R.id.inEmail);
        inTelp = (EditText) findViewById(R.id.inTelp);
        inAlamat = (EditText) findViewById(R.id.inAlamat);
        spinJK = (Spinner) findViewById(R.id.spinnerJK);

        btnupdate =(Button) findViewById(R.id.btnUpdate);
        btnsave = (Button) findViewById(R.id.btn_insertdata);
        btndelete=(Button) findViewById(R.id.btnhapus);

        Intent data = getIntent();
        final String iddata = data.getStringExtra("uid");

        if(iddata != null) {
            setTitle("Ubah Data");
            btnsave.setVisibility(View.GONE);
            btnupdate.setVisibility(View.VISIBLE);
            btndelete.setVisibility(View.VISIBLE);

//            uid.setText(data.getStringExtra("uid"));
            inNama.setText(data.getStringExtra("unama"));
            inEmail.setText(data.getStringExtra("uemail"));
            inTelp.setText(data.getStringExtra("utelp"));
            inAlamat.setText(data.getStringExtra("ualamat"));

            // set on jenis kelamin
            spinJK.setSelection(data.getStringExtra("utelp") == "Perempuan" ? 2 : 1);
        } else {
            setTitle("Tambah Data");
        }

        pd = new ProgressDialog(this);

        spinJK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1){
                    valJK = "Laki Laki" ;
                } else if (i == 2){
                    valJK = "Perempuan";
                } else {
                    valJK = "" ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

                        if (response.body().getStatus() == 1){
                            Intent i = new Intent(MainActivity.this, TampilData.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                        Toast.makeText(MainActivity.this, response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        pd.hide();

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

                if (validateData()) return ;

                pd.setMessage("update ....");
                pd.setCancelable(false);
                pd.show();

                String sInNama = inNama.getText().toString() ;
                String sInEmail = inEmail.getText().toString() ;
                String sInTelp = inTelp.getText().toString() ;
                String sInJk = valJK;
                String sInAlamat = inAlamat.getText().toString() ;

                ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);
                Call<ResponsModel> update = api.ubahData(
                        iddata,
                        sInNama,
                        sInEmail,
                        sInTelp,
                        sInJk,
                        sInAlamat
                );
                update.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        Log.d("Retro", "Response");
                        if (response.body().getStatus() == 1){
                            Intent i = new Intent(MainActivity.this, TampilData.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                        Toast.makeText(MainActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
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

                if (validateData()) return ;

                pd.setMessage("send data ... ");
                pd.setCancelable(false);
                pd.show();

                String sInNama = inNama.getText().toString() ;
                String sInEmail = inEmail.getText().toString() ;
                String sInTelp = inTelp.getText().toString() ;
                String sInJk = valJK;
                String sInAlamat = inAlamat.getText().toString() ;

                ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);

                Call<ResponsModel> sendbio = api.tambahUser(
                        sInNama,
                        sInEmail,
                        sInTelp,
                        sInJk,
                        sInAlamat
                );
                sendbio.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        int kode = response.body().getStatus();
                        String message = response.body().getMessage();

                        if(kode == 1)
                        {
                            Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MainActivity.this, TampilData.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else
                        {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
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

    public boolean validateData(){
        String sInNama = inNama.getText().toString() ;
        String sInEmail = inEmail.getText().toString() ;
        String sInTelp = inTelp.getText().toString() ;
        String sInAlamat = inAlamat.getText().toString() ;

        if (sInNama.equals("")){
            Toast.makeText(this, "Harap mengisi nama terlebih dahulu.", Toast.LENGTH_SHORT).show();
            return true ;
        } else if (sInEmail.equals("")){
            Toast.makeText(this, "Harap mengisi email terlebih dahulu.", Toast.LENGTH_SHORT).show();
            return true ;
        } else if (sInTelp.equals("")){
            Toast.makeText(this, "Harap mengisi no telp terlebih dahulu.", Toast.LENGTH_SHORT).show();
            return true ;
        } else if (valJK.equals("")){
            Toast.makeText(this, "Harap memilih jenis kelamin terlebih dahulu.", Toast.LENGTH_SHORT).show();
            return true ;
        } else if (sInAlamat.equals("")){
            Toast.makeText(this, "Harap mengisi alamat terlebih dahulu.", Toast.LENGTH_SHORT).show();
            return true ;
        }

        return false ;
    }
}
