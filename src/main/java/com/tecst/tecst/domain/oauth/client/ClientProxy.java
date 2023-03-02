package com.tecst.tecst.domain.oauth;

import com.tecst.tecst.domain.user.entity.User;

public interface ClientProxy {
    User getUserData(String accessToken);
}