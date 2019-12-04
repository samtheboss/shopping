package shopping.akshar.com.shopping.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.fragment.CartFragment;
import shopping.akshar.com.shopping.fragment.ProductFragment;
import shopping.akshar.com.shopping.fragment.ProductdetailFragment;
import shopping.akshar.com.shopping.pojo.Productmodel;

public class Productadapter extends RecyclerView.Adapter<Productadapter.ViewHolder>implements Filterable {

    private Context context;
    private List<Productmodel> productmodels;
    private List<Productmodel> productserched;

    public Productadapter(Context context, List<Productmodel> productmodels) {
        this.context = context;
        this.productmodels = productmodels;
        productserched = new ArrayList<>(productmodels);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.card_click.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.product_image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

        holder.product_name.setText(productmodels.get(position).getProduct_name());
        holder.price.setText(productmodels.get(position).getProduct_price());
        holder.maxprice.setText(productmodels.get(position).getMax_prce());
        Glide.with(context).load(productmodels.get(position).getProduct_image()).into(holder.product_image);

        holder.card_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new ProductdetailFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_main,fragment)
                        .addToBackStack(null)
                        .commit();


                Bundle bundle = new Bundle();
                bundle.putString("product_id",productmodels.get(position).getProduct_id());
                bundle.putString("product_name",productmodels.get(position).getProduct_name());
                bundle.putString("price",productmodels.get(position).getProduct_price());
                bundle.putString("product_desc",productmodels.get(position).getProduct_desc());
                bundle.putString("product_image",productmodels.get(position).getProduct_image());
                bundle.putString("max_price",productmodels.get(position).getMax_prce());

                fragment.setArguments(bundle);


            }
        });

        /*holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,""+productmodels.get(position).getProduct_name(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }



    @Override
    public int getItemCount() {
        return productmodels.size();
    }

    @Override
    public Filter getFilter() {
        return searched;
    }

    private Filter searched  = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Productmodel> filteredList = new ArrayList<>();
            if (charSequence ==null || charSequence.length()==0){
                filteredList.addAll(productserched);
            }else {
                String filteredname = charSequence.toString().trim();

                for (Productmodel item: productserched){
                    if(item.getProduct_name().contains(filteredname)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults result =  new FilterResults();
            result.values = filteredList;

            return result;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productmodels.clear();
            productmodels.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView product_name;
        TextView price,maxprice;
        ImageView product_image;
        ImageButton favorite;
        CardView card_click;



        public ViewHolder(View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            product_image = itemView.findViewById(R.id.product_image);
            favorite = itemView.findViewById(R.id.favorite);
            maxprice =itemView.findViewById(R.id.maxiprice);
            card_click = itemView.findViewById(R.id.card_click);
        }


    }
}
