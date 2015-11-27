package malang.moe.repay.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import malang.moe.repay.R;
import malang.moe.repay.utils.DeveloperService;
import malang.moe.repay.utils.YouTubeFailureRecoveryActivity;

public class ExcerciseVideoShowActivity extends YouTubeFailureRecoveryActivity {

    Intent intent;
    YouTubePlayer player;
    String title, content, videoId;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_video_show);

        intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        videoId = intent.getStringExtra("videoId");
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DeveloperService.ANDROID_DEVELOPER_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        player.setFullscreen(true);
        if (!wasRestored) {
            player.cueVideo(videoId);
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    public void onResume() {
        super.onResume();
        if (player != null)
            player.setFullscreen(true);
    }
}
