package com.miedo.detodoaqui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.miedo.detodoaqui.Utils.BitmapUtils;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.IOException;

public class StepThreeFragment extends Fragment implements Step {

    public static final String TAG = StepThreeFragment.class.getSimpleName();

    ImageView iv_fondo;
    ImageView iv_logo;

    private static final int REQUEST_LOGO = 10;
    private static final int REQUEST_BACKGROUND = 20;

    Bitmap logoReal, fondoReal, logoFake, fondoFake;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obtener viewmodel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_step3, container, false);

        iv_fondo = view.findViewById(R.id.iv_fondo);
        iv_logo = view.findViewById(R.id.iv_perfil);

        view.findViewById(R.id.bt_pick_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickLogo(v);
            }
        });

        view.findViewById(R.id.bt_pick_background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickBackground(v);
            }
        });


        return view;
    }


    public void pickLogo(View view) {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (galleryIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(galleryIntent,
                    REQUEST_LOGO);
        }


    }

    public void pickBackground(View view) {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (galleryIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(galleryIntent,
                    REQUEST_BACKGROUND);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // todo use appropriate resultCode in your case
        if ((requestCode == REQUEST_LOGO || requestCode == REQUEST_BACKGROUND) && resultCode == FragmentActivity.RESULT_OK) {
            if (data.getData() != null) {
                // this case will occur in case of picking image from the Gallery,
                // but not when taking picture with a camera
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());

                    if (bitmap != null) {
                        if (requestCode == REQUEST_LOGO) {
                            logoReal = bitmap;
                            logoFake = BitmapUtils.getScaledDownBitmap(bitmap, 200, false);

                            iv_logo.setImageBitmap(logoFake);
                        } else if (requestCode == REQUEST_BACKGROUND) {
                            fondoReal = bitmap;
                            fondoFake = BitmapUtils.getScaledDownBitmap(bitmap, 600, false);

                            iv_fondo.setImageBitmap(fondoFake);
                        }

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);

        }

    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

        if (logoFake != null) {
            iv_logo.setImageBitmap(logoFake);
        }
        if (fondoFake != null) {
            iv_fondo.setImageBitmap(fondoFake);
        }


    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


}
