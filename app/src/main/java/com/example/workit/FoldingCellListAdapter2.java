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
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FoldingCellListAdapter2 extends ArrayAdapter<Services> {

    private HashSet<Integer> unfoldedIndexess = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    private View.OnClickListener callBtnClickListener;


    private Context mContext; //instance variable

    public FoldingCellListAdapter2(Context context, List<Services> objects) {
        super(context, 0, objects);
        this.mContext= context;

    }



    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        SharedPreferences mPrefs = getContext().getSharedPreferences("pref",MODE_PRIVATE);
        ArrayList<user> users;
        Gson gson = new Gson();
        String json = mPrefs.getString("allusers", "");
        Type type = new TypeToken<ArrayList< user >>() {}.getType();
        users = gson.fromJson(json,type);


        ArrayList<requests> requests;
        Gson gson1 = new Gson();
        String json1 = mPrefs.getString("allrequests", "");
        Type type1 = new TypeToken<ArrayList< requests >>() {}.getType();
        requests = gson.fromJson(json1,type1);



        // get item for selected view
        Services allJobs = getItem(position);


        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell2 = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell2 == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell2 = (FoldingCell) vi.inflate(R.layout.cell2, parent, false);
            // binding view parts to view holder
            viewHolder.image = cell2.findViewById(R.id.providerimg2);

            viewHolder.price = cell2.findViewById(R.id.title_price2);
            viewHolder.jobtitle = cell2.findViewById(R.id.jobtitle2);
            viewHolder.names = cell2.findViewById(R.id.name2);

            viewHolder.jobdescription = cell2.findViewById(R.id.jobdescription12);
            viewHolder.delivery_date = cell2.findViewById(R.id.content_delivery_time12);
            viewHolder.request_deadline = cell2.findViewById(R.id.content_deadline_time12);
            viewHolder.request = cell2.findViewById(R.id.request_count2);
            viewHolder.price1 = cell2.findViewById(R.id.head_image_center_text22);

            viewHolder.showplacee = cell2.findViewById(R.id.textView7);

            viewHolder.callingbtn1 = cell2.findViewById(R.id.hoyyyyyyyyy);




            //viewHolder.contentRequestBtn = cell2.findViewById(R.id.requestjob);
            cell2.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexess.contains(position)) {
                cell2.unfold(true);
            } else {
                cell2.fold(true);

            }
            viewHolder = (ViewHolder) cell2.getTag();
        }

        if (null == allJobs)
            return cell2;

        // bind data from selected element to view through view holder
        viewHolder.price.setText(String.valueOf(allJobs.getPrice()));
        viewHolder.jobtitle.setText(allJobs.getTitle());
        viewHolder.jobdescription.setText(allJobs.getDescription());
        viewHolder.price1.setText(String.valueOf(allJobs.getPrice()));

        viewHolder.request.setText(allJobs.getTitle());





        for(int i=0;i<users.size();i++)
        {

            if (users.get(i).getUniqueid().equals(allJobs.getIdp()))
            {

                viewHolder.names.setText(users.get(i).getName()+" "+users.get(i).getLastname());
            }

        }

        viewHolder.delivery_date.setText(allJobs.getDatecreation());
        viewHolder.request_deadline.setText(allJobs.getDeadline());
        Picasso.get().load("http://41.226.11.252:11866/uploads/"+allJobs.getIdp()+".png").into(viewHolder.image);

        viewHolder.showplacee.setOnClickListener(allJobs.getShowplace1BtnClickListener());


        viewHolder.callingbtn1.setOnClickListener(allJobs.getCall1BtnClickListener());
        return cell2;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexess.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexess.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexess.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    public View.OnClickListener getCallBtnClickListener() {
        return callBtnClickListener;
    }

    public void setCallBtnClickListener(View.OnClickListener callBtnClickListener) {
        this.callBtnClickListener = callBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView price;
        TextView price1;
        TextView showplacee;
        TextView callingbtn1;
        TextView requestbtn;
        TextView contentRequestBtn;
        CircleImageView image;
        TextView request;
        TextView jobtitle;
        TextView jobdescription;
        TextView names;
        TextView delivery_date;
        TextView request_deadline;
        TextView phone_number;
        TextView requestBtnClickListener1;
    }
}