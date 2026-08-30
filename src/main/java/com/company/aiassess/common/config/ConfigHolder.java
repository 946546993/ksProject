package com.company.aiassess.common.config;

import org.springframework.stereotype.Component;

/**
 * 系统参数持有者 [SDD §11]
 * <p>
 * t_sys_config 本地缓存 60s（cache:config:*），管理后台修改后清缓存即时生效。
 * 引擎类（RaschAbilityEngine 等）的算法参数一律从这里取，不写死。
 */
@Component
public class ConfigHolder {

    // TODO: getString/getInt/getDouble + 60s 缓存 + 管理后台改配置后的缓存失效
}
