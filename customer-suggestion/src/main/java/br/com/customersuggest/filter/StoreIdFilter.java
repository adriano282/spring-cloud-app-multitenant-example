package br.com.customersuggest.filter;


import br.com.customersuggest.store.StoreContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class StoreIdFilter implements Filter {

    private static final String STORE_ID_DEFAULT = "DEFAULT";
    private static final String STORE_ID_HEADER = "STORE_ID";

    private static final Logger log = LoggerFactory.getLogger(StoreIdFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String storeId = req.getHeader(STORE_ID_HEADER);

        log.info("Store id {} set.", storeId);

        if (storeId != null && !storeId.isEmpty())
            StoreContextHolder.getContext().setStoreId(storeId);
        else
            StoreContextHolder.getContext().setStoreId(STORE_ID_DEFAULT);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
