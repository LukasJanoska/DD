package com.damidev.dd.main.account.contacts.Events;


import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;
import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;

public class ServerEvent {
    private ServerContactsResultDto serverResponse;
    private ServerNewContactResultDto serverNewContactResponse;

    public ServerEvent(ServerContactsResultDto serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerContactsResultDto getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerContactsResultDto serverResponse) {
        this.serverResponse = serverResponse;
    }

}
