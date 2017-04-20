package com.hakiki95.appbiodata.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hakiki95.appbiodata.MainActivity;
import com.hakiki95.appbiodata.R;
import com.hakiki95.appbiodata.model.DataModel;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

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
        holder.nama.setText(dm.getNama());
        holder.usia.setText(dm.getUsia());
        holder.domisili.setText(dm.getDomisili());
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
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("nama", dm.getNama());
                    goInput.putExtra("usia", dm.getUsia());
                    goInput.putExtra("domisili", dm.getDomisili());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}
