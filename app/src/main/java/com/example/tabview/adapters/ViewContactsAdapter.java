package com.example.tabview.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tabview.models.Contacts;
import com.example.tabview.R;
import com.example.tabview.acttivities.ShowDetailContactsAcitivity;
import com.example.tabview.acttivities.MainActivity;
import com.example.tabview.fragments.ViewFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewContactsAdapter extends RecyclerView.Adapter<ViewContactsAdapter.ViewHolder>{

    Context mContext;
    ArrayList<Contacts> mList = new ArrayList<>();

    public ViewContactsAdapter(Context mContext, ArrayList<Contacts> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setData(ArrayList<Contacts> contactList) {
        this.mList = contactList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.row_contact, parent, false);
        return new ViewContactsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewContactsAdapter.ViewHolder holder, int position) {
        Contacts contacts = mList.get(position);
        holder.textViewName.setText(contacts.getName());
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
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Contacts contacts = new Contacts();
                MainActivity.contactsDatabase.contactsDAO().deleteByContactsId(contacts.getUid());
                Toast.makeText(mContext.getApplicationContext(),
                        "Contact deleted Successfully: "+ String.valueOf(contacts.getName()),
                        Toast.LENGTH_SHORT ).show();
//                Log.d("abc","deleted contact"+name);
//                tvDeleteContact.setText("");

                ((FragmentActivity)mContext).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.view_pager, new ViewFragment())
                        .commit();
            }
        });
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
