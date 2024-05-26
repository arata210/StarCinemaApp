package moe.arata210.starcinema.ui.mypage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MypageViewModel extends ViewModel {

    private final MutableLiveData<String> mUsername;

    public MypageViewModel() {
        mUsername = new MutableLiveData<>();
    }

    public LiveData<String> getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername.setValue(username);
    }
}
