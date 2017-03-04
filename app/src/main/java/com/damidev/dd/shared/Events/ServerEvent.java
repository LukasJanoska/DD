package com.damidev.dd.shared.Events;


import com.damidev.dd.shared.dataaccess.BaseResponseDto;
import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;
import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;
import com.damidev.dd.shared.dataaccess.ServerRegResultDto;
import com.damidev.dd.splashscreen.dataaccess.ServerMapResponseDto;

public class ServerEvent {
    private ServerContactsResultDto serverContactsResultDto;
    private BaseResponseDto baseResponseDto;
    private ServerNewContactResultDto serverNewContactResponse;
    private ServerRegResultDto serverRegResultDto;
    private ServerMapResponseDto serverMapResponseDto;

    public ServerEvent(ServerContactsResultDto serverResponse) {
        this.serverContactsResultDto = serverResponse;
    }

    public ServerEvent(BaseResponseDto baseResponse) {
        this.baseResponseDto = baseResponse;
    }

    public ServerEvent(ServerNewContactResultDto serverNewContactResponse) {
        this.serverNewContactResponse = serverNewContactResponse;
    }

    public ServerEvent(ServerRegResultDto serverRegResultDto) {
        this.serverRegResultDto = serverRegResultDto;
    }

    public ServerEvent(ServerMapResponseDto serverMapResponseDto) {
        this.serverMapResponseDto = serverMapResponseDto;
    }

    public ServerContactsResultDto getServerContactsResultDto() {
        return serverContactsResultDto;
    }

    public void setServerContactsResultDto(ServerContactsResultDto serverContactsResultDto) {
        this.serverContactsResultDto = serverContactsResultDto;
    }

    public BaseResponseDto getBaseResponseDto() {
        return baseResponseDto;
    }

    public void setBaseResponseDto(BaseResponseDto baseResponseDto) {
        this.baseResponseDto = baseResponseDto;
    }

    public ServerNewContactResultDto getServerNewContactResponse() {
        return serverNewContactResponse;
    }

    public void setServerNewContactResponse(ServerNewContactResultDto serverNewContactResponse) {
        this.serverNewContactResponse = serverNewContactResponse;
    }

    public ServerRegResultDto getServerRegResultDto() {
        return serverRegResultDto;
    }

    public void setServerRegResultDto(ServerRegResultDto serverRegResultDto) {
        this.serverRegResultDto = serverRegResultDto;
    }

    public ServerMapResponseDto getServerMapResponseDto() {
        return serverMapResponseDto;
    }

    public void setServerMapResponseDto(ServerMapResponseDto serverMapResponseDto) {
        this.serverMapResponseDto = serverMapResponseDto;
    }
}
