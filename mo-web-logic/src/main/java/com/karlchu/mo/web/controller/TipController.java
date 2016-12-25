package com.karlchu.mo.web.controller;

import com.karlchu.mo.common.Constants;
import com.karlchu.mo.common.util.DateUtil;
import com.karlchu.mo.core.business.TipCategoryManagementLocalBean;
import com.karlchu.mo.core.business.TipManagementLocalBean;
import com.karlchu.mo.core.dto.TipDTO;
import com.karlchu.mo.core.dto.UserDTO;
import com.karlchu.mo.jcr.api.FileItem;
import com.karlchu.mo.jcr.api.IJcrContent;
import com.karlchu.mo.web.command.TipCommand;
import com.karlchu.mo.web.security.util.SecurityUtils;
import com.karlchu.mo.web.util.BeanUtil;
import com.karlchu.mo.web.util.RequestUtil;
import com.karlchu.mo.web.validator.TipValidator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by khanhcq on 25-Oct-16.
 */
@Controller
public class TipController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private TipValidator tipValidator;
    
    @Autowired
    private TipManagementLocalBean tipManagementLocalBean;

    @Autowired
    private TipCategoryManagementLocalBean tipCategoryManagementLocalBean;

    @Autowired
    private IJcrContent jcrContent;

    @InitBinder
    public void initBinder(WebDataBinder binder){
    }

    @RequestMapping(value = "/admin/tip/list.html")
    public ModelAndView list(@ModelAttribute(value = Constants.FORM_MODEL_KEY)TipCommand command,
                             HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/tip/list");
        String crudaction = command.getCrudaction();
        try {
            if (Constants.ACTION_DELETE.equals(crudaction)){
                String[] checkList = command.getCheckList();
                if(checkList != null && checkList.length > 0) {
                    Integer totalDeleteItem;
                    totalDeleteItem = tipManagementLocalBean.deleteItems(command.getCheckList());
                    mav.addObject(Constants.ALTER, Constants.TYPE_SUCCESS);
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("tip.delete.successful", new Object[]{totalDeleteItem}));
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            mav.addObject(Constants.ALTER, Constants.TYPE_DANGER);
            mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("tip.delete.unsuccessful"));
        }
        referenceData(command, mav);
        executeSearch(command, request);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    private void referenceData(TipCommand command, ModelAndView mav) {
        mav.addObject("tipCategories", tipCategoryManagementLocalBean.findAll());
    }

    private void executeSearch(TipCommand command, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = buildPropertites(command);
        Object[] results = tipManagementLocalBean.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems(),null);
        command.setListResult((List<TipDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }

    private Map<String, Object> buildPropertites(TipCommand command) {
        Map<String, Object> properties = new HashMap();
        if (command.getPojo().getTipCategory() != null && command.getPojo().getTipCategory().getTipCategoryId() != null){
            properties.put("tipCategory.tipCategoryId", command.getPojo().getTipCategory().getTipCategoryId());
        }
        if (StringUtils.isNotBlank(command.getPojo().getTitle())){
            properties.put("title", command.getPojo().getTitle());
        }
        if (StringUtils.isNotBlank(command.getPojo().getSource())){
            properties.put("source", command.getPojo().getSource());
        }
        return properties;
    }

    @RequestMapping(value = {"/admin/tip/edit.html"})
    public ModelAndView editTip(@ModelAttribute(value = Constants.FORM_MODEL_KEY) TipCommand command,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes,
                                BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("/admin/tip/edit");
        TipDTO pojo = command.getPojo();
        try{
            if (Constants.INSERT_OR_UPDATE.equals(command.getCrudaction())){
                tipValidator.validate(command, bindingResult);
                if (!bindingResult.hasErrors()){
                    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                    MultipartFile fileUpload = multipartHttpServletRequest.getFile("img_file");
                    if (fileUpload != null && fileUpload.getSize() > 0){
                        FileItem fileAttach = BeanUtil.multipartFile2FileItem(fileUpload);
                        fileAttach.setOriginalFilename(fileAttach.getOriginalFilename());
                        String currentDate = DateUtil.date2String(new Date(System.currentTimeMillis()), "dd_MM_yyyy");
                        String fpath = "/" + Constants.TIP_IMG_PATH + "/" + currentDate + "/";
                        pojo.setThumbnail(jcrContent.write(fpath, fileAttach));
                    }
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserId(SecurityUtils.getLoginUserId());
                    pojo.setCreatedBy(userDTO);

                    tipManagementLocalBean.saveOrUpdate(pojo);
                    redirectAttributes.addFlashAttribute(Constants.ALTER, Constants.TYLE_SUCCESS);
                    redirectAttributes.addFlashAttribute(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("tip.save.successful"));
                    return new ModelAndView("redirect:/admin/tip/list.html");
                }
            }else if(pojo.getTipId() != null){
                command.setPojo(this.tipManagementLocalBean.findById(pojo.getTipId()));
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
