package com.damidev.dd.main.account.profileedit.Events;

import com.damidev.dd.shared.dataaccess.ServerRegResultDto;

public class ServerEvent {
    private ServerRegResultDto serverResponse;

    public ServerEvent(ServerRegResultDto serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerRegResultDto getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerRegResultDto serverResponse) {
        this.serverResponse = serverResponse;
    }
}
