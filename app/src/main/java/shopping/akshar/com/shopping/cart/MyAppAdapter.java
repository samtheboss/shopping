package shopping.akshar.com.shopping.cart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.pojo.Cartmodel;

public class MyAppAdapter extends RecyclerView.Adapter<MyAppAdapter.ViewHolder> {

    Context context;
    List<Cartmodel> cartmodels;
    String Delete_URL = "http://192.168.0.103/shopping/delete_cart.php";
    String product_id;
    private int overTotalPtice = 0;


    public MyAppAdapter(Context context, List<Cartmodel> cartmodels) {
        this.context = context;
        this.cartmodels = cartmodels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {



    }

    @Override
    public int getItemCount() {
        return cartmodels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cart_image;
        TextView cart_title, cart_price, quantity, costofitems;
        ImageView edit_quantity;
        Button add, remove;


        ViewHolder(View itemView) {
            super(itemView);
            costofitems = itemView.findViewById(R.id.totalcost);
            cart_image = itemView.findViewById(R.id.cart_image);
            cart_title = itemView.findViewById(R.id.cart_title);
            cart_price = itemView.findViewById(R.id.cart_price);
            add = itemView.findViewById(R.id.addquantity);
            remove = itemView.findViewById(R.id.remove);
            quantity = itemView.findViewById(R.id.tv_quantity);
            edit_quantity = itemView.findViewById(R.id.edit_quantity);


        }


    }
}
