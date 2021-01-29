package com.project.dp.Services;

import com.project.dp.Entities.ACL;
import com.project.dp.Exceptions.Classes.NoSuchACLException;
import com.project.dp.Repositories.ACLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ACLServiceImpl implements ACLService{

    private final ACLRepository aclRepository;

    @Autowired
    public ACLServiceImpl(ACLRepository aclRepository) {
        this.aclRepository = aclRepository;
    }

    @Override
    public ACL getACL(Long aclId) throws NoSuchACLException {
        Optional<ACL> aclOptional = this.aclRepository.findById(aclId);
        if (aclOptional.isEmpty()){
            throw new NoSuchACLException();
        }
        return aclOptional.get();
    }

    @Override
    public List<ACL> getAllACL() {
        return this.aclRepository.findAll();
    }

    @Override
    public ACL addACL(ACL acl){
        return this.aclRepository.save(acl);
    }

    @Override
    public void deleteACL(Long aclId) throws NoSuchACLException {
        Optional<ACL> aclOptional = this.aclRepository.findById(aclId);
        if (aclOptional.isEmpty()){
            throw new NoSuchACLException();
        }
        else{
            ACL acl = aclOptional.get();
            this.aclRepository.delete(acl);
        }
    }
}
