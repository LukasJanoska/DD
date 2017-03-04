package com.damidev.dd.main.account.editcontact.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentEditContactBinding;
import com.damidev.dd.main.account.contacts.ui.ContactsFragment;
import com.damidev.dd.main.account.editcontact.inject.EditContactComponent;
import com.damidev.dd.main.account.editcontact.inject.EditContactModule;
import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.rest.platform.BusProvider;


public class EditContactFragment extends D2MvvmFragment<FragmentEditContactBinding, EditContactViewModel>
        implements EditContactView {

    public static String EditContactFragmnetTag = "EDIT_CONTACTS_FRAGMENT_TAG";
    private ServerNewContactResultDto resultDto;

    public EditContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel().getName().set("");
        getViewModel().getSurName().set("");
        getViewModel().getPhone().set("");
        getViewModel().getEmail().set("");

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

    public static EditContactFragment newInstance(String someTitle) {
        EditContactFragment fragment = new EditContactFragment();

        return fragment;
    }

    /*@Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        resultDto = serverEvent.getServerNewContactResponse();
        getViewModel().addContactToDB(resultDto);
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Toast.makeText(getContext(),"Please, connect to the internet",Toast.LENGTH_SHORT).show();
    }
*/
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
