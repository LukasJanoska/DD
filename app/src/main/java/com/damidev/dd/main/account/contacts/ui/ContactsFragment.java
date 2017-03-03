package com.damidev.dd.main.account.contacts.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentContactsBinding;
import com.damidev.dd.main.account.contacts.Events.ServerEvent;
import com.damidev.dd.main.account.contacts.inject.ContactsComponent;
import com.damidev.dd.main.account.contacts.inject.ContactsModule;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.rest.platform.BusProvider;
import com.squareup.otto.Subscribe;


public class ContactsFragment extends D2MvvmFragment<FragmentContactsBinding, ContactsViewModel>
        implements ContactsView {

    public static String ContactsFragmnetTag = "CONTACTS_FRAGMENT_TAG";
    private ServerContactsResultDto serverContactsResultDto;
    private String token;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token = bundle.getString(LoginFragment.user_token);
            Log.i("token", token);
            //getToken
        }
        getViewModel().getAllContacts(token);
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((ContactsComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new ContactsModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_contacts);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static ContactsFragment newInstance(String someTitle, String token) {
        ContactsFragment contactsFragment = new ContactsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LoginFragment.user_token, token);
        contactsFragment.setArguments(bundle);
        return contactsFragment;
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        serverContactsResultDto = serverEvent.getServerResponse();
        getViewModel().saveContactsToDB(serverContactsResultDto);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }


}
