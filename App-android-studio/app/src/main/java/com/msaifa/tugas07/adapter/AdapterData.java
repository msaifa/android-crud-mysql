package com.msaifa.tugas07.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.msaifa.tugas07.MainActivity;
import com.msaifa.tugas07.R;
import com.msaifa.tugas07.model.DataModel;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    private List<DataModel> mList ;
    private Context ctx;


    public  AdapterData (Context ctx, List<DataModel> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.nama.setText(dm.getUnama());
        holder.usia.setText(dm.getUemail());
        holder.domisili.setText(dm.getUalamat());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, domisili, usia;
        DataModel dm;
        public HolderData (View v)
        {
            super(v);

            nama  = (TextView) v.findViewById(R.id.tvNama);
            usia = (TextView) v.findViewById(R.id.tvUsia);
            domisili = (TextView) v.findViewById(R.id.tvDomisili);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx,MainActivity.class);

                    goInput.putExtra("uid", String.valueOf(dm.getUid()));
                    goInput.putExtra("unama", dm.getUnama());
                    goInput.putExtra("uemail", dm.getUemail());
                    goInput.putExtra("utelp", dm.getUalamat());
                    goInput.putExtra("ujk", dm.getUalamat());
                    goInput.putExtra("ualamat", dm.getUalamat());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}
