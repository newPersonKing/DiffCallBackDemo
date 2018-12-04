package com.gy.diffutilmaster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import java.util.List;

/*调用calculateDiff 方法 会直接回调这里的方法*/
public class CustomDiffCallBack extends DiffUtil.Callback {

    private List<DataBean>  mOldDatas,mNewDatas;

    public CustomDiffCallBack(List<DataBean> mOldDatas,List<DataBean> mNewDatas){
           this.mOldDatas = mOldDatas;
           this.mNewDatas = mNewDatas;
    }


    @Override
    public int getOldListSize() {
        return mOldDatas!=null?mOldDatas.size():0;
    }

    @Override
    public int getNewListSize() {
          return mNewDatas!=null?mNewDatas.size():0;
    }

    /*新老数据集在同一个postion的Item是否是一个对象？（可能内容不同，如果这里返回true，会调用下面的方法）*/
    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return !mOldDatas.get(oldPosition).getTitle().equals(mNewDatas.get(newPosition).getTitle());
    }

    /*这个方法仅仅是上面方法返回ture才会调用，我的理解是只有notifyItemRangeChanged()才会调用，判断item的内容是否有变化*/
    @Override
    public boolean areContentsTheSame(int oldPosition, int newPosition) {
        return mOldDatas.get(oldPosition).getTitle().equals(mNewDatas.get(newPosition).getTitle());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        /*这里返回 相同item的内容变化*/
        Bundle bundle = new Bundle();
        if (!mOldDatas.get(oldItemPosition).getTitle().equals(mNewDatas.get(newItemPosition).getTitle())){
            bundle.putString("key",mNewDatas.get(newItemPosition).getTitle());
        }
        if (bundle.size()==0){
            return null;
        }
        return bundle;
    }
}
