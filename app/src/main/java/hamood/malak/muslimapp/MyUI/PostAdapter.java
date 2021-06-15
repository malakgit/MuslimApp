package hamood.malak.muslimapp.MyUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hamood.malak.muslimapp.MyUtils.MyPost;
import hamood.malak.muslimapp.R;

public class PostAdapter extends ArrayAdapter<MyPost>
{
    public PostAdapter(@NonNull Context context, int res) {
        super(context, R.layout.item_post);
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //building item view
        View vitem= LayoutInflater.from(getContext()).inflate(R.layout.item_post,parent,false);
        TextView titleitem=vitem.findViewById(R.id.titleitem);
        TextView itemdate=vitem.findViewById(R.id.itemdate);
        ImageView imageviewitem=vitem.findViewById(R.id.imageviewitem);



        //getting data source
        final MyPost myPost = getItem(position);



        //connect item view to data source
        titleitem.setText(myPost.getTitle());
        itemdate.setText(myPost.getDatepost().toGMTString());
       // imageviewitem.setImageIcon(myPost.get);


        return vitem;





    }
}
