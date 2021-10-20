package com.apllication.aplikasipendeteksitanah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private Button btn_history;
    private TextView suhu, kekeruhan, pH, index;
    String nameSuhu, namepH, nameTurbidity;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_history = findViewById(R.id.btn_history);
        suhu = findViewById(R.id.tV_nilaiSuhu);
        kekeruhan = findViewById(R.id.tV_nilaiKeruh);
        pH = findViewById(R.id.tV_nilaipH);
        index = findViewById(R.id.tV_index);
        layout = findViewById(R.id.layout);
        ambilData();
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HistoryDataActivity.class));
            }
        });
    }

    private void ambilData() {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sensor");
        Query query = reference.child("tanah").orderByKey().limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    nameSuhu = "" + ds.child("temperature").getValue();
                    namepH = "" + ds.child("ph").getValue();
                    nameTurbidity = "" + ds.child("moisture").getValue();
                    Log.d("suhu", nameSuhu);
                    suhu.setText(nameSuhu + "\u2103");
                    pH.setText(namepH);
                    kekeruhan.setText(nameTurbidity + "%");
                }

                Double Suhu = Double.parseDouble(nameSuhu);
                Double pHH = Double.parseDouble(namepH);
                Double kelembaban = Double.parseDouble(nameTurbidity);
                if (Suhu == 20) {
                        if (kelembaban >= 60 && kelembaban <= 80){
                            if (pHH >= 5.5 && pHH <= 6.5){
                                index.setText("Kacang Panjang");
                            }else {
                                index.setText("Bukan Tanah Untuk Tanaman Palawija");
                            }
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                } else if (Suhu >= 21 && Suhu <= 23) {
//                    if (kelembaban >=80 )
//                    Jagung,singkong,kacang panjang
                    if (kelembaban >= 60 && kelembaban <= 79) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            index.setText("Kacang Panjang");
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban >= 80) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            index.setText("Kacang Panjang\nJagung\nSingkong");
                        }
                        if (kelembaban >= 80 && kelembaban <= 83) {
                            if (pHH >= 5.5 && pHH <= 6.5) {
                                index.setText("Jagung\nSingkong");
                            }
                        } else if (kelembaban >= 84 && kelembaban <= 90) {
                            if (pHH >= 5.5 && pHH <= 6.5) {
                                index.setText("Singkong");
                            }
                        }
                        if (kelembaban >= 80 && kelembaban <= 83) {
                            if (pHH >= 6.6 && pHH <= 7.5) {
                                index.setText("Jagung");
                            }
                        }
                    }else {
                        index.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                } else if (Suhu >= 24 && Suhu <= 27) {
                    if (kelembaban >= 50 && kelembaban <= 59) {
                        if (pHH >= 6.0 && pHH <= 6.5) {
                            index.setText("Cabai Merah");
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban >= 60 && kelembaban <= 69) {
                        if (pHH >= 6.0 && pHH <= 6.5) {
                            index.setText("Cabai Merah\nKacang Panjang");
                        } else if (pHH >= 5.5 && pHH <= 6.0) {
                                index.setText("Kacang Panjang");
                            }else {
                                index.setText("Bukan Tanah Untuk Tanaman Palawija");
                            }
                    } else if (kelembaban == 70) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            index.setText("Kedelai\nKacang Panjang");
                        } else if (pHH >= 6.6 && pHH <= 7.5) {
                            index.setText("Kedelai");
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban >= 71 && kelembaban <= 79) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            index.setText("Kedelai\nKacang Panjang");
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban == 80) {
                        if (pHH == 5.6) {
                            index.setText("Jagung\nSingkong\nKedelai\nKacang Panjang");
                        } else if (pHH == 5.5) {
                            index.setText("Singkong\nKedelai\nKacang Panjang");
                        } else if (pHH >= 5.7 && pHH <= 6.5) {
                            index.setText("Jagung\nSingkong\nKedelai\nKacang Panjang");
                        } else if (pHH >= 6.6 && pHH <= 7.5) {
                            index.setText("Jagung\nKedelai");
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban == 82 && kelembaban == 83) {
                        if (pHH == 5.6) {
                            index.setText("Jagung\nSingkong\nKedelai");
                        } else if (pHH == 5.5) {
                            index.setText("Singkong\nKedelai");
                        } else if (pHH >= 5.7 && pHH <= 6.5) {
                            index.setText("Jagung\nSingkong\nKedelai");
                        } else if (pHH >= 6.6 && pHH <= 7.5) {
                            index.setText("Jagung\nKedelai");
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban == 84 && kelembaban == 85){
                        if (pHH == 5.6) {
                            index.setText("Singkong\nKedelai");
                        } else if (pHH == 5.5) {
                            index.setText("Singkong\nKedelai");
                        } else if (pHH >= 5.7 && pHH <= 6.5) {
                            index.setText("Singkong\nKedelai");
                        } else if (pHH >= 6.6 && pHH <= 7.5) {
                            index.setText("Kedelai");
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }else if (kelembaban == 86 && kelembaban <= 90) {
                        if (pHH >= 5.7 && pHH <= 6.5) {
                            index.setText("Singkong");
                        }else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }else {
                        index.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }

                } else if (Suhu == 28) {
                    if (kelembaban == 70.0) {
                        if (pHH >= 6 && pHH <= 6.5) {
                            index.setText("Cabai Merah & Kedelai");
                        }
                    } else if (kelembaban >= 71 && kelembaban <= 80) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            index.setText("Jagung\nSingkong\nKacang Panjang\nKedelai");
                        }
                    }else {
                        index.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                    if (kelembaban >= 81 && kelembaban <= 83) {
                        if (pHH > 5.6 && pHH <= 7.5) {
                            index.setText("Jagung\nKedelai");
                        } else if (pHH == 84) {
                            index.setText("Singkong\nKedelai");
                        } else if (pHH == 85) {
                            index.setText("Singkong\nKedelai");
                        } else if (pHH > 85 && pHH <= 90) {
                            index.setText("Singkong");
                        } else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }else {
                        index.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }

                } else if (Suhu == 29 && Suhu == 30) {
                    if (kelembaban >= 80 && kelembaban <= 90) {
                        if (pHH > 5.5 && pHH <= 6.5) {
                            index.setText("Singkong\nKacang Panjang\nKedelai");
                        } else if (pHH >= 6.5 && pHH <= 7.5) {
                            index.setText("Kedelai");
                        } else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }
                    if (kelembaban >= 70 && kelembaban <= 79) {
                        if (pHH > 5.5 && pHH <= 7.5) {
                            index.setText("Kedelai");
                        } else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }
                    if (kelembaban >= 60 && kelembaban <= 80) {
                        if (pHH > 5.5 && pHH <= 7.5) {
                            index.setText("Kedelai");
                        } else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else {
                        index.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }

                } else if (Suhu == 31) {
                    if (kelembaban >= 70 && kelembaban <= 85) {
                        if (pHH > 5.5 && pHH <= 7.5) {
                            index.setText("Kedelai");
                        } else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else {
                        index.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                } else if (Suhu == 32) {
                    if (kelembaban >= 80 && kelembaban <= 90) {
                        if (pHH > 5.5 && pHH <= 6.5) {
                            index.setText("Singkong");
                        } else {
                            index.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else {
                        index.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}