package com.pune.earnwealth.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pune.earnwealth.R;
import com.pune.earnwealth.models.TaskDeatils;

import java.util.ArrayList;

import static com.pune.earnwealth.helper.Utils.highlightText;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.Holder>
{
    Context cntx ;
    ArrayList<TaskDeatils> mainList ;
    ArrayList<TaskDeatils> attachList ;
    String searchText = "" ;

    public TaskAdapter(Context cntx) {
        this.cntx = cntx;
        attachList = new ArrayList<>();

    }

    public void onLoad(ArrayList<TaskDeatils> list)
    {
        this.mainList = list;
        attachList.addAll(mainList);
        notifyDataSetChanged();
    }

    public void onSearchTaskName(String task_name)
    {
        searchText = task_name ;
        attachList.clear();

        if(!task_name.equals(""))
        {
            for (TaskDeatils pojo: mainList)
            {
                if(pojo.getTaskName().toLowerCase().contains(task_name))
                {
                    attachList.add(pojo);
                }
            }
        }
        else
        {
            attachList.addAll(mainList);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(cntx).inflate(R.layout.holder_task , parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.Holder holder, int position) {

        TaskDeatils pojo = attachList.get(position);

        holder.name.setText(pojo.getTaskName() + "");
        holder.task.setText(pojo.getTaskDescription()+ "");
        holder.status.setText(pojo.getCurrentStatus() + "");
        holder.assiggn_date.setText(pojo.getAssignTo() + "");
        holder.due_date.setText(pojo.getTaskDuedate() + "");
        holder.task_id.setText(pojo.getTaskId() + "");

        // Higglight SearchText
        if(searchText.trim().length()>0){
            holder.name.setText(highlightText(searchText,pojo.getTaskName()));

        }else{
            holder.name.setText(Html.fromHtml(pojo.getTaskName()));
        }

    }

    @Override
    public int getItemCount() {
        return attachList.size();
    }


    public class Holder extends RecyclerView.ViewHolder
    {

        TextView name , task  , status , assiggn_date , due_date , task_id;

        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            task = itemView.findViewById(R.id.task);
             status = itemView.findViewById(R.id.status);
            assiggn_date = itemView.findViewById(R.id.assiggn_date);
            due_date = itemView.findViewById(R.id.due_date);
            task_id = itemView.findViewById(R.id.task_id);
        }
    }




}
