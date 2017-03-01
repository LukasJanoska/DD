package com.damidev.dd.notregistred.picture.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentPictureBinding;
import com.damidev.dd.notregistred.picture.inject.PictureComponent;
import com.damidev.dd.notregistred.picture.inject.PictureModule;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.splashscreen.dataaccess.ServerMapChildResponseDto;
import com.squareup.picasso.Picasso;

import butterknife.BindView;


public class PictureFragment extends D2MvvmFragment<FragmentPictureBinding, PictureViewModel>
        implements PictureView {

    public static String PictureFragmnetTag = "PICTURE_FRAGMENT_TAG";
    private ServerMapChildResponseDto res;

    @BindView(R.id.pictureTitleTextView)
    TextView pictureTitleTextView;

    @BindView(R.id.pictureAboutTextView)
    TextView pictureAboutTextView;

    @BindView(R.id.pictureImageView)
    ImageView imgView;

    public PictureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);

        Bundle args = getArguments();
        if(args!=null) {
            res = (ServerMapChildResponseDto) getArguments().getSerializable("finRes");
        }
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((PictureComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new PictureModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_picture);

        Picasso.with(getContext())
                .load(res.getPhotos().get(2))
                .into(imgView);
        pictureTitleTextView.setText(res.getTitle());
        pictureAboutTextView.setText(res.getDesc());

        return view;
    }

    public static PictureFragment newInstance(String tag, ServerMapChildResponseDto finRes) {
        PictureFragment pictureFragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putSerializable("finRes", finRes);
        pictureFragment.setArguments(args);
        return pictureFragment;
    }


}
