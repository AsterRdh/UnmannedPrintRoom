package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.PrUsers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IUserCenterService {
    public PrUsers getUserInfo(int userId);

    public List<Map> getAllUser();
}
