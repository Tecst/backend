package com.tecst.tecst.domain.oauth.client;

import com.tecst.tecst.domain.user.entity.User;

public interface ClientProxy {
    User getUserData(String accessToken);
}