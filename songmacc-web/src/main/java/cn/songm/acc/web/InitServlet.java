package cn.songm.acc.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.songm.acc.JsonUtilsInit;

public class InitServlet extends HttpServlet {

    private static final long serialVersionUID = 2393419970664601285L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        JsonUtilsInit.initialization();
    }

}
