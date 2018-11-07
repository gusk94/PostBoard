package ac.dankook.postboard.controller;

import ac.dankook.postboard.data.User;
import ac.dankook.postboard.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardRestController.class);
    @Autowired
    PostService postService;

    @RequestMapping(value = "/rest/board", method = RequestMethod.GET)
    public List<String[]> recentPostList() {
        User user = new User();
        user.setUserId("chd830");
//        HashMap<String,String> hashMap = new HashMap<>();
//        hashMap = postService.getPost(user);
//        return hashMap;
        List<String[]> post = postService.getPost(user);
        return post;
    }

}
