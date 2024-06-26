/*
 *  @description: SysUser.java
 *
 *  @author: xswang
 *  @email: wxs_code@126.com
 *  @version: 1.0
 *  @last update: 2024/6/25 上午10:51
 *  @date: 2024-6-25 11:13
 *
 */

package com.wxs.code.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wxs.code.core.entity.CoreEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Getter
@Setter
@Schema(description = "用户类")
@Builder
@TableName("sys_user")
public class SysUser extends CoreEntity {
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "id")
    Long id;
    @Schema(name = "name", description = "名称")
    String name;
    @Schema(name = "age", description = "年龄")
    Integer age;
    @Schema(name = "email", description = "邮箱")
    String email;
    @Schema(name = "password", description = "密码")
    String password;
}
