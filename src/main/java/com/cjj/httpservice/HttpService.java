package com.cjj.httpservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjj.config.feign.FeignConfig;
import com.cjj.httpservice.impl.HttpServiceImpl;


@FeignClient(name="httpService",url="${bus.url}", configuration = FeignConfig.class, fallback = HttpServiceImpl.class)
public interface HttpService {
	@RequestMapping(value="/testinit/init",method=RequestMethod.POST)
	String query(@RequestBody String name);
}
