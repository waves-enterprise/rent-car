package com.wavesenertprise.config;

import com.wavesenterprise.sdk.node.client.blocking.credentials.*;
import com.wavesenterprise.sdk.node.domain.*;
import javax.servlet.http.*;
import org.jetbrains.annotations.*;
import org.springframework.web.context.request.*;

class HttpSenderAddressProvider implements SenderAddressProvider {

    @NotNull
    @Override
    public Address address() {
        return Address.fromBase58(getRequest().getHeader("X-Tx-Sender"));
    }

    private HttpServletRequest getRequest() {
        RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
        if (attribs != null) {
            return ((ServletRequestAttributes) attribs).getRequest();
        }
        throw new IllegalArgumentException("Request must not be null!");
    }
}
