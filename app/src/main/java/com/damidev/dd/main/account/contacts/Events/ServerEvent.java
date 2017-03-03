package com.damidev.dd.main.account.contacts.Events;


import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;

public class ServerEvent {
    private ServerContactsResultDto serverResponse;

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
