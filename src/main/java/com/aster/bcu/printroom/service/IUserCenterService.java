package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.PrUsers;
import org.springframework.stereotype.Service;

@Service
public interface IUserCenterService {
    public PrUsers getUserInfo(int userId);
}
