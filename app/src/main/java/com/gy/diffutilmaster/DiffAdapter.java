package com.gy.diffutilmaster;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.DiffUtilHolder> {

    private List<DataBean> dataBeans ;

    public void setDate(List<DataBean> dataBeans){
        this.dataBeans = dataBeans;
    }

    @NonNull
    @Override
    public DiffUtilHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view  =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new DiffUtilHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiffUtilHolder diffUtilHolder, int i) {
          diffUtilHolder.tv_title.setText(dataBeans.get(i).getTitle());
    }

    @Override
    public void onBindViewHolder(@NonNull DiffUtilHolder holder, int position, @NonNull List<Object> payloads) {
        /*payloads 就是返回的不同*/
       if (payloads.isEmpty()){
           /*没有不同 就直接走两参数的方法*/
           onBindViewHolder(holder,position);
       }else{
           /*有不同 这里复制*/
           Bundle payload = (Bundle) payloads.get(0);
           DataBean bean = dataBeans.get(position);
           holder.tv_title.setText(bean.getTitle());
       }
    }

    @Override
    public int getItemCount() {
        return dataBeans!=null ? dataBeans.size():0;
    }

    class DiffUtilHolder extends RecyclerView.ViewHolder{

        TextView tv_title;

        public DiffUtilHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }

}
