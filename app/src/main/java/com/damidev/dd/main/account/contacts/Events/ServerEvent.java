package com.damidev.dd.main.account.contacts.Events;


import com.damidev.dd.shared.dataaccess.BaseResponseDto;
import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;

public class ServerEvent {
    private ServerContactsResultDto serverResponse;
    private BaseResponseDto baseResponse;

    public ServerEvent(ServerContactsResultDto serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerEvent(BaseResponseDto baseResponse) {
        this.baseResponse = baseResponse;
    }

    public ServerContactsResultDto getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerContactsResultDto serverResponse) {
        this.serverResponse = serverResponse;
    }

    public BaseResponseDto getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponseDto baseResponse) {
        this.baseResponse = baseResponse;
    }
}
