package com.example.library;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterForMainActivity extends RecyclerView.Adapter<AdapterForMainActivity.ViewHolder>  {

    private ArrayList<Book> list;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;


    public AdapterForMainActivity(ArrayList<Book> list, Context context) {
        this.context = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(context);

    }


    //метод обновления сожержимого адаптера
    public void updateAdapter(ArrayList<Book> books){
        this.list.clear();
        this.list.addAll(books);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_main,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Book book = list.get(position);

        holder.id.setText(book.getId()+"");

        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
       // holder.annotation.setText(book.getAnnotation());
        holder.pages.setText(book.getPageCount() + "");
        holder.year.setText(book.getYearOfPubl());
        if (book.isWish()){
            holder.wish.setChecked(true);
        }else {
            holder.wish.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView id, title, author, pages, year; //, annotation;
        CheckBox wish;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            id = (TextView) itemView.findViewById(R.id.idMain);
            title = itemView.findViewById(R.id.titleMain);
            author = itemView.findViewById(R.id.authorMain);
          //  annotation = itemView.findViewById(R.id.annotationMain);
            pages = itemView.findViewById(R.id.pagesMain);
            year = itemView.findViewById(R.id.yearMain);
            wish = itemView.findViewById(R.id.wishMain);
            wish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                   if (wish.isChecked()){
                       list.get(getAdapterPosition()).setWish(true);
                       DB.updateBook(list.get(getAdapterPosition()).getId(),"yes",context);
                    }else {
                       list.get(getAdapterPosition()).setWish(false);
                       DB.updateBook(list.get(getAdapterPosition()).getId(),"no",context);
                   }

                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Book getItem(int id) {
        return list.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        }
}
