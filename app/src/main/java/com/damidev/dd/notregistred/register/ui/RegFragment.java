package com.damidev.dd.notregistred.register.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentRegBinding;
import com.damidev.dd.main.base.ui.MainActivity;
import com.damidev.dd.notregistred.base.ui.NotRegistredActivity;
import com.damidev.dd.notregistred.register.inject.RegComponent;
import com.damidev.dd.notregistred.register.inject.RegModule;
import com.damidev.dd.shared.dialog.progressdialog.ui.ErrorDialog;
import com.damidev.dd.shared.dialog.progressdialog.ui.ProgressDialog;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.inject.FragmentModule;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Collections;


public class RegFragment extends D2MvvmFragment<FragmentRegBinding, RegViewModel>
        implements RegView {

    public static String RegFragmnetTag = "REG_FRAGMENT_TAG";

    private ProgressDialog progressDialog;
    CallbackManager callbackManager;

    private LoginButton regButton;

    public static String user_profile_id_tag = "user_profile_id";
    public static String user_token = "user_token";

    public RegFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logOut();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((RegComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .fragmentModule(new FragmentModule(this))
                .module(new RegModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_reg);

        regButton = (LoginButton) view.findViewById(R.id.reg_button_fb);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookInit();
            }
        });

        return view;
    }

    public static RegFragment newInstance(String someTitle) {
        RegFragment loginFragment = new RegFragment();
        return loginFragment;
    }

    @MainThread
    @Override
    public void showProgressDialog() {
        progressDialog = ProgressDialog.newInstance("loading");
        progressDialog.show(getActivity().getSupportFragmentManager(), "progress");
    }

    @MainThread
    @Override
    public void hideProgressDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @MainThread
    @Override
    public void showErrorDialog(final String errMsg) {
        ErrorDialog.newInstance(errMsg).show(getActivity().getSupportFragmentManager(), "error");
        Toast.makeText(getContext(), "email already registred, please do login", Toast.LENGTH_LONG).show();
    }

    @MainThread
    @Override
    public void showErrorToast(final String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_LONG).show();
    }

    private void facebookInit() {
        regButton.setReadPermissions(Collections.singletonList("public_profile, email, user_birthday, user_friends"));

        regButton.setFragment(this);
        // Other app specific specialization

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                //Store Facebook data to webservice .
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                try {
                                    // String picture = object.getString("picture");
                                    //JSONObject jobj = new JSONObject(picture);
                                    //JSONObject dataObj = jobj.getJSONObject("data");

                                    String name = object.getString("name");
                                    String userEmail = object.getString("email");
                                    String userId = object.getString("id");

                                    getViewModel().onClickFBRegister(userEmail, object.getString("id"), object.getString("picture"));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday,picture");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    public void startMainActivity(int userProfileId, String token) {
        getActivity().finish();
        Intent myIntent = new Intent(getActivity(), MainActivity.class);
        myIntent.putExtra(user_profile_id_tag, userProfileId);
        myIntent.putExtra(user_token, token);
        startActivity(myIntent);
    }

    @Override
    public void startNotRegistredActivity() {
        Intent myIntent = new Intent(getActivity(), NotRegistredActivity.class);
        startActivity(myIntent);
    }
}
