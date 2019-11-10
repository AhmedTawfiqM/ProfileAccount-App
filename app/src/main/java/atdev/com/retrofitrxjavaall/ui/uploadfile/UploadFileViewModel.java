package atdev.com.retrofitrxjavaall.ui.uploadfile;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import atdev.com.retrofitrxjavaall.pojo.repositories.UploadRepo;

public class UploadFileViewModel extends ViewModel {


    public LiveData<String> UploadPhoto(Context context, String des, Uri fileURi)
    throws Exception{

        UploadRepo uploadRepo = new UploadRepo();
        return uploadRepo.UploadPhoto(context, des, fileURi);
    }

    public LiveData<String> UploadPhotoWithData(Context context, Map map, Uri fileURi)
            throws Exception{

        UploadRepo uploadRepo = new UploadRepo();
        return uploadRepo.UploadPhotoWithData(context, map, fileURi);
    }


}
