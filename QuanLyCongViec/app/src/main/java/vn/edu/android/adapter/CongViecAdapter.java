package vn.edu.android.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.android.model.CongViec;
import vn.edu.android.quanlycongviec.R;

/**
 * Created by Nguyen Trung Truc on 10/13/2016.
 */
public class CongViecAdapter extends ArrayAdapter<CongViec> {

    Activity context;
    int resource;
    List<CongViec> listData;
    public CongViecAdapter(Activity context, int resource, List<CongViec> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listData=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row= inflater.inflate(this.resource,null);

        TextView txtTenCongViec = (TextView) row.findViewById(R.id.txtTenCongViec);
        TextView txtMota = (TextView) row.findViewById(R.id.txtMota);
        TextView txtNgayHT = (TextView) row.findViewById(R.id.txtNgayHT);
        TextView txtGioHT = (TextView) row.findViewById(R.id.txtGioHT);

        CongViec congViec = this.listData.get(position);
        txtTenCongViec.setText(congViec.getTenCongViec());
        txtMota.setText(congViec.getMoTa());
        txtNgayHT.setText(congViec.getNgayHT());
        txtGioHT.setText(congViec.getGioHT());

        return row;
    }

}
