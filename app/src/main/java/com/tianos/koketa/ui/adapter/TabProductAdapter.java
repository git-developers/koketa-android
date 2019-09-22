package com.tianos.koketa.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.tianos.koketa.R;
import com.tianos.koketa.database.OrderDb;
import com.tianos.koketa.database.OrderDetailDb;
import com.tianos.koketa.entity.Order;
import com.tianos.koketa.entity.OrderDetail;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.ui.fragment.ProductDetailFragment;
import com.tianos.koketa.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabProductAdapter extends RecyclerView.Adapter<TabProductAdapter.ProductViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<OrderDetail> lst;

    public TabProductAdapter(Context context, List<OrderDetail> lst) {
        this.context = context;
        this.lst = lst;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_tab_product, parent, false);
        return new ProductViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        OrderDetail orderDetail = this.lst.get(position);
        Product product = orderDetail.getProduct();

        holder.tvName.setText(product.getName());
        holder.tvFamily.setText(product.getFamily());
        holder.tvStock.setText(Integer.toString(product.getStock()) + " Unidades");
        holder.tvPrice.setText("SOL " + Util.money(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (this.lst != null) ? this.lst.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        @BindView(R.id.card_view)
        CardView cardView;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_family)
        TextView tvFamily;

        @BindView(R.id.tv_stock)
        TextView tvStock;

        @BindView(R.id.tv_price)
        TextView tvPrice;


        public ProductViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            ButterKnife.bind(this, view);

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    int position = getLayoutPosition();
                    OrderDetail orderDetail = lst.get(position);

                    final CharSequence[] items = {"Eliminar"};
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    //dialogBuilder.setTitle("Pick a color");
                    dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            CharSequence mChosenFile = items[which];

                            cardView.setVisibility(View.INVISIBLE);

                            OrderDetailDb orderDetailDb = new OrderDetailDb(context);
                            orderDetailDb.deleteById(String.valueOf(orderDetail.getId()));
                        }

                    });
                    dialogBuilder.create().show();

                    return true;
                }
            });

        }

    }
}
