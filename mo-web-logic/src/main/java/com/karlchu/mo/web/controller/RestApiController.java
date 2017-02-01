package com.karlchu.mo.web.controller;

import com.karlchu.mo.common.security.DesEncryptionUtils;
import com.karlchu.mo.web.editor.CustomDateEditor;
import com.karlchu.mo.web.editor.CustomTimestampEditor;
import com.karlchu.mo.web.security.RestUserDetails;
import com.karlchu.rest.security.utils.RestSecUtils;
import com.karlchu.rest.security.user.UserDetailsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Hieu Le on 10/14/2016.
 */
@Controller
public class RestApiController {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserDetailsService userDetailsService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new CustomTimestampEditor());
        binder.registerCustomEditor(Date.class, new CustomDateEditor("dd/MM/yyyy"));
    }

    /**
     * Authenticate user
     * @param username Login user name (required)
     * @param password User password (required)
     * @return
     * @see RestUserDetails contains user info, null if not login successful
     */
    @RequestMapping(value = "/mobile/retailer/auth.html")
    public @ResponseBody
    RestUserDetails authenticate(@RequestParam(value = "userName") String username,
                                 @RequestParam(value = "password") String password) {

        RestUserDetails userDetails = null;
        try {
            username = username.trim();

            userDetails = (RestUserDetails) userDetailsService.loadUserByUsername(username);
            if (DesEncryptionUtils.getInstance().decrypt(userDetails.getPassword()).equals(password)) {
                // clear password to return to client
                userDetails.setPassword(null);
                RestSecUtils.loginSuccess(userDetails);
            } else {
                throw new Exception("Invalid password");
            }
        } catch (Exception e) {
            logger.warn("Could NOT authenticate for user " + username, e);
            userDetails = null;
        }
        return userDetails;
    }
//
//    /**
//     * Change user password
//     * @param oldPassword User password (required)
//     * @param newPassword User password (required)
//     * @return Integer status
//     * 1: success
//     * 0: error occur
//     * -1: old password invalid
//     */
//    @RequestMapping(value = "/mobile/retailer/changePassword.html")
//    public @ResponseBody
//    Integer changePassword(@RequestParam(value = "oldPassword") String oldPassword,
//                           @RequestParam(value = "newPassword") String newPassword) {
//        Integer res;
//        Long userId = null;
//        try {
//            userId = RetailerRestSecUtils.getPrincipal().getUserId();
//            UserDTO userDTO = userManagementLocalBean.findUserByUserId(userId);
//            if (!DesEncryptionUtils.getInstance().decrypt(userDTO.getPassword()).equals(oldPassword)){
//                res = -1;
//            } else{
//                Boolean status = userManagementLocalBean.changeUserPassword(userId, newPassword.trim());
//                if(status){
//                    res = 1;
//                    customerMetaManagementLocalBean.updateCustomerPasswordChanged(RetailerRestSecUtils.getPrincipal().getCustomerId());
//                }else {
//                    res = 0;
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Could NOT change password for userId " + userId, e);
//            res = 0;
//        }
//        return res;
//    }
//
//    /**
//     * Save or update beneficiary for customer
//     * @param beneficiaryId required if update beneficiary
//     * @param fullName required
//     * @param phoneNumber required
//     * @param dateOfBirth string, required, dd/MM/yyyy
//     * @return BeneficiaryDTO if successful, 'null' otherwise
//     */
//    @RequestMapping(value = "/mobile/retailer/saveBeneficiary.html")
//    public @ResponseBody
//    BeneficiaryDTO saveBeneficiary(@RequestParam(value = "beneficiaryId", required = false) Long beneficiaryId, @RequestParam(value = "fullName") String fullName, @RequestParam(value = "phoneNumber", required = false) String phoneNumber
//            , @RequestParam(value = "dateOfBirth") Date dateOfBirth) {
//        BeneficiaryDTO beneficiaryDTO = null;
//        try {
//            Integer maxBeneficiary;
//            try {
//                maxBeneficiary = Integer.valueOf((String) CacheUtil.getInstance().getValue(RetailerLoyaltySettingDTO.KeyEnum.MaximumRegisterBeneficiary.toString()));
//            } catch (Exception e) {
//                maxBeneficiary = Integer.MAX_VALUE;
//            }
//            Long customerId = RetailerRestSecUtils.getPrincipal().getCustomerId();
//            Integer currentBeneficiary = retailerInfoMetaManagementLocalBean.countNoBeneficiary(customerId);
//            if(currentBeneficiary < maxBeneficiary && StringUtils.isNotBlank(fullName) && dateOfBirth != null){
//                beneficiaryDTO = new BeneficiaryDTO();
//                beneficiaryDTO.setFullName(fullName.trim());
//                beneficiaryDTO.setPhoneNumber(phoneNumber != null ? phoneNumber.trim() : null);
//                beneficiaryDTO.setDateOfBirth(dateOfBirth);
//                beneficiaryDTO.setCreatedBy(RetailerRestSecUtils.getPrincipal().getUserId());
//                beneficiaryDTO.setBeneficiaryId(beneficiaryId);
//                CustomerDTO customer = new CustomerDTO();
//                customer.setCustomerId(customerId);
//                beneficiaryDTO.setCustomer(customer);
//                beneficiaryDTO = retailerInfoMetaManagementLocalBean.saveOrUpdate(beneficiaryDTO, customerId);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return beneficiaryDTO;
//    }
//
//    /**
//     * Delete beneficiary for customer
//     * @param beneficiaryId required
//     * @return 'true' if successful, 'false' otherwise
//     */
//    @RequestMapping(value = "/mobile/retailer/deleteBeneficiary.html")
//    public @ResponseBody
//    boolean deleteBeneficiary(@RequestParam(value = "beneficiaryId", required = true) Long beneficiaryId) {
//        boolean isSuccess = false;
//        try {
//            retailerInfoMetaManagementLocalBean.deleteBeneficiary(beneficiaryId);
//            isSuccess = true;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return isSuccess;
//    }
//
//    /**
//     * Save feedback for customer
//     * @param categoryId required
//     * @param description
//     * @return FeedBackDTO if successfully, 'null' otherwise
//     */
//    @RequestMapping(value = "/mobile/retailer/saveFeedback.html")
//    public @ResponseBody
//    FeedBackDTO saveFeedback(@RequestParam(value = "categoryId", required = true) Long categoryId,
//                             @RequestParam(value = "description", required = false) String description) {
//        FeedBackDTO feedBackDTO = null;
//        try {
//            feedBackDTO = new FeedBackDTO();
//            feedBackDTO.setDescription(description);
//            feedBackDTO.setCreatedBy(RetailerRestSecUtils.getPrincipal().getUserId());
//            feedBackDTO.setStatus(Constants.STATUS_ACTIVE);
//            FeedBackCategoryDTO feedBackCategoryDTO = new FeedBackCategoryDTO();
//            feedBackCategoryDTO.setFeedBackCategoryId(categoryId);
//            feedBackDTO.setFeedBackCategory(feedBackCategoryDTO);
//            feedBackDTO.setCustomerId(RetailerRestSecUtils.getPrincipal().getCustomerId());
//            feedBackMetaManagementLocalBean.saveItem(feedBackDTO);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return feedBackDTO;
//    }
//
//    /**
//     * Delete feedback for customer
//     * @param feedbackId required
//     * @return 'true' if successfully, 'false' otherwise
//     */
//    @RequestMapping(value = "/mobile/retailer/deleteFeedback.html")
//    public @ResponseBody
//    boolean deleteFeedback(@RequestParam(value = "feedbackId") Long feedbackId) {
//        boolean isSuccess = false;
//        try {
//            feedBackMetaManagementLocalBean.deleteItem(new String[]{feedbackId.toString()});
//            isSuccess = true;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return isSuccess;
//    }
//
//    /**
//     * Get customer feedback list, each feedback item has a list of answers
//     * @see FeedBackDTO
//     * @see com.karlchu.fcv.retailer.supplier.dto.AnswerDTO
//     * @return list of feedbacks
//     */
//    @RequestMapping(value = "/mobile/retailer/getFeedbacks.html")
//    public @ResponseBody
//    List<FeedBackDTO> getFeedbacks() {
//        List<FeedBackDTO> feedBackDTOs;
//        try {
//            feedBackDTOs = feedBackMetaManagementLocalBean.findAndFetchAnswersByCustomerId(RetailerRestSecUtils.getPrincipal().getCustomerId());
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            feedBackDTOs = new ArrayList<>();
//        }
//        return feedBackDTOs;
//    }
//
//    /**
//     * Get feedback category list
//     * @see FeedBackCategoryDTO
//     * @return list of feedback categories
//     */
//    @RequestMapping(value = "/mobile/retailer/getFeedbackCates.html")
//    public @ResponseBody
//    List<FeedBackCategoryDTO> getFeedbackCates() {
//        List<FeedBackCategoryDTO> feedBackCategoryDTOs;
//        try {
//            feedBackCategoryDTOs = feedBackMetaManagementLocalBean.fetchAllFeedbackCategory("displayOrder", null);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            feedBackCategoryDTOs = new ArrayList<>();
//        }
//        return feedBackCategoryDTOs;
//    }
//
//    /**
//     * Get list of active campaigns
//     * @see MobileCampaignDTO
//     * @return list of campaigns
//     */
//    @RequestMapping(value = "/mobile/retailer/getCampaigns.html")
//    public @ResponseBody
//    List<MobileCampaignDTO> getCampaigns() {
//        List<MobileCampaignDTO> campaignDTOs;
//        try {
//            campaignDTOs = campaignBusinessManagementLocalBean.findActiveCampaign4Mobile();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            campaignDTOs = new ArrayList<>();
//        }
//        return campaignDTOs;
//    }
//
//    /**
//     * Get list of active complaint categories
//     * @see ComplaintCateDTO
//     * @return list of complaint category
//     */
//    @RequestMapping(value = "/mobile/retailer/getComplaintCates.html")
//    public @ResponseBody
//    List<ComplaintCateDTO> getComplaintCates() {
//        List<ComplaintCateDTO> complaintCateDTOs;
//        try {
//            StringBuffer whereClause = new StringBuffer();
//            whereClause.append(" status = ").append(ComplaintCateDTO.StatusEnum.ACTIVE.getValue());
//            Object[] objects = complaintCateManagementLocalBean.search(new HashedMap(),"displayOrder",null,null,null,whereClause.toString());
//            complaintCateDTOs = (List<ComplaintCateDTO>) objects[1];
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            complaintCateDTOs = new ArrayList<>();
//        }
//        return complaintCateDTOs;
//    }
//
//    /**
//     * Submit complaint
//     * @param complaintCateId required
//     * @param content not require
//     * @param customerId not required
//     * @return 'true' if successfully, 'false' otherwise
//     */
//    @RequestMapping(value = {"/mobile/retailer/submitComplaint.html","/mobile/salesman/submitComplaint.html"})
//    public @ResponseBody
//    boolean submitComplaint(@RequestParam(value = "complaintCateId") Long complaintCateId,
//                            @RequestParam(value = "customerId", required = false) Long customerId,
//                            @RequestParam(value = "content", required = false) String content) {
//        boolean isSuccess = false;
//        try {
//            if(customerId == null){
//                customerId = RetailerRestSecUtils.getPrincipal().getCustomerId();
//            }
//            ComplaintDTO complaintDTO = new ComplaintDTO();
//
//            CustomerDTO customerDTO = new CustomerDTO();
//            customerDTO.setCustomerId(customerId);
//            complaintDTO.setComplaintBy(customerDTO);
//
//            ComplaintCateDTO cateDTO = new ComplaintCateDTO();
//            cateDTO.setComplaintCateId(complaintCateId);
//            complaintDTO.setComplaintCate(cateDTO);
//
//            UserDTO userDTO = new UserDTO();
//            userDTO.setUserId(RetailerRestSecUtils.getPrincipal().getUserId());
//            complaintDTO.setCreatedBy(userDTO);
//
//            complaintDTO.setContent(content);
//
//            complaintManagementLocalBean.saveOrUpdate(complaintDTO);
//            isSuccess = true;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return isSuccess;
//    }
//
//    /**
//     * Get customer info for current login user
//     * @return null if not found or user is disabled
//     * @see CustomerDTO
//     */
//    @RequestMapping(value = "/mobile/retailer/getCustomerInfo.html")
//    public @ResponseBody
//    CustomerDTO getCustomerInfo() {
//        try {
//            RestUserDetails restUserDetails = RetailerRestSecUtils.getPrincipal();
//            if (userManagementLocalBean.userActive(restUserDetails.getUserId())) {
//                return retailerInfoMetaManagementLocalBean.findByCustomerId(restUserDetails.getCustomerId());
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * Update customer/user info
//     * @param name not required, put this param if user change his name
//     * @param dateOfBirth not required, put this param if user change his date of birth (dd/MM/yyyy)
//     * @param phoneNumber not required, put this param if user change his phone number
//     * @return '0' not allow to update, '1' done, '2' pending approval
//     */
//    @RequestMapping(value = "/mobile/retailer/updateCustomer.html")
//    public @ResponseBody
//    int updateCustomerInfo(@RequestParam(value = "name", required = false) String name,
//                               @RequestParam(value = "dateOfBirth", required = false) Date dateOfBirth,
//                               @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
//        int res = 0;
//        try {
//            if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(phoneNumber) || dateOfBirth != null) {
//                RestUserDetails restUserDetails = RetailerRestSecUtils.getPrincipal();
//                res = customerMetaManagementLocalBean.updateInfoAndRelatedUser(restUserDetails.getCustomerId(), restUserDetails.getUserId(), name, dateOfBirth, phoneNumber);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return res;
//    }
//
//    /**
//     * Get retailer settings for permissions to changing phone number, bank account number,...
//     * @return null if not found, in this case, user can change anythings
//     * @see RetailerSettingMobileDTO
//     */
//    @RequestMapping(value = "/mobile/retailer/getRetailerSettings.html")
//    public @ResponseBody
//    RetailerSettingMobileDTO getRetailerSettings() {
//        RetailerSettingMobileDTO retailerSettingMobileDTO = new RetailerSettingMobileDTO();
//        try {
//            List<RetailerLoyaltySettingDTO> retailerLoyaltySettingDTOs = retailerLoyaltySettingManagementLocalBean.findAll(null, null);
//            for (RetailerLoyaltySettingDTO retailerLoyaltySettingDTO : retailerLoyaltySettingDTOs) {
//                if (Constants.RETAILER_LOYALTY_SETTINGS_KEY_PHONE.equals(retailerLoyaltySettingDTO.getKey())) {
//                    retailerSettingMobileDTO.setPhoneNumberMode(getRetailerSettingMode(retailerLoyaltySettingDTO.getValue()));
//                } else if (Constants.RETAILER_LOYALTY_SETTINGS_KEY_BANK.equals(retailerLoyaltySettingDTO.getKey())) {
//                    retailerSettingMobileDTO.setBankAccountMode(getRetailerSettingMode(retailerLoyaltySettingDTO.getValue()));
//                } else if (Constants.RETAILER_LOYALTY_SETTINGS_KEY_NAME.equals(retailerLoyaltySettingDTO.getKey())) {
//                    retailerSettingMobileDTO.setFullNameMode(getRetailerSettingMode(retailerLoyaltySettingDTO.getValue()));
//                } else if (Constants.RETAILER_LOYALTY_SETTINGS_KEY_BIRTHDAY.equals(retailerLoyaltySettingDTO.getKey())) {
//                    retailerSettingMobileDTO.setDateOfBirthMode(getRetailerSettingMode(retailerLoyaltySettingDTO.getValue()));
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return retailerSettingMobileDTO;
//    }
//
//    private int getRetailerSettingMode(String value) {
//        int mode = 1;
//        if (value != null) {
//            switch (value) {
//                case Constants.RETAILER_LOYALTY_SETTINGS_VALUE_NOT_ALLOW:
//                    mode = 0;
//                    break;
//                case Constants.RETAILER_LOYALTY_SETTINGS_VALUE_IN_DIRECT:
//                    mode = 2;
//            }
//        }
//        return mode;
//    }
//
//    /**
//     * find summary point cycle of customer
//     * @see SummaryPointCycleDTO
//     * @return SummaryPointCycleDTO, null if error or not found
//     */
//    @RequestMapping(value = {"/mobile/retailer/findSummaryPointCycleOfCustomer.html","/mobile/salesman/findSummaryPointCycleOfCustomer.html"})
//    public @ResponseBody
//    SummaryPointCycleDTO findSummaryPointCycleOfCustomer(@RequestParam(value = "customerId", required = false) Long customerId) {
//        try {
//            if(customerId == null){
//                customerId = RetailerRestSecUtils.getPrincipal().getCustomerId();
//            }
//            String showSummaryPoint = "no";
//            if(CacheUtil.getInstance().getValue(WebConstants.RETAILER_LOYALTY_SETTINGS_KEY_DETAIL_POINT_APP) != null){
//                showSummaryPoint = (String) CacheUtil.getInstance().getValue(WebConstants.RETAILER_LOYALTY_SETTINGS_KEY_DETAIL_POINT_APP);
//            }
//            if(showSummaryPoint.equalsIgnoreCase("yes")){
//                return customerMetaManagementLocalBean.findSummaryPointCycleOfCustomer(customerId);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return new SummaryPointCycleDTO();
//    }
//
//    /**
//     * find detail point cycle of customer
//     * @see SummaryCustomerPointDTO
//     * @return SummaryCustomerPointDTO, null if error or not found
//     */
//    @RequestMapping(value = {"/mobile/retailer/findDetailPointCycleOfCustomer.html","/mobile/salesman/findDetailPointCycleOfCustomer.html"})
//    public @ResponseBody
//    SummaryCustomerPointDTO findDetailPointCycleOfCustomer(@RequestParam(value = "month", required = true) Integer month,
//                                                        @RequestParam(value = "year", required = true) Integer year,
//                                                        @RequestParam(value = "customerId", required = false) Long customerId) {
//        try {
//            if(customerId == null){
//                customerId = RetailerRestSecUtils.getPrincipal().getCustomerId();
//            }
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.MONTH, month - 1);
//            calendar.set(Calendar.YEAR, year);
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            Timestamp fromDate = new Timestamp(calendar.getTimeInMillis());
//
//            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//            Timestamp toDate = new Timestamp(calendar.getTimeInMillis());
//
//            return customerMetaManagementLocalBean.findDetailPointCycleOfCustomer(customerId, fromDate, toDate);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * Get list of gifts that user can exchange by him point at current time
//     * @see CustomerGiftExchangeDTO
//     * @return DTO contains gift information, null if error or not found
//     */
//    @RequestMapping(value = {"/mobile/retailer/getGiftMathPoint.html","/mobile/salesman/getGiftMathPoint.html"})
//    public @ResponseBody
//    CustomerGiftExchangeDTO getGiftMatchPoint(@RequestParam(value = "customerId", required = false) Long customerId) {
//        try {
//            if(customerId == null){
//                customerId = RetailerRestSecUtils.getPrincipal().getCustomerId();
//            }
//            return giftExchangeRequestBusinessManagementLocalBean.findGiftMatchPointAndCycleInAllCycle(customerId);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * Get list of gifts by category
//     * @see CustomerGiftCategoryDTO
//     * @return DTO contains gift information group by category, null if error or not found
//     */
//    @RequestMapping(value = {"/mobile/retailer/getGiftByCate.html","/mobile/salesman/getGiftByCate.html"})
//    public @ResponseBody
//    CustomerGiftCategoryDTO getGiftByCate(@RequestParam(value = "customerId", required = false) Long customerId) {
//        try {
//            if(customerId == null){
//                customerId = RetailerRestSecUtils.getPrincipal().getCustomerId();
//            }
//            return giftExchangeRequestBusinessManagementLocalBean.findGiftWithCategory(customerId);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * Send gift exchange request, note that client must update remain point for customer
//     * @param quantity required, must be > 0
//     * @param cycleGiftId required
//     * @param beneficiaryIds not required, if empty, the gift is for himself
//     * @return Map<String,Object> key: status, point
//     * key: status
//     * 1: success
//     * 0: fail
//     * -1: limited
//     *
//     * key: point - the remaining point of customer or null if error
//     *
//     */
//    @RequestMapping(value = "/mobile/retailer/sendGiftExchangeRequest.html")
//    public @ResponseBody
//    Map<String,Object> sendGiftExchangeRequest(@RequestParam(value = "quantity") Integer quantity,
//                                   @RequestParam(value = "cycleGiftId") Long cycleGiftId,
//                                   @RequestParam(value = "beneficiaryIds", required = false) List<Long> beneficiaryIds) {
//        Double currentPoint;
//        Map<String,Object> map = new HashedMap();
//        try {
//
//            RestUserDetails restUserDetails = RetailerRestSecUtils.getPrincipal();
//            Long customerId = restUserDetails.getCustomerId();
//            currentPoint = customerMetaManagementLocalBean.findTotalCurrentPoint4Customer(customerId).doubleValue();
//            CycleGiftDTO cycleGiftDTO = cycleGiftBusinessManagementLocalBean.findById(cycleGiftId);
//            Double exchangePoint = cycleGiftDTO.getExchangePoint().doubleValue() * quantity;
//
//            GiftExchangeRequestDTO giftExchangeRequestDTO = new GiftExchangeRequestDTO();
//            giftExchangeRequestDTO.setCustomerId(customerId);
//            giftExchangeRequestDTO.setQuantity(quantity);
//            giftExchangeRequestDTO.setCycleId(cycleGiftDTO.getCycle().getCycleId());
//            giftExchangeRequestDTO.setExchangePoint(new BigDecimal(exchangePoint));
//            giftExchangeRequestDTO.setGiftId(cycleGiftDTO.getGift().getGiftId());
//            giftExchangeRequestDTO.setCycleGiftId(cycleGiftId);
//            giftExchangeRequestDTO.setBeneficiaryIds(beneficiaryIds);
//            Integer status = giftExchangeRequestBusinessManagementLocalBean.save(giftExchangeRequestDTO);
//            if(status == 1){
//                // calculate remain point
//                currentPoint = currentPoint >= exchangePoint ? currentPoint  - exchangePoint : 0;
//            }else{
//                currentPoint = null;
//            }
//            map.put("status", status);
//            map.put("point", currentPoint);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            map.put("point", null);
//        }
//        return map;
//    }
//
//
//    /**
//     * SM Send gift exchange request for customer, note that client must update remain point for customer
//     * @param quantity required, must be > 0
//     * @param cycleGiftId required
//     * @param customerId required
//     * @param beneficiaryIds not required, if empty, the gift is for customer himself
//     * @return GiftExchangeRequestDTO - giftName, quantity, missing point/percentage if any, requestId; null if error
//     * @see GiftExchangeRequestDTO
//     */
//    @RequestMapping(value = "/mobile/salesman/sendGiftExchangeRequest.html")
//    public @ResponseBody
//    GiftExchangeRequestDTO salesmanSendGiftExchangeRequest(@RequestParam(value = "quantity") Integer quantity,
//                                           @RequestParam(value = "cycleGiftId") Long cycleGiftId,
//                                           @RequestParam(value = "customerId") Long customerId,
//                                           @RequestParam(value = "beneficiaryIds", required = false) List<Long> beneficiaryIds) {
//        try {
//            CustomerDTO customerDTO = customerMetaManagementLocalBean.findById(customerId);
//            String phone = customerDTO.getPhoneNumber();
//            if(StringUtils.isNotBlank(phone)){
//                GiftExchangeRequestDTO requestNeedVerify = giftExchangeRequestBusinessManagementLocalBean.saveRequestNeedVerify(RetailerRestSecUtils.getPrincipal().getUserId(), customerId, cycleGiftId, quantity, beneficiaryIds);
//                if(requestNeedVerify != null){
//                    NoticeTypeDTO noticeTypeDTO = (NoticeTypeDTO) CacheUtil.getInstance().getValue(NoticeTypeDTO.NoticeType.SMS.toString());
//                    DefaultNoticeTemplateDTO templateDTO = SMSUtil.getMessageTemplate("SmsExchangeVerifyCode");
//
//                    String msg = MessageFormat.format(templateDTO.getContent(), requestNeedVerify.getGiftName(), requestNeedVerify.getVerifyCode());
//                    MessageSentDTO dto = new MessageSentDTO();
//                    dto.setPhoneNumber(phone);
//                    dto.setCustomer(customerDTO);
//                    dto.setTemplate(templateDTO);
//                    dto.setNoticeType(noticeTypeDTO);
//                    dto.setMessageOut(msg);
//                    dto.setStatus(SmsStatusEnum.Open.getValue());
//                    dto = messageSentManagementLocalBean.save(dto);
//
//                    Object[] smsSentStatus  = GapitSmsUtils.getInstance().sendSms(phone, msg);
//                    Integer gwStatus = (Integer) smsSentStatus[1];
//                    String gwMessage = (String) smsSentStatus[2];
//                    messageSentManagementLocalBean.updateSMSGatewayStatus(dto.getMessageSentId(), gwStatus, gwMessage);
//                    requestNeedVerify.setVerifyCode(null);
//                }
//                return requestNeedVerify;
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * SM Send gift exchange request for customer, note that client must update remain point for customer
//     * @param requestId required
//     * @param verifyCode required
//     * @return Current point, null if error/fail
//     */
//    @RequestMapping(value = "/mobile/salesman/verifyGiftExchangeRequest.html")
//    public @ResponseBody
//    Double salesmanVerifyGiftExchangeRequest(@RequestParam(value = "requestId") Long requestId,
//                                                           @RequestParam(value = "verifyCode") String verifyCode) {
//        try {
//            return giftExchangeRequestBusinessManagementLocalBean.verifyRequestAndSubtractPoint(requestId,verifyCode);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * Get current gift exchange requests for customer
//     * @return the list of GiftExchangeRequestDTOs
//     */
//    @RequestMapping(value = "/mobile/retailer/getGiftExchangeRequests.html")
//    public @ResponseBody
//    List<GiftExchangeRequestDTO> getGiftExchangeRequests() {
//        List<GiftExchangeRequestDTO> giftExchangeRequestDTOs = null;
//        try {
//            RestUserDetails restUserDetails = RetailerRestSecUtils.getPrincipal();
//            giftExchangeRequestDTOs = giftExchangeRequestBusinessManagementLocalBean.findCurrentRequests4Customer(restUserDetails.getCustomerId());
//
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            giftExchangeRequestDTOs = new ArrayList<>();
//        }
//        return giftExchangeRequestDTOs;
//    }
//
//    /**
//     * Delete gift exchange request
//     * @param giftExchangeRequestId required
//     * @return 'true' if successful, 'false' otherwise
//     */
//    @RequestMapping(value = "/mobile/retailer/deleteGiftExchangeRequest.html")
//    public @ResponseBody
//    boolean deleteGiftExchangeRequest(@RequestParam(value = "giftExchangeRequestId", required = true) Long giftExchangeRequestId) {
//        boolean isSuccess = false;
//        try {
//            //@TODO: need revert customer point when needed
////            giftExchangeRequestBusinessManagementLocalBean.delete(giftExchangeRequestId);
////            isSuccess = true;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return isSuccess;
//    }
//
//    /**
//     * Register customer
//     * @param distributorCode not required
//     * @param customerCode not required
//     * @param name
//     * @param phoneNumber
//     * @param dateOfBirth string, pattern dd/MM/yyyy
//     * @return '0' if already registered, '-1' invalid registering, '1' successful
//     */
//    @RequestMapping(value = "/mobile/retailer/registerCustomer.html")
//    public @ResponseBody
//    int registerCustomer(@RequestParam(value = "distributorCode", required = false) String distributorCode,
//                                   @RequestParam(value = "customerCode", required = false) String customerCode,
//                    @RequestParam(value = "name") String name, @RequestParam(value = "phoneNumber") String phoneNumber
//                    , @RequestParam(value = "dateOfBirth") Date dateOfBirth) {
//        int res = -1;
//        try {
//            if (StringUtils.isNotBlank(customerCode)
//                && StringUtils.isNotBlank(phoneNumber) && StringUtils.isNotBlank(name)) {
//                boolean existed = customerCycleRegisterManagementLocalBean.checkRegisteredByPhone(phoneNumber.trim());
//                if (existed) {
//                    res = 0;
//                } else {
//                    CustomerCycleRegisterDTO customerCycleRegisterDTO = new CustomerCycleRegisterDTO();
//                    if(StringUtils.isNotBlank(distributorCode)){
//                        customerCycleRegisterDTO.setDistributorCode(distributorCode.trim());
//                        customerCycleRegisterDTO.setCustomerCode(distributorCode.trim().concat(customerCode.trim()));
//                    }
//                    customerCycleRegisterDTO.setShopCode(customerCode);
//                    customerCycleRegisterDTO.setName(name.trim());
//                    customerCycleRegisterDTO.setPhoneNumber(phoneNumber.trim());
//                    customerCycleRegisterDTO.setDayOfBirth(dateOfBirth);
//                    CustomerDTO customerDTO = customerCycleRegisterManagementLocalBean.registerCustomerAndCreateAccount(customerCycleRegisterDTO);
//                    if(customerDTO == null){
//                        res = -1;
//                    }else{
//                        res = 1;
//                        if(customerDTO.getUser() != null){
//                            //@TODO: send sms acc here or customer must get acc via another existed function???
////                            String phoneSupport = SMSUtil.getPhoneSupport();
//                            NoticeTypeDTO noticeTypeDTO = (NoticeTypeDTO) CacheUtil.getInstance().getValue(NoticeTypeDTO.NoticeType.SMS.toString());
////                            DefaultNoticeTemplateDTO templateDTO = SMSUtil.getMessageTemplate("LoyaltyRegisterViaApp");
////                            String msg = MessageFormat.format(templateDTO.getContent(), customerDTO.getUser().getUserName(), DesEncryptionUtils.getInstance().decrypt(customerDTO.getUser().getPassword()), phoneSupport);
////                            MessageSentDTO dto = new MessageSentDTO();
////                            dto.setPhoneNumber(customerDTO.getPhoneNumber());
////                            dto.setCustomer(customerDTO);
////                            dto.setTemplate(templateDTO);
////                            dto.setNoticeType(noticeTypeDTO);
////                            dto.setMessageOut(msg);
////                            dto.setStatus(SmsStatusEnum.Open.getValue());
////                            dto = messageSentManagementLocalBean.save(dto);
////
////                            Object[] smsSentStatus  = GapitSmsUtils.getInstance().sendSms(customerDTO.getPhoneNumber(), msg);
////                            Integer gwStatus = (Integer) smsSentStatus[1];
////                            String gwMessage = (String) smsSentStatus[2];
////                            messageSentManagementLocalBean.updateSMSGatewayStatus(dto.getMessageSentId(), gwStatus, gwMessage);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return res;
//    }
//
//
//    /**
//     * Get list of customers are managed by user
//     * @see CustomerDTO
//     * @return DTOs contains customer information, null if error or not found
//     */
//    @RequestMapping(value = "/mobile/salesman/getCustomers.html")
//    public @ResponseBody
//    List<CustomerDTO> getCustomers() {
//        try {
//            return retailerInfoMetaManagementLocalBean.findCustomerManagedByUser(RetailerRestSecUtils.getPrincipal().getUserId());
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//
//    /**
//     * Find customer by phoneNumber and outlet link code
//     * @see CustomerDTO
//     * @return DTOs contains customer information, null - blank if error or not found
//     */
//    @RequestMapping(value = "/mobile/salesman/findCustomer.html")
//    public @ResponseBody
//    List<CustomerDTO> findCustomer(@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
//                             @RequestParam(value = "code", required = false) String code,
//                                   @RequestParam(value = "index", required = false) Integer index) {
//        if (StringUtils.isNotBlank(phoneNumber) || StringUtils.isNotBlank(code)) {
//            try {
//                Integer maxValue = 20;
//                if (index == null){
//                    index = 0;
//                }else if(index != 0){
//                    index = index*maxValue;
//                }
//                Map<String,Object> properties = new HashedMap();
//                if(StringUtils.isNotBlank(phoneNumber)){
//                    properties.put("phoneNumber",phoneNumber);
//                }
//                if(StringUtils.isNotBlank(code)){
//                    properties.put("code",code);
//                }
//                Object [] objects = customerMetaManagementLocalBean.search(properties,"code",null,index, maxValue,null,null);
//                return (List<CustomerDTO>) objects[1];
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Request login info via phone
//     * @param phone
//     * @return Integer status
//     * 2: alert: Please SMS "CGHL MK" to 8077
//     * 1: info sending via SMS
//     * 0: fail get info
//     */
//    @RequestMapping(value = "/mobile/retailer/getLoginInfo.html")
//    public @ResponseBody
//    Integer getLoginInfo(@RequestParam(value = "phone") String phone) {
//        Integer res = 0;
//        if (StringUtils.isNotBlank(phone)) {
//            try {
//                List<CustomerDTO> customerDTOs = customerMetaManagementLocalBean.findByPhone(phone);
//                for(CustomerDTO customerDTO : customerDTOs){
//                    if(customerDTO.getUser() != null){
//                        NoticeTypeDTO noticeTypeDTO = (NoticeTypeDTO) CacheUtil.getInstance().getValue(NoticeTypeDTO.NoticeType.SMS.toString());
//                        DefaultNoticeTemplateDTO templateDTO = SMSUtil.getMessageTemplate("SmsGetLoginInfo");
//
//                        Map<String,Object> properties = new HashedMap();
//                        properties.put("customer.customerId", customerDTO.getCustomerId());
//                        properties.put("template.defaultNoticeTemplateId", templateDTO.getDefaultNoticeTemplateId());
//                        properties.put("status", SmsStatusEnum.Completed.getValue());
//                        Object [] objects = messageSentManagementLocalBean.search(properties, null, null, 0, 1, null, null, null);
//                        Integer noSent = Integer.valueOf(objects[0].toString()) ;
//
//                        if(noSent == 0 && templateDTO.getStatus() == 1){
//                            UserDTO userDTO = userManagementLocalBean.findUserByUserId(customerDTO.getUser().getUserId());
//                            String msg = MessageFormat.format(templateDTO.getContent(), userDTO.getUserName(), DesEncryptionUtils.getInstance().decrypt(userDTO.getPassword()));
//                            String msg4Log = MessageFormat.format(templateDTO.getContent(), userDTO.getUserName(), "******");
//                            MessageSentDTO dto = new MessageSentDTO();
//                            dto.setPhoneNumber(phone);
//                            dto.setCustomer(customerDTO);
//                            dto.setTemplate(templateDTO);
//                            dto.setNoticeType(noticeTypeDTO);
//                            dto.setMessageOut(msg4Log);
//                            dto.setStatus(SmsStatusEnum.Open.getValue());
//                            dto = messageSentManagementLocalBean.save(dto);
//
//                            Object[] smsSentStatus  = GapitSmsUtils.getInstance().sendSms(phone, msg);
//                            Integer gwStatus = (Integer) smsSentStatus[1];
//                            String gwMessage = (String) smsSentStatus[2];
//                            messageSentManagementLocalBean.updateSMSGatewayStatus(dto.getMessageSentId(), gwStatus, gwMessage);
//                            res = 1;
//                        }else{
//                            res = 2;
//                        }
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//                res = 0;
//            }
//        }
//        return res;
//    }
//
//    /**
//     * Get list of active gift and rated point
//     * @see CustomerRatingGiftDTO
//     * @return CustomerRatingGiftDTO
//     */
//    @RequestMapping(value = "/mobile/retailer/getGifts.html")
//    public @ResponseBody
//    CustomerRatingGiftDTO getGift() {
//        CustomerRatingGiftDTO customerRatingGiftDTO = new CustomerRatingGiftDTO();
//        Long customerId = RetailerRestSecUtils.getPrincipal().getCustomerId();
//        List<CycleGiftDTO> giftDTOs;
//        try {
//            giftDTOs = cycleGiftBusinessManagementLocalBean.findActiveGiftForCustomer(customerId);
//            List<RatingGiftDTO> ratingGiftDTOs = ratingGiftManagementLocalBean.findRatedGiftByCustomer(customerId);
//            Map<Long,Integer> mapGiftPoint = new HashedMap();
//            for(RatingGiftDTO ratingGiftDTO : ratingGiftDTOs){
//                mapGiftPoint.put(ratingGiftDTO.getCycleGift().getCycleGiftId(),ratingGiftDTO.getPoint());
//            }
//            customerRatingGiftDTO.setCycleGifts(giftDTOs);
//            customerRatingGiftDTO.setMapCycleGiftPoint(mapGiftPoint);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return customerRatingGiftDTO;
//    }
//
//
//    /**
//     * Rating cycle gift
//     * @param cycleGiftId
//     * @param point
//     * @return Integer status
//     * 1: success rating
//     * 0: fail rating
//     */
//    @RequestMapping(value = "/mobile/retailer/ratingGift.html")
//    public @ResponseBody
//    Integer ratingGift(@RequestParam(value = "cycleGiftId") Long cycleGiftId,
//                       @RequestParam(value = "point") Integer point) {
//        try {
//            CustomerDTO customerDTO = new CustomerDTO();
//            customerDTO.setCustomerId(RetailerRestSecUtils.getPrincipal().getCustomerId());
//            CycleGiftDTO cycleGiftDTO = new CycleGiftDTO();
//            cycleGiftDTO.setCycleGiftId(cycleGiftId);
//
//            RatingGiftDTO ratingGiftDTO = new RatingGiftDTO(null,customerDTO,cycleGiftDTO,null,point);
//            ratingGiftManagementLocalBean.saveOrUpdateItem(ratingGiftDTO);
//            return 1;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return 0;
//    }
//
//
//    /**
//     * Get change info history of customer
//     * @see ChangePhoneHistoryDTO
//     * @return list of ChangePhoneHistoryDTO
//     */
//    @RequestMapping(value = "/mobile/retailer/changePhoneHistory.html")
//    public @ResponseBody
//    List<ChangePhoneHistoryDTO> changePhoneHistory() {
//        List<ChangePhoneHistoryDTO> changeInfoRequestDTOs;
//        try {
//            changeInfoRequestDTOs = changeInfoRequestManagementLocalBean.findChangePhoneRequest(RetailerRestSecUtils.getPrincipal().getCustomerId());
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            changeInfoRequestDTOs = new ArrayList<>();
//        }
//        return changeInfoRequestDTOs;
//    }
//
//    /**
//     * Update customer username
//     * @return null if fail, username if success
//     */
//    @RequestMapping(value = "/mobile/retailer/updateUsername.html")
//    public @ResponseBody
//    String updateUsername(@RequestParam(value = "username") String username) {
//        try {
//            username = userManagementLocalBean.changeUsername(RetailerRestSecUtils.getPrincipal().getUserId(), username);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            username = null;
//        }
//        return username;
//    }
}
