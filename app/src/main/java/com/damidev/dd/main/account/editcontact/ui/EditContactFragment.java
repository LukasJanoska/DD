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
import com.damidev.dd.shared.dataaccess.Contact;
import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.rest.platform.BusProvider;
import com.squareup.otto.Subscribe;


public class EditContactFragment extends D2MvvmFragment<FragmentEditContactBinding, EditContactViewModel>
        implements EditContactView {

    public static String EditContactFragmnetTag = "EDIT_CONTACTS_FRAGMENT_TAG";
    private ServerNewContactResultDto resultDto;
    private int contactId;
    private Contact contact;

    public EditContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            contactId = bundle.getInt("contact_id");
            setEditTexts(contactId);
        }
        setHasOptionsMenu(true);
    }

    private void setEditTexts(int contactId) {
        contact = getViewModel().getContact(contactId);
        if(contact.getName() == null) {
            contact.setName("");
            getViewModel().getName().set(contact.getName());
        } else {
            getViewModel().getName().set(contact.getName());
        }
        if(contact.getLastname() == null) {
            contact.setLastname("");
            getViewModel().getSurName().set(contact.getLastname());
        } else {
            getViewModel().getSurName().set(contact.getLastname());
        }
        getViewModel().getEmail().set(contact.getEmail());
        if(contact.getPhone() == null) {
            contact.setPhone("");
            getViewModel().getPhone().set(contact.getPhone());
        } else {
            getViewModel().getPhone().set(contact.getPhone());
        }
        /*if(contact.getDescription() == null) {
            contact.setDescription("");
            getViewModel().get().set(contact.get_description());
        } else {
            getViewModel().getDescr().set(contact.get_description());
        }*/
    }

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
        getViewModel().replaceWithContactFragment();
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
        ContactsFragment fragment = ContactsFragment.newInstance(ContactsFragment.ContactsFragmnetTag, token);
        ft.replace(R.id.fragment_main_container, fragment);
        ft.commit();
    }

}
