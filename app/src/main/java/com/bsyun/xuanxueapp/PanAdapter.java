package com.bsyun.xuanxueapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PanAdapter extends RecyclerView.Adapter<PanAdapter.PanViewHolder> {

    private List<PanEntity> panDataList = new ArrayList<>();
    private Context context;

    public PanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pan_cell, parent, false);
        return new PanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanViewHolder holder, int position) {
        PanEntity entity = panDataList.get(position);

        holder.tvGongwei.setText(entity.getGongwei());
        holder.tvDipangan.setText(entity.getDipangan());
        holder.tvTianpangan.setText(entity.getTianpangan());
        holder.tvJiuxing2.setText(entity.getJiuxing2());
        holder.tvBamen.setText(entity.getBamen());
        holder.tvBashen.setText(entity.getBashen());
        holder.tvMenke.setText(entity.getMenke());
        holder.tvXingke.setText(entity.getXingke());
        holder.tvChangsheng.setText(entity.getChangsheng());

        // Handle nullable/optional fields: jiuxing1 (Tian Qin Star) and tiangan1 (Tian Qin Stem)
        if (entity.getJiuxing1() != null && !entity.getJiuxing1().isEmpty()) {
            holder.tvJiuxing1.setText(entity.getJiuxing1());
            holder.tvJiuxing1.setVisibility(View.VISIBLE);
        } else {
            holder.tvJiuxing1.setVisibility(View.GONE);
        }

        if (entity.getTiangan1() != null && !entity.getTiangan1().isEmpty()) {
            holder.tvTiangan1.setText(entity.getTiangan1());
            holder.tvTiangan1.setVisibility(View.VISIBLE);
        } else {
            holder.tvTiangan1.setVisibility(View.GONE);
        }

        // Handle MaKong ImageView
        if (entity.getMakong() != 0) { // Assuming 0 means no icon
            holder.ivMakong.setImageResource(entity.getMakong());
            holder.ivMakong.setVisibility(View.VISIBLE);
        } else {
            holder.ivMakong.setVisibility(View.GONE); // Or setImageDrawable(null) if preferred
        }
    }

    @Override
    public int getItemCount() {
        return panDataList.size();
    }

    public void addData(List<PanEntity> newData) {
        panDataList.clear();
        if (newData != null) {
            panDataList.addAll(newData);
        }
        notifyDataSetChanged();
    }

    static class PanViewHolder extends RecyclerView.ViewHolder {
        TextView tvGongwei, tvDipangan, tvTianpangan, tvJiuxing1, tvTiangan1, tvJiuxing2, tvBamen, tvBashen, tvMenke, tvXingke, tvChangsheng;
        ImageView ivMakong;

        public PanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGongwei = itemView.findViewById(R.id.tv_gongwei);
            tvDipangan = itemView.findViewById(R.id.tv_dipangan);
            tvTianpangan = itemView.findViewById(R.id.tv_tianpangan);
            tvJiuxing1 = itemView.findViewById(R.id.tv_jiuxing1);
            tvTiangan1 = itemView.findViewById(R.id.tv_tiangan1);
            tvJiuxing2 = itemView.findViewById(R.id.tv_jiuxing2);
            tvBamen = itemView.findViewById(R.id.tv_bamen);
            tvBashen = itemView.findViewById(R.id.tv_bashen);
            ivMakong = itemView.findViewById(R.id.iv_makong);
            tvMenke = itemView.findViewById(R.id.tv_menke);
            tvXingke = itemView.findViewById(R.id.tv_xingke);
            tvChangsheng = itemView.findViewById(R.id.tv_changsheng);
        }
    }
}
