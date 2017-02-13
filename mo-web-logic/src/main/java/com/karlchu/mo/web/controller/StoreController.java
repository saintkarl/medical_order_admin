package com.karlchu.mo.web.controller;

import com.karlchu.mo.common.Constants;
import com.karlchu.mo.core.business.StoreManagementLocalBean;
import com.karlchu.mo.core.dto.StoreDTO;
import com.karlchu.mo.web.command.StoreCommand;
import com.karlchu.mo.web.util.RequestUtil;
import com.karlchu.mo.web.validator.StoreValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by khanhcq on 25-Oct-16.
 */
@Controller
public class StoreController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private StoreValidator storeValidator;
    
    @Autowired
    private StoreManagementLocalBean storeManagementLocalBean;

    @InitBinder
    public void initBinder(WebDataBinder binder){
    }

    @RequestMapping(value = "/admin/store/list.html")
    public ModelAndView list(@ModelAttribute(value = Constants.FORM_MODEL_KEY)StoreCommand command,
                             HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/store/list");
        String crudaction = command.getCrudaction();
        try {
            if (Constants.ACTION_DELETE.equals(crudaction)){
                String[] checkList = command.getCheckList();
                if(checkList != null && checkList.length > 0) {
                    Integer totalDeleteItem;
                    totalDeleteItem = storeManagementLocalBean.deleteItems(command.getCheckList());
                    mav.addObject(Constants.ALTER, Constants.TYPE_SUCCESS);
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("store.delete.successful", new Object[]{totalDeleteItem}));
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            mav.addObject(Constants.ALTER, Constants.TYPE_DANGER);
            mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("database.delete.unsuccessful"));
        }
        referenceData(command, mav);
        executeSearch(command, request);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    private void referenceData(StoreCommand command, ModelAndView mav) {

    }

    private void executeSearch(StoreCommand command, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = buildPropertites(command);
        Object[] results = storeManagementLocalBean.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems(),null);
        command.setListResult((List<StoreDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }

    private Map<String, Object> buildPropertites(StoreCommand command) {
        Map<String, Object> properties = new HashMap();
        if (StringUtils.isNotBlank(command.getPojo().getName())){
            properties.put("name", command.getPojo().getName());
        }
        return properties;
    }

    @RequestMapping(value = {"/admin/store/edit.html"})
    public ModelAndView editStore(@ModelAttribute(value = Constants.FORM_MODEL_KEY) StoreCommand command,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes,
                                BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("/admin/store/edit");
        StoreDTO pojo = command.getPojo();
        try{
            if (Constants.INSERT_OR_UPDATE.equals(command.getCrudaction())){
                storeValidator.validate(command, bindingResult);
                if (!bindingResult.hasErrors()){
                    storeManagementLocalBean.saveOrUpdate(pojo);
                    redirectAttributes.addFlashAttribute(Constants.ALTER, Constants.TYLE_SUCCESS);
                    redirectAttributes.addFlashAttribute(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("store.save.successful"));
                    return new ModelAndView("redirect:/admin/store/list.html");
                }
            }else if(pojo.getStoreId() != null){
                command.setPojo(this.storeManagementLocalBean.findById(pojo.getStoreId()));
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            mav.addObject(Constants.ALTER, Constants.TYLE_DANGER);
            mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("database.exception"));
        }
        referenceData(command, mav);
        return mav;
    }

}
