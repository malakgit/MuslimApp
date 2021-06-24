package hamood.malak.muslimapp.MyUI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

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

//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        //building item view
//        View vitem= LayoutInflater.from(getContext()).inflate(R.layout.item_post,parent,false);
//        TextView titleitem=vitem.findViewById(R.id.titleitem);
//        TextView itemdate=vitem.findViewById(R.id.itemdate);
//        ImageView imageviewitem=vitem.findViewById(R.id.imageviewitem);
//
//
//
//        //getting data source
//        final MyPost myPost = getItem(position);
//
//
//
//        //connect item view to data source
//        titleitem.setText(myPost.getTitle());
//        itemdate.setText(myPost.getDatepost().toGMTString());
//       // imageviewitem.setImageIcon(myPost.get);
//        return vitem;
//    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //building item view
        View vitem= LayoutInflater.from(getContext()).inflate(R.layout.item_post,parent,false);
        TextView titleitem=vitem.findViewById(R.id.titleitem);
        TextView itemdate=vitem.findViewById(R.id.itemdate);
        ImageView imageviewitem =vitem.findViewById(R.id.imageviewitem);
        ImageView mapbtn=vitem.findViewById(R.id.mapbtn);



        //getting data source

        final MyPost myPost = getItem(position);
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://maps.google.co.in/maps?q=" + myPost.getLocation()));
                // if (intent.resolveActivity(getPackageManager()) != null) {
                getContext().startActivity(intent);
                // }
            }
        });
        downloadImageUsingPicasso(myPost.getImage(),imageviewitem);
        //downloadImageToMemory(myTask.getImage(),imageView);
        //downloadImageToLocalFile(myPost.getImage(),);
        //todo טיפול באירוע מחיקה



        return vitem;
    }



    private void downloadImageUsingPicasso(String imageUrL, ImageView toView)
    {
        Picasso.with(getContext())
                .load(imageUrL)
                .centerCrop()
                .error(R.drawable.common_full_open_on_phone)
                .resize(90,90)
                .into(toView);
    }
    private void downloadImageToLocalFile(String image,String fileURL, final ImageView toView) {
        StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl(fileURL);
        final File localFile;
        try {
            localFile = File.createTempFile("images", "jpg");


            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Toast.makeText(getContext(), "downloaded Image To Local File", Toast.LENGTH_SHORT).show();
                    toView.setImageURI(Uri.fromFile(localFile));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Toast.makeText(getContext(), "onFailure downloaded Image To Local File "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                    exception.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
