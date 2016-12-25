package com.karlchu.mo.web.validator;


import com.karlchu.mo.core.dto.TipDTO;
import com.karlchu.mo.web.command.TipCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TipValidator extends ApplicationObjectSupport implements Validator{
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Override
    public boolean supports(Class<?> aClass) {
        return TipCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TipCommand command = (TipCommand)o;
        checkRequiredField(command, errors);
        checkUniqueCode(command, errors);
    }

    private void checkUniqueCode(TipCommand command, Errors errors){
        TipDTO pojo = command.getPojo();
        String title = pojo.getTitle();
        if(StringUtils.isNotBlank(title)){
            try {
//                TipDTO dto = tipLocalBean.findByTitle(title);
//                if(pojo.getTipId() == null || !pojo.getTipId().equals(dto.getTipId())){
//                    errors.rejectValue("pojo.title", "error.duplicated", new Object[]{this.getMessageSourceAccessor().getMessage("tip.title")}, "value duplicated.");
//                }
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }
        }
    }

    private void checkRequiredField(TipCommand command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.title", "errors.required", new Object[]{this.getMessageSourceAccessor().getMessage("tip.title")}, "non-empty value required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.tipCategory.tipCategoryId", "errors.required", new Object[]{this.getMessageSourceAccessor().getMessage("tip.category")}, "non-empty value required.");
    }
}
