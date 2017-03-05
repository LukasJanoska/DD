package com.damidev.dd.notregistred.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentLoginBinding;
import com.damidev.dd.main.base.ui.MainActivity;
import com.damidev.dd.notregistred.login.inject.LoginComponent;
import com.damidev.dd.notregistred.login.inject.LoginModule;
import com.damidev.dd.notregistred.register.ui.RegFragment;
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


public class LoginFragment extends D2MvvmFragment<FragmentLoginBinding, LoginViewModel>
        implements LoginView {

    private ProgressDialog progressDialog;
    CallbackManager callbackManager;

    private LoginButton loginButton;

    public static String user_profile_id_tag = "user_profile_id";
    public static String user_token = "user_token";

    public static String LoginFragmnetTag = "LOGIN_FRAGMENT_TAG";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel().getEmail().set("mmmm@mm.mm");
        getViewModel().getPassword().set("mmm");

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
        ((LoginComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .fragmentModule(new FragmentModule(this))
                .module(new LoginModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_login);

        loginButton = (LoginButton) view.findViewById(R.id.login_button_fb);
        facebookInit();

        return view;
    }

    public static LoginFragment newInstance(String someTitle) {
        LoginFragment loginFragment = new LoginFragment();
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

    private void facebookInit() {
        loginButton.setReadPermissions(Collections.singletonList("public_profile, email, user_birthday, user_friends"));
        loginButton.setFragment(this);
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

                                    getViewModel().onClickFBLogin(userEmail, object.getString("id"));

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

    @MainThread
    @Override
    public void showErrorDialog(final String errMsg) {
        ErrorDialog.newInstance(errMsg).show(getActivity().getSupportFragmentManager(), "error");
        replaceWithRegFragment();
        Toast.makeText(getContext(), "email not registred, please do registration", Toast.LENGTH_LONG).show();
    }

    @MainThread
    @Override
    public void showErrorToast(final String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_LONG).show();
    }

    @MainThread
    @Override
    public void replaceWithRegFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        RegFragment loginFragment = RegFragment.newInstance(RegFragment.RegFragmnetTag);
        ft.replace(R.id.fragment_container, loginFragment);
        ft.commit();
    }

    @Override
    public void startMainActivity(int userProfileId, String token) {
        getActivity().finish();
        Intent myIntent = new Intent(getActivity(), MainActivity.class);
        myIntent.putExtra(user_profile_id_tag, userProfileId);
        myIntent.putExtra(user_token, token);
        startActivity(myIntent);
    }
}
