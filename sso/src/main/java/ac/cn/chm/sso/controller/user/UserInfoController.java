package ac.cn.chm.sso.controller.user;

import ac.cn.chm.sso.model.UserInfo;
import ac.cn.chm.sso.service.UserInfoService;
import ac.cn.chm.base.Page;
import ac.cn.chm.result.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/us")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping(value="/info/{pageNum}/{pageSize}")
    public ResultInfo userInfo(Page page, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception{
        ResultInfo result = new ResultInfo();
        try{
            page.setCurrentPage(pageNum);
            page.setShowCount(pageSize);
//            List<UserInfo> users = userInfoService.listPagePd(page);
//            result.put("users",users);
        }catch (Exception e){
            e.printStackTrace();
            result.error();
        }
        return result;
    }
}
