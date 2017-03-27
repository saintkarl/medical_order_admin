package com.karlchu.mo.web.validator;


import com.karlchu.mo.web.command.StoreCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StoreValidator extends ApplicationObjectSupport implements Validator{
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Override
    public boolean supports(Class<?> aClass) {
        return StoreCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StoreCommand command = (StoreCommand)o;
        checkRequiredField(command, errors);
        checkUniqueCode(command, errors);
    }

    private void checkUniqueCode(StoreCommand command, Errors errors){
//        StoreDTO pojo = command.getPojo();
//        String title = pojo.getTitle();
//        if(StringUtils.isNotBlank(title)){
//            try {
//                StoreDTO dto = storeLocalBean.findByTitle(title);
//                if(pojo.getStoreId() == null || !pojo.getStoreId().equals(dto.getStoreId())){
//                    errors.rejectValue("pojo.title", "error.duplicated", new Object[]{this.getMessageSourceAccessor().getMessage("store.title")}, "value duplicated.");
//                }
//            }catch (Exception e){
//                log.error(e.getMessage(),e);
//            }
//        }
    }

    private void checkRequiredField(StoreCommand command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.name", "errors.required", new Object[]{this.getMessageSourceAccessor().getMessage("store.title")}, "non-empty value required.");
    }
}
