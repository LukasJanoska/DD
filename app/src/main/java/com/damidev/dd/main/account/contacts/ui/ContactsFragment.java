package com.damidev.dd.main.account.contacts.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentContactsBinding;
import com.damidev.dd.shared.Events.ErrorEvent;
import com.damidev.dd.shared.Events.ServerEvent;
import com.damidev.dd.main.account.contacts.inject.ContactsComponent;
import com.damidev.dd.main.account.contacts.inject.ContactsModule;
import com.damidev.dd.main.account.newcontact.ui.NewContactFragment;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.dataaccess.Contact;
import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.rest.platform.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class ContactsFragment extends D2MvvmFragment<FragmentContactsBinding, ContactsViewModel>
        implements ContactsView {

    public static String ContactsFragmnetTag = "CONTACTS_FRAGMENT_TAG";
    private ServerContactsResultDto serverContactsResultDto;
    private String token;
    protected RecyclerView mRecyclerView;
    protected ContactAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private int removedAtPosition;

    private static int DATASET_COUNT = 15;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        getViewModel().getSearch().set("");

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token = bundle.getString(LoginFragment.user_token);
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

        mRecyclerView = (RecyclerView) view.findViewById(R.id.contactsRecView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecyclerViewLayoutManager();

        return view;
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, final int swipeDir) {
                removedAtPosition = viewHolder.getAdapterPosition();
                getViewModel().deleteContact(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
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
        serverContactsResultDto = serverEvent.getServerContactsResultDto();

        List<Contact> contactList = getViewModel().saveContactsToDB(serverContactsResultDto);
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.addAll(contactList);

        mAdapter = new ContactAdapter(contacts);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Subscribe
    public void onServerEvent(String result){
        if(result.equals("success")) {
            ArrayList<Contact> contacts = getViewModel().removeContactFromDB(removedAtPosition);

            mAdapter = new ContactAdapter(contacts);
            mRecyclerView.setAdapter(mAdapter);

            /*mRecyclerView.removeViewAt(removedAtPosition);
            mAdapter.notifyItemRemoved(removedAtPosition);
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(removedAtPosition, mAdapter.getItemCount());*/
        }
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Toast.makeText(getContext(),"Please, connect to the internet",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_contacts_fragment, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_contact:
                getViewModel().onNewContactClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setContactsAdapterAfterSearch(ArrayList<Contact> contacts) {
        mAdapter = new ContactAdapter(contacts);
        mRecyclerView.setAdapter(mAdapter);
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

    @Override
    public void replaceWithNewContactFragmnet() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        NewContactFragment fragment = NewContactFragment.newInstance(NewContactFragment.NewContactFragmnetTag);
        ft.replace(R.id.fragment_main_container, fragment); //.addToBackStack(null);
        ft.commit();
    }
}
