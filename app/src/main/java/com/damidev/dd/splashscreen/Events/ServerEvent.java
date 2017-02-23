package com.damidev.dd.splashscreen.Events;

import com.damidev.dd.splashscreen.dataaccess.ServerMapResponseDto;

public class ServerEvent {
    private ServerMapResponseDto serverResponse;

    public ServerEvent(com.damidev.dd.splashscreen.dataaccess.ServerMapResponseDto serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerMapResponseDto getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerMapResponseDto serverResponse) {
        this.serverResponse = serverResponse;
    }
}
