package shopping.akshar.com.shopping.adapter;

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

public class Cartadapter extends RecyclerView.Adapter<Cartadapter.ViewHolder> {

    Context context;
    List<Cartmodel> cartmodels;
    String Delete_URL = "http://192.168.0.103/shopping/delete_cart.php";
    String product_id;
    private int overTotalPtice = 0;


    public Cartadapter(Context context, List<Cartmodel> cartmodels) {
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


        holder.cart_title.setText(cartmodels.get(position).getProduct_name());
        holder.cart_price.setText(cartmodels.get(position).getProduct_price());
        Glide.with(context).load(cartmodels.get(position).getCart_imageurl()).into(holder.cart_image);
        /* int oneproduct = ((Integer.valueOf(cartmodels.get(position).getProduct_price())))
         * Integer.valueOf(cartmodels.get(position).getCart_qty());*/


        holder.edit_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQuantity();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int unit = Integer.parseInt(holder.quantity.getText().toString().trim());

                int output = unit + 1;
                String outprint = Integer.toString(output);
                holder.quantity.setText(outprint);
                int onepro = Integer.parseInt(holder.cart_price.getText().toString().trim());
                int qyny = Integer.parseInt(holder.quantity.getText().toString().trim());
                int oneproduct = onepro * qyny;
                overTotalPtice = overTotalPtice - oneproduct;
                String totalprice = Integer.toString(overTotalPtice);
                holder.costofitems.setText(totalprice);

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int unit = Integer.parseInt(holder.quantity.getText().toString().trim());
                if (unit > 0) {
                    int output = unit - 1;
                    String outprint = Integer.toString(output);
                    holder.quantity.setText(outprint);
                    int onepro = Integer.parseInt(holder.cart_price.getText().toString().trim());
                    int qyny = Integer.parseInt(holder.quantity.getText().toString().trim());
                    int oneproduct = onepro * qyny;
                    overTotalPtice = overTotalPtice - oneproduct;
                    String totalprice = Integer.toString(overTotalPtice);
                    holder.costofitems.setText(totalprice);

                } else {
                    holder.quantity.setText("0");
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("total_cost", String.valueOf(overTotalPtice));
        bundle.putString("quantity_cost", cartmodels.get(position).getCart_qty());

        product_id = cartmodels.get(position).getProduct_id();
    }



    /*open dialogbox in edit quantity*/

    private void AddQuantity() {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.edittext_dialog, null);
        final EditText edit_dialog = view.findViewById(R.id.edit_dialog);
        Button dialog_btn = view.findViewById(R.id.dialog_btn);
        builder.setTitle("Enter Quantity");

        dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quant = edit_dialog.getText().toString().trim();


            }
        });

        builder.setView(view);
        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
