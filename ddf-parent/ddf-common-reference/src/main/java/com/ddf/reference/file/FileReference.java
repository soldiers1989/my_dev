/*
package com.ddf.reference.file;

import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.dic.AreaReferenceFallback;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@FeignClient(value = "service-file", fallback = FileReferenceFallback.class)
public interface FileReference {
    //文件的形式上传，一个input_name上传多个文件")
    @RequestMapping(value = "/file/sameFileName/batchupload", method = { RequestMethod.POST })
    public ApiResponse<List<String>> fileUpload4sameFileName(HttpServletRequest request);
}
*/
