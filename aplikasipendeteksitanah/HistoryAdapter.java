package com.apllication.aplikasipendeteksitanah;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.myViewHolder> {
    Context context;
    ArrayList<HistoryList> lists;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;

    public HistoryAdapter(Context context, ArrayList<HistoryList> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_history_list, parent, false);
        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final HistoryList list = lists.get(position);
        holder.hari.setText(list.getHari() + "/");
        holder.bulan.setText(list.getBulan() + "/");
        holder.tahun.setText(list.getTahun());
        holder.suhu.setText(list.getSuhu());
        holder.ph.setText(list.getPh());
        holder.kekeruhan.setText(list.getKekeruhan());
        Double Suhu = Double.parseDouble(list.getSuhu());
        Double pHH = Double.parseDouble(list.getPh());
        Double kelembaban = Double.parseDouble(list.getKekeruhan());
        holder.tanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder = new AlertDialog.Builder(view.getRootView().getContext());
                View dialogView = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.dialog_history, null);
                dialogBuilder.setView(dialogView);
                dialogBuilder.setCancelable(true);
                alertDialog = dialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                TextView tV_tanaman = dialogView.findViewById(R.id.tV_tanaman);
                if (Suhu == 20) {
                    if (kelembaban >= 60 && kelembaban <= 80){
                        if (pHH >= 5.5 && pHH <= 6.5){
                            tV_tanaman.setText("Kacang Panjang");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }else {
                        tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                } else if (Suhu >= 21 && Suhu <= 23) {
//                    if (kelembaban >=80 )
//                    Jagung,singkong,kacang panjang
                    if (kelembaban >= 60 && kelembaban <= 79) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            tV_tanaman.setText("Kacang Panjang");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban >= 80) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            tV_tanaman.setText("Kacang Panjang\nJagung\nSingkong");
                        }
                        if (kelembaban >= 80 && kelembaban <= 83) {
                            if (pHH >= 5.5 && pHH <= 6.5) {
                                tV_tanaman.setText("Jagung\nSingkong");
                            }
                        } else if (kelembaban >= 84 && kelembaban <= 90) {
                            if (pHH >= 5.5 && pHH <= 6.5) {
                                tV_tanaman.setText("Singkong");
                            }
                        }
                        if (kelembaban >= 80 && kelembaban <= 83) {
                            if (pHH >= 6.6 && pHH <= 7.5) {
                                tV_tanaman.setText("Jagung");
                            }
                        }
                    } else {
                        tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                } else if (Suhu >= 24 && Suhu <= 27) {
                    if (kelembaban >= 50 && kelembaban <= 59) {
                        if (pHH >= 6.0 && pHH <= 6.5) {
                            tV_tanaman.setText("Cabai Merah");
                        }
                    } else if (kelembaban >= 60 && kelembaban <= 69) {
                        if (pHH >= 6.0 && pHH <= 6.5) {
                            tV_tanaman.setText("Cabai Merah\nKacang Panjang");
                        }else if (pHH >= 5.5 && pHH <= 6.0) {
                            tV_tanaman.setText("Kacang Panjang");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban == 70) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            tV_tanaman.setText("Kedelai\nKacang Panjang");
                        } else if (pHH >= 6.6 && pHH <= 7.5) {
                            tV_tanaman.setText("Kedelai");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban >= 71 && kelembaban <= 79) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            tV_tanaman.setText("Kedelai\nKacang Panjang");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban == 80) {
                        if (pHH == 5.6) {
                            tV_tanaman.setText("Jagung\nSingkong\nKedelai\nKacang Panjang");
                        } else if (pHH == 5.5) {
                            tV_tanaman.setText("Singkong\nKedelai\nKacang Panjang");
                        } else if (pHH >= 5.7 && pHH <= 6.5) {
                            tV_tanaman.setText("Jagung\nSingkong\nKedelai\nKacang Panjang");
                        } else if (pHH >= 6.6 && pHH <= 7.5) {
                            tV_tanaman.setText("Jagung\nKedelai");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban == 82 && kelembaban == 83) {
                        if (pHH == 5.6) {
                            tV_tanaman.setText("Jagung\nSingkong\nKedelai");
                        } else if (pHH == 5.5) {
                            tV_tanaman.setText("Singkong\nKedelai");
                        } else if (pHH >= 5.7 && pHH <= 6.5) {
                            tV_tanaman.setText("Jagung\nSingkong\nKedelai");
                        } else if (pHH >= 6.6 && pHH <= 7.5) {
                            tV_tanaman.setText("Jagung\nKedelai");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else if (kelembaban == 84 && kelembaban == 85){
                        if (pHH == 5.6) {
                            tV_tanaman.setText("Singkong\nKedelai");
                        } else if (pHH == 5.5) {
                            tV_tanaman.setText("Singkong\nKedelai");
                        } else if (pHH >= 5.7 && pHH <= 6.5) {
                            tV_tanaman.setText("Singkong\nKedelai");
                        } else if (pHH >= 6.6 && pHH <= 7.5) {
                            tV_tanaman.setText("Kedelai");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }else if (kelembaban == 86 && kelembaban <= 90) {
                        if (pHH >= 5.7 && pHH <= 6.5) {
                            tV_tanaman.setText("Singkong");
                        }else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }else {
                        tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }

                 }else if (Suhu == 28) {
                    if (kelembaban == 70.0) {
                        if (pHH >= 6 && pHH <= 6.5) {
                            tV_tanaman.setText("Cabai Merah & Kedelai");
                        }
                    } else if (kelembaban >= 71 && kelembaban <= 80) {
                        if (pHH >= 5.5 && pHH <= 6.5) {
                            tV_tanaman.setText("Jagung\nSingkong\nKacang Panjang\nKedelai");
                        }

                    }else {
                        tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                    if (kelembaban >= 81 && kelembaban <= 83) {
                        if (pHH > 5.6 && pHH <= 7.5) {
                            tV_tanaman.setText("Jagung\nKedelai");
                        } else if (pHH == 84) {
                            tV_tanaman.setText("Singkong\nKedelai");
                        } else if (pHH == 85) {
                            tV_tanaman.setText("Singkong\nKedelai");
                        } else if (pHH > 85 && pHH <= 90) {
                            tV_tanaman.setText("Singkong");
                        } else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }else {
                        tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }

                } else if (Suhu == 29 && Suhu == 30) {
                    if (kelembaban >= 80 && kelembaban <= 90) {
                        if (pHH > 5.5 && pHH <= 6.5) {
                            tV_tanaman.setText("Singkong\nKacang Panjang\nKedelai");
                        } else if (pHH >= 6.5 && pHH <= 7.5) {
                            tV_tanaman.setText("Kedelai");
                        } else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }
                    if (kelembaban >= 70 && kelembaban <= 79) {
                        if (pHH > 5.5 && pHH <= 7.5) {
                            tV_tanaman.setText("Kedelai");
                        } else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    }
                    if (kelembaban >= 60 && kelembaban <= 80) {
                        if (pHH > 5.5 && pHH <= 7.5) {
                            tV_tanaman.setText("Kedelai");
                        } else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else {
                        tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }

                } else if (Suhu == 31) {
                    if (kelembaban >= 70 && kelembaban <= 85) {
                        if (pHH > 5.5 && pHH <= 7.5) {
                            tV_tanaman.setText("Kedelai");
                        } else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else {
                        tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                } else if (Suhu == 32) {
                    if (kelembaban >= 80 && kelembaban <= 90) {
                        if (pHH > 5.5 && pHH <= 6.5) {
                            tV_tanaman.setText("Singkong");
                        } else {
                            tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                        }
                    } else {
                        tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                    }
                }else {
                    tV_tanaman.setText("Bukan Tanah Untuk Tanaman Palawija");
                }
                Button btn_selesai = dialogView.findViewById(R.id.btn_selesai);

                btn_selesai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView hari, bulan, tahun, suhu, ph, kekeruhan;
        Button tanaman;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.hari);
            bulan = itemView.findViewById(R.id.bulan);
            tahun = itemView.findViewById(R.id.tahun);
            suhu = itemView.findViewById(R.id.suhu);
            ph = itemView.findViewById(R.id.tV_pH);
            kekeruhan = itemView.findViewById(R.id.kelembaban);
            tanaman = itemView.findViewById(R.id.btn_view_tanaman);
        }
    }
}
