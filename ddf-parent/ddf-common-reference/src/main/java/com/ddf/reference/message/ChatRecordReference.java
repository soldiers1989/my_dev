package com.ddf.reference.message;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "service-message"/*,fallback = ChatRecordReferenceFallback.class*/)
public interface ChatRecordReference {

}
