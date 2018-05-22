package br.com.zuulservice.filter;

import br.com.zuulservice.store.StoreContextHolder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StoreIdZuulFilter extends ZuulFilter {

    private static final String STORE_ID = "STORE_ID";
    private static final String PRE_ZUUL_FILTER = "pre";
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private static final Logger logger = LoggerFactory.getLogger(StoreIdZuulFilter.class);


    @Override
    public String filterType() {
        return PRE_ZUUL_FILTER;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run () {
        RequestContext ctx = RequestContext.getCurrentContext();

        ctx.addZuulRequestHeader(STORE_ID, StoreContextHolder.getContext().getStoreId());

        logger.debug("Adding the STORE_ID to the request header: {}", StoreContextHolder.getContext().getStoreId());

        return null;
    }

}
