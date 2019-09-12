package com.tianos.koketa.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tianos.koketa.R;
import com.tianos.koketa.ui.interfaceKoketa.InterfaceKoketa;
import com.tianos.koketa.util.dialog.DialogFragment;

import butterknife.BindView;


public class ProfileActivity extends BaseActivity implements InterfaceKoketa {

    @BindView(R.id.header_cover_image)
    ImageView headerCoverImage;

    @BindView(R.id.user_profile_name)
    TextView userProfileName;

    @BindView(R.id.user_profile_short_bio)
    TextView userProfileShortBio;

    @BindView(R.id.row_1)
    TextView row1;

    @BindView(R.id.row_2)
    TextView row2;

    @BindView(R.id.row_3)
    TextView row3;

    @BindView(R.id.row_4)
    TextView row4;

    @BindView(R.id.row_5)
    TextView row5;

    @BindView(R.id.rl_main)
    RelativeLayout rlMain;

    @BindView(R.id.ll_header_cover_image)
    LinearLayout llHeaderCoverImage;

    @BindView(R.id.fab)
    FloatingActionButton fab;

//    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initSetup();
        initToolBar();
        navigationDrawer();




//        initData();
//        navigationDrawer();

        /*
        new ParticleSystem(ProfileActivity.this, 80, R.mipmap.coin_1, 10000)
            .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 90)
            .setRotationSpeed(100)
            .setAcceleration(0.000017f, 90)
            .emitWithGravity(findViewById(R.id.header_cover_image), Gravity.LEFT, 8);
        */
    }

    @Override
    public void initSetup() {
        super.initSetup();

//        user = PreferencesManager.getInstance(this).realmGetUser();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment.dialogChangePassword(ProfileActivity.this);
            }
        });
    }

    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle(R.string.profile);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);


//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });



    }

    /*
    @Override
    public void initData() {

        if (user == null) {

            rlMain.setVisibility(View.GONE);

            Util.showDialog(
            ProfileActivity.this,
                    getEtString(R.string.no_data_3),
                    getEtString(R.string.close),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ProfileActivity.this, DashboardActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        }
                    });
            return;
        }

        userProfileName.setText(user.getName());
        userProfileShortBio.setText(user.getMessage());

        row1.setText(user.getCode());
        row2.setText("(" + user.getNivel() + ") " + getNivel(user.getNivel()));
        row3.setText("(" + user.getOrigen() + ") " + getOrigen(user.getOrigen()));
        row4.setText(versionCode());
        row5.setText(versionName());

        headerCoverImage.animate()
            .alpha(1f)
            .scaleX(2.5f)
            .scaleY(2.5f)
            .rotation(360f)
            .setDuration(7000)
        ;
    }
    */

    protected void doAnimation() {


        /*
        new ParticleSystem(ProfileActivity.this, 80, R.mipmap.coin_2, 10000)
            .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
            .setRotationSpeed(144)
            .setAcceleration(0.000017f, 90)
            .emitWithGravity(findViewById(R.id.emiter_top_left), Gravity.RIGHT, 8)
        ;
        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        finish();
    }
}
