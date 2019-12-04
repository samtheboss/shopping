package shopping.akshar.com.shopping.cartView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import shopping.akshar.com.shopping.R;


public class cartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextInputEditText rideid;
    TextView time;
    Button moreInformation;

    public cartViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        rideid = itemView.findViewById(R.id.rideid);
        time = itemView.findViewById(R.id.time);
        moreInformation = itemView.findViewById(R.id.moreInfor);
        moreInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(v.getContext(), cartSingleActivity.class);
                Bundle b = new Bundle();
                b.putString("requestId", rideid.getText().toString());
                intent.putExtras(b);

                v.getContext().startActivity(intent);*/
            }
        });

    }

    @Override
    public void onClick(View v) {
       /* Intent intent = new Intent(v.getContext(), cartSingleActivity.class);
        Bundle b = new Bundle();
        b.putString("requestId", rideid.getText().toString());
        intent.putExtras(b);

        v.getContext().startActivity(intent);*/


    }
}
