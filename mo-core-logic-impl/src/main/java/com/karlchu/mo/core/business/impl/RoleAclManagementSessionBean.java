package com.karlchu.mo.core.business.impl;

import com.karlchu.mo.common.util.DozerSingletonMapper;
import com.karlchu.mo.core.business.RoleAclManagementLocalBean;
import com.karlchu.mo.core.data.entity.RoleAclEntity;
import com.karlchu.mo.core.data.session.RoleAclLocalBean;
import com.karlchu.mo.core.dto.PermissionDTO;
import com.karlchu.mo.core.dto.RoleAclDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/23/16
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "RoleAclManagementSessionEJB")
public class RoleAclManagementSessionBean implements RoleAclManagementLocalBean {

    @EJB
    private RoleAclLocalBean roleAclLocalBean;

    public RoleAclManagementSessionBean() {
    }

    @Override
    public RoleAclDTO addItem(RoleAclDTO pojo) throws DuplicateKeyException {
        RoleAclEntity entity = DozerSingletonMapper.getInstance().map(pojo, RoleAclEntity.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setCreatedDate(now);
        entity = roleAclLocalBean.save(entity);
        return DozerSingletonMapper.getInstance().map(entity, RoleAclDTO.class);
    }

    @Override
    public RoleAclDTO updateItem(RoleAclDTO pojo) throws ObjectNotFoundException, DuplicateKeyException {
        RoleAclEntity dbItem = this.roleAclLocalBean.findById(pojo.getRoleAclId());
        if(dbItem == null) throw new ObjectNotFoundException("Can not find UserACL with ID " + pojo.getRoleAclId());
        RoleAclEntity item = DozerSingletonMapper.getInstance().map(pojo, RoleAclEntity.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        item.setCreatedDate(dbItem.getCreatedDate());
        item.setModifiedDate(now);

        item = roleAclLocalBean.update(item);
        return DozerSingletonMapper.getInstance().map(item, RoleAclDTO.class);
    }

    @Override
    public Map<Long, Long> findByRoleId(Long roleId) {
        List<RoleAclEntity> listEntity = this.roleAclLocalBean.findByRoleId(roleId);
        Map<Long, Long> map = new HashMap<Long, Long>();
        for(RoleAclEntity entity : listEntity){
            map.put(Long.valueOf(entity.getPermission().getPermissionId()), entity.getRole().getRoleId());
        }
        return map;
    }

    @Override
    public void deleteByPermissionIdRoleId(Long roleId, Long userId) {
        this.roleAclLocalBean.deleteByPermissionRoleId(roleId, userId);//To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        this.roleAclLocalBean.deletByRoleId(roleId);
    }

    @Override
    public List<PermissionDTO> getPermissionByRoleId(Long roleId) {
        List<RoleAclEntity> listEntity = this.roleAclLocalBean.findByRoleId(roleId);
        List<PermissionDTO> res = new ArrayList<>();
        for(RoleAclEntity entity : listEntity){
            res.add(DozerSingletonMapper.getInstance().map(entity.getRole(), PermissionDTO.class));
        }
        return res;
    }
}
