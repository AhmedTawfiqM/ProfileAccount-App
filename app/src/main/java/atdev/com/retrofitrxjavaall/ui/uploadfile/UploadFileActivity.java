package atdev.com.retrofitrxjavaall.ui.uploadfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import atdev.com.retrofitrxjavaall.R;
import atdev.com.retrofitrxjavaall.databinding.ActivityUploadFileBinding;
import okhttp3.RequestBody;

public class UploadFileActivity extends AppCompatActivity {

    //Vars
    private static final int RESULT_LOAD_IMAGE = 9;
    private static final int RESULT_LOAD_IMAGE_WITHDATA = 10;
    ActivityUploadFileBinding binding;
    UploadFileViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload_file);

        viewModel = ViewModelProviders.of(this).get(UploadFileViewModel.class);

        binding.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE_WITHDATA);
            }
        });


        binding.btnUploadLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImageUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            @SuppressWarnings("deprecation")
            Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
            cursor.moveToFirst();

            int column_index = cursor.getColumnIndex(projection[0]);
            String imagePath = cursor.getString(column_index);
            cursor.close();

            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                //Bitmap imageBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

                Toast.makeText(getApplicationContext(), imagePath, Toast.LENGTH_SHORT).show();

                UploadPhotoNow(Uri.parse(imagePath));
            }

        } else if (requestCode == RESULT_LOAD_IMAGE_WITHDATA && resultCode == RESULT_OK && null != data) {

            Uri selectedImageUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            @SuppressWarnings("deprecation")
            Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
            cursor.moveToFirst();

            int column_index = cursor.getColumnIndex(projection[0]);
            String imagePath = cursor.getString(column_index);
            cursor.close();

            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                //Bitmap imageBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

                Toast.makeText(getApplicationContext(), imagePath, Toast.LENGTH_SHORT).show();

                UploadPhotoWithDataNow(Uri.parse(imagePath));
            }

        } else {
            Toast.makeText(getApplicationContext(), "You have not selected and image", Toast.LENGTH_SHORT).show();
        }

    }

    private void UploadPhotoWithDataNow(Uri parse) {

        Map<String, RequestBody> mapData = new HashMap<>();
        //Default Values
        mapData.put("first_name", createRequestfromString("OS"));
        mapData.put("last_name", createRequestfromString("andorid"));
        mapData.put("email", createRequestfromString("andorid@"));

        try {
            viewModel.UploadPhotoWithData(getApplicationContext(), mapData, parse)
                    .observe(this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {

                            Toast.makeText(UploadFileActivity.this, s, Toast.LENGTH_LONG).show();

                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void UploadPhotoNow(Uri imagePath) {

        String des = binding.etDesc.getText().toString();
        if (des.isEmpty()) {
            des = "descripion";
        }

        try {
            viewModel.UploadPhoto(getApplicationContext(), des, imagePath)
                    .observe(this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {

                            Toast.makeText(UploadFileActivity.this, s, Toast.LENGTH_LONG).show();

                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //https://androidclarified.com/android-image-upload-example/
    }

    @NonNull
    private RequestBody createRequestfromString(String string) {

        return RequestBody.create(okhttp3.MultipartBody.FORM, string);
    }
}
