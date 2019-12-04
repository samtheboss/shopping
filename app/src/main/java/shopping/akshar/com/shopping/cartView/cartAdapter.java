package shopping.akshar.com.shopping.cartView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import java.util.List;

import shopping.akshar.com.shopping.R;

public class cartAdapter extends RecyclerView.Adapter<cartViewHolder> {
    private List<cartObject> itemList;
    private Context context;

    public cartAdapter(List<cartObject> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        cartViewHolder rcv = new cartViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewHolder holder, int position) {
        holder.rideid.setText(itemList.get(position).getRideId());
        holder.time.setText(itemList.get(position).getRequestTime());
        holder.rideid.setEnabled(false);


    }

    @Override
    public int getItemCount() {

       return this.itemList.size();
    }
}
