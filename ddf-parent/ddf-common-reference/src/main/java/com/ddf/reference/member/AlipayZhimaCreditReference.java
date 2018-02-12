package com.ddf.reference.member;

import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(value = "service-member"/*,fallback = AlipayZhimaCreditReferenceFallback.class*/)
public interface AlipayZhimaCreditReference {

}
