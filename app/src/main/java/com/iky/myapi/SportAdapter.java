package com.iky.myapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

    //atribute
    private Context mcontext;
    private ArrayList<Sport>sports;
    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;

    }

    //yang ada di dalam kurung itu parameter kenapa sport tidak diberi parameter karena ambil dta di internet dan data di android masih kosong
    public SportAdapter(Context mcontext) {
        this.mcontext = mcontext;
        this.sports = new ArrayList<>();
    }
// buat setter untuk mengambil data dari internet
    public void setSports(ArrayList<Sport> sports) {
        this.sports = sports;
        //mengubah tampilan data android jika ada data baru
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    //memanggil layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.list_sport_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    // menghubungkan 1 object dengan 1 layout
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //mengambil data Sport
        Sport currentSport = sports.get(position);
        holder.txtName.setText(currentSport.getName());
        holder.txtNationality.setText(currentSport.getNationality());
        Picasso.get().load(currentSport.getImagePath()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtName,txtNationality;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gambar);
            txtName = itemView.findViewById(R.id.name);
            txtNationality = itemView.findViewById(R.id.Nationality);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.aksiKlik(getAdapterPosition());

                    }
                }
            });
        }
    }
}
