package ac.dankook.postboard.controller;

import ac.dankook.postboard.constants.UrlConstants;
import ac.dankook.postboard.data.Post;
import ac.dankook.postboard.service.PostService;
import ac.dankook.postboard.service.UserService;
import ac.dankook.postboard.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UrlConstants.REST)
public class PostRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostRestController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public boolean writePost(@RequestParam String title, @RequestParam String content, HttpServletRequest request) {
        String userNo = HttpUtils.getUserNoFromCookie(request);

        if (StringUtils.isNotBlank(userNo)) {

            Post post = new Post();
            post.setUserNo(Integer.parseInt(userNo));
            post.setTitle(title);
            post.setContent(content);
            post.setPositive(0);
            post.setNegative(0);

            postService.setPost(post);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Post> postList(HttpServletRequest request) {
        String userNo = HttpUtils.getUserNoFromCookie(request);
        if (StringUtils.isNotBlank(userNo)) {
            return postService.getPostByUserNo(userNo);
        }
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object getList(HttpServletRequest request) {
        String userNo = HttpUtils.getUserNoFromCookie(request);
        if (StringUtils.isNotBlank(userNo)) {
            Model model = new Model() {
                @Override
                public Model addAttribute(String s, Object o) {
                    return null;
                }

                @Override
                public Model addAttribute(Object o) {
                    return null;
                }

                @Override
                public Model addAllAttributes(Collection<?> collection) {
                    return null;
                }

                @Override
                public Model addAllAttributes(Map<String, ?> map) {
                    return null;
                }

                @Override
                public Model mergeAttributes(Map<String, ?> map) {
                    return null;
                }

                @Override
                public boolean containsAttribute(String s) {
                    return false;
                }

                @Override
                public Map<String, Object> asMap() {
                    return null;
                }
            };

            List<Post> list = postService.getList(userNo);

            String userName = userService.getUserName(userNo);
            model.addAttribute("list", list);
            model.addAttribute("user", userName);

            return model;
        }
        return "";
    }
}
