package br.com.zuulservice.filter;

import br.com.zuulservice.store.StoreMappingConfig;
import br.com.zuulservice.store.StoreContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class StoreIdFilter implements Filter {

    private static final String STORE_ID_DEFAULT = "DEFAULT";

    @Autowired
    private StoreMappingConfig storeMapping;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String url = servletRequest.getServerName();

        String storeId = storeMapping.getStoreMapping().get(url);

        if (storeId != null && !storeId.isEmpty())
            StoreContextHolder.getContext().setStoreId(storeId);
        else
            StoreContextHolder.getContext().setStoreId(STORE_ID_DEFAULT);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
