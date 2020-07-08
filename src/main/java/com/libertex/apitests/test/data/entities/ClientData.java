package com.libertex.apitests.test.data.entities;

import com.mx.testframework.utils.RandomString;

public class ClientData {
/*    public static final String fullName = "Full_name_RFTxH";
    public static final String username = "Username_ATPCJG";*/

    public static String getFullName(){
        return "Full_name_".concat(RandomString.randomString(6));
    }

    public static String getUserName(){
        return "Username_".concat(RandomString.randomString(6));
    }

}
