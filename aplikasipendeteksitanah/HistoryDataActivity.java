
package com.apllication.aplikasipendeteksitanah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryDataActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    RecyclerView rv_list;
    DatabaseReference reference;
    HistoryAdapter adapter;
    ArrayList<HistoryList> data = new ArrayList<>();
    ProgressDialog pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_data);

        rv_list = findViewById(R.id.rv_history);
        rv_list.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv_list.setLayoutManager(lm);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv_list.addItemDecoration(divider);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        pg = new ProgressDialog(this);
        pg.setMessage("Tunggu ...");
        pg.show();
        init();
    }

    private void init() {
        Query query = reference.child("sensor").child("tanah");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HistoryList list = new HistoryList();
                    list.setHari(snapshot.child("tanggal").getValue().toString());
                    list.setBulan(snapshot.child("bulan").getValue().toString());
                    list.setTahun(snapshot.child("tahun").getValue().toString());
                    list.setSuhu(snapshot.child("temperature").getValue().toString());
                    list.setPh(snapshot.child("ph").getValue().toString());
                    list.setKekeruhan(snapshot.child("moisture").getValue().toString());

                    data.add(list);
                    if (list == null){
                        pg.show();
                    }else{
                        pg.dismiss();
                    }
                }
                adapter = new HistoryAdapter(HistoryDataActivity.this, data);
                rv_list.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}