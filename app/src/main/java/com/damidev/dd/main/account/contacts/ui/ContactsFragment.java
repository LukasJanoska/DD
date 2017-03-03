package com.damidev.dd.main.account.contacts.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentContactsBinding;
import com.damidev.dd.main.account.contacts.inject.ContactsComponent;
import com.damidev.dd.main.account.contacts.inject.ContactsModule;
import com.damidev.dd.shared.inject.D2MvvmFragment;


public class ContactsFragment extends D2MvvmFragment<FragmentContactsBinding, ContactsViewModel>
        implements ContactsView {

    public static String ContactsFragmnetTag = "CONTACTS_FRAGMENT_TAG";

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public static ContactsFragment newInstance(String someTitle) {
        ContactsFragment contactsFragment = new ContactsFragment();
        return contactsFragment;
    }

}
