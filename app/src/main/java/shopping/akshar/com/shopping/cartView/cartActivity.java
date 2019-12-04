package shopping.akshar.com.shopping.cartView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import shopping.akshar.com.shopping.R;


public class cartActivity extends AppCompatActivity {
    private RecyclerView mHistoryRecyclerView;
    private String  userid;
    private RecyclerView.Adapter mHistoryAdapter;
    private RecyclerView.LayoutManager mHistoryLayoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcart);

        mHistoryRecyclerView = findViewById(R.id.historyRecyclerView);
        mHistoryRecyclerView.setNestedScrollingEnabled(false);
        mHistoryRecyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.progress);
        mHistoryLayoutManager = new LinearLayoutManager(cartActivity.this);
        mHistoryRecyclerView.setLayoutManager(mHistoryLayoutManager);

      //  mHistoryAdapter = new cartAdapter(getDataHistory(), cartActivity.this);

        mHistoryRecyclerView.setAdapter(mHistoryAdapter);

        //cartObject object = new cartObject("1234");

       //customerOrDriver = getIntent().getExtras().getString("customerOrDriver");
        userid = FirebaseAuth.getInstance().getUid();

        getUserHistoryIds();


        mHistoryAdapter.notifyDataSetChanged();

    }

    private void getUserHistoryIds() {
        DatabaseReference userHistoryDatabase = FirebaseDatabase.getInstance().getReference().child("history").child(userid);

        userHistoryDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot history : dataSnapshot.getChildren()) {
                        getcartInformation(history.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {
            }
        });
    }


    private void getcartInformation(String cartId) {
        DatabaseReference historyDatabase = FirebaseDatabase.getInstance().getReference("cartInformation").child(userid);
        historyDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String rideId = dataSnapshot.getKey();
                    Long timestamp = 0L;
                    String distance = "";
                    Double ridePrice = 0.0;

                    if (dataSnapshot.child("Time").getValue() != null) {
                        timestamp = Long.valueOf(dataSnapshot.child("Time").getValue().toString());
                    }

                        progressBar.setVisibility(View.GONE);


                    cartObject obj = new cartObject(rideId, getDate(timestamp));
                    resultHistory.add(obj);
                    mHistoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private String getDate(Long time) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(time * 1000);

        String date = DateFormat.format("MM-dd-yyyy hh:mm", cal).toString();
        return date;
    }


    private ArrayList resultHistory = new ArrayList();

    private List<cartObject> getDataHistory() {
        return resultHistory;
    }
}
