package ac.cn.chm.uc.user.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
@TableName("uc_user_info")
@Data
public class UserInfo {
    @TableId(value = "user_id",type=IdType.UUID)
    private Long userId;

    @TableField(value="user_account")
    private String userAccount;
    @TableField(value="user_pass")
    private String userPass;

    @TableField(value="add_time",fill = FieldFill.INSERT)
    private Date addTime;

    @TableField(value="user_icon")
    private String userIcon;

    @TableField(value="user_status",fill = FieldFill.INSERT)
    private String userStatus;

    @TableLogic
    @TableField(value="deleted",fill = FieldFill.INSERT)
    private String deleted;

    @TableField(value="delete_time")
    private String deleteTime;

    @TableField(value="vali_time")
    private Date valiTime;

    @TableField(value="operator_id")
    private Long operatorId;

    @TableField(value="owner_id")
    private Long ownerId;

    @TableField(value="user_row1")
    private String userRow1;

    @TableField(value="user_row2")
    private String userRow2;

    @TableField(value="user_row3")
    private String userRow3;
}