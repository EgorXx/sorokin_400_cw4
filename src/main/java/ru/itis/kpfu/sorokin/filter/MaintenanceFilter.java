package ru.itis.kpfu.sorokin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "Maintenance", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "maintenance-mode", value = "false")
        }
)
public class MaintenanceFilter implements Filter {
    private Boolean maintenanceMode = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String mode = filterConfig.getInitParameter("maintenance-mode");
        this.maintenanceMode = Boolean.parseBoolean(mode);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (maintenanceMode) {
            req.getRequestDispatcher("maintenance.ftl").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
