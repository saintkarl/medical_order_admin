package com.karlchu.mo.common;

public class Constants {
    /**
     * Sort direction constants
     */
    public static final String SORT_ASC = "2";
    public static final String SORT_DESC = "1";


    //~ Static fields/initializers =============================================

    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String DATE_FORMAT_VN = "dd/MM/yyyy";

    public static final String MONTH_YEAR_FORMAT = "MM-yyyy";
    /**
     * The Alphabet number for search query
     */
    public static final String ALPHABET_SEARCH_PREFIX = "^$^";
    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute that holds the list form
     */
    public static final String LIST_MODEL_KEY = "items";

    /**
     * The request scope attribute that holds the form
     */
    public static final String FORM_MODEL_KEY = "item";


    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ADMIN";


    /**
     * The name of the user's role list, common.a request-scoped attribute
     * when adding/editing common.a user.
     */
    public static final String USER_ROLES = "userRoles";

    public static final String TOS_ROLES = "TOSRoles";

    /**
     * The name of the available roles list, common.a request-scoped attribute
     * when adding/editing common.a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";


    /**
     * Acegi security constants
     */

    public static final String ACEGI_SECURITY_FORM_USERNAME_KEY = "j_username";
    public static final String ACEGI_SECURITY_FORM_PASSWORD_KEY = "j_password";
    public static final String ACEGI_SECURITY_LAST_USERNAME_KEY = "ACEGI_SECURITY_LAST_USERNAME";

    public static final String ACEGI_SECURITY_COOKIE_CURL_NAME_KEY = "i_curlName";
    public static final String ACEGI_SECURITY_COOKIE_TYPE_KEY = "i_type";


    /**
     * Cookie for web and content security
     */
    public static final String LOGIN_USER_ID_COOKIE = "j_loggined_userid";
    public static final String LOGIN_CHECKSUM = "j_loginned_checksum";
    public static final String LOGIN_SCHOOL_ID_COOKIE = "j_school_id";
    public static final String LOGIN_ROLE_COOKIE = "j_role";

    public static final String LOGON = "LOGON";

    public static final String PASSCODE_VALIDATION_FLAG = "passcodeValidation";
    public static final String PASSCODE_FLAG = "loginPasscode";


    /**
     * Global meta data
     */
    public static final String GLOBAL_META_ROLE_PREFIX = "GL_META_ROLE_";
    public static final String ACEGI_SECURITY_ORG_CODE = "ACEGI_SECURITY_ORG_CODE";
    public static final String ACEGI_SECURITY_ORG_KEY = "ACEGI_SECURITY_ORG_KEY";
    public static final String ACEGI_REMEMBER_ME_COOKIE_KEY = "RetirementRememberMeKey";
    public static final String SECURITY_CREDENTIAL_DELIMITER = "SECURITY_CREDENTIAL_DELIMITER";
    public static final String ACEGI_SECURITY_FORM_ORG_KEY = "ACEGI_SECURITY_FORM_ORG_KEY";
    public static final String ORG_CODE = "ORG_CODE";
    public static final String ORG_ID = "ORG_ID";
    public static final String ORG_SESSION = "ORG_SESSION";
    public static final String LOGON_DEFAULT_PERMISSION = "LOGON";
    public static final String PLATFORM_CACHE_JNDI_URI = "java:jboss/infinispan/container/moPlatform";


    public static final String INSERT_OR_UPDATE = "insert-update";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_UPDATE = "update";
    public static final String ACTION_INSERT = "insert";
    public static final String ACTION_EXPORT = "export";
    public static final String ACTION_SEARCH = "search";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String MESSAGE_RESPONSE = "messageResponse";

    public static final String TYLE_SUCCESS = "success";
    public static final String TYLE_ERROR = "error";
    public static final String TYLE_DANGER = "danger";
    public static final String ALTER = "alert";

    public static final String USERNAME = "username";
    public static final String CODE = "code";
    public static final String EMAIL = "email";

    public static final String JCR_SEPARATOR = "/";
    public static final String image_path = "/jackrabbit/repository/default";
    public static final String CONFIRM_WORKING_IMAGE = "/working";
    public static final String IMAGE_OUTLET = "/outlet";
    public static final String IMAGE_QUESTION_PATH = "/survey/question";

    public static final String INSERT = "INSERT";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    public static final int COLUMN_NUM = 7;

    //approve gift transfer
    public static final int GIFT_PENDING = 0;
    public static final int GIFT_APPROVE = 1;
    public static final int GIFT_DECLINE = -1;

    //change retailer account
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;

    public static final String TYPE_SUCCESS = "success";
    public static final String TYPE_ERROR = "error";
    public static final String TYPE_DANGER = "danger";

    public static final String REDIRECT_INSERT = "redirect_insert";
    public static final String REDIRECT_UPDATE = "redirect_update";
    public static final String REDIRECT_ERROR = "redirect_error";

    public static final String TIP_IMG_PATH = "tip";

}
