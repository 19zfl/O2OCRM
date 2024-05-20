package com.o2ocrm.basic.utils.register;

import cn.hutool.crypto.SecureUtil;

/**
 * @ClassName:SaltUtil
 * @Description: 注册功能加盐加密工具类
 * @Author:zfl19
 * @CreateDate:2024/5/20 18:39
 */

public class SaltUtil {

    public static String encrypt(String saltV, String origin) {
        return SecureUtil.md5().setSalt(saltV.getBytes()).digestHex(origin);
    }

}
