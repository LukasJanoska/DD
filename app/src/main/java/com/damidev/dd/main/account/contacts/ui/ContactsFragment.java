package com.damidev.dd.main.account.contacts.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    protected RecyclerView mRecyclerView;
    protected ContactAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    private static int DATASET_COUNT = 15;


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

        initDataset();
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

        mRecyclerView = (RecyclerView) view.findViewById(R.id.contactsRecView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecyclerViewLayoutManager();

        mAdapter = new ContactAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
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

    private void initDataset() {

        if(DATASET_COUNT > 5) {
            DATASET_COUNT = 5;
        }
        mDataset = new String[DATASET_COUNT];
        mDataset[0] = "Cottage";
        mDataset[1] = "Home";
        mDataset[2] = "Garage";
        mDataset[3] = "Hotel";
        mDataset[4] = "Garden";
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
