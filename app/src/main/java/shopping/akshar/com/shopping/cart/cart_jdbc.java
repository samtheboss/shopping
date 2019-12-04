package shopping.akshar.com.shopping.cart;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.pojo.Cartmodel;

public class cart_jdbc extends AppCompatActivity {

    private ArrayList<cart_model> itemArrayList;  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private RecyclerView cartView; //RecyclerView
    private RecyclerView.LayoutManager mLayoutmanager;
    private boolean success = false; // boolean


    private static final String Db_url = "jgbc:mysql://rbx103.truehost.co.ke/eunidrip_shopping";
    private static final String user_name = "eunidrip_onshop";
    private static final String pass = "smart@3318";
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_jdbc);
        cartView = findViewById(R.id.cart_view);
        mLayoutmanager = new LinearLayoutManager(this);
        itemArrayList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        SyncData orderData = new SyncData();
        orderData.execute("");


    }

    private class SyncData extends AsyncTask<String, String, String>
    {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(cart_jdbc.this, "Synchronising",
                    "RecyclerView Loading! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(Db_url, user_name, pass);
                if (conn == null)
                {
                    success = false;
                }
                else {
                    String token =user.getUid();
                    String query = "SELECT * FROM cart,product WHERE cart.product_id = product.product_id and cart.token = '"+token+"'";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs != null)
                    {
                        while (rs.next())
                        {

                            try {
                                itemArrayList.add(new cart_model(rs.getString("product_name"),rs.getString("product_price"),rs.getString("product_image")));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        msg = "Found";
                        success = true;
                    } else {
                        msg = "No Data found!";
                        success = false;
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) // disimissing progress dialoge, showing error and setting up my gridview
        {
            progress.dismiss();
            Toast.makeText(cart_jdbc.this, msg + "", Toast.LENGTH_LONG).show();
            if (success == false)
            {
            }
            else {
                try
                {
                    myAppAdapter = new MyAppAdapter(itemArrayList , cart_jdbc.this);
                    cartView.setAdapter(myAppAdapter);
                } catch (Exception ex)
                {

                }

            }
        }
    }

    public class MyAppAdapter extends RecyclerView.Adapter<MyAppAdapter.ViewHolder> {
        private List<cart_model> values;
        public Context context;

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            // public image title and image url
            TextView cart_title,cart_price;
            ImageView imageView;
            public View layout;

            ViewHolder(View v)
            {
                super(v);
                layout = v;
                cart_price = itemView.findViewById(R.id.cart_price);
                cart_title = itemView.findViewById(R.id.cart_title);
                imageView = (ImageView) v.findViewById(R.id.imageView);
            }
        }

        // Constructor
        MyAppAdapter(List<cart_model> myDataset, Context context)
        {
            values = myDataset;
            this.context = context;
        }

        @Override
        public MyAppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.cart_row_layout, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Binding items to the view
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            final cart_model classListItems = values.get(position);
            holder.cart_title.setText(classListItems.getProduct_name());
            holder.cart_price.setText(classListItems.getPoduct_price());
            Glide.with(context).load(classListItems.getProduct_img_url()).into(holder.imageView);
        }

        // get item count returns the list item count
        @Override
        public int getItemCount() {
            return values.size();
        }

    }
}

