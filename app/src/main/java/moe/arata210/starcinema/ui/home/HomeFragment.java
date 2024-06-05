package moe.arata210.starcinema.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import moe.arata210.starcinema.R;
import moe.arata210.starcinema.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private TextView bottomText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieList = new ArrayList<>();
        movieList.add(new Movie("哆啦A梦:大雄的地球交响乐", "今井一晓", "水田山葵,大原惠美,嘉数由美,木村昴,关智一", "6.7", R.drawable.poster_dola));
        movieList.add(new Movie("间谍过家家 代号:白", "片桐崇", "江口拓也,早见沙织,种崎敦美,松田健一郎", "7.4", R.drawable.poster_spy));
        movieList.add(new Movie("电影1", "未知", "未知", "暂无评", R.drawable.poster));
        movieList.add(new Movie("电影2", "未知", "未知", "暂无评", R.drawable.poster));
        movieList.add(new Movie("电影3", "未知", "未知", "暂无评", R.drawable.poster));
        movieList.add(new Movie("电影4", "未知", "未知", "暂无评", R.drawable.poster));
        movieList.add(new Movie("电影5", "未知", "未知", "暂无评", R.drawable.poster));

        movieAdapter = new MovieAdapter(movieList);
        recyclerView.setAdapter(movieAdapter);

        bottomText = rootView.findViewById(R.id.bottom_text);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    // RecyclerView 已经滚动到底部
                    bottomText.setVisibility(View.VISIBLE);
                } else {
                    // RecyclerView 还未滚动到底部
                    bottomText.setVisibility(View.GONE);
                }
            }
        });



        return rootView;
    }
}