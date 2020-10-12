package filter;

import dao.UserDao;
import vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.geom.FlatteningPathIterator;
import java.io.IOException;

/**
 * @author 13281
 */

public class Filter implements javax.servlet.Filter {
    private String notFilterPath;
    private String mustFilterPath;
    private int FLAG = 0;
    private String MANAGER = "管理员";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //从配置文件里获取不需要过滤的网站地址
        notFilterPath = filterConfig.getInitParameter("notFilterUrl");
        mustFilterPath = filterConfig.getInitParameter("mustFilterUrl");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //此过滤器负责过滤普通用户的访问权限
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String role = String.valueOf(request.getSession().getAttribute("role"));

        //获取请求网站的地址
        String path = request.getServletPath();
        Cookie[] cookies = request.getCookies();
        /**
         * 给出过滤逻辑
         * 判断当前请求的urlPattern是否需要过滤
         * 如果不需要，则直接放行
         * 判断用户是否登录
         * 未登录，转发给error.jsp并传递错误提示“您尚未登录，不能访问该资源”
         * 登录则向后传递
         */
        User user = null;
        if(notFilterPath.indexOf(path) == -1){
            //判断用户是否曾经是否勾选过七天免登陆
            if(cookies != null){
                for(Cookie c :cookies){
                    user = new UserDao().get(c.getValue());
                    if(user.getUserName() != null) {
                        //找到了该用户
                        FLAG = 1;
                        break;
                    }
                }
            }
            if(FLAG == 1){
                if(user.getRole() == null){
                    //退出登录但未关闭Tomcat是，FLAG无法初始化为0，手动初始化
                    FLAG = 0;
                    this.doFilter(servletRequest,servletResponse,filterChain);
                }
                //如果曾经勾选过，判断其身份，根据身份进行放行
                if(user.getRole().equals(MANAGER)){
                    //如果是管理员，则无条件放行
                    request.getSession().setAttribute("role",user.getRole());
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    //如果是普通用户，则判断是否有足够权限访问该网站
                    if(mustFilterPath.contains(path)){
                        //权限不够
                        request.getSession().setAttribute("errorMsg","您的权限不够，不能访问该资源");
                        request.getRequestDispatcher("/powerError.jsp").forward(request,(HttpServletResponse)servletResponse);
                    }else {
                        //权限足够
                        request.getSession().setAttribute("role",user.getRole());
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                }
            }else if(role != null){
                //如果是直接通过输入账号密码进行登录，则判断其身份
                if(role.equals(MANAGER)){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else {
                    //如果是普通用户，则判断是否有足够权限访问该网站
                    if(mustFilterPath.contains(path)){
                        //权限不够
                        request.getSession().setAttribute("errorMsg","您的权限不够，不能访问该资源");
                        request.getRequestDispatcher("/powerError.jsp").forward(request,(HttpServletResponse)servletResponse);
                    }else {
                        //权限足够
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                }
            }else{
                request.getSession().setAttribute("errorMsg","您尚未登录，不能访问该资源");
                request.getRequestDispatcher("/error.jsp").forward(request,(HttpServletResponse)servletResponse);

            }
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        FLAG = 0;
    }
}
