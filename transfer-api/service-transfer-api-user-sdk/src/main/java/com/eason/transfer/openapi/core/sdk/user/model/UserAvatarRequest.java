package com.eason.transfer.openapi.core.sdk.user.model;

import com.eason.transfer.openapi.core.common.model.FileItem;
import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAvatarRequest extends Request {

    private FileItem avatar;

}
