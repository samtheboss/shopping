package shopping.akshar.com.shopping.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.adapter.Productadapter;
import shopping.akshar.com.shopping.pojo.Categorymodel;
import shopping.akshar.com.shopping.pojo.Productmodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    RecyclerView product_recyclerView;
    private ProgressBar progressBar;
    Productadapter adpter;
    private Context context;
    private List<Productmodel> productmodels;
    private String URL = "http://eunidripapp.eunidripirrigationsystems.com/app_apis/product.php";
    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        product_recyclerView = view.findViewById(R.id.product_recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        productmodels = new ArrayList<>();

        productBind();
        // LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        //  product_recyclerView.setLayoutManager(manager);

        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        product_recyclerView.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);

        MenuItem searchItem =menu.findItem(R.id.search);

        SearchView serchview = (SearchView) searchItem.getActionView();

        serchview.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void productBind() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                JSONObject jsonObject = null;

                for (int i=0;i<response.length();i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Productmodel productmodel = new Productmodel();
                        productmodel.setProduct_id(jsonObject.getString("product_id"));
                        productmodel.setProduct_name(jsonObject.getString("product_name"));
                        productmodel.setProduct_price(jsonObject.getString("product_price"));
                        productmodel.setProduct_desc(jsonObject.getString("product_desc"));
                        productmodel.setProduct_image(jsonObject.getString("product_image"));
                        productmodel.setMax_prce(jsonObject.getString("max_price"));



                        productmodels.add(productmodel);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Productadapter productadapter = new Productadapter(getActivity(),productmodels);
                product_recyclerView.setAdapter(productadapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adpter.getFilter().filter(s);

        return false;
    }}