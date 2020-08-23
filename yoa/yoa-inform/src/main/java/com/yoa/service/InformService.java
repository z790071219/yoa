package com.yoa.service;

import com.yoa.base.service.BaseService;
import com.yoa.model.Inform;

import java.util.List;

public interface InformService extends BaseService<Inform> {
    List<Inform> getAllInform();
    void sendMessageToUser(Inform inform);
    void clearUserMessage();
}
