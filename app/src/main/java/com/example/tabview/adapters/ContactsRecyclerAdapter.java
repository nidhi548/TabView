package com.example.tabview.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tabview.models.Contacts;
//import com.example.tabview.R;
import com.example.tabview.R;
import com.example.tabview.acttivities.ShowDetailContactsAcitivity;
//import com.example.tabview.acttivities.MainActivity;

import java.util.ArrayList;

//import static com.example.tabview.acttivities.MainActivity.*;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Contacts> mList = new ArrayList<>();

    public ContactsRecyclerAdapter(Context mContext, ArrayList<Contacts> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setData(ArrayList<Contacts> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.row_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsRecyclerAdapter.ViewHolder holder, int position) {
        Contacts contacts = mList.get(position);
        holder.textViewName.setText(contacts.getName());

        Glide.with(mContext).load(R.drawable.ic_launcher_foreground).apply(new RequestOptions().circleCrop()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowDetailContactsAcitivity.class);
                intent.putExtra("name", contacts.getName());
                intent.putExtra("phone", contacts.getPhone_Number());
                intent.putExtra("birthday", contacts.getBirthday());
//                intent.putExtra("catId", category.getCatId());
                mContext.startActivity(intent);
            }
        });
//        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Contacts contacts = new Contacts();
//                contactsDatabase.contactsDAO().deleteByContactsId(contacts.getUid());
////                Toast.makeText(getApplicationContext(),
////                        "Contact deleted Successfully: "+ String.valueOf(name),
////                        Toast.LENGTH_SHORT ).show();
////                Log.d("abc","deleted contact"+name);
////                tvDeleteContact.setText("");
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageView;

        ImageView ivEdit, ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_name_row);
            imageView = itemView.findViewById(R.id.iv_image_row);
            ivEdit = itemView.findViewById(R.id.iv_Edit);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }


    }

}
