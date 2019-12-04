package shopping.akshar.com.shopping.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.adapter.Cartadapter;
import shopping.akshar.com.shopping.pojo.Cartmodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    String URL = "http://eunidripapp.eunidripirrigationsystems.com/app_apis/viewCart.php";
    String qty_cartURL = "http://eunidripapp.eunidripirrigationsystems.com/app_apis/cart.php";
    private List<Cartmodel> cartmodels;
    RecyclerView recycler_cart;
    Cartmodel models = new Cartmodel();
    TextView total_price;
    Button btn_change;
    FirebaseAuth auth;
    FirebaseUser user;


    SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";


    public CartFragment() {
        // Required empty   public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recycler_cart = view.findViewById(R.id.recycler_cart);
        total_price = view.findViewById(R.id.total_price);
        // btn_change = view.findViewById(R.id.btn_change);
        cartmodels = new ArrayList<>();


        sendReview();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        total_price.setText(user.getUid());
        Bundle bundle = getArguments();

        //  int totalprice = Integer.parseInt(models.getProduct_price())*Integer.parseInt(models.getCart_qty());
        //  String totalPrice = Integer.toString(totalprice);
        //   total_price.setText(totalPrice);      //  String totalPrice =  bundle.getString("total_cost");
        //  total_price.setText(totalPrice);
        /* show editetxt dialog on button click event*/

      /*  btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.edittext_dialog,null);
                final EditText edit_dialog = view1.findViewById(R.id.edit_dialog);
                Button dialog_btn = view1.findViewById(R.id.dialog_btn);

                dialog_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!edit_dialog.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(),"Edit Success",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"Please fill it",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setView(view1);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_cart.setLayoutManager(linearLayoutManager);
        return view;
    }

    private void sendReview() {



        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject dat = array.getJSONObject(i);

                        //sharedPreferences.getString(KEY_EMAIL, null);

                        Cartmodel cartmodel = new Cartmodel();
                        cartmodel.setCart_id(dat.getString("cart_id"));
                        cartmodel.setToken(dat.getString("token"));
                        cartmodel.setProduct_name(dat.getString("product_name"));
                        cartmodel.setProduct_price(dat.getString("product_price"));
                        cartmodel.setCart_imageurl(dat.getString("product_image"));
                        cartmodel.setCart_date(dat.getString("cart_date"));
                        cartmodels.add(cartmodel);
                    }
                    Cartadapter cartadapter = new Cartadapter(getActivity(), cartmodels);
                    recycler_cart.setAdapter(cartadapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String> params = new HashMap<>();
                String userid = user.getUid();

                params.put("userid", userid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
    private void cartView() {

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject dat = array.getJSONObject(i);

                        //sharedPreferences.getString(KEY_EMAIL, null);

                        Cartmodel cartmodel = new Cartmodel();
                        cartmodel.setCart_id(dat.getString("cart_id"));
                        cartmodel.setToken(dat.getString("token"));
                        cartmodel.setProduct_name(dat.getString("product_name"));
                        cartmodel.setProduct_price(dat.getString("product_price"));
                        cartmodel.setCart_imageurl(dat.getString("product_image"));
                        cartmodel.setCart_date(dat.getString("cart_date"));
                        cartmodels.add(cartmodel);
                    }
                    Cartadapter cartadapter = new Cartadapter(getActivity(), cartmodels);
                    recycler_cart.setAdapter(cartadapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"error loading the cart" , Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                String userid = user.getUid();

                Map<String, String> params = new HashMap<>();
                params.put("userid", userid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }


}
