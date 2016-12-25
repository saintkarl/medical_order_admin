package com.karlchu.mo.core.data.session;

import com.karlchu.mo.core.data.entity.UsersEntity;

import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;

@Local
public interface UsersLocalBean extends GenericSessionBean<UsersEntity, Long> {
    List<UsersEntity> findByName(String fullName, String name);

    List<UsersEntity> findByUsernameCodeOrEmail(String username, String code, String email, Long userId);

    UsersEntity findByCode(String code);

    Boolean checkDuplicatedUniqueData(UsersEntity usersEntity);

    List<UsersEntity> searchByUserGroup(String code);

    List<UsersEntity> searchByMangerLine(String managerCode);

    List<UsersEntity> findByRole(String roleCode);

    UsersEntity findByUserName(String username) throws ObjectNotFoundException;

    List<UsersEntity> findByUserNames(List<String> userCodes);

    List<UsersEntity> findByEmails(List<String> emails);

    UsersEntity findByEmail(String emails) throws ObjectNotFoundException;

    List<UsersEntity> findByUserNameQuery(String query);
}
