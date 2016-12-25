package com.karlchu.mo.web.controller;

import com.karlchu.mo.common.Constants;
import com.karlchu.mo.core.business.TipCategoryManagementLocalBean;
import com.karlchu.mo.core.dto.TipCategoryDTO;
import com.karlchu.mo.web.command.TipCategoryCommand;
import com.karlchu.mo.web.util.WebCommonUtil;
import com.karlchu.mo.web.validator.TipCategoryValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TipCategoryController extends ApplicationObjectSupport {
    private transient final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private TipCategoryManagementLocalBean tipCategoryManagementLocalBean;
    @Autowired
    private TipCategoryValidator productCategoryValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
    }

    @RequestMapping(value = {"/admin/tip-category/list.html"})
    public ModelAndView viewCategory(@ModelAttribute(value = Constants.LIST_MODEL_KEY) TipCategoryCommand command)  {
        ModelAndView mav = new ModelAndView("/admin/tip-category/list");
        try {
            if (Constants.ACTION_DELETE.equals(command.getCrudaction())) {
                String[] checkList = command.getCheckList();
                if (checkList != null && checkList.length > 0) {
                    tipCategoryManagementLocalBean.deleteByIds(checkList);
                    mav.addObject(Constants.ALTER, Constants.TYPE_SUCCESS);
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("database.delete.cat.group.successful"));
                }
            }
            Map<String,String> messageMap = new HashMap<>();
            messageMap.put(Constants.REDIRECT_UPDATE,this.getMessageSourceAccessor().getMessage("cat.group.update.successful"));
            messageMap.put(Constants.REDIRECT_INSERT,this.getMessageSourceAccessor().getMessage("cat.group.add.successful"));
            messageMap.put(Constants.REDIRECT_ERROR,this.getMessageSourceAccessor().getMessage("database.exception"));
            WebCommonUtil.addRedirectMsg(mav,command.getCrudaction(),messageMap);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            mav.addObject(Constants.ALTER, Constants.TYPE_DANGER);
            mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("database.exception"));
        }
        executeSearch(command);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    private void executeSearch(TipCategoryCommand command) {
        List<TipCategoryDTO> tipCategoryDTOs = tipCategoryManagementLocalBean.findAll();
        command.setTotalItems(tipCategoryDTOs.size());
        command.setListResult(tipCategoryDTOs);
    }

    @RequestMapping(value = {"/ajax/admin/tip-category/edit.html"})
    @ResponseBody
    public ModelAndView ajaxEdit(@ModelAttribute(value = Constants.FORM_MODEL_KEY) TipCategoryCommand  command,
                                 BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("/admin/tip-category/edit");
        TipCategoryDTO dto  = command.getPojo();
        Long tipCategoryId = dto.getTipCategoryId();
        try {
            if (Constants.INSERT_OR_UPDATE.equals(command.getCrudaction())) {
                productCategoryValidator.validate(command, bindingResult);
                if (!bindingResult.hasErrors()) {
                    if (tipCategoryId != null){
                        tipCategoryManagementLocalBean.updateItem(dto);
                        mav.addObject(Constants.MESSAGE_RESPONSE, Constants.REDIRECT_UPDATE);
                    } else {
                        tipCategoryManagementLocalBean.addItem(dto);
                        mav.addObject(Constants.MESSAGE_RESPONSE, Constants.REDIRECT_INSERT);
                    }
                }
            } else if(tipCategoryId != null) {
                dto = tipCategoryManagementLocalBean.findById(tipCategoryId);
                command.setPojo(dto);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            mav.addObject(Constants.MESSAGE_RESPONSE, Constants.REDIRECT_ERROR);
        }
        mav.addObject(Constants.FORM_MODEL_KEY, command);
        return mav;
    }
}
