package com.damidev.dd.main.account.editcontact.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentEditContactBinding;
import com.damidev.dd.main.account.contacts.ui.ContactsFragment;
import com.damidev.dd.main.account.editcontact.inject.EditContactComponent;
import com.damidev.dd.main.account.editcontact.inject.EditContactModule;
import com.damidev.dd.shared.Events.ErrorEvent;
import com.damidev.dd.shared.Events.ServerEvent;
import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.rest.platform.BusProvider;
import com.google.common.eventbus.Subscribe;


public class EditContactFragment extends D2MvvmFragment<FragmentEditContactBinding, EditContactViewModel>
        implements EditContactView {

    public static String EditContactFragmnetTag = "EDIT_CONTACTS_FRAGMENT_TAG";
    private ServerNewContactResultDto resultDto;
    private int contactId;

    public EditContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            contactId = bundle.getInt("contact_id");
        }

        getViewModel().getName().set("");
        getViewModel().getSurName().set("");
        getViewModel().getPhone().set("");
        getViewModel().getEmail().set("");

        setHasOptionsMenu(true);
    }

    /*private void setEditTexts(int userProfileId) {
        profile = getViewModel().getUserProfile(userProfileId);
        if(profile.get_name() == null) {
            profile.set_name("");
            getViewModel().getName().set(profile.get_name());
        } else {
            getViewModel().getName().set(profile.get_name());
        }
        if(profile.get_last_name() == null) {
            profile.set_last_name("");
            getViewModel().getSurName().set(profile.get_last_name());
        } else {
            getViewModel().getSurName().set(profile.get_last_name());
        }
        getViewModel().getEmail().set(profile.get_email());
        if(profile.get_phone() == null) {
            profile.set_phone("");
            getViewModel().getPhone().set(profile.get_phone());
        } else {
            getViewModel().getPhone().set(profile.get_phone());
        }
        if(profile.get_description() == null) {
            profile.set_description("");
            getViewModel().getDescr().set(profile.get_description());
        } else {
            getViewModel().getDescr().set(profile.get_description());
        }
    }*/

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((EditContactComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new EditContactModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_edit_contact);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_contact_fragment, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_contact:
                getViewModel().onSaveClick(contactId);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        resultDto = serverEvent.getServerNewContactResponse();
        getViewModel().updateContactInDB(resultDto);
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Toast.makeText(getContext(),"Please, connect to the internet",Toast.LENGTH_SHORT).show();
    }

    public static EditContactFragment newInstance(String someTitle, int contactId) {
        EditContactFragment editContactFragment = new EditContactFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("contact_id", contactId);
        editContactFragment.setArguments(bundle);
        return editContactFragment;
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
