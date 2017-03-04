package com.damidev.dd.main.account.newcontact.Events;


import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;

public class ServerEvent {

    private ServerNewContactResultDto serverNewContactResponse;

    public ServerEvent(ServerNewContactResultDto serverResponse) {
        this.serverNewContactResponse = serverResponse;
    }

    public ServerNewContactResultDto getServerResponse() {
        return serverNewContactResponse;
    }

    public void setServerResponse(ServerNewContactResultDto serverResponse) {
        this.serverNewContactResponse = serverResponse;
    }

}
