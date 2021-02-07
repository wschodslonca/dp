package com.project.dp.Services;

import com.project.dp.Entities.ACL;
import com.project.dp.Exceptions.Classes.NoSuchACLException;
import com.project.dp.Dao.ACLDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ACLServiceImpl implements ACLService{

    private final ACLDao aclDao;

    @Autowired
    public ACLServiceImpl(ACLDao aclDao) {
        this.aclDao = aclDao;
    }

    @Override
    public ACL getACL(Long aclId) throws NoSuchACLException {
        Optional<ACL> aclOptional = this.aclDao.findById(aclId);
        if (aclOptional.isEmpty()){
            throw new NoSuchACLException();
        }
        return aclOptional.get();
    }

    @Override
    public List<ACL> getAllACL() {
        return this.aclDao.findAll();
    }

    @Override
    public void save(Long userId, String table, Long record){
        ACL acl = new ACL();
        acl.setUserId(userId);
        acl.setTabName(table);
        acl.setRowId(record);
        this.aclDao.save(acl);
    }

    @Override
    public void delete(Long userId, String table, Long record) {
        Optional<ACL> aclOptional = this.aclDao.findByUserIdAndTabNameAndRowId(userId,table,record);
        if (aclOptional.isEmpty()){
            throw new NoSuchACLException();
        }
        else {
            ACL acl = aclOptional.get();
            this.aclDao.delete(acl);
        }
    }

    @Override
    public void deleteACL(Long aclId) throws NoSuchACLException {
        Optional<ACL> aclOptional = this.aclDao.findById(aclId);
        if (aclOptional.isEmpty()){
            throw new NoSuchACLException();
        }
        else{
            ACL acl = aclOptional.get();
            this.aclDao.delete(acl);
        }
    }
}
