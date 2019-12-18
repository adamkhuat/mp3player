package vn.kat.mp3playerfinal.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.kat.mp3playerfinal.Adapter.ViewPagerPlaylistNhacAdapter;
import vn.kat.mp3playerfinal.Fragment.Fragment_Dia_Nhac;
import vn.kat.mp3playerfinal.Fragment.Fragment_Play_DanhSach_BaiHat;
import vn.kat.mp3playerfinal.Model.BaiHat;
import vn.kat.mp3playerfinal.R;

public class PlayMusicActivity extends AppCompatActivity {

    Toolbar toolbarPlayMusic;
    ViewPager vpPlayMusic;
    TextView tvTimeSong;
    SeekBar seekbarSong;
    TextView tvTotalTimeSong;
    ImageButton imgButtonSuffer;
    ImageButton imgButtonPrev;
    ImageButton imgButtonPlay;
    ImageButton imgButtonNext;
    ImageButton imgButtonRepeat;
    public static ArrayList<BaiHat> baiHatArrayList = new ArrayList<>();
    public static ViewPagerPlaylistNhacAdapter viewPagerPlaylistNhacAdapter;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_DanhSach_BaiHat fragment_play_danhSach_baiHat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        GetDataFromIntent();
        init();
        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlaylistNhacAdapter.getItem(1) != null) {
                    if (baiHatArrayList.size() > 0) {
                        fragment_dia_nhac.Playnhac(baiHatArrayList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgButtonPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgButtonPlay.setImageResource(R.drawable.iconplay);
                    if (fragment_dia_nhac.objectAnimator != null) {
                        fragment_dia_nhac.objectAnimator.pause();
                    }
                } else {
                    mediaPlayer.start();
                    imgButtonPlay.setImageResource(R.drawable.iconpause);
                    if (fragment_dia_nhac.objectAnimator != null) {
                        fragment_dia_nhac.objectAnimator.resume();
                    }
                }
            }
        });
        imgButtonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgButtonRepeat.setImageResource(R.drawable.iconsyned);
                        imgButtonSuffer.setImageResource(R.drawable.iconsuffle);
                    }
                    imgButtonRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imgButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgButtonSuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgButtonSuffer.setImageResource(R.drawable.iconshuffled);
                        imgButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgButtonSuffer.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    imgButtonSuffer.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekbarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (baiHatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < baiHatArrayList.size()) {
                        imgButtonPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = baiHatArrayList.size();
                            }
                            position--;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > baiHatArrayList.size() - 1) {
                            position = 0;
                        }
                        new PlayMP3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgButtonPrev.setClickable(false);
                imgButtonNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgButtonPrev.setClickable(true);
                        imgButtonNext.setClickable(true);
                    }
                }, 5000);
            }
        });
        imgButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (baiHatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < baiHatArrayList.size()) {
                        imgButtonPlay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = baiHatArrayList.size() - 1;
                        }

                        if (repeat == true) {
                            position++;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMP3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgButtonPrev.setClickable(false);
                imgButtonNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgButtonPrev.setClickable(true);
                        imgButtonNext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        baiHatArrayList.clear();
        if (intent != null) {
            if (intent.hasExtra("music")) {
                BaiHat baiHat = intent.getParcelableExtra("music");
                baiHatArrayList.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                this.baiHatArrayList = baiHatArrayList;
            }
        }

    }

    private void init() {
        toolbarPlayMusic = findViewById(R.id.toolbarPlayMusic);
        vpPlayMusic = findViewById(R.id.vpPlayMusic);
        tvTimeSong = findViewById(R.id.tvTimeSong);
        seekbarSong = findViewById(R.id.seekbarSong);
        tvTotalTimeSong = findViewById(R.id.tvTotalTimeSong);
        imgButtonSuffer = findViewById(R.id.imgButtonSuffer);
        imgButtonPrev = findViewById(R.id.imgButtonPrev);
        imgButtonPlay = findViewById(R.id.imgButtonPlay);
        imgButtonNext = findViewById(R.id.imgButtonNext);
        imgButtonRepeat = findViewById(R.id.imgButtonRepeat);
        setSupportActionBar(toolbarPlayMusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayMusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                baiHatArrayList.clear();
            }
        });
        toolbarPlayMusic.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danhSach_baiHat = new Fragment_Play_DanhSach_BaiHat();
        viewPagerPlaylistNhacAdapter = new ViewPagerPlaylistNhacAdapter(getSupportFragmentManager());
        viewPagerPlaylistNhacAdapter.addFragment(fragment_play_danhSach_baiHat);
        viewPagerPlaylistNhacAdapter.addFragment(fragment_dia_nhac);
        vpPlayMusic.setAdapter(viewPagerPlaylistNhacAdapter);
        fragment_dia_nhac = (Fragment_Dia_Nhac) viewPagerPlaylistNhacAdapter.getItem(1);
        if (baiHatArrayList.size() > 0) {
            getSupportActionBar().setTitle(baiHatArrayList.get(0).getTenBaiHat());
            new PlayMP3().execute(baiHatArrayList.get(0).getLinkBaiHat());
            imgButtonPlay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMP3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekbarSong.setMax(mediaPlayer.getDuration());
    }

    public void UpdateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekbarSong.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < baiHatArrayList.size()) {
                        imgButtonPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = baiHatArrayList.size();
                            }
                            position--;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > baiHatArrayList.size() - 1) {
                            position = 0;
                        }
                        new PlayMP3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                    }
                    imgButtonPrev.setClickable(false);
                    imgButtonNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgButtonPrev.setClickable(true);
                            imgButtonNext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

}
