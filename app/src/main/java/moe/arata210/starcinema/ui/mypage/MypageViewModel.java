package moe.arata210.starcinema.ui.mypage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MypageViewModel extends ViewModel {

    private final MutableLiveData<String> mUsername;

    public MypageViewModel() {
        mUsername = new MutableLiveData<>();
        mUsername.setValue("用户7035"); // 初始化用户名，可以根据实际需求进行修改
    }

    public LiveData<String> getUsername() {
        return mUsername;
    }
}
