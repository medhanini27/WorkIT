package com.example.workit;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ramotion.foldingcell.FoldingCell;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FoldingCellListAdapter extends ArrayAdapter<Services>  {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    private View.OnClickListener placeBtnClickListener;
    private View.OnClickListener deleteBtnClickListener;



    private Context mContext; //instance variable
    Services s;

    public FoldingCellListAdapter(Context context, List<Services> objects) {
        super(context, 0, objects);
        this.mContext= context;

    }



    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view

        Services myjobs = getItem(position);

        SharedPreferences mPrefs = getContext().getSharedPreferences("pref",MODE_PRIVATE);
        ArrayList<requests> requests;
        Gson gson1 = new Gson();
        String json1 = mPrefs.getString("allrequests", "");
        Type type1 = new TypeToken<ArrayList< requests >>() {}.getType();
        requests = gson1.fromJson(json1,type1);







        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.price = cell.findViewById(R.id.title_price);
            viewHolder.jobtitle = cell.findViewById(R.id.jobtitle);
            viewHolder.jobdescription = cell.findViewById(R.id.job_description);
            viewHolder.request_count = cell.findViewById(R.id.request_count0);
            viewHolder.deliverytime = cell.findViewById(R.id.content_delivery_date0);
            viewHolder.deadlinetime = cell.findViewById(R.id.content_deadline_time0);
            viewHolder.jobdescription2 = cell.findViewById(R.id.jobtdescription0);
            viewHolder.jobtitle2 = cell.findViewById(R.id.content_from_address_1);
            viewHolder.showreq=cell.findViewById(R.id.showreq);






            viewHolder.contentRequestBtn = cell.findViewById(R.id.contentRequestBtn);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);

            } else {

                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == myjobs)
        { return cell;}

        // bind data from selected element to view through view holder
        viewHolder.price.setText(String.valueOf(myjobs.getPrice()));
        viewHolder.jobtitle.setText(myjobs.getTitle());
        viewHolder.jobdescription2.setText(myjobs.getDescription());
        viewHolder.jobdescription.setText(myjobs.getDescription());
        viewHolder.jobtitle2.setText(myjobs.getTitle());
        viewHolder.deadlinetime.setText(myjobs.getDeadline());
        viewHolder.deliverytime.setText(myjobs.getDatecreation());
        int a=0;
        for(int i=0;i<requests.size();i++)
        {
            if(myjobs.getUid().equals(requests.get(i).getIdservice())) a++;

        }
        viewHolder.request_count.setText(String.valueOf(a));



        // set custom btn handler for list item from that item


           viewHolder.contentRequestBtn.setOnClickListener(myjobs.getDeleteBtnClickListener());
           viewHolder.showreq.setOnClickListener(myjobs.getShowreqBtnClickListener());







        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    public View.OnClickListener getplaceBtnClickListener() {
        return placeBtnClickListener ;
    }

    public void setplaceBtnClickListener(View.OnClickListener placeBtnClickListener) {
        this.placeBtnClickListener = placeBtnClickListener;
    }

    public View.OnClickListener getDeleteBtnClickListener() {
        return deleteBtnClickListener;
    }

    public void setDeleteBtnClickListener(View.OnClickListener deleteBtnClickListener) {
        this.deleteBtnClickListener = deleteBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView price;
        TextView contentRequestBtn;
        TextView jobtitle ;
        TextView jobdescription;
        TextView request_count;
        TextView deliverytime;
        TextView deadlinetime;
        TextView jobdescription2;
        TextView jobtitle2;
        TextView showplace;
        TextView showreq;

    }
}
