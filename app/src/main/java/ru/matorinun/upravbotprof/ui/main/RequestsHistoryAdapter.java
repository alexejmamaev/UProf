package ru.matorinun.upravbotprof.ui.main;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.matorinun.upravbotprof.R;
import ru.matorinun.upravbotprof.data.room.RequestEntity;

public class RequestsHistoryAdapter extends RecyclerView.Adapter<RequestsHistoryAdapter.ChildViewHolder> {

    private Context mContext;
    private ItemClickListener mListener;

    private List<RequestEntity> mEntities;

    RequestsHistoryAdapter(Context context, ItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;

    }

    @NonNull
    @Override
    public RequestsHistoryAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_main_item, parent, false);
        return new ChildViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsHistoryAdapter.ChildViewHolder holder, int position) {
        RequestEntity request = mEntities.get(position);

        String status = TextUtils.isEmpty(request.getS_NAME()) ? "" : request.getS_NAME();

        holder.data_tv.setText(request.getCR_DATE());
        holder.id_tv.setText(mContext.getString(R.string.id_number, request.getREQUEST_ID()));
        holder.status_tv.setText(status);
        holder.address_tv.setText(request.getADDRESS());
        holder.description_tv.setText(request.getREQUEST_TEXT());

        int statusColor;
        switch (status.toLowerCase()) {
            case "отправлена":
                statusColor = R.drawable.circle_point_status_sent;
                break;
            case "в работе":
                statusColor = R.drawable.circle_point_status_in_process;
                break;
            case "закрыта":
                statusColor = R.drawable.circle_point_status_closed;
                break;
            case "работа выполнена":
                statusColor = R.drawable.circle_point_status_job_done;
                break;
            case "отменена":
                statusColor = R.drawable.circle_point_status_canceled;
                break;
            case "отклонена":
                statusColor = R.drawable.circle_point_status_declined;
                break;
            case "возвращена в работу":
                statusColor = R.drawable.circle_point_status_returned;
                break;

            default:
                statusColor = R.drawable.circle_point_status_sent;
                break;

        }
        holder.statusPoint.setBackgroundResource(statusColor);
    }

    @Override
    public int getItemCount() {
        if (mEntities == null) {
            return 0;
        }
        return mEntities.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView data_tv, id_tv, address_tv, status_tv, description_tv;
        private FrameLayout statusPoint;

        ChildViewHolder(@NonNull View itemView) {
            super(itemView);

            data_tv = itemView.findViewById(R.id.main_item_data_tv);
            id_tv = itemView.findViewById(R.id.main_item_id_tv);
            address_tv = itemView.findViewById(R.id.main_item_address_tv);
            status_tv = itemView.findViewById(R.id.main_item_status_tv);
            description_tv = itemView.findViewById(R.id.main_item_description_tv);

            statusPoint = itemView.findViewById(R.id.main_item_status_point);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mEntities != null && mEntities.size() > 0)
                mListener.OnItemClickListener(mEntities.get(getAdapterPosition()));
        }
    }

    void setData(List<RequestEntity> entities) {
        this.mEntities = entities;
        notifyDataSetChanged();
    }

}
