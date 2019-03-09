package ac.cn.chm.uc.test.controller;

import ac.cn.chm.result.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="testController")
public class TestController {

//    @Autowired
//    private GeneratorConfig gc;
    public ResultInfo generator(){
        ResultInfo result = new ResultInfo();
//        GeneratorUtil.generator(gc);
        return result;
    }
}
