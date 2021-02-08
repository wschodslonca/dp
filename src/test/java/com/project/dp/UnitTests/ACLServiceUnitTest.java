package com.project.dp.UnitTests;

import com.project.dp.Dao.ACLDao;
import com.project.dp.Entities.ACL;
import com.project.dp.Exceptions.Classes.NoSuchACLException;
import com.project.dp.Exceptions.Classes.ACLAlreadyExistsException;
import com.project.dp.Services.ACLServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.project.dp.DataProviders.ACLServiceUnitTestDataProvider.*;
import static com.project.dp.DataProviders.ACLServiceUnitTestDataProvider.aclId;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class ACLServiceUnitTest {



    @InjectMocks
    ACLServiceImpl aclService;

    @Mock
    ACLDao aclDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getACLTest(){
        when(aclDao.findById(aclId)).thenReturn(java.util.Optional.ofNullable(acl));
        ACL acl1 = this.aclService.getACL(aclId);
        assertNotNull(acl1);
        assertEquals(1L, acl1.getAclId());
        assertEquals(1L,acl1.getUserId());
        assertEquals("test_name",acl1.getTabName());
        assertEquals(1L,acl1.getRowId());
    }

    @Test
    void getACLNoSuchACLExceptionTest(){
        when(aclDao.findById(aclId)).thenReturn(Optional.empty());
        assertThrows(NoSuchACLException.class, () -> aclService.getACL(aclId));
    }

    @Test
    void getAllACL(){
        when(aclDao.findAll()).thenReturn(acls);
        List<ACL> aclsEntityList = this.aclService.getAllACL();
        assertEquals(acl,aclsEntityList.get(0));
        assertEquals(acl1,aclsEntityList.get(1));
    }

    @Test
    void saveAlreadyExistsExceptionTest(){
        when(aclDao.findByUserIdAndTabNameAndRowId(1L,"test_acl",1L)).thenReturn(Optional.ofNullable(acl));
        assertThrows(ACLAlreadyExistsException.class, () -> aclService.save(1L,"test_acl",1L));
    }

    @Test
    void deleteACLTest() {
        Optional<ACL> optionalACL = Optional.of(acl);

        when(aclDao.findById(aclId)).thenReturn(optionalACL);

        aclService.deleteACL(aclId);

        Mockito.verify(aclDao, times(1)).delete(optionalACL.get());
    }

    @Test
    void deleteACLNoSuchACLExceptionTest(){
        when(aclDao.findById(aclId)).thenReturn(Optional.empty());
        assertThrows(NoSuchACLException.class, () -> aclService.deleteACL(aclId));
    }

    @Test
    void deleteTest() {
        Optional<ACL> optionalACL = Optional.of(acl);

        when(aclDao.findByUserIdAndTabNameAndRowId(1L,"test_acl",1L)).thenReturn(optionalACL);

        aclService.delete(1L,"test_acl",1L);

        Mockito.verify(aclDao, times(1)).delete(optionalACL.get());
    }

    @Test
    void deleteNoSuchACLExceptionTest(){
        when(aclDao.findByUserIdAndTabNameAndRowId(1L,"test_acl",1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchACLException.class, () -> aclService.delete(1L,"test_acl",1L));
    }
}
