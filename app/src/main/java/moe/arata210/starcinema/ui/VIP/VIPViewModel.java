package moe.arata210.starcinema.ui.VIP;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VIPViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VIPViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("会员页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}