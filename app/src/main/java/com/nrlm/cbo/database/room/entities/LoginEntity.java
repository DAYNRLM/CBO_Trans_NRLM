package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoginEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String login_id;
    public String server_date_time;
    public String mobile_number;
    public String state_code;
    public String logout_days;
    public String state_short_name;
    public int language_id;

         /*{"status":1,"error":{"code":"E200","message":"Success"},"login_id":null,
    "server_date_time":null,"mobile_number":null,"state_code":null,"state_short_name":null,
    "logout_days":null,"language_id":0,"month":0,"clientId":"n{j5Y[<!Ps*HWCWg ","endPointUrl":"login",
    "loginAttempt":0,"hours":0,"minuts":0,"days":0}*/

}
