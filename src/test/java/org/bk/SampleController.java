package org.bk;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SampleController {

    @Autowired
    public ErrorAttributes errorAttributes;

    @RequestMapping(value = "/img/{equipmentId}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] insertDataFile(@PathVariable("equipmentId") final Long equipmentId) throws Exception {
        return IOUtils.toByteArray(SampleController.class.getResourceAsStream("/static/img/cream_dust.png"));
    }

    @RequestMapping("/testrequest")
    @ResponseBody
    public Map<String, String> sampleResponse(@RequestParam("aParam") String aParam) {
        Map<String, String> aMap = new HashMap<>();
        throw new RuntimeException("Some exception");
//        aMap.put("test", "test");
//        return aMap;
    }

}
