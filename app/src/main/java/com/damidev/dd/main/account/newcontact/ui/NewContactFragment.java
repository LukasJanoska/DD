package com.damidev.dd.main.account.newcontact.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentNewContactBinding;
import com.damidev.dd.main.account.contacts.ui.ContactsFragment;
import com.damidev.dd.main.account.newcontact.inject.NewContactComponent;
import com.damidev.dd.main.account.newcontact.inject.NewContactModule;
import com.damidev.dd.shared.Events.ServerEvent;
import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.rest.platform.BusProvider;
import com.squareup.otto.Subscribe;


public class NewContactFragment extends D2MvvmFragment<FragmentNewContactBinding, NewContactViewModel>
        implements NewContactView {

    public static String NewContactFragmnetTag = "NEW_CONTACTS_FRAGMENT_TAG";
    private ServerNewContactResultDto resultDto;

    public NewContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel().getName().set("");
        getViewModel().getSurName().set("");
        getViewModel().getPhone().set("");
        getViewModel().getEmail().set("");

        setHasOptionsMenu(true);
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((NewContactComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new NewContactModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_new_contact);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_contact_fragment, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save:
                getViewModel().onSaveClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static NewContactFragment newInstance(String someTitle) {
        NewContactFragment contactsFragment = new NewContactFragment();

        return contactsFragment;
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        resultDto = serverEvent.getServerNewContactResponse();
        getViewModel().addContactToDB(resultDto);
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

    public void replaceWithContactsFragment(String token) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ContactsFragment accountFragment = ContactsFragment.newInstance(ContactsFragment.ContactsFragmnetTag, token);
        ft.replace(R.id.fragment_main_container, accountFragment);
        ft.commit();
    }

}
