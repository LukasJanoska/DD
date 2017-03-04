package com.damidev.dd.main.account.newcontact.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentNewContactBinding;
import com.damidev.dd.main.account.contacts.ui.ContactAdapter;
import com.damidev.dd.main.account.newcontact.inject.NewContactComponent;
import com.damidev.dd.main.account.newcontact.inject.NewContactModule;
import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;


public class NewContactFragment extends D2MvvmFragment<FragmentNewContactBinding, NewContactViewModel>
        implements NewContactView {

    public static String NewContactFragmnetTag = "NEW_CONTACTS_FRAGMENT_TAG";
    private ServerContactsResultDto serverContactsResultDto;
    private String token;
    protected RecyclerView mRecyclerView;
    protected ContactAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    private static int DATASET_COUNT = 15;


    public NewContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

}
