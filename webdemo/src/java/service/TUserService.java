package service;

import dao.TUserDao;
import model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TUserService {

    @Autowired
    private TUserDao tUserDao;

    @Transactional
    public void add(TUser tUser) {
        tUserDao.testAdd(tUser);
    }
}
