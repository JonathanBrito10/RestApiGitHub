package com.jonathanbrito.restgithub.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.jonathanbrito.restgithub.model.Item;
import com.jonathanbrito.restgithub.view.DetalhesActivity;
import com.squareup.picasso.Picasso;
import com.jonathanbrito.restgithub.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;


    public ItemAdapter (Context appContext, List<Item> itemArray) {
        this.context = appContext;
        this.items = itemArray;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.usuarios,viewGroup, false);
        return new ViewHolder(view);
         }


    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
      holder.title.setText(items.get(position).getLogin());
      holder.gitLink.setText(items.get(position).getHtmlUrl());

        Picasso.with(context)
                .load(items.get(position).getAvatarUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title, gitLink;
        private ImageView imageView;
        public ViewHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.titulo);
            gitLink = (TextView) view.findViewById(R.id.gitLink);
            imageView = (ImageView) view.findViewById(R.id.foto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Item clickDataItem = items.get(pos);
                        Intent intent = new Intent(context, DetalhesActivity.class);
                        intent.putExtra("login", items.get(pos).getLogin());
                        intent.putExtra("html_url", items.get(pos).getHtmlUrl());
                        intent.putExtra("avatar_url", items.get(pos).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(view.getContext(), "Você Clicou" + clickDataItem.getLogin(),Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }


}
